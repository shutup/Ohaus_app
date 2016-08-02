package com.shutup.ohaus_app.drawer_menu_activity.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MeActivity extends BaseActivity implements MeConstatnts {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.me_info_photo)
    ImageView mMeInfoPhoto;
    @InjectView(R.id.me_info_photo_layout)
    RelativeLayout mMeInfoPhotoLayout;
    @InjectView(R.id.me_info_name)
    TextView mMeInfoName;
    @InjectView(R.id.me_info_name_layout)
    RelativeLayout mMeInfoNameLayout;
    @InjectView(R.id.me_info_phone)
    TextView mMeInfoPhone;
    @InjectView(R.id.me_info_phone_layout)
    RelativeLayout mMeInfoPhoneLayout;
    @InjectView(R.id.me_info_mail)
    TextView mMeInfoMail;
    @InjectView(R.id.me_info_mail_layout)
    RelativeLayout mMeInfoMailLayout;
    @InjectView(R.id.me_info_job)
    TextView mMeInfoJob;
    @InjectView(R.id.me_info_job_layout)
    RelativeLayout mMeInfoJobLayout;
    @InjectView(R.id.me_info_company)
    TextView mMeInfoCompany;
    @InjectView(R.id.me_info_company_layout)
    RelativeLayout mMeInfoCompanyLayout;
    @InjectView(R.id.me_info_province)
    TextView mMeInfoProvince;
    @InjectView(R.id.me_info_province_layout)
    RelativeLayout mMeInfoProvinceLayout;
    @InjectView(R.id.me_info_city)
    TextView mMeInfoCity;
    @InjectView(R.id.me_info_city_layout)
    RelativeLayout mMeInfoCityLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.inject(this);
        initToolBar();

    }


    private void initToolBar() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.menu_me_title);
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

    @OnClick({R.id.me_info_photo_layout, R.id.me_info_name_layout, R.id.me_info_phone_layout, R.id.me_info_mail_layout, R.id.me_info_job_layout, R.id.me_info_company_layout, R.id.me_info_province_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.me_info_photo_layout:
            if (BuildConfig.DEBUG) Log.d("MeActivity", "clicked");
                break;
            case R.id.me_info_name_layout:
                break;
            case R.id.me_info_phone_layout:
                break;
            case R.id.me_info_mail_layout:
                break;
            case R.id.me_info_job_layout:
                break;
            case R.id.me_info_company_layout:
                break;
            case R.id.me_info_province_layout:
                break;
        }
    }
}
