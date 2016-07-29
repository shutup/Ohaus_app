package com.shutup.ohaus_app.me;

/**
 * Created by shutup on 16/7/29.
 */
public class MeInfoItem {
    private String title;
    private String value;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MeInfoItem(String title, String value, int type) {
        this.title = title;
        this.value = value;
        this.type = type;
    }
}
