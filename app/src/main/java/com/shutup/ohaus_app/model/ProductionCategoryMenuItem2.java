package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenuItem2 {
    private String imageUrl;
    private String menuTitle;

    public ProductionCategoryMenuItem2(String imageUrl, String menuTitle) {
        this.imageUrl = imageUrl;
        this.menuTitle = menuTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}
