package com.shutup.ohaus_app.model;

/**
 * Created by shutup on 16/9/10.
 */
public class QuickNewsItem {
    private String iconUrl;
    private String description;

    public QuickNewsItem(String iconUrl, String description) {
        this.iconUrl = iconUrl;
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
