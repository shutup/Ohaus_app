package com.shutup.ohaus_app.api;

import java.util.List;

/**
 * Created by shutup on 16/9/22.
 */

public class CategoryEntity {

    /**
     * _id : 57e381cb88f377b777f43988
     * name : 实验室称重
     * subs : [{"name":"十万分之一天平","_id":"57e381d588f377b777f43989"},{"name":"分析精密天平","_id":"57e381e088f377b777f4398a"},{"name":"便携式天平","_id":"57e381e788f377b777f4398b"}]
     */

    private String _id;
    private String name;
    /**
     * name : 十万分之一天平
     * _id : 57e381d588f377b777f43989
     */

    private List<SubsEntity> subs;

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

    public List<SubsEntity> getSubs() {
        return subs;
    }

    public void setSubs(List<SubsEntity> subs) {
        this.subs = subs;
    }

    public static class SubsEntity {
        private String name;
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
}
