package com.shutup.ohaus_app.main.production_category;

import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.model.ProductionNormalItem;

import java.util.ArrayList;

/**
 * Created by shutup on 2016/10/23.
 */

public class ProductionCategoryDetailModel {
    private ProductCategoryEntity mProductCategoryEntity;
    private ArrayList<FilterOptionModel> mFilterOptionModels;
    private ProductionNormalItem mProductionNormalItem;

    public ProductionCategoryDetailModel(ProductCategoryEntity productCategoryEntity, ArrayList<FilterOptionModel> filterOptionModels, ProductionNormalItem productionNormalItem) {
        mProductCategoryEntity = productCategoryEntity;
        mFilterOptionModels = filterOptionModels;
        mProductionNormalItem = productionNormalItem;
    }

    public ProductCategoryEntity getProductCategoryEntity() {
        return mProductCategoryEntity;
    }

    public void setProductCategoryEntity(ProductCategoryEntity productCategoryEntity) {
        mProductCategoryEntity = productCategoryEntity;
    }

    public ArrayList<FilterOptionModel> getFilterOptionModels() {
        return mFilterOptionModels;
    }

    public void setFilterOptionModels(ArrayList<FilterOptionModel> filterOptionModels) {
        mFilterOptionModels = filterOptionModels;
    }

    public ProductionNormalItem getProductionNormalItem() {
        return mProductionNormalItem;
    }

    public void setProductionNormalItem(ProductionNormalItem productionNormalItem) {
        mProductionNormalItem = productionNormalItem;
    }
}
