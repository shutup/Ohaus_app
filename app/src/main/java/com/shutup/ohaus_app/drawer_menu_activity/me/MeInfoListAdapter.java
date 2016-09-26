package com.shutup.ohaus_app.drawer_menu_activity.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shutup.ohaus_app.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 16/7/29.
 */
public class MeInfoListAdapter extends BaseAdapter implements MeConstatnts {

    private ArrayList<MeInfoItem> mMeInfoItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MeInfoListAdapter(ArrayList<MeInfoItem> meInfoItems, Context context) {
        mMeInfoItems = meInfoItems;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mMeInfoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMeInfoItems.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return mMeInfoItems.get(position).getType();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.me_info_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == 4){
            viewHolder.mBottomLine.setVisibility(View.GONE);
        }else{
            viewHolder.mBottomLine.setVisibility(View.VISIBLE);
        }
        if (position == 5){
            viewHolder.mTopLine.setVisibility(View.VISIBLE);
        }else{
            viewHolder.mTopLine.setVisibility(View.GONE);
        }
        MeInfoItem meInfoItem = mMeInfoItems.get(position);
        viewHolder.mMeInfoTitle.setText(meInfoItem.getTitle());
        viewHolder.mMeInfoValue.setText(meInfoItem.getValue());

        if (meInfoItem.getType() == TYPE_1) {
            viewHolder.mUserImageBackArrow.setImageResource(R.drawable.forward_arrow_gray);
        } else if (meInfoItem.getType() == TYPE_2) {
            viewHolder.mUserImageBackArrow.setImageResource(R.drawable.down_arrow_orange);
        }
        return convertView;
    }

    static class ViewHolder {

        @InjectView(R.id.top_line)
        View mTopLine;
        @InjectView(R.id.bottom_line)
        View mBottomLine;
        @InjectView(R.id.me_info_title)
        TextView mMeInfoTitle;
        @InjectView(R.id.user_image_back_arrow)
        ImageView mUserImageBackArrow;
        @InjectView(R.id.me_info_value)
        TextView mMeInfoValue;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
