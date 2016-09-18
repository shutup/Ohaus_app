package com.shutup.ohaus_app.main.production_category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/9/18.
 */
public class ProductionCategoryItemsListAdapter extends RecyclerView.Adapter<ProductionCategoryItemsListAdapter.ViewHolder> {

    private ArrayList<ProductionNormalItem> mProductionNormalItems;
    private Context mContext;

    public ProductionCategoryItemsListAdapter(Context context,ArrayList<ProductionNormalItem> productionNormalItems) {
        mProductionNormalItems = productionNormalItems;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductionNormalItem productionNormalItem = mProductionNormalItems.get(position);
        holder.mContent.setText(productionNormalItem.getContentStr());
        holder.mTitle.setText(productionNormalItem.getTitleStr());
        Picasso.with(mContext).load(productionNormalItem.getIconUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mIcon);

    }

    @Override
    public int getItemCount() {
        return mProductionNormalItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.icon)
        ImageView mIcon;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.content)
        TextView mContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
