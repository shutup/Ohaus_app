package com.shutup.ohaus_app.main.production_category;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shutup.ohaus_app.api.CategoryListEntity;
import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.api.TianpingEntity;
import com.shutup.ohaus_app.common.Constants;
import com.shutup.ohaus_app.common.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by shutup on 2016/11/5.
 */

public abstract class Factory implements Constants{
    public static List<RealmObject> parseAllDetailItems(String jsonStr,Gson gson,CategoryListEntity categoryListEntity) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        List<RealmObject> data = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject mainObject = jsonArray.optJSONObject(i);
            int type = StringUtils.getEntityNameByType(categoryListEntity.getData().get(i).getSubCategory());
            JSONArray detailsObject = mainObject.optJSONArray("data");
            if (type == TYPE_FXJMTP) {
                for (int j = 0; j < detailsObject.length(); j++) {
                    TianpingEntity tianpingEntity = gson.fromJson(detailsObject.optJSONObject(j).toString(), TianpingEntity.class);
                    tianpingEntity.setProductCategoryEntity(categoryListEntity.getData().get(i));
                    data.add(tianpingEntity);
                }
            }
        }
        return data;
    }

    protected ArrayList<FilterOptionModel> convertToFilterOptionsArray(int type, Map<String,ArrayList<String>> filterOptions) {
        ArrayList<FilterOptionModel> mFilterOptionModels = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry:
                filterOptions.entrySet()){
            if (type == TYPE_FXJMTP) {
                mFilterOptionModels.add(new FilterOptionModel(ProductionCategoryFilterOptionAdapter.ITEM_TYPE_HEADER,StringUtils.getTianpinFilterOptionName(entry.getKey()),entry.getKey()));
            }
            for (String s:entry.getValue()
                    ) {
                mFilterOptionModels.add(new FilterOptionModel(ProductionCategoryFilterOptionAdapter.ITEM_TYPE_NORMAL,s,entry.getKey()));
            }
        }
        return mFilterOptionModels;
    }

    public abstract Map<String, ArrayList<FilterOptionModel>> makeData(Gson gson, RealmResults<ProductCategoryEntity> productCategoryEntities) ;
}
