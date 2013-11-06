package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cs371.letitfly.physics.MobileDevice;
import cs371.letitfly.physics.Physics;

public class ResultsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_results);
	    
	    Bundle bundle = getIntent().getExtras();
	    
	    TextView txtView = (TextView) this.findViewById(R.id.object_name);
	    txtView.setText("Object Name: "+ bundle.getString("objectName"));
	    
	    double mass = bundle.getDouble("objectMass");
	    
	    MobileDevice mobileDevice = MobileDevice.GALAXY_S4;
	    
	    double xVelocity = Physics.normalizeVelocity(bundle.getDouble("xVelocity"), mass, mobileDevice);
	    double yVelocity = Physics.normalizeVelocity(bundle.getDouble("yVelocity"), mass, mobileDevice);
	    double zVelocity = Physics.normalizeVelocity(bundle.getDouble("zVelocity"), mass, mobileDevice);
	    
	    double time = Physics.getTimeElapsed(yVelocity);
	    double xDisplacement = Physics.getDisplacement(Math.abs(xVelocity), time);
		double zDisplacement = Physics.getDisplacement(Math.abs(zVelocity), time);
		double displacement = Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(zDisplacement, 2));
		
		double feet = Math.sqrt(displacement * 3.28);
	    
		/*
	    TextView txtView1 = (TextView) this.findViewById(R.id.object_mass);
	    txtView1.setText("Object's weight (pounds): "+Double.toString(mass * 2.20462));
	    TextView txtView2 = (TextView) this.findViewById(R.id.x_acceleration);
	    txtView2.setText("X velocity: "+Double.toString(bundle.getDouble("xVelocity")) +  " " + String.valueOf(xVelocity));
	    TextView txtView3 = (TextView) this.findViewById(R.id.y_acceleration);
	    txtView3.setText("Y velocity: "+Double.toString(bundle.getDouble("yVelocity")) +  " " + String.valueOf(yVelocity));
	    TextView txtView4 = (TextView) this.findViewById(R.id.z_acceleration);
	    txtView4.setText("Z velocity: "+Double.toString(bundle.getDouble("zVelocity")) +  " " + String.valueOf(zVelocity));
	    
	    TextView txtView5 = (TextView) this.findViewById(R.id.latitude);
	    txtView5.setText("Latitude: "+Double.toString(bundle.getDouble("latitude")));
	    TextView txtView6 = (TextView) this.findViewById(R.id.longitude);
	    txtView6.setText("Longitude: "+Double.toString(bundle.getDouble("longitude")));
	    
	    TextView txtView7 = (TextView) this.findViewById(R.id.azimuth);
	    txtView7.setText("Degrees from North: "+Double.toString(bundle.getDouble("azimuth")));*/
	    
	    TextView txtView8 = (TextView) this.findViewById(R.id.feet);
	    String noun = feet > 1 ? "feet" : "foot";
	    txtView8.setText("The " + bundle.getString("objectName") + " traveled " + (int) Math.ceil(feet) + " " + noun + "!");
	}
	
	public void goToMap(View view) {
		Bundle bundle = getIntent().getExtras();
		Intent intent = new Intent(this, MapsActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
