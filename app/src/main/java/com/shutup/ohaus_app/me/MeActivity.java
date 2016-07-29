package com.shutup.ohaus_app.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MeActivity extends BaseActivity implements MeConstatnts{

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.userInfoListView)
    ListView mUserInfoListView;

    private ArrayList<MeInfoItem> mMeInfoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.inject(this);
        initToolBar();
        initData();
    }

    private void initData() {
        mMeInfoItems = new ArrayList<>();
        mMeInfoItems.add(new MeInfoItem("姓名","", TYPE_1));
        mMeInfoItems.add(new MeInfoItem("手机","", TYPE_1));
        mMeInfoItems.add(new MeInfoItem("邮箱","", TYPE_1));
        mMeInfoItems.add(new MeInfoItem("职务","", TYPE_1));
        mMeInfoItems.add(new MeInfoItem("公司","", TYPE_1));
        mMeInfoItems.add(new MeInfoItem("省份","", TYPE_2));
        mMeInfoItems.add(new MeInfoItem("城市","", TYPE_2));

        mUserInfoListView.setAdapter(new MeInfoListAdapter(mMeInfoItems,this));

    }

    private void initToolBar() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null){
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
}
