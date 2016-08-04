package com.shutup.ohaus_app.drawer_menu_activity.invite;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InviteActivity extends BaseActivity {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.invite_name_title)
    TextView inviteNameTitle;
    @InjectView(R.id.invite_name_value)
    EditText inviteNameValue;
    @InjectView(R.id.invite_phone_title)
    TextView invitePhoneTitle;
    @InjectView(R.id.invite_phone_value)
    EditText invitePhoneValue;
    @InjectView(R.id.user_image_back_arrow)
    ImageView userImageBackArrow;
    @InjectView(R.id.invite_info_province)
    TextView inviteInfoProvince;
    @InjectView(R.id.invite_info_city)
    TextView inviteInfoCity;
    @InjectView(R.id.invite_info_province_title)
    TextView inviteInfoProvinceTitle;
    @InjectView(R.id.invite_info_city_title)
    TextView inviteInfoCityTitle;
    @InjectView(R.id.invite_company_title)
    TextView inviteCompanyTitle;
    @InjectView(R.id.invite_company_value)
    EditText inviteCompanyValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ButterKnife.inject(this);
        initToolBar();
        initTitle();
    }

    private void initTitle() {
        inviteNameTitle.setText(Html.fromHtml(getString(R.string.invite_info_name_title)));
        invitePhoneTitle.setText(Html.fromHtml(getString(R.string.invite_info_phone_title)));
        inviteInfoCityTitle.setText(Html.fromHtml(getString(R.string.invite_city_title)));
        inviteInfoProvinceTitle.setText(Html.fromHtml(getString(R.string.invite_province_title)));
        inviteCompanyTitle.setText(Html.fromHtml(getString(R.string.invite_company_title)));
    }

    private void initToolBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.menu_invite_title);
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
}
