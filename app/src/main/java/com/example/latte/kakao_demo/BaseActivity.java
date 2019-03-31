package com.example.latte.kakao_demo;

import android.app.Activity;
import android.content.Intent;

import com.example.latte.kakao_demo.widget.WaitingDialog;

/**
 * Created by latte on 2019. 1. 14..
 */

public abstract class BaseActivity extends Activity{
    protected void showWaitingDialog() {
        WaitingDialog.showWaitingDialog(this);
    }

    protected void cancelWaitingDialog() {
        WaitingDialog.cancelWaitingDialog();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, RootLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, SampleSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
