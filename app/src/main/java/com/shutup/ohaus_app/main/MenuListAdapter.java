package com.shutup.ohaus_app.main;

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
 * Created by shutup on 16/7/28.
 */
public class MenuListAdapter extends BaseAdapter {

    private ArrayList<MenuItem> mMenuItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MenuListAdapter(Context context, ArrayList<MenuItem> menuItems) {
        this.mContext = context;
        this.mMenuItems = menuItems;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.menu_item_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuItem menuItem = mMenuItems.get(position);
        viewHolder.mMenuIcon.setBackgroundResource(menuItem.getMenuIcon());
        viewHolder.mMenuTitle.setText(menuItem.getMenuTitle());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.menu_icon)
        ImageView mMenuIcon;
        @InjectView(R.id.menu_title)
        TextView mMenuTitle;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
