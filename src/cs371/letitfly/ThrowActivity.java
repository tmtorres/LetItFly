package cs371.letitfly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.media.MediaPlayer;

public class ThrowActivity extends Activity implements MediaPlayer.OnCompletionListener{

	SensorManager sensorManager;
	private double[] velocity = {0, 0, 0};
	
	private MediaPlayer soundPlayer;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		createSensor();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_throw);

		final Bundle bundle = getIntent().getExtras();

		ImageButton button = (ImageButton) findViewById(R.id.throw_button);
		button.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
					//do nothing
				} 
				
				else if(event.getAction() == android.view.MotionEvent.ACTION_UP) {
						goToResults(bundle);
				}
				
				return false;
			}
		}); 

	}


	public void goToResults(Bundle bundle){
		bundle.putDouble("xVelocity", velocity[0]);
		bundle.putDouble("yVelocity", velocity[1]);
		bundle.putDouble("zVelocity", velocity[2]);
		Intent intent = new Intent(this, ResultsActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}

	@Override
	public void onPause() {
		super.onPause();
		
		if (sensorEventListener != null) {
		    sensorManager.unregisterListener(sensorEventListener);
		}
		
		if(soundPlayer != null && soundPlayer.isPlaying()) {
			if(soundPlayer.isPlaying())
				soundPlayer.stop();
			soundPlayer.release();
			soundPlayer = null;
		}
	}

	public void onStop() {
		super.onPause();
		
		if (sensorEventListener != null) {
		    sensorManager.unregisterListener(sensorEventListener);
		}
	}

	@Override
	public void onResume(){
		super.onResume();
		if(soundPlayer == null)
			soundPlayer = MediaPlayer.create(this, R.raw.sound_throw);
		
		soundPlayer.setOnCompletionListener(this);
		
		createSensor();

		final Bundle bundle = getIntent().getExtras();

		velocity = new double[3];

		ImageButton button = (ImageButton) findViewById(R.id.throw_button);
		button.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == android.view.MotionEvent.ACTION_DOWN ) {
					//createSensor();
				} 
				else if(event.getAction() == android.view.MotionEvent.ACTION_UP) {
					goToResults(bundle);
				}
				return false;
			}
		});
	}


	private void createSensor() {
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		Sensor linearAccelerationSenor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

		sensorManager.registerListener(sensorEventListener, 
				linearAccelerationSenor, 
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	private SensorEventListener sensorEventListener = 
			new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			changeMaxVals(event);
			
			//applying throwing sound
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			float acc = (float) Math.sqrt(x * x + y * y + z * z); 
			if(acc > 20) {
				//Log.d("TBBT", "" + acc);
				if(soundPlayer != null && !soundPlayer.isPlaying()) {
					soundPlayer.start();
					//picture.setImageResource(R.drawable.crack);
				}
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}


	};

	private void changeMaxVals(SensorEvent event) {
		for (int i = 0; i < velocity.length; i++) {
			velocity[i] += (float) event.values[i] * 0.1;
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) {
		
	}
}
