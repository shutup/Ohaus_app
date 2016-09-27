package com.shutup.ohaus_app.api;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shutup on 16/9/22.
 */

public class CategoryEntity extends RealmObject{

    /**
     * _id : 57e381cb88f377b777f43988
     * name : 实验室称重
     * subs : [{"name":"十万分之一天平","_id":"57e381d588f377b777f43989"},{"name":"分析精密天平","_id":"57e381e088f377b777f4398a"},{"name":"便携式天平","_id":"57e381e788f377b777f4398b"}]
     */
    @PrimaryKey
    private String _id;
    private String name;

    public void setSubs(RealmList<SubsEntity> subs) {
        this.subs = subs;
    }

    public RealmList<SubsEntity> getSubs() {
        return subs;
    }

    /**
     * name : 十万分之一天平
     * _id : 57e381d588f377b777f43989
     */

    private RealmList<SubsEntity> subs;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
