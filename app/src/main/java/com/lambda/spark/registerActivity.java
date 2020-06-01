package com.lambda.spark;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(registerActivity.this, HomeActivity.class));
            finish();
        }
        frameLayout = findViewById(R.id.register_FrameLayout);
        setFragment(new SignUpFragment());

//        Intent homeIntent= new Intent(registerActivity.this, SignInFragment.class);
//        startActivity(homeIntent);

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
