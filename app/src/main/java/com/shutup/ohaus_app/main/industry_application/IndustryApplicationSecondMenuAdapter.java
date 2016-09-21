package com.shutup.ohaus_app.main.industry_application;

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
 * Created by shutup on 16/9/20.
 */

public class IndustryApplicationSecondMenuAdapter extends RecyclerView.Adapter<IndustryApplicationSecondMenuAdapter.ViewHolder> {

    private ArrayList<IndustryApplicationMenuItem> mIndustryApplicationMenuItems;
    private Context mContext;

    public IndustryApplicationSecondMenuAdapter(Context context, ArrayList<IndustryApplicationMenuItem> industryApplicationMenuItems) {
        mContext = context;
        mIndustryApplicationMenuItems = industryApplicationMenuItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.industry_application_second_menu_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IndustryApplicationMenuItem industryApplicationMenuItem = mIndustryApplicationMenuItems.get(position);
        holder.mContent.setText(industryApplicationMenuItem.getContentStr());
        holder.mTitle.setText(industryApplicationMenuItem.getTitleStr());
        Picasso.with(mContext).load(industryApplicationMenuItem.getIconUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mIcon);

    }

    @Override
    public int getItemCount() {
        return mIndustryApplicationMenuItems.size();
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
