package com.shutup.ohaus_app.api;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shutup on 2016/10/17.
 */

public class ProductCategoryEntity extends RealmObject{
    private String _id;
    private String name;
    private String desc;
    private String categoryId;
    private String subCategoryId;
    private int minimumPrice;
    @PrimaryKey
    private String id;
    private String category;
    private String subCategory;

    private RealmList<RealmString> effect;
    private RealmList<RealmString> filter;



    /**
     * name : 2 Adventurer_Analytical_Draftshield_Closed_USB_Drive_Left_jpg
     * url : http://ohaus-api.oss-cn-hangzhou.aliyuncs.com/28e0c80b-6f88-4a56-a053-b34f8db9c574.jpg
     */

    private RealmList<NewImagesEntity> newImages;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public int getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(int minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public RealmList<RealmString> getEffect() {
        return effect;
    }

    public void setEffect(RealmList<RealmString> effect) {
        this.effect = effect;
    }

    public RealmList<RealmString> getFilter() {
        return filter;
    }

    public void setFilter(RealmList<RealmString> filter) {
        this.filter = filter;
    }

    public RealmList<NewImagesEntity> getNewImages() {
        return newImages;
    }

    public void setNewImages(RealmList<NewImagesEntity> newImages) {
        this.newImages = newImages;
    }

}
