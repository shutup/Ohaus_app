package com.shutup.ohaus_app.model;

import com.shutup.ohaus_app.api.ProductCategoryEntity;

/**
 * Created by shutup on 16/9/18.
 */
public class ProductionNormalItem {
    private String iconUrl;
    private String titleStr;
    private String contentStr;
    private float price;
    private int type;
    private ProductCategoryEntity productCategoryEntity;

    public static int TYPE_ProductionFirstCategory = 1;
    public static int TYPE_ProductionSecondCategory = 2;

    public ProductionNormalItem(String iconUrl, String titleStr, String contentStr, float price, ProductCategoryEntity productCategoryEntity) {
        this(iconUrl, titleStr, contentStr, price, TYPE_ProductionFirstCategory, productCategoryEntity);
    }

    public ProductionNormalItem(String iconUrl, String titleStr, String contentStr, float price, int type, ProductCategoryEntity productCategoryEntity) {
        this.iconUrl = iconUrl;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
        this.price = price;
        this.type = type;
        this.productCategoryEntity = productCategoryEntity;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ProductCategoryEntity getProductCategoryEntity() {
        return productCategoryEntity;
    }

    public void setProductCategoryEntity(ProductCategoryEntity productCategoryEntity) {
        this.productCategoryEntity = productCategoryEntity;
    }
}
