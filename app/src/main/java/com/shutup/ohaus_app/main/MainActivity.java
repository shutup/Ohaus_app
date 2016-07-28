package com.shutup.ohaus_app.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.shutup.ohaus_app.BuildConfig;
import com.shutup.ohaus_app.R;
import com.shutup.ohaus_app.common.BaseActivity;
import com.shutup.ohaus_app.downloads.DownloadsActivity;
import com.shutup.ohaus_app.favorite.FavoriteActivity;
import com.shutup.ohaus_app.history.HistoryActivity;
import com.shutup.ohaus_app.invite.InviteActivity;
import com.shutup.ohaus_app.me.MeActivity;
import com.shutup.ohaus_app.setting.SettingActivity;
import com.shutup.ohaus_app.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

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

    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<MenuItem> mMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initToolBar();
        initMenus();
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
        mMenuItems.add(new MenuItem(getString(R.string.menu_history_title),R.drawable.menu_history_icon,new Intent(this, HistoryActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_download_title),R.drawable.menu_downloads_icon, new Intent(this, DownloadsActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_favorite_title),R.drawable.menu_favorite_icon, new Intent(this, FavoriteActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_invite_title),R.drawable.menu_invite_icon, new Intent(this, InviteActivity.class)));
        mMenuItems.add(new MenuItem(getString(R.string.menu_setting_title),R.drawable.menu_setting_icon, new Intent(this, SettingActivity.class)));
        mDrawerMenuList.setAdapter(new MenuListAdapter(this,mMenuItems));
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
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        mToolbarTitle.setText(R.string.main_page_title);
    }
}
