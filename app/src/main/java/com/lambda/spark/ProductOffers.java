package com.lambda.spark;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductOffers extends AppCompatActivity {
    View reco;
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_offers);

        String pro_title = getIntent().getStringExtra("Title");
        String pro_offers = getIntent().getStringExtra("Offers");
        String pro_variants = getIntent().getStringExtra("Variants");

        TextView mTextView;
        mTextView = (TextView) findViewById(R.id.textView2);
        mTextView.setText(pro_title);
        mTextView = (TextView) findViewById(R.id.textView5);
        mTextView.setText(pro_offers);
        mTextView = (TextView) findViewById(R.id.textView7);
        mTextView.setText(pro_variants);
        Log.e("checkit","kr lia");
        ImageView mimageView = findViewById(R.id.imageView);
        if(pro_title.equals("Park Avenue Beer Shampoo")){
            mimageView.setImageResource(R.drawable.pro_offer1);
        }
        else if (pro_title.equals("Dettol Antiseptic Liquid")){
            mimageView.setImageResource(R.drawable.pro_offer2);
        }
        else if (pro_title.equals("Airtel 4G Hotspot")){
            mimageView.setImageResource(R.drawable.pro_offer3);
        }
        else{
            mimageView.setImageResource(R.drawable.pro_offer4);
        }
        setFragment(new FreqBought());
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment,fragment);
        fragmentTransaction.commit();
    }
}