package com.shutup.ohaus_app.drawer_menu_activity.history;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.DividerItemDecoration;
import com.shutup.ohaus_app.common.NormalItem;
import com.shutup.ohaus_app.common.RecyclerTouchListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity implements Constants {

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.select_all_btn)
    Button mSelectAllBtn;
    @InjectView(R.id.delete_selected_btn)
    Button mDeleteSelectedBtn;
    @InjectView(R.id.bottom_bar)
    LinearLayout mBottomBar;

    private ArrayList<NormalItem> mNormalItems;
    private HistoryListAdapter mListAdapter;
    private static int currentType = ACTIVITY_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.inject(this);
        initToolBar();
        initList();
        prepareData();
    }

    private void prepareData() {
        for (int i = 0; i < 100; i++) {
            mNormalItems.add(new NormalItem("1", "title " + i, "content " + i));
        }
        mListAdapter.notifyDataSetChanged();
    }

    private void initList() {
        mNormalItems = new ArrayList<>();
        mListAdapter = new HistoryListAdapter(mNormalItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (currentType == ACTIVITY_NORMAL) {
                    Toast.makeText(HistoryActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                } else if (currentType == ACTIVITY_EDIT) {

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        mRecyclerView.setAdapter(mListAdapter);
    }

    private void initToolBar() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.back_arrow_white);
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            mToolbarTitle.setText(R.string.menu_history_title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        } else if (item.getItemId() == R.id.menu_edit) {
            if (item.getTitle().toString().contentEquals(getString(R.string.menu_edit))) {
                currentType = ACTIVITY_EDIT;
                item.setTitle(R.string.menu_cancel);
                mBottomBar.setVisibility(View.VISIBLE);
                mListAdapter.setType(ACTIVITY_EDIT);
                mListAdapter.notifyDataSetChanged();
            } else if (item.getTitle().toString().contentEquals(getString(R.string.menu_cancel))) {
                for (NormalItem n : mListAdapter.getNormalItems()
                        ) {
                    n.setChecked(false);
                }
                mSelectAllBtn.setText(getString(R.string.btn_select_all_title));
                currentType = ACTIVITY_NORMAL;
                item.setTitle(R.string.menu_edit);
                mBottomBar.setVisibility(View.GONE);
                mListAdapter.setType(ACTIVITY_NORMAL);
                mListAdapter.notifyDataSetChanged();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.select_all_btn, R.id.delete_selected_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_all_btn:
                if (BuildConfig.DEBUG) Log.d("HistoryActivity", "all");
                selectAll((Button) view);
                break;
            case R.id.delete_selected_btn:
                deleteSelected();
                if (BuildConfig.DEBUG) Log.d("HistoryActivity", "delete");
                break;
        }
    }

    private void deleteSelected() {
        ArrayList<NormalItem> selectedList = new ArrayList<>();
        for (NormalItem n : mListAdapter.getNormalItems()) {
            if (n.isChecked()) {
                selectedList.add(n);
            }
        }
        mListAdapter.getNormalItems().removeAll(selectedList);
        mListAdapter.notifyDataSetChanged();
    }

    private void selectAll(Button btn) {
        if (btn.getText().toString().contentEquals(getString(R.string.btn_select_all_title))){
            btn.setText(R.string.btn_reverse_select_title);
            for (NormalItem n : mListAdapter.getNormalItems()) {
                n.setChecked(true);
            }
        }else if(btn.getText().toString().contentEquals(getString(R.string.btn_reverse_select_title))){
            for (NormalItem n : mListAdapter.getNormalItems()) {
                n.setChecked(false);
            }
            btn.setText(R.string.btn_select_all_title);
        }
        mListAdapter.notifyDataSetChanged();
    }
}
