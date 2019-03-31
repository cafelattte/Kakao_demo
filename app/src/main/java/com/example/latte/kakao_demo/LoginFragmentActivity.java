package com.example.latte.kakao_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by latte on 2019. 1. 15..
 */

public class LoginFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fragment);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
