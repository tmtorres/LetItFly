package cs371.letitfly;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cs371.letitfly.physics.Gravity;
import cs371.letitfly.physics.Physics;

public class SettingsActivity extends Activity {
	ArrayList<Gravity> object_array_list = new ArrayList<Gravity>(Arrays.asList(Gravity.values()));
	ListView listView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_settings);
	    
	    
	    listView = (ListView) findViewById(R.id.settings);
		ArrayAdapter<Gravity> arrayAdapter =      
				new ArrayAdapter<Gravity>(this, android.R.layout.simple_list_item_1, object_array_list);
		listView.setAdapter(arrayAdapter); 
		listView.setLongClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				Gravity selectedFromList = (Gravity) (listView.getItemAtPosition(myItemInt));
				Physics.g = selectedFromList.gravity();
				goToMain();
			}  
		});
	    
	    
	
	    // TODO Auto-generated method stub
	}
	public void goToMain()
	{
		Intent intent = new Intent(this, MainActivity.class);
  		startActivity(intent);
	}

}
