package cs371.letitfly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class ThrowActivity extends Activity{
	
	SensorManager sensorManager;
	private double[] maxVals;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_throw);
	    createSensor();
	    
	    final Bundle b = getIntent().getExtras();
	    
	    
	    maxVals = new double[3];
	    
	    ImageButton button = (ImageButton) findViewById(R.id.throw_button);
	    button.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
					// do nothing
				} else
				if(event.getAction() == android.view.MotionEvent.ACTION_UP){
					//Toast.makeText(getApplicationContext(), "Your Max Accelerations are: "+maxVals[0]+", "+maxVals[1]+", "+maxVals[2], Toast.LENGTH_SHORT).show();
					goToResults(b);
				}
				return false;
				}
			}); 
	    
	}
	
	
	public void goToResults(Bundle b){
		b.putDouble("xAcceleration", maxVals[0]);
		b.putDouble("yAcceleration", maxVals[1]);
		b.putDouble("zAcceleration", maxVals[2]);
		Intent intent = new Intent(this, ResultsActivity.class);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	@Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
	public void onStop() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
	@Override
	public void onResume(){
		super.onResume();
		createSensor();
		maxVals = new double[3];
		
		final Bundle b = getIntent().getExtras();
	    
	    
	    maxVals = new double[3];
	    
	    ImageButton button = (ImageButton) findViewById(R.id.throw_button);
	    button.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
					// do nothing
				} else
				if(event.getAction() == android.view.MotionEvent.ACTION_UP){
					//Toast.makeText(getApplicationContext(), "Your Max Accelerations are: "+maxVals[0]+", "+maxVals[1]+", "+maxVals[2], Toast.LENGTH_SHORT).show();
					goToResults(b);
				}
				return false;
				}
			}); 
		
	}
	
	
		private void createSensor() {
			sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

	        // pick the default Linear acceleration Sensor
	                Sensor linAclSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
	        
	                sensorManager.registerListener(sensorEventListener, 
	                        linAclSensor, 
	                        1000000); //replacing SensorManager.SENSOR_DELAY_UI
	    }
	
	    private SensorEventListener sensorEventListener = 
	            new SensorEventListener() {
	
	        @Override
	        public void onSensorChanged(SensorEvent event) {
	            //Log.d(TAG, event.toString());
	
	            // accelerationValues[0].setText("" + event.values[0]);
	          
	                changeMaxVals(event);
	           		
	            // displayCurrentRotation(event);
	        }
	
	//        @Override
	        public void onAccuracyChanged(Sensor sensor, int accuracy) {
	            // nothing to do!   
	        }
 
	
	    };
	    private void changeMaxVals(SensorEvent event) {
	        for(int i = 0; i < maxVals.length; i++)
	        {
	            if(Math.abs(event.values[i]) > maxVals[i]) {
	                maxVals[i] = (float) Math.abs(event.values[i]);
	                float value = ((int) (maxVals[i] * 1000)) / 1000f;
	                Log.d("","Max Values: " + value);
	            }
	        }
	        
	    }

}
