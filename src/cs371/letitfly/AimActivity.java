package cs371.letitfly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

public class AimActivity extends Activity implements LocationListener{
	
	SensorManager sensorManager;
	private double longitude;
	private double latitude;
	private int azimuth;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aim);
        createSensor();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		sensorManager.unregisterListener(sensorEventListener);
	}
	public void onStop(){
		super.onStop();
		sensorManager.unregisterListener(sensorEventListener);
	}
	
	
	public void getAimInfo(View view)
	{
		LocationManager locMgr = ((LocationManager) getSystemService(LOCATION_SERVICE));
        Location location = getLocation(locMgr);
        if(location!=null)
        {
        	goToThrow(location);
        }
        else
        {
        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    				this);
     
    			// set title
    			alertDialogBuilder.setTitle("No Location can be retrieved.");
     
    			// set dialog message
    			alertDialogBuilder
    				.setMessage("Would you like to edit your GPS preferences?")
    				.setCancelable(false)
    				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,int id) {
    						// if this button is clicked, close
    						// current activity
    						startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
    						AimActivity.this.finish();
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
        
        
       
		//Log.d("", "Latitude: "+latitude);
        
		//Toast.makeText(getApplicationContext(),"Your GPS Coordinates are: "+latitude+", "+longitude+"\n"+"Degrees from North: "+ azimuth, Toast.LENGTH_SHORT).show();
		
		
	}
	
	public Location getLocation(LocationManager lm)
	{
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null){
        	return location;
        }
        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location!=null)
        {
        	return location;
        }
        location = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if(location!=null)
        {
        	return location;
        }
        else
        {
        	return null;
        }
		
	}
	
	public void goToThrow(Location location)
	{
		latitude =  location.getLatitude();
		longitude = location.getLongitude();
		
		Intent intent = new Intent(this, ThrowActivity.class);
		Bundle b = getIntent().getExtras();
		b.putDouble("latitude", latitude);
		b.putDouble("longitude", longitude);
		b.putDouble("azimuth",azimuth);
		intent.putExtras(b);
        startActivity(intent);
		
	}
	
	public void createSensor()
	{
		 sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		 Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		 Sensor magSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		 sensorManager.registerListener(sensorEventListener, 
                 accelSensor, 
                 1000000);
		 sensorManager.registerListener(sensorEventListener, 
                 magSensor, 
                 1000000);
	}
	
	//------------Getting Degree Variation from North-----------------//
	//---------------- 0 = North, 180 = South ------------------------//
	float[] mGravity;
	float[] mGeomagnetic;
	private SensorEventListener sensorEventListener = 
            new SensorEventListener() {
		@Override
		public void onSensorChanged(SensorEvent event) {
		    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
		        mGravity = event.values;
		    if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
		        mGeomagnetic = event.values;
		    if (mGravity != null && mGeomagnetic != null) {
		        float R[] = new float[9];
		        float I[] = new float[9];
		        boolean success = SensorManager.getRotationMatrix(R, I, mGravity,
		                mGeomagnetic);
		        if (success) {
		            float orientation[] = new float[3];
		            SensorManager.getOrientation(R, orientation);
		            azimuth = (int) Math.round(Math.toDegrees(orientation[0]));
		            //Log.d("","Bearing: "+ azimuth);
		        }
		    }
		    
		}
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // nothing to do!   
        }
	
	};
	
	@Override
	public void onLocationChanged(Location location) {   
		latitude =  location.getLatitude();
		longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	}

}
