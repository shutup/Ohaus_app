package com.shutup.ohaus_app.main.industry_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.RecyclerTouchListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class IndustryApplicationSecondActivity extends BaseActivity implements Constants{

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.industry_application_second_menu_recyclerView)
    RecyclerView mIndustryApplicationSecondMenuRecyclerView;

    private ArrayList<IndustryApplicationMenuItem> mIndustryApplicationMenuItems;
    private IndustryApplicationSecondMenuAdapter mIndustryApplicationSecondMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_application_second);
        ButterKnife.inject(this);
        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mIndustryApplicationMenuItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mIndustryApplicationMenuItems.add(new IndustryApplicationMenuItem("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg", "title" + i, "content" + i));
        }
        mIndustryApplicationSecondMenuAdapter = new IndustryApplicationSecondMenuAdapter(this, mIndustryApplicationMenuItems);
        mIndustryApplicationSecondMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mIndustryApplicationSecondMenuRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mIndustryApplicationSecondMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mIndustryApplicationSecondMenuRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mIndustryApplicationSecondMenuRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mIndustryApplicationSecondMenuRecyclerView.setAdapter(mIndustryApplicationSecondMenuAdapter);
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
        }
        // Title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            Intent intent = getIntent();
            mToolbarTitle.setText(intent.getStringExtra(INTENT_MENU_TITLE));
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
