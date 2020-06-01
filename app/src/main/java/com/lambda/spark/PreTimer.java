package com.lambda.spark;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Locale;

public class PreTimer extends AppCompatActivity {
    String text;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_timer);
        Button b = (Button) findViewById(R.id.button);
        Button a = (Button) findViewById(R.id.button2);
        text = "Do you want to proceed to billing?";
        tts=new TextToSpeech(PreTimer.this, status -> {
            // TODO Auto-generated method stub
            if(status == TextToSpeech.SUCCESS){
                int result=tts.setLanguage(Locale.US);
                if(result==TextToSpeech.LANG_MISSING_DATA ||
                        result==TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.e("error", "This Language is not supported");
                }
                else{
                    if(text==null||"".equals(text))
                    {
                        text = "Content not available";
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    }else
                        tts.speak("Buy "+text, TextToSpeech.QUEUE_FLUSH, null);

                }
            }
            else
                Log.e("error", String.valueOf(status));
        });
                            final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            //int ans;
                            voiceautomation();

                        }
                    }, 2700);



        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PreTimer.this, BillingTimer.class);
                startActivity(intent);
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PreTimer.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void voiceautomation(){
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT,"Do you want to proceed for billing?");
        startActivityForResult(voice,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (arrayList.get(0).toString().equals("yes")){
                Intent intent = new Intent(PreTimer.this, BillingTimer.class);
                startActivity(intent);
            }

        }
    }
}

