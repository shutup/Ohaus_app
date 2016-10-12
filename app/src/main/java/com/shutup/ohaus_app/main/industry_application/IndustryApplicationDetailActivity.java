package com.shutup.ohaus_app.main.industry_application;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class IndustryApplicationDetailActivity extends BaseActivity {

    @InjectView(R.id.webView)
    WebView mWebView;
    @InjectView(R.id.homeImageView)
    ImageView mHomeImageView;
    @InjectView(R.id.favoriteImageView)
    ImageView mFavoriteImageView;
    @InjectView(R.id.shareImageView)
    ImageView mShareImageView;
    @InjectView(R.id.downloadImageView)
    ImageView mDownloadImageView;
    @InjectView(R.id.lastImageView)
    ImageView mLastImageView;
    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_application_detail);
        ButterKnife.inject(this);

        initToolbar();
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
        }
        // Title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.homeImageView, R.id.favoriteImageView, R.id.shareImageView, R.id.downloadImageView, R.id.lastImageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homeImageView:
                break;
            case R.id.favoriteImageView:
                break;
            case R.id.shareImageView:
                break;
            case R.id.downloadImageView:
                break;
            case R.id.lastImageView:
                break;
        }
    }
}
