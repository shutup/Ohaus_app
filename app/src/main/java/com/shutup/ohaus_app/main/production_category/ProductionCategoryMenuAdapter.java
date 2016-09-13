package com.shutup.ohaus_app.main.production_category;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenuAdapter extends RecyclerView.Adapter<ProductionCategoryMenuAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<ProductionCategoryMenuItem> mProductionCategoryMenuItems;

    public ProductionCategoryMenuAdapter(Context context, ArrayList<ProductionCategoryMenuItem> productionCategoryMenuItems) {
        mContext = context;
        mProductionCategoryMenuItems = productionCategoryMenuItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_menu_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductionCategoryMenuItem productionCategoryMenuItem = mProductionCategoryMenuItems.get(position);
        holder.mRecyclerViewMenuTitle.setText(productionCategoryMenuItem.getMenuTitle());
        if (BuildConfig.DEBUG)
            Log.d("ProductionCategoryMenuA", "productionCategoryMenuItem.isSelected():" + productionCategoryMenuItem.isSelected());
        if (productionCategoryMenuItem.isSelected()) {
            holder.mRecyclerViewMenuIdentifyView.setBackgroundColor(mContext.getResources().getColor(R.color.recyclerView_menu_identify_view_select_Color));
            holder.mRecyclerViewMenuTitle.setBackgroundColor(mContext.getResources().getColor(R.color.recyclerView_menu_text_view_select_Color));
        }else {
            holder.mRecyclerViewMenuIdentifyView.setBackgroundColor(mContext.getResources().getColor(R.color.recyclerView_menu_identify_view_normal_Color));
            holder.mRecyclerViewMenuTitle.setBackgroundColor(mContext.getResources().getColor(R.color.recyclerView_menu_text_view_normal_Color));
        }
    }

    @Override
    public int getItemCount() {
        return mProductionCategoryMenuItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.recyclerView_menu_identify_view)
        View mRecyclerViewMenuIdentifyView;
        @InjectView(R.id.recyclerView_menu_title)
        TextView mRecyclerViewMenuTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
