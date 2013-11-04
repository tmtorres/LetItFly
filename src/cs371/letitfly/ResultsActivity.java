package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_results);
	    
	    Bundle b = getIntent().getExtras();
	    
	    TextView txtView = (TextView) this.findViewById(R.id.object_name);
	    txtView.setText("Object Name: "+b.getString("objectName"));
	    TextView txtView1 = (TextView) this.findViewById(R.id.object_mass);
	    txtView1.setText("Object's Mass: "+Double.toString(b.getDouble("objectMass")));
	    TextView txtView2 = (TextView) this.findViewById(R.id.x_acceleration);
	    txtView2.setText("X Accleration: "+Double.toString(b.getDouble("xAcceleration")));
	    TextView txtView3 = (TextView) this.findViewById(R.id.y_acceleration);
	    txtView3.setText("Y Accleration: "+Double.toString(b.getDouble("yAcceleration")));
	    TextView txtView4 = (TextView) this.findViewById(R.id.z_acceleration);
	    txtView4.setText("Z Accleration: "+Double.toString(b.getDouble("zAcceleration")));
	    
	    TextView txtView5 = (TextView) this.findViewById(R.id.latitude);
	    txtView5.setText("Latitude: "+Double.toString(b.getDouble("latitude")));
	    TextView txtView6 = (TextView) this.findViewById(R.id.longitude);
	    txtView6.setText("Longitude: "+Double.toString(b.getDouble("longitude")));
	    
	    TextView txtView7 = (TextView) this.findViewById(R.id.azimuth);
	    txtView7.setText("Degrees from North: "+Double.toString(b.getDouble("azimuth")));
	    
	    // TODO Auto-generated method stub
	}
	
	public void goToMap(View view)
	{
		Bundle b = getIntent().getExtras();
		Intent intent = new Intent(this, MapsActivity.class);
		intent.putExtras(b);
		startActivity(intent);
	}

}
