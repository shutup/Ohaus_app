package com.shutup.ohaus_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.login_btn)
    Button mLoginBtn;
    @InjectView(R.id.login_input_phone)
    EditText mLoginInputPhone;
    @InjectView(R.id.login_input_pwd)
    EditText mLoginInputPwd;

    private boolean isPhoneOk;
    private boolean isPwdOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initToolbar();
//        mLoginInputPhone.getBackground().mutate().setColorFilter(getResources().getColor(R.color.me_info_list_divider_color), PorterDuff.Mode.SRC_ATOP);
        initEvent();
    }

    private void initEvent() {
        mLoginInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    isPhoneOk = true;
                } else {
                    isPhoneOk = false;
                }
                setLoginBtnStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLoginInputPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6) {
                    isPwdOk = true;
                } else {
                    isPwdOk = false;
                }
                setLoginBtnStatus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setLoginBtnStatus() {
        if (isPhoneOk && isPwdOk) {
            mLoginBtn.setBackgroundColor(getResources().getColor(R.color.loginBtnValid));
            mLoginBtn.setEnabled(true);
        } else {
            mLoginBtn.setBackgroundColor(getResources().getColor(R.color.loginBtnNormal));
            mLoginBtn.setEnabled(false);
        }
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            // Title
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.login_title);
        }
    }


    @OnClick(R.id.login_btn)
    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
