package com.example.latte.kakao_demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.latte.kakao_demo.SpeechAPI.GetPermissionActivity;
import com.example.latte.kakao_demo.SpeechAPI.STT_API;
import com.example.latte.kakao_demo.SpeechAPI.TTS_API;

import static com.kakao.util.helper.Utility.getPackageInfo;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentt = new Intent(MainActivity.this, SelectAPIActivity.class);
        startActivity(intentt);

        Button sttButton = (Button) findViewById(R.id.sttButton);
        sttButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "STT Button clicked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, STT_API.class);
                startActivity(intent);
            }
        });

        Button ttsButton = (Button) findViewById(R.id.ttsButton);
        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "TTS Button clicked", Toast.LENGTH_LONG).show();
                Log.v("MainActivity", "TTS Button clicked");

                Intent intent = new Intent(MainActivity.this, TTS_API.class);
                startActivity(intent);
            }
        });
    }
}
