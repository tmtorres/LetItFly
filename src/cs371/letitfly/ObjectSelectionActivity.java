package cs371.letitfly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ObjectSelectionActivity extends Activity {

	private ListView listView;
	private double objectMassSelected;
	ArrayList<String> object_array_list = new ArrayList<String>();
	private HashMap<String, Double> map = new HashMap<String, Double>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obj);
		
		//toast.makeText(getApplicationContext(), "create", //toast.LENGTH_SHORT).show();
		
		// retrieving new object and weight if added a new object
		Bundle bundle = this.getIntent().getExtras();
		if(bundle!=null)
		{
			map.put(bundle.getString("newObject"), bundle.getDouble("newWeight"));
		}
		
		
		Map<String,?> keys =  getSharedPreferences("projectilePrefs", 0).getAll();
		if(keys.isEmpty()==false)
		{
			//toast.makeText(getApplicationContext(), "shared prefs", //toast.LENGTH_SHORT).show();
			getSharedPrefs();
		}
		else
		{
			//toast.makeText(getApplicationContext(), "assets pop", //toast.LENGTH_SHORT).show();
			populateWithAssets();
		}
		
		
		//populateListView();

		listView = (ListView) findViewById(R.id.object_list);
		ArrayAdapter<String> arrayAdapter =      
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, object_array_list);
		listView.setAdapter(arrayAdapter); 
		listView.setLongClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				String selectedFromList = (String) (listView.getItemAtPosition(myItemInt));
				////toast.makeText(getApplicationContext(), selectedFromList, //toast.LENGTH_SHORT).show();
				String [] temp;
            	temp = selectedFromList.split("\\t");
            
				goToAim(temp[0]);
			}  
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int pos, long id) {
            	
            	String selectedFromList = (String) (listView.getItemAtPosition(pos));
            	String [] temp;
            	temp = selectedFromList.split("\\t");
            	
            	deleteDialog(temp[0]);
            		
            	
                return true;
            }
        }); 
	}
	
	 @Override
	    protected void onResume() {
	        super.onResume();
	        //toast.makeText(getApplicationContext(), "resume", //toast.LENGTH_SHORT).show();
	        //getSharedPrefs();
	    }
	 
	public void populateWithAssets(){
		try{
		//if internal storage not available, load objects from assets
  	  Scanner sc = new Scanner(getAssets().open("projectiles.txt"));
  	  
	  	  while(sc.hasNextLine())
	  	  {
	  		  if(sc.hasNextDouble()==false) //current token is a string
	  		  {
	  			  String key = "";
	  			  while(sc.hasNextDouble()==false)
	  			  {
	  				  key = key + sc.next() + " ";
	  			  }
	  			  map.put(key, sc.nextDouble());
	  		  }
	  		  else
	  		  {
	  			  map.put("null", sc.nextDouble());
	  		  }
	  	  }
	  	  for (String key : map.keySet()) {
	  		   object_array_list.add(key+"\t"+ map.get(key)+" lbs");
	  	  }
	  	  
	  	  storeSharedPrefs();
	  	  
	    } catch (Exception f) {
	       Log.d("damn", "FAIL");
	    }
	}

	public void goToAim(String objectSelected)
    {
		storeSharedPrefs();
    	objectMassSelected = map.get(objectSelected);
    	Intent intent = new Intent(this, AimActivity.class);
    	Bundle b = new Bundle();
    	b.putString("objectName", objectSelected);
    	b.putDouble("objectMass",objectMassSelected);
    	intent.putExtras(b);
        startActivity(intent);
    }
	
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.addobject, menu);
          return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      if (item.getItemId() == R.id.menuItem1) {
    	storeSharedPrefs();
    	Intent intent = new Intent(this, AddObjectActivity.class);
  		startActivity(intent);
      }
      return true;
    } 
    public void storeSharedPrefs(){
    	SharedPreferences.Editor editor = getSharedPreferences("projectilePrefs", 0).edit();

    	for( Entry<String, Double> entry : map.entrySet() ) 
    	  editor.putString( entry.getKey(), entry.getValue().toString() );
    	editor.commit();
    }
    public void getSharedPrefs(){
    	SharedPreferences prefs = getSharedPreferences("projectilePrefs", 0);

    	for( Entry<String, ?> entry : prefs.getAll().entrySet() ) 
    	  map.put( entry.getKey(), Double.parseDouble(entry.getValue().toString()));
    	for (String key : map.keySet()) {
	  		   object_array_list.add(key+"\t"+map.get(key)+" lbs");
	  	 }
    }
    public void deleteDialog(final String nameOfObject){
    	
    	
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
    	
			alertDialogBuilder.setTitle("Delete Object");
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Are you sure you want to delete "+nameOfObject+"?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						map.remove(nameOfObject);	
		            	SharedPreferences.Editor editor = getSharedPreferences("projectilePrefs", 0).edit();
		            	editor.remove(nameOfObject);
		            	editor.commit();
		            	finish();
	            		startActivity(getIntent());
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						
						dialog.cancel();
					}
				});
				
				
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
    }

}