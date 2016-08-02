package com.shutup.ohaus_app.drawer_menu_activity.downloads;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.NormalItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/8/1.
 */
public class DownloadsListAdapter extends RecyclerView.Adapter<DownloadsListAdapter.MyViewHolder> implements Constants {

    public ArrayList<NormalItem> getNormalItems() {
        return mNormalItems;
    }

    private ArrayList<NormalItem> mNormalItems;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public DownloadsListAdapter(ArrayList<NormalItem> normalItems) {
        mNormalItems = normalItems;
        this.type = ACTIVITY_NORMAL;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int pos = position;
        NormalItem normalItem = mNormalItems.get(position);
        if (type == ACTIVITY_EDIT) {
            holder.mCheckboxLayout.setVisibility(View.VISIBLE);
            holder.mCheckbox.setChecked(normalItem.isChecked());
            holder.mCheckbox.setTag(normalItem);
            holder.mCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;

                    NormalItem normalItem1 = (NormalItem) cb.getTag();
                    normalItem1.setChecked(cb.isChecked());

                    mNormalItems.get(pos).setChecked(cb.isChecked());
                }
            });
        } else if (type == ACTIVITY_NORMAL) {
            holder.mCheckboxLayout.setVisibility(View.GONE);
        }
        holder.mTitle.setText(normalItem.getTitleStr());
        holder.mContent.setText(normalItem.getContentStr());
    }

    @Override
    public int getItemCount() {
        return mNormalItems.size();
    }

    static

//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public ImageView mIcon;
//        public TextView mTitle,mContent;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            mIcon = (ImageView) itemView.findViewById(R.id.icon);
//            mTitle = (TextView) itemView.findViewById(R.id.title);
//            mContent = (TextView) itemView.findViewById(R.id.content);
//        }
//    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.checkbox_layout)
        LinearLayout mCheckboxLayout;
        @InjectView(R.id.checkbox)
        CheckBox mCheckbox;
        @InjectView(R.id.icon)
        ImageView mIcon;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.content)
        TextView mContent;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
