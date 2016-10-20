package com.shutup.ohaus_app.api;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shutup on 2016/10/17.
 */

public class NewImagesEntity extends RealmObject{
    @PrimaryKey
    private String url;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
