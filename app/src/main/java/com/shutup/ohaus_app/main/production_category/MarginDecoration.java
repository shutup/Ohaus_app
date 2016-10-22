package com.shutup.ohaus_app.main.production_category;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shutup.ohaus_app.R;

/**
 * Created by shutup on 2016/10/21.
 */

public class MarginDecoration extends RecyclerView.ItemDecoration {
    private int margin;

    public MarginDecoration(Context context) {
        this.margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
    }


    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin, margin, margin, margin);
    }
}
