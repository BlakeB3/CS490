package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private TextToSpeech tts;
    private TextView nameTv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = tts.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                        tts.speak("hello",TextToSpeech.QUEUE_FLUSH,null,null);
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));

                    String resultString = "";

                    for (String s : result)
                    {
                        resultString += s + " ";
                    }
                    final String resultString2 = resultString;

                    System.out.println(resultString);

                    tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status == TextToSpeech.SUCCESS) {
                                int ttsLang = tts.setLanguage(Locale.US);

                                if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                                        || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                                    Log.e("TTS", "The Language is not supported!");
                                } else {
                                    Log.i("TTS", "Language Supported.");
                                    System.out.println("'" + resultString2 + "'");

                                    if (resultString2.equals("hello ")){
                                        tts.speak("what is your name", TextToSpeech.QUEUE_FLUSH, null, null);
                                    }

                                    if (resultString2.equals("my name is Blake my name is blayke ")){
                                        tts.speak("ok Blake, what can I do for you.", TextToSpeech.QUEUE_FLUSH, null, null);
                                        nameTv = MainActivity.this.findViewById(R.id.textView3);
                                        nameTv.setVisibility(View.VISIBLE);
                                    }

                                    if (resultString2.equals("what time is it ")){
                                        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
                                        Date now = new Date();
                                        String[] strDate = sdfDate.format(now).split(":");
                                        if(strDate[1].contains("00"))
                                            strDate[1] = "o'clock";
                                        String resultDate = "";
                                        for (String b : strDate)
                                        {
                                            resultDate += b + " ";
                                        }
                                        tts.speak("the time is" + resultDate , TextToSpeech.QUEUE_FLUSH, null, null);
                                    }
                                    if (resultString2.equals("what medicines should I take what medicine should I take ")){
                                        tts.speak("I think you have a fever. Please take this medicine", TextToSpeech.QUEUE_FLUSH, null, null);
                                    }



                                    if (resultString2.equals("I am not feeling good what should I do ")){
                                        tts.speak("I can understand. Please tell me your symptoms in short.",TextToSpeech.QUEUE_FLUSH,null,null);
                                    }
                                }
                                Log.i("TTS", "Initialization success.");
                            } else {
                                Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


                break;
            }

        }
    }
}