package cs371.letitfly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity {
	 
    // Google Map
    private GoogleMap googleMap;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Bundle b = getIntent().getExtras();
     
        try {
            // Loading map
            initilizeMap(b);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
    
    public Double getCoord(String value){
    	Bundle b = getIntent().getExtras();
    	
    	double dist = .006/6371; //hardcoded km
    	double brng = Math.toRadians(b.getDouble("azimuth"));
    	double lat1 = Math.toRadians(b.getDouble("latitude"));
    	double lon1 = Math.toRadians(b.getDouble("longitude"));

    	double latitude = Math.asin( Math.sin(lat1)*Math.cos(dist) + Math.cos(lat1)*Math.sin(dist)*Math.cos(brng) );
    	double a = Math.atan2(Math.sin(brng)*Math.sin(dist)*Math.cos(lat1), Math.cos(dist)-Math.sin(lat1)*Math.sin(latitude));
    	System.out.println("a = " +  a);
    	double lon2 = lon1 + a;

    	double longitude = (lon2+ 3*Math.PI) % (2*Math.PI) - Math.PI;
    	
    	
    	if(value.equals("long")){
    		return Math.toDegrees(longitude);
    	}
    	else if(value.equals("lat")){
    		return Math.toDegrees(latitude);
    	}
    	return null;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main, menu);
          return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
      case R.id.menuItem1:
    	Intent intent = new Intent(this, ObjectSelectionActivity.class);
    	//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  		startActivity(intent);
        break;
      }
      return true;
    } 
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap(Bundle b) {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            
            
            
         // latitude and longitude
            double latitude = b.getDouble("latitude");
            double longitude = b.getDouble("longitude");
            double latDest = getCoord("lat");
            double longDest = getCoord("long");
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(latDest, longDest)).zoom(20).build();
     
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
           
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Object Thrown From Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            MarkerOptions markerDest = new MarkerOptions().position(new LatLng(latDest, longDest)).title("Object Landed Here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            
            
            
            // adding marker
            googleMap.addMarker(marker);
            googleMap.addMarker(markerDest);
 
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        Bundle b = getIntent().getExtras();
        initilizeMap(b);
    }
 
}