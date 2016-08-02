package com.shutup.ohaus_app.common;

/**
 * Created by shutup on 16/8/1.
 */
public class NormalItem {


    private boolean isChecked;
    private String iconUrl;
    private String titleStr;
    private String contentStr;

    public NormalItem(String iconUrl, String titleStr, String contentStr) {
        this.iconUrl = iconUrl;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }
}
