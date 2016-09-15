package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/6.
 */
public class BannerItem {

    public BannerItem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    private String imageUrl;
    private String bannerId;
}
