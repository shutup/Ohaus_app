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
import com.shutup.ohaus_app.api.CategoryEntity;
import com.shutup.ohaus_app.api.SubsEntity;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.GridSpacingItemDecoration;
import com.shutup.ohaus_app.common.RecyclerTouchListener;
import com.shutup.ohaus_app.model.ProductionCategoryMenuItem;
import com.shutup.ohaus_app.model.ProductionCategoryMenuItem2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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

    private ArrayList<ArrayList<ProductionCategoryMenuItem2>> mArrayLists;
    private boolean isFirstIn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category);
        ButterKnife.inject(this);
        initToolbar();

        initRecyclerViewMenu();
        initRecyclerViewMenu2();

        loadDataFromLocal();
    }

    private void loadDataFromLocal() {
        RealmQuery<CategoryEntity> categoryEntityRealmQuery = RealmQuery.createQuery(Realm.getDefaultInstance(),CategoryEntity.class);
        RealmResults<CategoryEntity> categoryEntities= categoryEntityRealmQuery.findAllAsync();
        categoryEntities.addChangeListener(new RealmChangeListener<RealmResults<CategoryEntity>>() {
            @Override
            public void onChange(RealmResults<CategoryEntity> categoryEntities) {
                updateView(categoryEntities);
            }
        });
    }

    private void updateView(List<CategoryEntity> categoryEntities) {
        CategoryEntity categoryEntity;
        List<SubsEntity> subsEntityList;
        SubsEntity subsEntity;
        ProductionCategoryMenuItem productionCategoryMenuItem;
        mArrayLists.clear();
        mProductionCategoryMenuItems.clear();
        for (int j = 0; j < categoryEntities.size(); j++) {
            categoryEntity = categoryEntities.get(j);
            if (j == 0) {
                productionCategoryMenuItem = new ProductionCategoryMenuItem(categoryEntity.get_id(),categoryEntity.getName(), true);
            } else {
                productionCategoryMenuItem = new ProductionCategoryMenuItem(categoryEntity.get_id(),categoryEntity.getName());
            }
            subsEntityList = categoryEntity.getSubs();
            mProductionCategoryMenuItem2s = new ArrayList<>();
            for (int i = 0; i < subsEntityList.size(); i++) {
                subsEntity = subsEntityList.get(i);
                mProductionCategoryMenuItem2s.add(new ProductionCategoryMenuItem2(categoryEntity.get_id(),subsEntity.get_id(),"http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg", subsEntity.getName()));
            }
            mArrayLists.add(mProductionCategoryMenuItem2s);
            mProductionCategoryMenuItems.add(productionCategoryMenuItem);
        }
        if (isFirstIn){
            mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(ProductionCategoryActivity.this, mArrayLists.get(0));
            mRecyclerViewItems.setAdapter(mProductionCategoryMenu2Adapter);
            isFirstIn = false;
        }
        mProductionCategoryMenuAdapter.notifyDataSetChanged();
        mProductionCategoryMenu2Adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void initRecyclerViewMenu2() {
        mArrayLists = new ArrayList<>();
        mProductionCategoryMenuItem2s = new ArrayList<>();

        mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(this, mArrayLists.size()>0?mArrayLists.get(0):new ArrayList<ProductionCategoryMenuItem2>());
        mRecyclerViewItems.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mRecyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.goods_recommend_item_padding);
        mRecyclerViewItems.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));
//        mRecyclerViewItems.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        mRecyclerViewItems.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewItems, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProductionCategoryMenuItem2 productionCategoryMenuItem2 = mProductionCategoryMenu2Adapter.getProductionCategoryMenuItem2s().get(position);
                Intent intent = new Intent(getApplicationContext(), ProductionCategoryItemsListActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(productionCategoryMenuItem2);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewItems.setAdapter(mProductionCategoryMenu2Adapter);
    }

    private void initRecyclerViewMenu() {
        mProductionCategoryMenuItems = new ArrayList<>();
        mProductionCategoryMenuAdapter = new ProductionCategoryMenuAdapter(this, mProductionCategoryMenuItems);
        mRecyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewMenu.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerViewMenu.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerViewMenu, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                updateSelected(position);
                mProductionCategoryMenu2Adapter = new ProductionCategoryMenu2Adapter(ProductionCategoryActivity.this, mArrayLists.get(position));
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

    @Subscribe(sticky = true)
    public void onMSg(List<CategoryEntity> categoryEntities) {
        loadDataFromNetWork(categoryEntities);
        EventBus.getDefault().removeStickyEvent(categoryEntities);
    }

    private void loadDataFromNetWork(List<CategoryEntity> categoryEntities) {
        updateView(categoryEntities);
    }
}
