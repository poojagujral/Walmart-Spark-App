package com.lambda.spark;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.model.Progress;
import com.camerakit.CameraKitView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import android.Manifest;

import android.app.ProgressDialog;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProductScan extends AppCompatActivity{

    private CameraKitView cameraKitView;
    private Button photoButton;
    private StorageReference storageRef;
    private Uri uri;

    private ProgressDialog dialog;

    private View.OnClickListener photoOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, final byte[] photo) {



                    File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
                    storageRef = FirebaseStorage.getInstance().getReference();
                    storageRef = storageRef.child(getIntent()+"/hello.jpg");
                    dialog = new ProgressDialog(ProductScan.this);
                    dialog.setMessage("Uploading snap, identifying product...");
//                    Log.e("check1", String.valueOf(photo));
                    //try {
                    dialog.show();
                    storageRef.putBytes(photo)
                            .continueWithTask(task -> {
                                if (!task.isSuccessful()) {
                                    ProductScan.this.runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if (dialog.isShowing()) {
                                                dialog.dismiss();
                                            }
                                            // Stuff that updates the UI

                                        }
                                    });


                                    Log.e("unsuccessful","File not uploaded");
                                }
                                return storageRef.getDownloadUrl();
                            }).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            uri = (Uri) task.getResult();

                            Log.i("upload", "Upload Success - "+uri);
                            AndroidNetworking.post("http://192.168.1.9:8081/upload/")
                                    //.addMultipartFile("Obj", photo)
                                    .addBodyParameter("link", String.valueOf(uri))
                                    //.addBodyParameter("lastname", "Shekhar")
                                    //.setTag("test")
                                    //.setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            // do anything with response
                                            ProductScan.this.runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                    // Stuff that updates the UI

                                                }
                                            });
                                            try {

                                                Log.e("check2", String.valueOf(response.get("res")));
                                                int ans = Integer.parseInt(String.valueOf(response.get("res")));
                                                Log.i("ans", String.valueOf(ans));
                                                String title,offers,variants;
                                                if(ans == 1){
                                                    //String title,offers,variants;
                                                    title = "Park Avenue Beer Shampoo";
                                                    offers = "Buy 2 Get 1 Free";
                                                    variants = "180 ml, 350ml";
                                                }
                                                else if(ans == 2){
                                                    title = "Dettol Antiseptic Liquid";
                                                    offers = "Buy 2 Get 1 Free";
                                                    variants = "125ml, 250ml, 500ml";
                                                }
                                                else if(ans == 3){
                                                    title = "Airtel 4G Hotspot";
                                                    offers = "5GB data free";
                                                    variants = "No variants available";
                                                }
                                                else{
                                                    title = "Product not found";
                                                    offers = "NA";
                                                    variants = "NA";
                                                }

                                                Intent scanIntent= new Intent(ProductScan.this,ProductOffers.class);
                                                scanIntent.putExtra("Title",title);
                                                scanIntent.putExtra("Offers",offers);
                                                scanIntent.putExtra("Variants", variants);
                                                startActivity(scanIntent);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        @Override
                                        public void onError(ANError error) {
                                            // handle error
                                            ProductScan.this.runOnUiThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    if (dialog.isShowing()) {
                                                        dialog.dismiss();
                                                    }
                                                    // Stuff that updates the UI

                                                }
                                            });
                                            Log.e("check2", String.valueOf(error));
                                        }
                                    });
                        }
                        else {
                            ProductScan.this.runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (dialog.isShowing()) {
                                        dialog.dismiss();
                                    }
                                    // Stuff that updates the UI

                                }
                            });
                            Log.i("upload", "Upload Failed - "+task);

                        }
                    });
//                    FileOutputStream outputStream = null;
//                    try {
//                        outputStream = new FileOutputStream(savedPhoto.getPath());
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        outputStream.write(photo);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                        AndroidNetworking.get("https:192.168.1.9:8081/upload/")
//                                .addPathParameter("pageNumber", "0")
//                                .addQueryParameter("obj","6" )
//                                .addHeaders("token", "1234")
//                                .setTag("test")
//                                .setPriority(Priority.LOW)
//                                .build()
//                                .getAsJSONArray(new JSONArrayRequestListener() {
//                                    @Override
//                                    public void onResponse(JSONArray response) {
//                                        // do anything with response
//                                        Log.e("check2", String.valueOf(response));
//                                    }
//                                    @Override
//                                    public void onError(ANError error) {
//                                        // handle error
//                                        Log.e("check2","fail");
//                                    }
//                                });
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Do something after 100ms
//                            //int ans;
//
//
//                        }
//                    }, 12000);



//                    } catch (java.io.IOException e) {
//                        e.printStackTrace();
//                        Log.e("CKDemo", "Exception in photo callback");
//                    }
                }
            });
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_scan);



        cameraKitView = findViewById(R.id.camera);
        photoButton = findViewById(R.id.photoButton);
        photoButton.setOnClickListener(photoOnClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
