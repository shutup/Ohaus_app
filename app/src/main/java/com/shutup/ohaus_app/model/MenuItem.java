package com.shutup.ohaus_app.model;

import android.content.Intent;
import android.support.annotation.DrawableRes;

/**
 * Created by shutup on 16/7/28.
 */
public class MenuItem {
    private String menuTitle;
    private @DrawableRes int menuIcon;



    private Intent mIntent;

    public MenuItem(String menuTitle, int menuIcon, Intent mIntent) {
        this.menuTitle = menuTitle;
        this.menuIcon = menuIcon;
        this.mIntent = mIntent;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }
}
