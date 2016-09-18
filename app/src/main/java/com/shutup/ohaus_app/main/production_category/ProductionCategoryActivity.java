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
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.GridSpacingItemDecoration;
import com.shutup.ohaus_app.common.RecyclerTouchListener;
import com.shutup.ohaus_app.model.ProductionCategoryMenuItem;
import com.shutup.ohaus_app.model.ProductionCategoryMenuItem2;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProductionCategoryActivity extends BaseActivity implements Constants{

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

    private ArrayList<ArrayList<ProductionCategoryMenuItem2>> mArrayLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category);
        ButterKnife.inject(this);
        initToolbar();
        initData();
        initRecyclerViewMenu();
        initRecyclerViewMenu2();
    }

    private void initData() {
        mArrayLists = new ArrayList<>();
        mProductionCategoryMenuItem2s = new ArrayList<>();
        mProductionCategoryMenuItems = new ArrayList<>();
        ProductionCategoryMenuItem productionCategoryMenuItem ;

        for (int j = 0; j < 5; j++) {
            if (j==0) {
                productionCategoryMenuItem = new ProductionCategoryMenuItem(""+j,true);
            }else {
                productionCategoryMenuItem = new ProductionCategoryMenuItem(""+j);
            }
            for (int i = 0; i < 4; i++) {
                mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg","title"+i));
            }
            mArrayLists.add(mProductionCategoryMenuItem2s);
            mProductionCategoryMenuItems.add(productionCategoryMenuItem);
        }
    }

    private void initRecyclerViewMenu2() {
        mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(this, mArrayLists.get(0));
        mRecyclerViewItems.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        mRecyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.goods_recommend_item_padding);
        mRecyclerViewItems.addItemDecoration(new GridSpacingItemDecoration(2,spacingInPixels,true));
//        mRecyclerViewItems.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        mRecyclerViewItems.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewItems, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProductionCategoryMenuItem2 productionCategoryMenuItem2 = mProductionCategoryMenu2Adapter.getProductionCategoryMenuItem2s().get(position);
                Intent intent = new Intent(getApplicationContext(), ProductionCategoryItemsListActivity.class);
                intent.putExtra(INTENT_MENU_TITLE,productionCategoryMenuItem2.getMenuTitle());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewItems.setAdapter(mProductionCategoryMenu2Adapter);
    }

    private void initRecyclerViewMenu() {

        mProductionCategoryMenuAdapter = new ProductionCategoryMenuAdapter(this,mProductionCategoryMenuItems);
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMenu.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerViewMenu.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewMenu, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                updateSelected(position);
                mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(ProductionCategoryActivity.this,mArrayLists.get(position));
                mRecyclerViewItems.setAdapter(mProductionCategoryMenu2Adapter);
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
