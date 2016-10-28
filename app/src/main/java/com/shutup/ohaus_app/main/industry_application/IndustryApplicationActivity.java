package com.shutup.ohaus_app.main.industry_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.RecyclerTouchListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class IndustryApplicationActivity extends BaseActivity {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recyclerView_industry_application_menu)
    RecyclerView mRecyclerViewIndustryApplicationMenu;

    private ArrayList<IndustryApplicationMenuItem> mIndustryApplicationMenuItems;
    private IndustryApplicationMenuAdapter mIndustryApplicationMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_application);
        ButterKnife.inject(this);

        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mIndustryApplicationMenuItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mIndustryApplicationMenuItems.add(new IndustryApplicationMenuItem("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg", "title" + i, "content" + i));
        }
        mIndustryApplicationMenuAdapter = new IndustryApplicationMenuAdapter(this, mIndustryApplicationMenuItems);
        mRecyclerViewIndustryApplicationMenu.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewIndustryApplicationMenu.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerViewIndustryApplicationMenu.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerViewIndustryApplicationMenu, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                IndustryApplicationMenuItem industryApplicationMenuItem = mIndustryApplicationMenuItems.get(position);
                Intent intent = new Intent(IndustryApplicationActivity.this, IndustryApplicationSecondActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(industryApplicationMenuItem);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewIndustryApplicationMenu.setAdapter(mIndustryApplicationMenuAdapter);
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
        }
        // Title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.industry_application_str);
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
