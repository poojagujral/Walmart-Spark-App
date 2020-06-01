package com.lambda.spark;
import android.annotation.SuppressLint;

import android.app.Activity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductPop extends AppCompatActivity {
    private DatabaseReference mrootDatabase, mdemoDatabase, mleafDatabase;
    private int position;
    private String title_pro;
    private int price_pro;
    private String section;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_pop);
        Button a = (Button) findViewById(R.id.button4);
        Button b = (Button) findViewById(R.id.button3);
        position = getIntent().getIntExtra("ID",0);
        String pro_title = getIntent().getStringExtra("Title");
        title_pro = getIntent().getStringExtra("Title");
        section = getIntent().getStringExtra("Section");
        String pro_price = getIntent().getStringExtra("Price");
        price_pro = Integer.parseInt(pro_price.substring(1));
        String pro_image = getIntent().getStringExtra("Image");
        String pro_status = getIntent().getStringExtra("Status");
        TextView mTextView;
        mTextView = (TextView) findViewById(R.id.textView6);
        mTextView.setText(pro_title);

        mTextView = findViewById(R.id.nav_reco_imgname3);
        mTextView.setText(pro_status);
        mTextView.setTextColor(Color.parseColor("#008000"));
        if(pro_status.equals("Out of Stock")){
            a.setEnabled(false);
            b.setEnabled(true);
            mTextView.setTextColor(Color.RED);
        }
        mTextView = (TextView) findViewById(R.id.nav_reco_imgname1);

        mTextView.setText(pro_price);

//        HorizontalProductModel product = (HorizontalProductModel) getIntent().getSerializableExtra("full_object");
        ImageView img = findViewById(R.id.imageView);
//        Log.e("img recd", String.valueOf(product.getProductImg()));
        img.setImageResource(Integer.parseInt(pro_image));



        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) ((int)width*.95), (int) (height*.85));


        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mrootDatabase = FirebaseDatabase.getInstance().getReference();
                mdemoDatabase = mrootDatabase.child("demo3");
                mleafDatabase = mdemoDatabase.child(String.valueOf(position));
                mleafDatabase.child("title").setValue(title_pro);
                mleafDatabase.child("price").setValue(price_pro);
                mleafDatabase.child("section").setValue(section);
                Log.e("testo", String.valueOf(mdemoDatabase));
//                Query allElements = mdemoDatabase.limitToFirst(10);
//                Log.e("results", String.valueOf(allElements));
                //Toast.makeText(this, "Product Added to List", )
                Toast.makeText(ProductPop.this, "Product Added to List", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
