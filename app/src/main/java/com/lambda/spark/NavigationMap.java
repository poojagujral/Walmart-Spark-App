package com.lambda.spark;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class NavigationMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Spinner spinner1;
    LocationManager locationManager;
    LocationListener locationListener;
    private List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
    ImageView img1,img2,img3,img4;
    TextToSpeech tts;
    String text;
    TextView et;

//    TextView txt1,txt2,txt3,txt4;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mapFragment.getMapAsync(this);
        img1 = findViewById(R.id.nav_reco_img1);
        img2 = findViewById(R.id.nav_reco_img2);
        img3 = findViewById(R.id.nav_reco_img3);
        img4 = findViewById(R.id.nav_reco_img4);
        //tts = new TextToSpeech(this, (OnInitListener) this);
        //tts.setLanguage(Locale.US);
        //tts = new TextToSpeech(getApplicationContext(), ConvertTextToSpeech());
        //et = findViewById(R.id.)
        tts=new TextToSpeech(getApplicationContext(), status -> {
            // TODO Auto-generated method stub
            if(status == TextToSpeech.SUCCESS){
                int result=tts.setLanguage(Locale.US);
                if(result==TextToSpeech.LANG_MISSING_DATA ||
                        result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("error", "This Language is not supported");
                }
                else{
                    ConvertTextToSpeech();
                }
            }
            else
                Log.e("error", String.valueOf(status));
        });
//        txt1 = findViewById(R.id.nav_reco_imgname1);
//        txt2 = findViewById(R.id.nav_reco_imgname2);
//        txt3 = findViewById(R.id.nav_reco_imgname3);
//        txt4 = findViewById(R.id.nav_reco_imgname4);
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext17, "Organic Strawberries", "$30","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext10, "Limes", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext1, "Organic Hass Avocado","$30","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext4, "Bag of Organic Bananas", "$15","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext8, "Organic Garlic", "$5","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext16, "Organic Baby Spinach", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext2, "Banana", "$10","Grocery"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext4, "Organic Blueberries", "$25","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext10, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext11, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext12, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext13, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext14, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext15, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext16, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.recotext17, "Organic Baby Spinach", "$10","Grocery"));

    }


    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        //text = et.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak("Buy "+text, TextToSpeech.QUEUE_FLUSH, null);


}

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //spinner1 = (Spinner) findViewById(R.id.spinner_1);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //locationManager.req
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                mMap.setIndoorEnabled(true);
                mMap.setBuildingsEnabled(true);
                Random rand = new Random();
//                int rand_int1 = rand.nextInt(16);
//                int rand_int2 = rand.nextInt(16);
//                int rand_int3 = rand.nextInt(16);
//                int rand_int4 = rand.nextInt(16);
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                Log.i("Latitude", String.valueOf(location.getLatitude()));
                LatLng dstLocation = new LatLng(28.613390, 77.089300);
                String title;
//                final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Do something after 100ms
//                            //int ans;
//
//
//
//                        }
//                    }, 12000);
                int img_num, rand_int1, rand_int2, rand_int3, rand_int4;
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i=0; i<7 ; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
//                for (int i=0; i<4; i++) {
//                    System.out.println("Random Number= "+(list.get(i)));
//                }
                title = String.valueOf(horizontalProductModelList.get(list.get(0)).getProductTitle());
                text = title;
                ConvertTextToSpeech();
                img_num = horizontalProductModelList.get(list.get(0)).getProductImg();
//                txt1.setText(title);
                img1.setImageResource(img_num);
                //title = String.valueOf(horizontalProductModelList.get(rand_int2).getProductTitle());
                img_num = horizontalProductModelList.get(list.get(1)).getProductImg();
//                txt2.setText(title);
                img2.setImageResource(img_num);
                //title = String.valueOf(horizontalProductModelList.get(rand_int3).getProductTitle());
                img_num = horizontalProductModelList.get(list.get(2)).getProductImg();
//                txt3.setText(title);
                img3.setImageResource(img_num);
                //title = String.valueOf(horizontalProductModelList.get(rand_int4).getProductTitle());
                img_num = horizontalProductModelList.get(list.get(3)).getProductImg();
//                txt4.setText(title);
                img4.setImageResource(img_num);

                mMap.addMarker(new MarkerOptions().position(dstLocation).title("Destination Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,25));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.e("status check","rahul");
            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, locationListener);
//                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//                mMap.clear();
//                LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
            }
        }


    }

//    @Override
//    public void onInit(int status) {
//        if(status == TextToSpeech.SUCCESS){
//            int result=tts.setLanguage(Locale.US);
//            if(result==TextToSpeech.LANG_MISSING_DATA ||
//                    result==TextToSpeech.LANG_NOT_SUPPORTED){
//                Log.e("error", "This Language is not supported");
//            }
//            else{
//                ConvertTextToSpeech();
//            }
//        }
//        else
//            Log.e("error", String.valueOf(status));
//    }
}
