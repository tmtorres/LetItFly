package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void goToObjSelection(View view) {
	    Intent intent = new Intent(this, ObjectSelectionActivity.class);
	    startActivity(intent);
	}
	
	public void goToScores(View view){
		 Intent intent = new Intent(this, ScoresActivity.class);
		 startActivity(intent);
	}

	 @Override
	    public boolean onCreateOptionsMenu(Menu menu){

	         MenuInflater inflater = getMenuInflater();
	         inflater.inflate(R.menu.settings, menu);
	          return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	if(item.getItemId() == R.id.settings) {
	    	Intent intent = new Intent(this, SettingsActivity.class);
	    	//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	  		startActivity(intent);
	      }
	      return true;
	    } 

}
