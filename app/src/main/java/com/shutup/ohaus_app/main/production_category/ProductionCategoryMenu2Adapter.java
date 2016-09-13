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
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenu2Adapter extends RecyclerView.Adapter<ProductionCategoryMenu2Adapter.ViewHolder> {

    private ArrayList<ProductionCategoryMenuItem2> mProductionCategoryMenuItem2s;
    private Context mContext;

    public ProductionCategoryMenu2Adapter(Context context,ArrayList<ProductionCategoryMenuItem2> productionCategoryMenuItem2s) {
        mProductionCategoryMenuItem2s = productionCategoryMenuItem2s;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_menu2_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductionCategoryMenuItem2 productionCategoryMenuItem2 = mProductionCategoryMenuItem2s.get(position);
        Picasso.with(mContext).load(productionCategoryMenuItem2.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mRecyclerViewMenuImageView);
        holder.mRecyclerViewMenuTextView.setText(productionCategoryMenuItem2.getMenuTitle());
    }

    @Override
    public int getItemCount() {
        return mProductionCategoryMenuItem2s.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.recyclerView_menu_image_view)
        ImageView mRecyclerViewMenuImageView;
        @InjectView(R.id.recyclerView_menu_text_view)
        TextView mRecyclerViewMenuTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
