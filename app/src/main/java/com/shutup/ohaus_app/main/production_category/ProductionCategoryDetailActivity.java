package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.model.ProductionNormalItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
}
