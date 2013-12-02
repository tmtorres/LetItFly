package cs371.letitfly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ScoresActivity extends Activity {
	private ListView listView;
	ArrayList<String> object_array_list = new ArrayList<String>();
	
	private HashMap<String, Double> map = new HashMap<String, Double>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_scores);

	    Map<String,?> keys =  getSharedPreferences("projectileScores", 0).getAll();
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
		
		listView = (ListView) findViewById(R.id.object_scores_list);
		ArrayAdapter<String> arrayAdapter =      
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, object_array_list);
		listView.setAdapter(arrayAdapter); 
		listView.setLongClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				String selectedFromList = (String) (listView.getItemAtPosition(myItemInt));
				
				String [] temp;
            	temp = selectedFromList.split("\\t");
            	SharedPreferences prefs = getSharedPreferences("projectilePrefs", 0);
            	String weight = prefs.getString(temp[0], null);
            	Toast toast = Toast.makeText(getApplicationContext(), "Weight: "+weight, Toast.LENGTH_SHORT);
            	toast.show();
			}  
		});
	
	    // TODO Auto-generated method stub
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
	  			  map.put(key, 0.0);
	  			  sc.nextDouble();
	  		  }
	  		  else
	  		  {
	  			  map.put("null", 0.0);
	  			  sc.nextDouble();
	  		  }
	  	  }
	  	  
	  	  // adding 0 values for highest score and populating arraylist
	  	  SharedPreferences.Editor defaultScores = getSharedPreferences("projectileScores", 0).edit();
	  	  
	  	  for (String key : map.keySet()) {
	  		   object_array_list.add(key+"\t"+ map.get(key)+" feet");
	  		   defaultScores.putInt(key, 0);
	  	  }
	  	  defaultScores.commit();
	  	  //storeSharedPrefs();
	  	  
	    } catch (Exception f) {
	       Log.d("damn", "FAIL");
	    }
	}
	
	public void getSharedPrefs(){
		SharedPreferences prefs = getSharedPreferences("projectileScores", 0);

    	for( Entry<String, ?> entry : prefs.getAll().entrySet() ) 
    	  map.put( entry.getKey(), Double.parseDouble(entry.getValue().toString()));
    	for (String key : map.keySet()) {
	  		   object_array_list.add(key+"\t"+map.get(key)+" feet");
	  	 }
    }

}
