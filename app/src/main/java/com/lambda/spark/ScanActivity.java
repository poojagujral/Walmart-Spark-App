package com.lambda.spark;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class ScanActivity extends AppCompatActivity {

    Camera scanMyProd_camera;
    FrameLayout scan_cameraview;
    ScanCamera scanCamera;
    Button scan_my_prod_btn;
    RecyclerView scan_reco_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        scan_cameraview = findViewById(R.id.scan_cameraview);
        scanMyProd_camera = Camera.open();

        scan_cameraview =findViewById(R.id.scan_cameraview);
        scan_my_prod_btn= findViewById(R.id.scanMyProd_btn);
//        scan_reco_recyclerView= findViewById(R.id.reco_scan_recyclerView);

        scanCamera = new ScanCamera(this, scanMyProd_camera);
        scan_cameraview.addView(scanCamera);

        scan_reco_recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        scan_reco_recyclerView.setLayoutManager(layoutManager);
    }
}
