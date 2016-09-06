package com.shutup.ohaus_app.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.drawer_menu_activity.downloads.DownloadsActivity;
import com.shutup.ohaus_app.drawer_menu_activity.favorite.FavoriteActivity;
import com.shutup.ohaus_app.drawer_menu_activity.history.HistoryActivity;
import com.shutup.ohaus_app.drawer_menu_activity.invite.InviteActivity;
import com.shutup.ohaus_app.drawer_menu_activity.me.MeActivity;
import com.shutup.ohaus_app.drawer_menu_activity.setting.SettingActivity;
import com.shutup.ohaus_app.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements Constants{

    @InjectView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.drawer)
    DrawerLayout mDrawer;
    @InjectView(R.id.drawer_user_image)
    ImageView mDrawerUserImage;
    @InjectView(R.id.drawer_user_name)
    TextView mDrawerUserName;
    @InjectView(R.id.drawer_menu_list)
    ListView mDrawerMenuList;
    @InjectView(R.id.drawer_view)
    LinearLayout mDrawerView;
    @InjectView(R.id.banner_viewPager)
    ViewPager mBannerViewPager;
    @InjectView(R.id.banner_indicator)
    LinearLayout mBannerIndicator;

    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<MenuItem> mMenuItems;
    private ArrayList<BannerItem> mBannerItems;
    private ArrayList<ImageView> mDotsImageViews;
    private Handler mHandler = null;
    private Runnable runnable;
    private boolean isAutoChange = true;
    private GestureDetector mGestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mHandler = new Handler(Looper.getMainLooper());
        initToolBar();
        initMenus();
        initBanner();
    }

    private void initMenus() {

        //init user image
        Picasso.with(this).load("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg").placeholder(R.mipmap.ic_launcher)
                .transform(new CircleTransform()).into(mDrawerUserImage);
        mDrawerUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MeActivity.class));
            }
        });
        //init menus
        mMenuItems = new ArrayList<>();
        mMenuItems.add(new MenuItem(getString(R.string.menu_history_title), R.drawable.menu_history_icon, new Intent(this, HistoryActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_download_title), R.drawable.menu_downloads_icon, new Intent(this, DownloadsActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_favorite_title), R.drawable.menu_favorite_icon, new Intent(this, FavoriteActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_invite_title), R.drawable.menu_invite_icon, new Intent(this, InviteActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_setting_title), R.drawable.menu_setting_icon, new Intent(this, SettingActivity.class)));
        mDrawerMenuList.setAdapter(new MenuListAdapter(this, mMenuItems));
        mDrawerMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem menuItem = mMenuItems.get(position);
                startActivity(menuItem.getIntent());
                if (BuildConfig.DEBUG) Log.d("MainActivity", "clicked");
            }
        });

//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
//            //将侧边栏顶部延伸至status bar
//            mDrawer.setFitsSystemWindows(true);
//            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//            mDrawer.setClipToPadding(false);
//        }
        //set drawer width
        Display display = getWindowManager().getDefaultDisplay();
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) mDrawerView.getLayoutParams();
        params.width = (int) (0.74 * display.getWidth());
        mDrawerView.setLayoutParams(params);
        //
        // init drawer toggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();

        mDrawer.addDrawerListener(mDrawerToggle);
    }

    private void initToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        mToolbarTitle.setText(R.string.main_page_title);
    }

    private void initBanner() {
        mBannerItems = new ArrayList<>();
        mBannerItems.add(new BannerItem("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg"));
        mBannerItems.add(new BannerItem("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e"));
        mBannerItems.add(new BannerItem("http://v1.qzone.cc/avatar/201406/18/20/03/53a180238e68a151.JPG!200x200.jpg"));
        mBannerViewPager.setAdapter(new BannerPagerAdapter(this, mBannerItems));
        mGestureDetector = new GestureDetector(this, new TapGestureListener());
        mBannerViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
        mBannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeDotState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isAutoChange = false;
                }else if (state == ViewPager.SCROLL_STATE_IDLE){
                    isAutoChange = true;
                }else if (state == ViewPager.SCROLL_STATE_SETTLING){
                    isAutoChange = false;
                }
            }
        });
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentIndex = mBannerViewPager.getCurrentItem();
                if (isAutoChange) {
                    if (currentIndex < mBannerViewPager.getChildCount()) {
                        currentIndex++;
                        mBannerViewPager.setCurrentItem(currentIndex,true);
                    }else {
                        mBannerViewPager.setCurrentItem(0);
                    }
                    mHandler.postDelayed(runnable,BANNER_CHANGE_DELAY);
                }else {
                    mHandler.postDelayed(runnable,BANNER_CHANGE_DELAY);
                }
            }
        };
        mHandler.postDelayed(runnable,BANNER_CHANGE_DELAY);

        mDotsImageViews = new ArrayList<>();
        for (int i = 0; i < mBannerItems.size(); i++) {
            ImageView dotIV = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = params.rightMargin = 4;
            mBannerIndicator.addView(dotIV);
            mDotsImageViews.add(dotIV);
        }
        changeDotState(0);
    }

    private void changeDotState(int position) {
        for (int i = 0; i < mDotsImageViews.size(); i++) {
            if (i == position) {
                mDotsImageViews.get(i).setImageResource(R.drawable.dot_focus);
            } else {
                mDotsImageViews.get(i).setImageResource(R.drawable.dot_normal);
            }
        }
    }

    class TapGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            int currentIndex = mBannerViewPager.getCurrentItem();
            if (BuildConfig.DEBUG) Log.d("TapGestureListener", "currentIndex:" + currentIndex);
            return true;
        }
    }
}
