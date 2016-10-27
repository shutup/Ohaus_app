package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.model.ProductionNormalItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProductionCategoryDetailActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.productionName)
    TextView mProductionName;
    @InjectView(R.id.productionPrice)
    TextView mProductionPrice;
    @InjectView(R.id.production_category_detail_toolbar_line_left)
    View mProductionCategoryDetailToolbarLineLeft;
    @InjectView(R.id.production_category_detail_toolbar_line_right)
    View mProductionCategoryDetailToolbarLineRight;
    @InjectView(R.id.production_category_filter_option_view_price_label)
    TextView mProductionCategoryFilterOptionViewPriceLabel;
    @InjectView(R.id.production_category_filter_option_view_model_label)
    TextView mProductionCategoryFilterOptionViewModelLabel;
    @InjectView(R.id.production_category_filter_option_view_order_label)
    TextView mProductionCategoryFilterOptionViewOrderLabel;
    @InjectView(R.id.production_category_filter_option_top_bar)
    RelativeLayout mProductionCategoryFilterOptionTopBar;
    @InjectView(R.id.recyclerViewFilterOptionsDetails)
    RecyclerView mRecyclerViewFilterOptionsDetails;
    @InjectView(R.id.production_category_filter_option_view_item_icon)
    ImageView mProductionCategoryFilterOptionViewItemIcon;
    @InjectView(R.id.production_detail_name_price_title)
    TextView mProductionDetailNamePriceTitle;
    @InjectView(R.id.production_detail_name_price_options)
    Button mProductionDetailNamePriceOptions;
    @InjectView(R.id.production_detail_name_price)
    RelativeLayout mProductionDetailNamePrice;
    @InjectView(R.id.production_category_filter_option_view_btn_ok)
    Button mProductionCategoryFilterOptionViewBtnOk;
    @InjectView(R.id.production_category_filter_option_layout)
    LinearLayout mProductionCategoryFilterOptionLayout;
    @InjectView(R.id.production_category_filter_option_layout_bg)
    RelativeLayout mProductionCategoryFilterOptionLayoutBg;

    private ProductCategoryEntity mProductCategoryEntity;
    private ArrayList<FilterOptionModel> mFilterOptionModels;
    private ProductionNormalItem mProductionNormalItem;
    private boolean isFilterVisable = false;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_category_detail);
        ButterKnife.inject(this);
        initToolbar();
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

    @Subscribe(sticky = true)
    public void onMSg(ProductionCategoryDetailModel productionCategoryDetailModel) {
        EventBus.getDefault().removeStickyEvent(productionCategoryDetailModel);
        if (productionCategoryDetailModel.getProductionNormalItem().getType() == ProductionNormalItem.TYPE_ProductionSecondCategory) {
            mProductionName.setText(productionCategoryDetailModel.getProductionNormalItem().getTitleStr());
            mProductionPrice.setText(productionCategoryDetailModel.getProductionNormalItem().getContentStr());
        } else {
            mProductionName.setText("");
            mProductionPrice.setText("");
        }
    }

    @OnClick({R.id.production_detail_name_price, R.id.production_category_filter_option_view_btn_ok, R.id.production_category_filter_option_layout, R.id.production_category_filter_option_layout_bg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.production_detail_name_price:
                showOrDismissFilterOptions();
                break;
            case R.id.production_category_filter_option_view_btn_ok:
                break;
            case R.id.production_category_filter_option_layout:
                showOrDismissFilterOptions();
                break;
            case R.id.production_category_filter_option_layout_bg:
                //eat the touch event
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (isFilterVisable) {
            showOrDismissFilterOptions();
        }
    }

    private void showOrDismissFilterOptions() {
        Animation animation = null;
        if (isFilterVisable) {
            animation = AnimationUtils.loadAnimation(this, R.anim.production_category_down_out);
            mProductionCategoryFilterOptionLayout.setAnimation(animation);
            mProductionCategoryFilterOptionLayout.setVisibility(View.INVISIBLE);
        } else {
            animation = AnimationUtils.loadAnimation(this, R.anim.production_category_up_in);
            mProductionCategoryFilterOptionLayout.setAnimation(animation);
            mProductionCategoryFilterOptionLayout.setVisibility(View.VISIBLE);
        }
        isFilterVisable = !isFilterVisable;
    }
}
