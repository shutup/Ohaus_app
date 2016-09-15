package com.shutup.ohaus_app.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.model.BannerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shutup on 16/9/6.
 */
public class BannerPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<BannerItem> mBannerItems;
    private ArrayList<View> mViews;

    public BannerPagerAdapter(Context context, ArrayList<BannerItem> bannerItems) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mBannerItems = bannerItems;
        mViews = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.banner_item_layout,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_imageView);
        Picasso.with(mContext).load(mBannerItems.get(position).getImageUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
        mViews.add(view);
        container.addView(mViews.get(position));
        return mViews.get(position);
//        if (mViews.size() < position) {
//            container.addView(mViews.get(position));
//            return mViews.get(position);
//        }else {
//            return mViews.get(mViews.size() - 1);
//        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mBannerItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
