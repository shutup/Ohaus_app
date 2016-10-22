package com.shutup.ohaus_app.main.production_category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

import com.shutup.ohaus_app.R;

/**
 * Created by shutup on 2016/10/22.
 */

public class ProductionCategoryFilterOptionItemVIewHolder extends RecyclerView.ViewHolder {
    public Button mCheckedTextView;
    public ProductionCategoryFilterOptionItemVIewHolder(View itemView) {
        super(itemView);
        mCheckedTextView = (Button) itemView.findViewById(R.id.production_category_filter_option_view_item_name);
    }
}
