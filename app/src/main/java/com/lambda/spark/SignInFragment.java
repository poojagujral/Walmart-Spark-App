package com.lambda.spark;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class SignInFragment extends Fragment {
//
//
//    public SignInFragment() {
//        // Required empty public constructor
//    }
//
//    private TextView appTitle_signIn;
//    private TextView tagline_signIn;
//
//    private ImageView logo_signIn;
//
//    private TextView signIn_tv;
//
//    private EditText signInEmail;
//    private EditText signInPwd;
//
//    private TextView forgotPwd;
//    private TextView dontHaveAnAccount;
//    private FrameLayout parentFrameLayout;
//
//    private Button signInBtn;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
//        dontHaveAnAccount = view.findViewById(R.id.dont_have_an_account);
//        parentFrameLayout = getActivity().findViewById(R.id.register_FrameLayout);
//
//        signInEmail= view.findViewById(R.id.signInEmail);
//        signInPwd= view.findViewById(R.id.signInPwd);
//        signInBtn= view.findViewById(R.id.signInBtn);
//        return view;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        dontHaveAnAccount.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v){
//                setFragment(new SignUpFragment());
//            }}
//        );
//    }
//
//    private void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideiout_from_left);
//        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
//        fragmentTransaction.commit();
//    }
//}
//

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInFragment extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    //private ProgressBar progressBar;
    private Button btnLogin;

    private TextView dontHaveAnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignInFragment.this, HomeActivity.class));
            finish();
        }

        // set the view now

        setContentView(R.layout.fragment_sign_in);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.signInEmail);
        inputPassword = (EditText) findViewById(R.id.signInPwd);
        dontHaveAnAccount = findViewById(R.id.signup_btn);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.signInBtn);
        //btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

//        btnSignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
//            }
//        });

//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
//            }
//        });
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(SignInFragment.this, registerActivity.class);
                startActivity(intent);
                finish();
            }}
        );


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInFragment.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(SignInFragment.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInFragment.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}