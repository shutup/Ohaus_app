package com.shutup.ohaus_app.main.production_category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.GridSpacingItemDecoration;
import com.shutup.ohaus_app.common.ItemOffsetDecoration;
import com.shutup.ohaus_app.common.RecyclerTouchListener;
import com.shutup.ohaus_app.main.goods_recommend.GoodsRecommendDetailActivity;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductionCategoryActivity extends BaseActivity {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recyclerView_menu)
    RecyclerView mRecyclerViewMenu;
    @InjectView(R.id.recyclerView_items)
    RecyclerView mRecyclerViewItems;

    private ArrayList<ProductionCategoryMenuItem> mProductionCategoryMenuItems;
    private ProductionCategoryMenuAdapter mProductionCategoryMenuAdapter;

    private ArrayList<ProductionCategoryMenuItem2> mProductionCategoryMenuItem2s;
    private ProductionCategoryMenu2Adapter mProductionCategoryMenu2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category);
        ButterKnife.inject(this);
        initToolbar();
        initRecyclerViewMenu();
        initRecyclerViewMenu2();
    }

    private void initRecyclerViewMenu2() {
        mProductionCategoryMenuItem2s = new ArrayList<>();
        mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg","title1"));
        mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2("http://v1.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg","title2"));
        mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2("http://v1.qzone.ccm/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg","title3"));
        mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg","title4"));
        mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(this, mProductionCategoryMenuItem2s);
        mRecyclerViewItems.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        mRecyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.goods_recommend_item_padding);
        mRecyclerViewItems.addItemDecoration(new GridSpacingItemDecoration(2,spacingInPixels,true));
//        mRecyclerViewItems.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        mRecyclerViewItems.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewItems, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), GoodsRecommendDetailActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewItems.setAdapter(mProductionCategoryMenu2Adapter);
    }

    private void initRecyclerViewMenu() {
        mProductionCategoryMenuItems = new ArrayList<>();
        mProductionCategoryMenuItems.add(new ProductionCategoryMenuItem("1",true));
        mProductionCategoryMenuItems.add(new ProductionCategoryMenuItem("2"));
        mProductionCategoryMenuItems.add(new ProductionCategoryMenuItem("3"));

        mProductionCategoryMenuAdapter = new ProductionCategoryMenuAdapter(this,mProductionCategoryMenuItems);
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMenu.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerViewMenu.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewMenu, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                updateSelected(position);

//                Intent intent = new Intent(getApplicationContext(), QuickNewsDetailActivity.class);
//                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewMenu.setAdapter(mProductionCategoryMenuAdapter);
    }

    private void updateSelected(int position) {
        for (ProductionCategoryMenuItem p : mProductionCategoryMenuItems) {
            p.setSelected(false);
        }
        ProductionCategoryMenuItem productionCategoryMenuItem = mProductionCategoryMenuItems.get(position);
        productionCategoryMenuItem.setSelected(true);
        mProductionCategoryMenuAdapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
        }
        // Title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.production_category_str);
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
