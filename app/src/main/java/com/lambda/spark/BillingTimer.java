package com.lambda.spark;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class BillingTimer extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.bill_timer);

        final TextView timertextview = findViewById(R.id.textView3);
        new CountDownTimer(10000, 1000){

            public void onTick(long mlsecuntildone){
                Log.e("Seconds Left", String.valueOf(mlsecuntildone/1000));
                int minutes = (int) (mlsecuntildone / 60000);
                int seconds = (int) (mlsecuntildone/1000 - (minutes*60));
                timertextview.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));

            }

            @Override
            public void onFinish() {

                final TextView queue_number = findViewById(R.id.textView2);

                queue_number.setVisibility(View.INVISIBLE);
                timertextview.setText("Time up!");
                timertextview.setTextColor(Color.RED);
                Log.e("We are Done", "No more CountDown");
            }
        }.start();

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) ((int)width*.87), (int) (height*.7));


    }
}
