package com.shutup.ohaus_app.main.industry_application;

/**
 * Created by shutup on 16/9/18.
 */
public class IndustryApplicationMenuItem {
    private String iconUrl;
    private String titleStr;
    private String contentStr;

    public IndustryApplicationMenuItem(String iconUrl, String titleStr, String contentStr) {
        this.iconUrl = iconUrl;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
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
