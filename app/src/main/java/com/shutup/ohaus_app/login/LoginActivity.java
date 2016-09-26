package com.shutup.ohaus_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.api.OhaosiService;
import com.shutup.ohaus_app.api.RetrofitManager;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.SharedPreferenceUtils;
import com.shutup.ohaus_app.main.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity implements Constants{

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
    @InjectView(R.id.login_btn)
    Button loginBtn;
    @InjectView(R.id.newuser)
    TextView newuser;

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

    @OnClick({R.id.login_btn, R.id.newuser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.newuser:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    private void doLogin() {
        OhaosiService ohaosiService = RetrofitManager.getInstance().createReq(OhaosiService.class);
        Call<ResponseBody> userLogin = ohaosiService.userLogin(mLoginInputPhone.getText().toString(), mLoginInputPwd.getText().toString());
        userLogin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (BuildConfig.DEBUG) Log.d("LoginActivity", "response:" + response);
                SharedPreferenceUtils.getEditerInstance(LoginActivity.this).putBoolean(IS_LOGIN,true);
                SharedPreferenceUtils.getEditerInstance(LoginActivity.this).commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (BuildConfig.DEBUG) Log.d("LoginActivity", "t:" + t);
            }
        });
    }
}
