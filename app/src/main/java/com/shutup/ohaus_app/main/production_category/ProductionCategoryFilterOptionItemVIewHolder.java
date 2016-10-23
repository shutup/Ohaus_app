package com.shutup.ohaus_app.main.production_category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shutup.ohaus_app.R;

/**
 * Created by shutup on 2016/10/22.
 */

public class ProductionCategoryFilterOptionItemVIewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public ProductionCategoryFilterOptionItemVIewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.production_category_filter_option_view_item_name);
    }
}
