package cs371.letitfly;

import cs371.letitfly.physics.MobileDevice;
import cs371.letitfly.physics.Physics;
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
	    
	    double weight = b.getDouble("objectMass");
	    double mass = weight / Physics.g;
	    
	    MobileDevice mobileDevice = MobileDevice.GALAXY_S4;
	    
	    double xVelocity = Physics.normalizeAcceleration(b.getDouble("xAcceleration"), mass, mobileDevice);
	    double yVelocity = Physics.normalizeAcceleration(b.getDouble("yAcceleration"), mass, mobileDevice);
	    double zVelocity = Physics.normalizeAcceleration(b.getDouble("zAcceleration"), mass, mobileDevice);
	    
	    double time = Physics.getTimeElapsed(yVelocity);
	    double xDisplacement = Physics.getDisplacement(xVelocity, time);
		double zDisplacement = Physics.getDisplacement(zVelocity, time);
		double displacement = Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(zDisplacement, 2));
		
		double feet = displacement * 3.28;
	    
	    TextView txtView1 = (TextView) this.findViewById(R.id.object_mass);
	    txtView1.setText("Object's Mass: "+Double.toString(mass));
	    TextView txtView2 = (TextView) this.findViewById(R.id.x_acceleration);
	    txtView2.setText("X Accleration: "+Double.toString(xVelocity));
	    TextView txtView3 = (TextView) this.findViewById(R.id.y_acceleration);
	    txtView3.setText("Y Accleration: "+Double.toString(yVelocity));
	    TextView txtView4 = (TextView) this.findViewById(R.id.z_acceleration);
	    txtView4.setText("Z Accleration: "+Double.toString(zVelocity));
	    
	    TextView txtView5 = (TextView) this.findViewById(R.id.latitude);
	    txtView5.setText("Latitude: "+Double.toString(b.getDouble("latitude")));
	    TextView txtView6 = (TextView) this.findViewById(R.id.longitude);
	    txtView6.setText("Longitude: "+Double.toString(b.getDouble("longitude")));
	    
	    TextView txtView7 = (TextView) this.findViewById(R.id.azimuth);
	    txtView7.setText("Degrees from North: "+Double.toString(b.getDouble("azimuth")));
	    
	    TextView txtView8 = (TextView) this.findViewById(R.id.feet);
	    txtView8.setText("The " + b.getString("objectName") + "traveled " + feet + " feet!");
	    
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
