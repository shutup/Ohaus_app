package com.shutup.ohaus_app.main.industry_application;

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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class IndustryApplicationActivity extends BaseActivity {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recyclerView_menu)
    RecyclerView mRecyclerViewMenu;

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
        mRecyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMenu.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerViewMenu.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerViewMenu, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewMenu.setAdapter(mIndustryApplicationMenuAdapter);
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
