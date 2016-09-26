package com.shutup.ohaus_app.main.quick_news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.model.QuickNewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/9/10.
 */
public class QuickNewsAdapter extends RecyclerView.Adapter<QuickNewsAdapter.MyViewHolder> {

    private Context mContext = null;
    private ArrayList<QuickNewsItem> mQuickNewsItems = null;

    public QuickNewsAdapter(Context context, ArrayList<QuickNewsItem> quickNewsItems) {
        mContext = context;
        mQuickNewsItems = quickNewsItems;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_news_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuickNewsItem quickNewsItem = mQuickNewsItems.get(position);
        Picasso.with(mContext).load(quickNewsItem.getIconUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mQuickNewsImageView);
        holder.mQuickNewsTextView.setText(quickNewsItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return mQuickNewsItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.quick_news_image_view)
        ImageView mQuickNewsImageView;
        @InjectView(R.id.quick_news_text_view)
        TextView mQuickNewsTextView;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
