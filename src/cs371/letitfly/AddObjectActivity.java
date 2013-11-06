package cs371.letitfly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddObjectActivity extends Activity {
	private EditText edittext1, edittext2;
	
	private File myFile;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_addobject);
	    
	    File path=new File("/data/myData","myfolder");
		path.mkdirs();
		myFile =new File(path,"projectiles.txt");
	  
	
	    // TODO Auto-generated method stub
	}
	
	public void sendData(View view)
	{
		edittext1 = (EditText) findViewById(R.id.edit_name);
		edittext2 = (EditText) findViewById(R.id.edit_weight);
		
		String name = edittext1.getText().toString();
		double weight = Double.parseDouble(edittext2.getText().toString());
		try{
			//write asset to internal storege to use next time
	    	  PrintWriter pw = new PrintWriter(new FileWriter(myFile, true));
	    	  pw.println("\n"+name+" "+weight);
	    	  pw.close();
		}
		catch (IOException e) {
		}
		
		Bundle b = new Bundle();
		Intent intent = new Intent(this, ObjectSelectionActivity.class);
		b.putString("newObject",name);
		b.putDouble("newWeight", weight);
		intent.putExtras(b);
		startActivity(intent);
	}

}
