package com.shutup.ohaus_app.main.production_category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shutup.ohaus_app.R;

/**
 * Created by shutup on 2016/10/28.
 */

public class ProductionDetailDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_detail_layout, container, false);
        return view;
    }
}
