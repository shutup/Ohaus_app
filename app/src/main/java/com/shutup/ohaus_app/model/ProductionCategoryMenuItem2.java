package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/13.
 */
public class ProductionCategoryMenuItem2 {
    private String imageUrl;
    private String menuTitle;
    private String id;
    private String pid;

    public ProductionCategoryMenuItem2(String pid, String id, String imageUrl, String menuTitle) {
        this.pid = pid;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
