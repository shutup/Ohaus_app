package com.shutup.ohaus_app.main.goods_recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.model.GoodsRecommendItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/9/10.
 */
public class GoodsRecommendAdapter extends RecyclerView.Adapter<GoodsRecommendAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<GoodsRecommendItem> mGoodsRecommendItems;

    public GoodsRecommendAdapter(Context context, ArrayList<GoodsRecommendItem> goodsRecommendItems) {
        mContext = context;
        mGoodsRecommendItems = goodsRecommendItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_recommend_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsRecommendItem goodsRecommendItem = mGoodsRecommendItems.get(position);
        Picasso.with(mContext).load(goodsRecommendItem.getIconUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mGoodsRecommendItemImageView);
        holder.mGoodsRecommendItemTextView.setText(goodsRecommendItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mGoodsRecommendItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.goods_recommend_item_image_view)
        ImageView mGoodsRecommendItemImageView;
        @InjectView(R.id.goods_recommend_item_text_view)
        TextView mGoodsRecommendItemTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
