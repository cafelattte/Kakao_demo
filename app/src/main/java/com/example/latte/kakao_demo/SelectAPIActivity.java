package com.example.latte.kakao_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.latte.kakao_demo.SpeechAPI.TTS_API;

public class SelectAPIActivity extends AppCompatActivity {
    ImageButton imageButton_kakao;
    ImageButton imageButton_naver;
    ImageButton imageButton_google;
    ImageButton imageButton_kt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_api_layout);

        imageButton_kakao = (ImageButton) findViewById(R.id.kakaoButton);
        imageButton_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectAPIActivity.this, TTS_API.class);
                startActivity(intent);
            }
        });

        imageButton_naver = (ImageButton) findViewById(R.id.naverButton);
        imageButton_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectAPIActivity.this, Naver_API.class);
                startActivity(intent);
            }
        });

        imageButton_google = (ImageButton) findViewById(R.id.googleButton);
        imageButton_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectAPIActivity.this, "Google API selected", Toast.LENGTH_SHORT).show();
            }
        });

        imageButton_kt = (ImageButton) findViewById(R.id.ktButton);
        imageButton_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SelectAPIActivity.this, "KT API selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
