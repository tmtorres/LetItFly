package cs371.letitfly;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cs371.letitfly.physics.Object;


public class ObjectSelectionActivity extends Activity {

	private ListView lv;
	private double objectMassSelected;
	private HashMap<String, Double> hm = new HashMap <String, Double>(); 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obj);
        
        // -------------Hardcoded Values ---------------- //
        lv = (ListView) findViewById(R.id.object_list);
        ArrayList<String> object_array_list = new ArrayList<String>();
        object_array_list.add("basketball");
        object_array_list.add("cat");
        object_array_list.add("football");
        object_array_list.add("rock");
        
        hm.put("basketball", 0.62369);
        hm.put("cat", 4.53592);
        hm.put("football",0.453592);
        hm.put("rock", 2.26796);
        
        ArrayAdapter<String> arrayAdapter =      
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, object_array_list);
                lv.setAdapter(arrayAdapter); 
                
                
                lv.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                      String selectedFromList =(String) (lv.getItemAtPosition(myItemInt));
                      //Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_SHORT).show();
                      goToAim(selectedFromList);
                }  
                });
    }
    
    public void goToAim(String objectSelected)
    {
    	objectMassSelected = hm.get(objectSelected);
    	Intent intent = new Intent(this, AimActivity.class);
    	Bundle b = new Bundle();
    	b.putString("objectName", objectSelected);
    	b.putDouble("objectMass",objectMassSelected);
    	intent.putExtras(b);
        startActivity(intent);
    }
    
    
}