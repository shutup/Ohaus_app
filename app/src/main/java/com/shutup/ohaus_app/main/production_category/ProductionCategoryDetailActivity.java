package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProductionCategoryDetailActivity extends BaseActivity {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.production_detail_view_pager)
    ViewPager mProductionDetailViewPager;
    @InjectView(R.id.tabLayout)
    TabLayout mTabLayout;

    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFragments;
    private List<String> mFragmentTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category_detail);
        ButterKnife.inject(this);
        initToolbar();
        initFragments();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mProductionDetailViewPager);
    }

    private void initFragments() {
        mFragmentTitles = new ArrayList<>();
        mFragmentTitles.add("商品");
        mFragmentTitles.add("详情");

        mFragments = new ArrayList<>();
        mFragments.add(new ProductionDetailInfoFragment());
        mFragments.add(new ProductionDetailDetailFragment());
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentTitles.get(position);
            }
        };
        mProductionDetailViewPager.setAdapter(mFragmentPagerAdapter);
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
}
