package com.example.latte.kakao_demo.SpeechAPI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;
import com.kakao.sdk.newtoneapi.TextToSpeechManager;

import java.util.ArrayList;

public class STT_API extends AppCompatActivity {
    public void onCerate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SDK initialize
        SpeechRecognizerManager.getInstance().initializeLibrary(getApplicationContext());
        TextToSpeechManager.getInstance().initializeLibrary(getApplicationContext());

        // build client builder
        SpeechRecognizerClient.Builder sttBuilder = new SpeechRecognizerClient.Builder()
                .setServiceType(SpeechRecognizerClient.SERVICE_TYPE_WEB)
                .setUserDictionary("text");

        // build client
        SpeechRecognizerClient sttClient = sttBuilder.build();

        sttClient.setSpeechRecognizeListener(new SpeechRecognizeListener() {
            @Override
            public void onReady() {
                Toast.makeText(getApplicationContext(), "I am ready!!", Toast.LENGTH_SHORT);
                Log.i("STT", "onReady");
            }

            @Override
            public void onBeginningOfSpeech() {
                Toast.makeText(getApplicationContext(), "I think here is Beginning of Speech", Toast.LENGTH_LONG);
                Log.i("STT", "onBeginning");
            }

            @Override
            public void onEndOfSpeech() {
                Toast.makeText(getApplicationContext(), "I think here is End of Speech", Toast.LENGTH_LONG);
                Log.i("STT", "onEnd");
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                Toast.makeText(getApplicationContext(), "There are Error in the Speech", Toast.LENGTH_LONG);
                Log.e("STT errorCode:" + String.valueOf(errorCode), errorMsg);
            }

            @Override
            public void onPartialResult(String partialResult) {
                Toast.makeText(getApplicationContext(), "PartialResult", Toast.LENGTH_LONG);
                Log.d("STT PartialResult", partialResult);
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> texts = results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS);
                ArrayList<Integer> confs = results.getIntegerArrayList(SpeechRecognizerClient.KEY_CONFIDENCE_VALUES);
                int arraySize = texts.size();
                Log.v("STT Results", String.valueOf(arraySize));

                Toast.makeText(getApplicationContext(), "Result: " + texts.get(0), Toast.LENGTH_LONG);
                for (int i = 0; i < texts.size(); i++) {
                    Log.v(texts.get(i), String.valueOf(confs.get(i)));
                }
            }

            @Override
            public void onAudioLevel(float audioLevel) {
                Toast.makeText(getApplicationContext(), "Audio level:" + audioLevel, Toast.LENGTH_SHORT);
                Log.i("STT listener", "Audio level:"+audioLevel);
            }

            @Override
            public void onFinished() {
                Toast.makeText(getApplicationContext(), "Speaking finished!!", Toast.LENGTH_LONG);
                Log.i("STT listener", "onFinished");
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        SpeechRecognizerManager.getInstance().finalizeLibrary();
        TextToSpeechManager.getInstance().finalizeLibrary();
    }

}
