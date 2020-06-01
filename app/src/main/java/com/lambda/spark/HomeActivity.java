package com.lambda.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import android.content.Intent;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FrameLayout frameLayout;
    private FirebaseAuth auth;
    private FirebaseUser Uber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent chatIntent= new Intent(HomeActivity.this, Chatbot.class);
                startActivity(chatIntent);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.home_frame_layout);
        setFragment(new HomeFragment());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_scan_icon) {
            //todo: search icon code

            Intent find_my_prod = new Intent(this, ProductScan.class);
            startActivity(find_my_prod);

            return true;

        }
        else if (id == R.id.home_findMyprod_icon){
            Intent mapIntent= new Intent(HomeActivity.this, NavigationMap.class);
            startActivity(mapIntent);
        }
        else if (id == R.id.home_billingQueue_icon){

            Intent billIntent= new Intent(HomeActivity.this, PreTimer.class);
            startActivity(billIntent);

        }
        else if(id == R.id.nav_home){
            finish();
            return true;
        }

        if(id == R.id.home_scan_icon){
            //todo: notif code
            Intent find_my_prod = new Intent(this, ScanActivity.class);
            startActivity(find_my_prod);
            return true;

        }else if (id == R.id.home_billingQueue_icon){
            //todo: cart code
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_profile) {
            Intent profileIntent= new Intent(HomeActivity.this, ProfileActivity.class);
               startActivity(profileIntent);

        } else if (id == R.id.nav_shoppingList) {

            Intent shoppingIntent= new Intent(HomeActivity.this, ShoppingList.class);
            startActivity(shoppingIntent);

        } else if (id == R.id.nav_purchases) {

        } else if (id == R.id.nav_settings) {
//            Intent mapIntent= new Intent(HomeActivity.this, Chatbot.class);
//            startActivity(mapIntent);

        }
        else if (id == R.id.nav_sign_out) {
            auth=FirebaseAuth.getInstance();
            auth.signOut();
            Uber=FirebaseAuth.getInstance().getCurrentUser();
            Log.i("user", String.valueOf(Uber));
            if (Uber == null) {
                // No user is signed in
                startActivity(new Intent(HomeActivity.this, SignInFragment.class));
                finish();
            }


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
