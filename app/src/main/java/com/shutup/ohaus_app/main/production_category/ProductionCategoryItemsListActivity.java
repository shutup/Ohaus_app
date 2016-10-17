package com.shutup.ohaus_app.main.production_category;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.api.CategoryListEntity;
import com.shutup.ohaus_app.api.OhaosiService;
import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.api.RetrofitManager;
import com.shutup.ohaus_app.api.TianpingEntity;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.RecyclerTouchListener;
import com.shutup.ohaus_app.common.StringUtils;
import com.shutup.ohaus_app.model.ProductionCategoryMenuItem2;
import com.shutup.ohaus_app.model.ProductionNormalItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductionCategoryItemsListActivity extends BaseActivity implements Constants{

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recyclerView_items_list)
    RecyclerView mRecyclerViewItemsList;
    @InjectView(R.id.production_category_price_option)
    TextView mProductionCategoryPriceOption;
    @InjectView(R.id.production_category_filter_option)
    TextView mProductionCategoryFilterOption;
    @InjectView(R.id.production_category_filter_option_bg_view)
    LinearLayout productionCategoryFilterOptionBgView;
    @InjectView(R.id.production_category_filter_option_view)
    LinearLayout productionCategoryFilterOptionView;

    private ArrayList<ProductionNormalItem> mProductionNormalItems;
    private ProductionCategoryItemsListAdapter mProductionCategoryItemsListAdapter;
    private boolean isAsc = true;
    private boolean isFilterVisable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category_items_list);
        ButterKnife.inject(this);
        initToolBar();
        initRecyclerView();
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

    private void initRecyclerView() {
        mProductionNormalItems = new ArrayList<>();
        mProductionCategoryItemsListAdapter = new ProductionCategoryItemsListAdapter(this, mProductionNormalItems);
        mRecyclerViewItemsList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerViewItemsList.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewItemsList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerViewItemsList.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerViewItemsList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerViewItemsList.setAdapter(mProductionCategoryItemsListAdapter);
    }

    private void initToolBar() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
            setSupportActionBar(mToolbar);
        }
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

    @OnClick({R.id.production_category_price_option, R.id.production_category_filter_option, R.id.production_category_filter_option_bg_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.production_category_price_option:
                sortTheItems();
                break;
            case R.id.production_category_filter_option:
                updateFilterOptions();
                break;
            case R.id.production_category_filter_option_bg_view:
                updateFilterOptions();
                break;
        }
    }

    private void updateFilterOptions() {
        Animation animation = null;
        if (isFilterVisable) {
            animation = AnimationUtils.loadAnimation(this, R.anim.production_category_right_out);
            productionCategoryFilterOptionBgView.setAnimation(animation);
            productionCategoryFilterOptionView.setBackgroundColor(Color.WHITE);
            productionCategoryFilterOptionBgView.setVisibility(View.INVISIBLE);
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.production_category_right_in);
            productionCategoryFilterOptionBgView.setAnimation(animation);
            productionCategoryFilterOptionView.setBackgroundColor(Color.WHITE);
            productionCategoryFilterOptionBgView.setVisibility(View.VISIBLE);
        }
        isFilterVisable = !isFilterVisable;
    }

    private void sortTheItems() {
        Collections.sort(mProductionNormalItems, new Comparator<ProductionNormalItem>() {
            @Override
            public int compare(ProductionNormalItem lhs, ProductionNormalItem rhs) {
                if (isAsc) {
                    return Float.compare(rhs.getPrice(), lhs.getPrice());
                } else {
                    return Float.compare(lhs.getPrice(), rhs.getPrice());
                }
            }
        });
        isAsc = !isAsc;
        for (int i = 0; i < mProductionNormalItems.size(); i++) {
            ProductionNormalItem p = mProductionNormalItems.get(i);
        }
        mProductionCategoryItemsListAdapter.notifyDataSetChanged();
    }

    @Subscribe(sticky = true)
    public void onProductionCategoryMenuItem2Receive(ProductionCategoryMenuItem2 productionCategoryMenuItem2) {
        mToolbarTitle.setText(productionCategoryMenuItem2.getMenuTitle());
        if (BuildConfig.DEBUG)
            Log.d("ProductionCategoryItems", productionCategoryMenuItem2.getId());
        if (BuildConfig.DEBUG)
            Log.d("ProductionCategoryItems", productionCategoryMenuItem2.getPid());

        OhaosiService ohaosiService = RetrofitManager.getInstance().createReq(OhaosiService.class);
        Call<ResponseBody> test = ohaosiService.getProductionLists(productionCategoryMenuItem2.getPid(),productionCategoryMenuItem2.getId());
        test.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (BuildConfig.DEBUG)
                        Log.d("ProductionCategoryItems", "response:" + response.body().toString());
                    try {
                        String jsonStr = response.body().string();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        CategoryListEntity categoryListEntity = gson.fromJson(jsonStr,CategoryListEntity.class);
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONArray jsonArray = jsonObject.optJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject mainObject = jsonArray.optJSONObject(i);
                            int type  = StringUtils.getEntityNameByType(categoryListEntity.getData().get(i).getSubCategory());
                            JSONArray detailsObject = mainObject.optJSONArray("data");
                            if (type == TYPE_FXJMTP) {
                                ArrayList<Object> data = new ArrayList<Object>();
                                for (int j = 0; j < detailsObject.length(); j++) {
                                    TianpingEntity tianpingEntity = gson.fromJson(detailsObject.optJSONObject(i).toString(),TianpingEntity.class);
                                    data.add(tianpingEntity);
                                }
                            }
                        }
                        for (int i = 0; i < categoryListEntity.getData().size(); i++) {
                            ProductCategoryEntity productCategoryEntity = categoryListEntity.getData().get(i);
                            mProductionNormalItems.add(new ProductionNormalItem(productCategoryEntity.getNewImages().get(0).getUrl(),productCategoryEntity.getName(),"最低 ￥"+productCategoryEntity.getMinimumPrice(),productCategoryEntity.getMinimumPrice()));
                        }
                        refreshUI();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void refreshUI() {
        mProductionCategoryItemsListAdapter.notifyDataSetChanged();
    }


}
