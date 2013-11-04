package cs371.letitfly;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cs371.letitfly.R;

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
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(latitude, longitude)).zoom(15).build();
     
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
           
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Object Thrown From Here");
             
            // adding marker
            googleMap.addMarker(marker);
 
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