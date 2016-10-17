package com.shutup.ohaus_app.api;

import java.util.List;

/**
 * Created by shutup on 2016/10/17.
 */

public class ProductCategoryEntity {
    private String _id;
    private String name;
    private String desc;
    private String categoryId;
    private String subCategoryId;
    private int minimumPrice;
    private String id;
    private String category;
    private String subCategory;
    private List<String> effect;
    private List<String> filter;

    /**
     * name : 2 Adventurer_Analytical_Draftshield_Closed_USB_Drive_Left_jpg
     * url : http://ohaus-api.oss-cn-hangzhou.aliyuncs.com/28e0c80b-6f88-4a56-a053-b34f8db9c574.jpg
     */

    private List<NewImagesEntity> newImages;

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

    public List<String> getEffect() {
        return effect;
    }

    public void setEffect(List<String> effect) {
        this.effect = effect;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public List<NewImagesEntity> getNewImages() {
        return newImages;
    }

    public void setNewImages(List<NewImagesEntity> newImages) {
        this.newImages = newImages;
    }
}
