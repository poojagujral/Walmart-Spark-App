package com.lambda.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.argb;


public class SignUpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView appTitle;
    private TextView tagline1;
    private TextView signup_text;
    private TextView need_assistance_tv;
    private TextView tv_already_have_an_account;


    private ImageView logo_signup;

    private EditText fullname;
    private EditText age;
    private RadioGroup gender;
    private EditText emailAdd;
    private EditText createPwd;
    private EditText confirmPwd;

    private CheckBox assistance_y_box;
    private CheckBox assistance_n_box;

    private Button signup_btn;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    //private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        parentFrameLayout = getActivity().findViewById(R.id.register_FrameLayout);

//        appTitle = view.findViewById(R.id.appTitle_signIn);
//        tagline1 = view.findViewById(R.id.tagline_signIn);
//        signup_text = view.findViewById(R.id.signIn_tv);
//
//        logo_signup = view.findViewById(R.id.logo_signIn);

        fullname = view.findViewById(R.id.fullName);
        age = view.findViewById(R.id.age);
        gender = view.findViewById(R.id.radioGrp);
        emailAdd = view.findViewById(R.id.email2);
        createPwd = view.findViewById(R.id.createPwd);
        confirmPwd = view.findViewById(R.id.confirmPwd);

//        assistance_y_box = view.findViewById(R.id.assistance_y_box);
//        assistance_n_box = view.findViewById(R.id.assistance_n_box);

        signup_btn = view.findViewById(R.id.signup_btn);

        alreadyHaveAnAccount = view.findViewById(R.id.signin_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpFragment.this.getActivity(), SignInFragment.class);
                startActivity(intent);
            }
        });

        emailAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fullname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        gender.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                checkInputs();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        emailAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        createPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(emailAdd.getText())) {
            if (!TextUtils.isEmpty(fullname.getText())) {
                if (!TextUtils.isEmpty(age.getText())) {

                        if (!TextUtils.isEmpty(createPwd.getText()) && createPwd.length() >= 8) {
                            if (!TextUtils.isEmpty((confirmPwd.getText())) && confirmPwd.length() >= 8) {
                                signup_btn.setEnabled(true);
                                signup_btn.setTextColor(argb(255, 255, 255, 255));

                            } else {
                                signup_btn.setEnabled(false);
                                signup_btn.setTextColor(argb(50, 255, 255, 255));


                            }

                        } else {
                            signup_btn.setEnabled(false);
                            signup_btn.setTextColor(argb(50, 255, 255, 255));


                        }


                } else {
                    signup_btn.setEnabled(false);
                    signup_btn.setTextColor(argb(50, 255, 255, 255));


                }
            } else {
                signup_btn.setEnabled(false);
                signup_btn.setTextColor(argb(50, 255, 255, 255));

            }

        } else {
            signup_btn.setEnabled(false);
            signup_btn.setTextColor(argb(50, 255, 255, 255));

        }
    }

    private void checkEmailAndPassword() {
        if (emailAdd.getText().toString().matches(emailPattern)) {
            if (createPwd.getText().toString().equals(confirmPwd.getText().toString())){

                //multiple clicks on button disabled

                signup_btn.setEnabled(false);
                signup_btn.setTextColor(argb(50, 255, 255, 255));


                //data being sent via internet
                firebaseAuth.createUserWithEmailAndPassword(emailAdd.getText().toString(), createPwd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user acc created here
                            //now storing user full name in DB

                            Map<Object, String> userdata = new HashMap<>();
                            userdata.put("fullname", fullname.getText().toString());
                            userdata.put("age", age.getText().toString());

                            firebaseFirestore.collection("USERS") //creating directory to store users' names
                                    .add(userdata)//adding users to our directory
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            if(task.isSuccessful()){

                                                //if data stored in DB and take to home page
                                                Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                                                startActivity(homeIntent);
                                                getActivity().finish();

                                            }
                                            else {
                                                signup_btn.setEnabled(true);
                                                signup_btn.setTextColor(argb(255, 255, 255, 255)); //enabled btn in case error shows
                                                String error = task.getException().getMessage();
                                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else {
                            signup_btn.setEnabled(true);
                            signup_btn.setTextColor(argb(255, 255, 255, 255)); //enabled btn in case error shows
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }else {
                confirmPwd.setError("Passwords don't match");
            }

        }else {

            emailAdd.setError("Invalid Email");

        }
    }
}




//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}