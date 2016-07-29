package com.shutup.ohaus_app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.shutup.ohaus_app.main.MainActivity;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initToolbar();
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
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
