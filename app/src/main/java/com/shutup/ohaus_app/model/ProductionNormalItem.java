package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/18.
 */
public class ProductionNormalItem {
    private String iconUrl;
    private String titleStr;
    private String contentStr;
    private float price;

    public ProductionNormalItem(String iconUrl, String titleStr, String contentStr, float price) {
        this.iconUrl = iconUrl;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
