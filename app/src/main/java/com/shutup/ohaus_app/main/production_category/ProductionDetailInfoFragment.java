package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.common.RecyclerTouchListener;
import com.shutup.ohaus_app.model.ProductionNormalItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by shutup on 2016/10/28.
 */

public class ProductionDetailInfoFragment extends Fragment {
    @InjectView(R.id.productionName)
    TextView mProductionName;
    @InjectView(R.id.productionPrice)
    TextView mProductionPrice;
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
    private ProductionCategoryFilterOptionAdapter mProductionCategoryFilterOptionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_info_layout, container, false);
        ButterKnife.inject(this, view);
        initFilterRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initFilterRecyclerView() {
        mFilterOptionModels = new ArrayList<>();
        mProductionCategoryFilterOptionAdapter = new ProductionCategoryFilterOptionAdapter(getContext(), mFilterOptionModels);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerViewFilterOptionsDetails.setLayoutManager(gridLayoutManager);
        mRecyclerViewFilterOptionsDetails.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewFilterOptionsDetails.addItemDecoration(new MarginDecoration(getContext()));
        mRecyclerViewFilterOptionsDetails.setAdapter(mProductionCategoryFilterOptionAdapter);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mProductionCategoryFilterOptionAdapter.isHeader(mFilterOptionModels.get(position).getType()) ? gridLayoutManager.getSpanCount(): 1;
            }
        });
        mRecyclerViewFilterOptionsDetails.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerViewFilterOptionsDetails, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!mProductionCategoryFilterOptionAdapter.isHeader(mFilterOptionModels.get(position).getType())) {
                    mFilterOptionModels.get(position).setSelected(!mFilterOptionModels.get(position).isSelected());
                    refreshFilterUI();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void refreshFilterUI() {
        mProductionCategoryFilterOptionAdapter.notifyDataSetChanged();
    }

    @Subscribe(sticky = true)
    public void onMSg(ProductionCategoryDetailModel productionCategoryDetailModel) {
        StringBuilder filterOptionStr = new StringBuilder();
        boolean isFirst = false;
        mFilterOptionModels.clear();
        for (int i = 0; i < productionCategoryDetailModel.getFilterOptionModels().size(); i++) {
            FilterOptionModel f = productionCategoryDetailModel.getFilterOptionModels().get(i);
            if (f.isSelected()) {
                if (!isFirst) {
                    filterOptionStr.append(f.getName());
                    isFirst = true;
                } else {
                    filterOptionStr.append("/" + f.getName());
                }
            }
            mFilterOptionModels.add(f);
        }
        refreshFilterUI();
        mProductionDetailNamePriceOptions.setText(filterOptionStr.toString());
        mProductCategoryEntity = productionCategoryDetailModel.getProductCategoryEntity();
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

    private void showOrDismissFilterOptions() {
        Animation animation = null;
        if (isFilterVisable) {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.production_category_down_out);
            mProductionCategoryFilterOptionLayout.setAnimation(animation);
            mProductionCategoryFilterOptionLayout.setVisibility(View.INVISIBLE);
        } else {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.production_category_up_in);
            mProductionCategoryFilterOptionLayout.setAnimation(animation);
            mProductionCategoryFilterOptionLayout.setVisibility(View.VISIBLE);
        }
        isFilterVisable = !isFilterVisable;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
