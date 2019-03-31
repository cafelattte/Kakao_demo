package com.example.latte.kakao_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by latte on 2019. 1. 14..
 */

public class RootLoginActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_kakao_login);
        Button activityButton = findViewById(R.id.button_login_with_activity);
        Button fragmentButton = findViewById(R.id.button_login_with_fragment);
        activityButton.setOnClickListener(this);
        fragmentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_login_with_activity:
                intent = new Intent(RootLoginActivity.this, SampleLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.button_login_with_fragment:
                intent = new Intent(RootLoginActivity.this, LoginFragmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
