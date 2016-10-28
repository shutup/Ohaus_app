package com.shutup.ohaus_app.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.SharedPreferenceUtils;
import com.shutup.ohaus_app.main.MainActivity;

public class LaunchActivity extends BaseActivity implements Constants {

    private Handler mHandler;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mSharedPreferences = SharedPreferenceUtils.getInstance(LaunchActivity.this);

        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                jumpToTarget(msg);
                return false;
            }
        });

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPreferences.getBoolean(IS_LOGIN,false)){
                    mHandler.sendEmptyMessage(IS_LOGIN_OK);
                }else {
                    mHandler.sendEmptyMessage(IS_LOGIN_FAIL);
                }
            }
        },3000);
    }

    private void jumpToTarget(Message msg) {
        Intent intent = null;
        if (msg.what == IS_LOGIN_FAIL) {
            intent = new Intent(this, LoginActivity.class);
        }else if (msg.what == IS_LOGIN_OK) {
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
