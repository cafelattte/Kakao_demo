package com.example.latte.kakao_demo.SpeechAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.latte.kakao_demo.R;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;
import com.kakao.sdk.newtoneapi.TextToSpeechClient;
import com.kakao.sdk.newtoneapi.TextToSpeechListener;
import com.kakao.sdk.newtoneapi.TextToSpeechManager;

public class TTS_API extends AppCompatActivity implements TextToSpeechListener {
    private TextToSpeechClient ttsClient;
    TextToSpeechListener textToSpeechListener;
    EditText tts_input_text;
    EditText tts_input_speed;
    Button ttsButton1left;
    Button ttsButton1right;
    Button ttsButton2left;
    Button ttsButton2right;
    Button ttsButton3left;
    Button ttsButton3right;
    Button ttsButton4left;
    Button ttsButton4right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_layout);

//        Toast.makeText(TTS_API.this, "TTS_API", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TTS_API.this, GetPermissionActivity.class);
        startActivity(intent);

        tts_input_text = (EditText) findViewById(R.id.tts_input_text);
        tts_input_speed = (EditText) findViewById(R.id.tts_input_speed);
        ttsButton1left = (Button) findViewById(R.id.ttsButton1left);
        ttsButton1right = (Button) findViewById(R.id.ttsButton1right);
        ttsButton2left = (Button) findViewById(R.id.ttsButton2left);
        ttsButton2right = (Button) findViewById(R.id.ttsButton2right);
        ttsButton3left = (Button) findViewById(R.id.ttsButton3left);
        ttsButton3right = (Button) findViewById(R.id.ttsButton3right);
        ttsButton4left = (Button) findViewById(R.id.ttsButton4left);
        ttsButton4right = (Button) findViewById(R.id.ttsButton4right);

        // Initialize SDK
        TextToSpeechManager.getInstance().initializeLibrary(getApplicationContext());
        SpeechRecognizerManager.getInstance().initializeLibrary(getApplicationContext());

        // build client builder
/*
        ttsClient = new TextToSpeechClient.Builder()
                .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_2)
                .setSpeechSpeed(tts_input_speed.getText().toString())
                .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)
                .setListener(textToSpeechListener)
                .build();
*/

        ttsButton1left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_DIALOG_BRIGHT)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton1right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_2)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_DIALOG_BRIGHT)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton2left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_MAN_DIALOG_BRIGHT)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton2right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_2)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_MAN_DIALOG_BRIGHT)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton3left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton3right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_2)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_WOMAN_READ_CALM)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton4left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_1)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_MAN_READ_CALM)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

        ttsButton4right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ttsClient = new TextToSpeechClient.Builder()
                        .setSpeechMode(TextToSpeechClient.NEWTONE_TALK_2)
                        .setSpeechSpeed(Double.parseDouble(tts_input_speed.getText().toString()))
                        .setSpeechVoice(TextToSpeechClient.VOICE_MAN_READ_CALM)
                        .setListener(textToSpeechListener)
                        .build();
                if (ttsClient.isPlaying()) {
                    Toast.makeText(TTS_API.this, "now TTS is playing", Toast.LENGTH_SHORT).show();
                } else {
                    ttsClient.setSpeechText(tts_input_text.getText().toString());
                    ttsClient.play();
                }
            }
        });

    }

    @Override
    public void onError(int code, String message) {
        Toast.makeText(getApplicationContext(), "Error, code:" + String.valueOf(code) + ", msg: " + message, Toast.LENGTH_SHORT).show();
        Log.e("TTS: " + String.valueOf(code), message);
    }

    @Override
    public void onFinished() {
        int intSentSize = ttsClient.getSentDataSize();
        int intRecvSize = ttsClient.getReceivedDataSize();

        final String tts_output = "handleFinished() SentSize : " + intSentSize + "RecvSize : " + intRecvSize;

        Toast.makeText(TTS_API.this, tts_output, Toast.LENGTH_SHORT).show();
        Log.i("TTS", tts_output);
    }

    public void onDestroy() {
        super.onDestroy();

        TextToSpeechManager.getInstance().finalizeLibrary();
        SpeechRecognizerManager.getInstance().finalizeLibrary();
    }

}
