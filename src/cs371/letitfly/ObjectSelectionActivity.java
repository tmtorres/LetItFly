package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cs371.letitfly.physics.Projectile;

public class ObjectSelectionActivity extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obj);
		listView = (ListView) findViewById(R.id.object_list);
		ArrayAdapter<Projectile> arrayAdapter =      
				new ArrayAdapter<Projectile>(this, android.R.layout.simple_list_item_1, Projectile.values());
		listView.setAdapter(arrayAdapter); 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
				Projectile selectedFromList = (Projectile) (listView.getItemAtPosition(myItemInt));
				//Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_SHORT).show();
				goToAim(selectedFromList);
			}  
		});
	}

	public void goToAim(Projectile projectile) {
		Intent intent = new Intent(this, AimActivity.class);
		Bundle b = new Bundle();
		b.putString("objectName", projectile.description());
		b.putDouble("objectMass", projectile.weight());
		intent.putExtras(b);
		startActivity(intent);
	}
}