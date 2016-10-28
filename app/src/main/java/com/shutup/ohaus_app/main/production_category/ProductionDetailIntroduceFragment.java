package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.shutup.ohaus_app.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by shutup on 2016/10/28.
 */

public class ProductionDetailIntroduceFragment extends Fragment {
    @InjectView(R.id.production_detail_layout_webview)
    WebView mProductionDetailLayoutWebview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_detail_introduce_layout, container, false);
        ButterKnife.inject(this, view);
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
        mProductionDetailLayoutWebview.getSettings().setJavaScriptEnabled(true);
        mProductionDetailLayoutWebview.loadDataWithBaseURL("", "<style>img{display: inline;height: auto;max-width: 100%;}</style>"+productionCategoryDetailModel.getProductCategoryEntity().getDesc(), "text/html", "UTF-8", "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
