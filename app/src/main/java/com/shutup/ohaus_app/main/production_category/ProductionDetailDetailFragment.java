package com.shutup.ohaus_app.main.production_category;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shutup.ohaus_app.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by shutup on 2016/10/28.
 */

public class ProductionDetailDetailFragment extends Fragment {

    @InjectView(R.id.production_detail_layout_view_pager)
    ViewPager mProductionDetailLayoutViewPager;
    @InjectView(R.id.production_detail_layout_left_title)
    TextView mProductionDetailLayoutLeftTitle;
    @InjectView(R.id.production_detail_layout_right_title)
    TextView mProductionDetailLayoutRightTitle;
    private List<Fragment> mFragments;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_detail_layout, container, false);
        ButterKnife.inject(this, view);
        initViewPager();
        changeTitle(true);
        return view;
    }

    private void changeTitle(boolean isLeft) {
        if (isLeft) {
            mProductionDetailLayoutLeftTitle.setTextColor(Color.parseColor("#ed3058"));
            mProductionDetailLayoutRightTitle.setTextColor(Color.parseColor("#4a4a4a"));
        }else {
            mProductionDetailLayoutLeftTitle.setTextColor(Color.parseColor("#4a4a4a"));
            mProductionDetailLayoutRightTitle.setTextColor(Color.parseColor("#ed3058"));
        }
    }

    private void initViewPager() {
        mFragments = new ArrayList<>();
        mFragments.add(new ProductionDetailIntroduceFragment());
        mFragments.add(new ProductionDetailspecificationsFragment());
        mFragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mProductionDetailLayoutViewPager.setAdapter(mFragmentPagerAdapter);

        mProductionDetailLayoutViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitle( position == 0 );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.production_detail_layout_left_title, R.id.production_detail_layout_right_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.production_detail_layout_left_title:
                changeTitle(true);
                mProductionDetailLayoutViewPager.setCurrentItem(0,true);
                break;
            case R.id.production_detail_layout_right_title:
                changeTitle(false);
                mProductionDetailLayoutViewPager.setCurrentItem(1,true);
                break;
        }
    }
}
