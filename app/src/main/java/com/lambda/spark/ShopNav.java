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
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShopNav extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Spinner spinner1;
    LocationManager locationManager;
    LocationListener locationListener;
    private List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
    private DatabaseReference mrootDatabase, mdemoDatabase, mleafDatabase;

    ArrayList<String> myProducts;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_shop);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        img1 = findViewById(R.id.nav_reco_img1);
//        img2 = findViewById(R.id.nav_reco_img2);
//        img3 = findViewById(R.id.nav_reco_img3);
//        img4 = findViewById(R.id.nav_reco_img4);
//        txt1 = findViewById(R.id.nav_reco_imgname1);
//        txt2 = findViewById(R.id.nav_reco_imgname2);
//        txt3 = findViewById(R.id.nav_reco_imgname3);
//        txt4 = findViewById(R.id.nav_reco_imgname4);
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img1, "Organic Strawberries", "$30","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img2, "Limes", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img3, "Organic Hass Avocado","$30","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img4, "Bag of Organic Bananas", "$15","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img5, "Organic Garlic", "$5","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img6, "Organic Baby Spinach", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img7, "Banana", "$10","Grocery"));
//        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.reco_prod_img8, "Organic Blueberries", "$25","Grocery"));
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

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();
                mMap.setIndoorEnabled(true);
                mMap.setBuildingsEnabled(true);
//                Random rand = new Random();
//                int rand_int1 = rand.nextInt(8);
//                int rand_int2 = rand.nextInt(8);
//                int rand_int3 = rand.nextInt(8);
//                int rand_int4 = rand.nextInt(8);
                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                Log.i("Latitude", String.valueOf(location.getLatitude()));
//                LatLng dstLocation = new LatLng(28.613390, 77.089300);
//                String title;
//                int img_num;
//                title = String.valueOf(horizontalProductModelList.get(rand_int1).getProductTitle());
//                img_num = horizontalProductModelList.get(rand_int1).getProductImg();
//                txt1.setText(title);
//                img1.setImageResource(img_num);
//                title = String.valueOf(horizontalProductModelList.get(rand_int2).getProductTitle());
//                img_num = horizontalProductModelList.get(rand_int2).getProductImg();
//                txt2.setText(title);
//                img2.setImageResource(img_num);
//                title = String.valueOf(horizontalProductModelList.get(rand_int3).getProductTitle());
//                img_num = horizontalProductModelList.get(rand_int3).getProductImg();
//                txt3.setText(title);
//                img3.setImageResource(img_num);
//                title = String.valueOf(horizontalProductModelList.get(rand_int4).getProductTitle());
//                img_num = horizontalProductModelList.get(rand_int4).getProductImg();
//                txt4.setText(title);
//                img4.setImageResource(img_num);



                mrootDatabase = FirebaseDatabase.getInstance().getReference();
                mdemoDatabase = mrootDatabase.child("demo3");

                Log.e("check1","1");
                //myProducts.add("Rahul");
                //ArrayAdapter<String>()
                //productAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myProducts);
                //myList.setAdapter(productAdapter);
                mdemoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            for (int i = 0; i < 8; i++){
//                                ArrayList<String> myProducts = new ArrayList<String>();
                                if(snapshot.child(String.valueOf(i)).exists()){
                                    //myProducts.add((String) snapshot.child(String.valueOf(i)).child("section").getValue());
                                    String dept = (String) snapshot.child(String.valueOf(i)).child("section").getValue();
                                    Log.e("sec check",dept);
                                    if(dept.equals("Grocery")){
                                        LatLng dstLocation = new LatLng(28.613390, 77.089300);
                                        mMap.addMarker(new MarkerOptions().position(dstLocation).title("Grocery").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                                    }
                                    else if(dept.equals("Beverage")){
                                        LatLng dstLocation = new LatLng(28.613350, 77.089260);
                                        mMap.addMarker(new MarkerOptions().position(dstLocation).title("Beverage").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                                    }
                                    else if(dept.equals("Dairy")){
                                        LatLng dstLocation = new LatLng(28.613405, 77.089312);
                                        mMap.addMarker(new MarkerOptions().position(dstLocation).title("Dairy").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                                    }
                                    else if(dept.equals("Meat")){
                                        LatLng dstLocation = new LatLng(28.613381, 77.089305);
                                        mMap.addMarker(new MarkerOptions().position(dstLocation).title("Destination Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
                                    }
                                    //Log.e("check val",myProducts.get(0));
                                    //Log.e("list test", (String) snapshot.child(String.valueOf(i)).child("title").getValue());
                                    //productAdapter.notifyDataSetChanged();
                                }
                                HashMap<String,Integer> hm = new HashMap<String,Integer>();
//                                for (int it = 0; it < myProducts.size();it++) {
//                                    hm.put(myProducts.get(it), it);
//                                }
                                LatLng dstLocation = new LatLng(28.613390, 77.089300);
                                mMap.addMarker(new MarkerOptions().position(dstLocation).title("Destination Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                            }
//                    Log.e("list test", (String) snapshot.child(String.valueOf()).child("title").getValue());
                        }
                        //Log.e("check val",myProducts.get(0));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("cancelled","kyun");
                    }
                });









//                mMap.addMarker(new MarkerOptions().position(dstLocation).title("Destination Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,22));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

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
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//                mMap.clear();
//                LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
            }
        }


    }
}

