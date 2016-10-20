package com.shutup.ohaus_app.api;

import io.realm.RealmObject;

/**
 * Created by shutup on 10/20/16.
 */
public class RealmString extends RealmObject{


    private String val;

    public RealmString() {
    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
