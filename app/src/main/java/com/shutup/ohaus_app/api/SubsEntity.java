package com.shutup.ohaus_app.api;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Maggie on 9/27/16.
 */

public class SubsEntity extends RealmObject{

    private String name;
    @PrimaryKey
    private String _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
