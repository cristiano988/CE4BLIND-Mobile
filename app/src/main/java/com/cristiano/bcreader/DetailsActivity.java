package com.cristiano.bcreader;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;
    TextView details;
    String text = null;
    TextToSpeech tts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        intent = getIntent();
        text =  intent.getStringExtra("section");

        details = (TextView)findViewById(R.id.textViewDetails);
        details.setText(text);

        tts = new TextToSpeech(getApplicationContext(), initListener);
    }

    TextToSpeech.OnInitListener initListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int i) {
            if(i != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt"));
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        tts.stop();
    }
}
