package com.example.latte.kakao_demo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.latte.kakao_demo.SpeechAPI.GetPermissionActivity;

import java.io.*;
import java.net.*;

public class Naver_API extends AppCompatActivity {
    EditText naver_input_text;
    EditText naver_input_speed;
    TextView naver_progress_message;

    Button KoreanMButton;
    Button KoreanFButton;
    Button EnglishMButton;
    Button EnglishFButton;
    Button JapaneseMButton;
    Button JapaneseFButton;
    Button ChineseMButton;
    Button ChineseFButton;

    MediaPlayer audioPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_naver_layout);

        naver_input_text = (EditText) findViewById(R.id.naver_input_text);
        naver_input_speed = (EditText) findViewById(R.id.naver_input_speed);
        naver_progress_message = (TextView) findViewById(R.id.naver_progress_message);

        KoreanMButton = (Button) findViewById(R.id.naver_Korean_M);
        KoreanFButton = (Button) findViewById(R.id.naver_Korean_F);
        EnglishMButton = (Button) findViewById(R.id.naver_English_M);
        EnglishFButton = (Button) findViewById(R.id.naver_English_F);
        JapaneseMButton = (Button) findViewById(R.id.naver_Japanese_M);
        JapaneseFButton = (Button) findViewById(R.id.naver_Japanese_F);
        ChineseMButton = (Button) findViewById(R.id.naver_Chinese_M);
        ChineseFButton = (Button) findViewById(R.id.naver_Chinese_F);

        audioPlay = new MediaPlayer();

        Intent intent = new Intent(Naver_API.this, GetPermissionActivity.class);
        startActivity(intent);

        KoreanMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "jinho";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        KoreanFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "mijin";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        EnglishMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "matt";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        EnglishFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "clara";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        JapaneseMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "shinji";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        JapaneseFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "shinji";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        ChineseMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "liangliang";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });

        ChineseFButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaker = "meimei";
                String speed = String.valueOf(naver_input_speed.getText().toString());
                String text = naver_input_text.getText().toString();

                new TTS_API_Convert_and_Play().execute(speaker, speed, text);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class TTS_API_Convert_and_Play extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            naver_progress_message.setText("TTS Convert Loading...");
        }

        @Override
        protected String doInBackground(String... params) {
            naver_progress_message.setText("TTS Converting...");

            String result = "";
            try {
                String clientId = getResources().getString(R.string.naver_clientId);
                String clientSecret = getResources().getString(R.string.naver_clientSecret);
                String encodedText = URLEncoder.encode(params[2], "UTF-8");
                String apiURL = "https://naveropenapi.apigw.ntruss.com/voice/v1/tts";

                URL url = new URL(apiURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
                urlConnection.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // post request
                String postParams = "";
                postParams = postParams + "speaker=" + params[0];
                postParams = postParams + "&speed=" + params[1];
                postParams = postParams + "&text=" + encodedText;

                urlConnection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                // response
                int responseCode = urlConnection.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    InputStream is = urlConnection.getInputStream();
                    int read = 0;
                    byte[] bytes = new byte[1024];

                    File dir = new File(Environment.getExternalStorageDirectory(), "NaverClova");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    result = Environment.getExternalStorageDirectory().toString() + "/NaverClova/naverclova.mp3";
                    File file = new File(result);

                    file.createNewFile();

                    OutputStream os = new FileOutputStream(file);
                    while ((read = is.read(bytes)) != -1) {
                        os.write(bytes, 0, read);
                    }
                    is.close();
                } else {
                    br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();

                    Log.e("Naver_TTS", response.toString());
                }

            } catch (Exception e) {
                Toast.makeText(Naver_API.this, e.toString(), Toast.LENGTH_SHORT).show();
                Log.e("Naver_TTS", e.toString());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            naver_progress_message.setText("TTS Convert Complete!");

            try {
                audioPlay.reset();
                audioPlay.setDataSource(result);
                audioPlay.prepare();
                audioPlay.start();
            } catch (Exception e) {
                Log.e("Naver_TTS", e.toString());
            }
        }
    }
}
