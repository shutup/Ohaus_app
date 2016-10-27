package com.shutup.ohaus_app.main.production_category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shutup.ohaus_app.R;

import java.util.ArrayList;

/**
 * Created by shutup on 2016/10/22.
 */

public class ProductionCategoryFilterOptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static int ITEM_TYPE_HEADER = 1;
    public static int ITEM_TYPE_NORMAL = 2;

    private Context mContext;

    public void setFilterOptionModels(ArrayList<FilterOptionModel> filterOptionModels) {
        mFilterOptionModels = filterOptionModels;
    }

    private ArrayList<FilterOptionModel> mFilterOptionModels;

    public ProductionCategoryFilterOptionAdapter(Context context, ArrayList<FilterOptionModel> filterOptionModels) {
        mContext = context;
        mFilterOptionModels = filterOptionModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isHeader(viewType)) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.production_category_filter_option_header_layout, parent, false);
            return new ProductionCategoryFilterOptionHeaderViewHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.production_category_filter_option_item_layout, parent, false);
            return new ProductionCategoryFilterOptionItemVIewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(getItemViewType(position))) {
            ((ProductionCategoryFilterOptionHeaderViewHolder)holder).mTextView.setText(mFilterOptionModels.get(position).getName());
        }else {
                if (mFilterOptionModels.get(position).isSelected()) {
                    ((ProductionCategoryFilterOptionItemVIewHolder)holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                    ((ProductionCategoryFilterOptionItemVIewHolder)holder).mTextView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.filter_option_item_bg_selected));
                }else {

                    ((ProductionCategoryFilterOptionItemVIewHolder)holder).mTextView.setTextColor(mContext.getResources().getColor(R.color.common_text_color));
                    ((ProductionCategoryFilterOptionItemVIewHolder)holder).mTextView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.filter_option_item_bg_normal));
                }
                ((ProductionCategoryFilterOptionItemVIewHolder)holder).mTextView.setText(mFilterOptionModels.get(position).getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mFilterOptionModels.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mFilterOptionModels.size();
    }

    public boolean isHeader(int type) {
        return type == ITEM_TYPE_HEADER;
    }
}
