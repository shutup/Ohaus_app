package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shutup.ohaus_app.R;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by shutup on 2016/10/28.
 */

public class ProductionDetailspecificationsFragment extends Fragment {
    @InjectView(R.id.production_detail_layout_image)
    ImageView mProductionDetailLayoutImage;

    PhotoViewAttacher mPhotoViewAttacher;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_detail_specifications_layout, container, false);
        ButterKnife.inject(this, view);
        mPhotoViewAttacher = new PhotoViewAttacher(mProductionDetailLayoutImage);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(sticky = true)
    public void onMSg(ProductionCategoryDetailModel productionCategoryDetailModel) {
        Picasso.with(getContext()).load(productionCategoryDetailModel.getProductCategoryEntity().getNewImages().last().getUrl()).placeholder(R.mipmap.ohaosi_icon).into(mProductionDetailLayoutImage);
        mPhotoViewAttacher.update();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
