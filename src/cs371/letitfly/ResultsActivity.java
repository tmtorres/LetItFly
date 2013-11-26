package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import cs371.letitfly.physics.MobileDevice;
import cs371.letitfly.physics.Physics;

public class ResultsActivity extends Activity {
	private double feet;
	private double timeDelay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_results);
	    
	    Bundle bundle = getIntent().getExtras();
	    
	   // TextView txtView = (TextView) this.findViewById(R.id.object_name);
	   // txtView.setText("Object Name: "+ bundle.getString("objectName"));
	    
	    double mass = bundle.getDouble("objectMass")*0.453592; // convert pounds to kg;
	    
	    MobileDevice mobileDevice = MobileDevice.GALAXY_S4;
	    
	    double xVelocity = Physics.normalizeVelocity(bundle.getDouble("xVelocity"), mass, mobileDevice);
	    double yVelocity = Physics.normalizeVelocity(bundle.getDouble("yVelocity"), mass, mobileDevice);
	    double zVelocity = Physics.normalizeVelocity(bundle.getDouble("zVelocity"), mass, mobileDevice);
	    
	    double time = Physics.getTimeElapsed(yVelocity);
	    double xDisplacement = Physics.getDisplacement(Math.abs(xVelocity), time);
		double zDisplacement = Physics.getDisplacement(Math.abs(zVelocity), time);
		double displacement = Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(zDisplacement, 2));
		
		double yDisplacement = Math.sqrt(0.5 * Physics.g * Math.pow(time / 2, 2));
		timeDelay = 2 * Math.sqrt((2 * yDisplacement) / Physics.g);
		
		Log.d("", "yDisplacement: " + yDisplacement);
		Log.d("", "timeDelay: " + timeDelay);
		
		feet = Math.sqrt(displacement * 3.28);
		//timeDelay = time/2.0;
	    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            	Bundle bundle = getIntent().getExtras();
        		Intent intent = new Intent(ResultsActivity.this, MapsActivity.class);
        		bundle.putDouble("feet",feet);
        		intent.putExtras(bundle);
        		startActivity(intent);
        		finish();
            }
        }, (long)timeDelay*1000);

	}
	
	public void goToMap(View view) {
		Bundle bundle = getIntent().getExtras();
		Intent intent = new Intent(this, MapsActivity.class);
		bundle.putDouble("feet",feet);
		intent.putExtras(bundle);
		
		startActivity(intent);
	}

}
