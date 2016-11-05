package com.shutup.ohaus_app.main.production_category;

import android.os.Message;

import com.google.gson.Gson;
import com.shutup.ohaus_app.api.ProductCategoryEntity;
import com.shutup.ohaus_app.api.RealmString;
import com.shutup.ohaus_app.api.TianpingEntity;
import com.shutup.ohaus_app.common.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by shutup on 2016/11/5.
 */

public class TianpinFactory extends Factory {
    @Override
    public Map<String, ArrayList<FilterOptionModel>> makeData(Gson gson, RealmResults<ProductCategoryEntity> productCategoryEntities) {
        Map<String, ArrayList<String>> filterOptions = new LinkedHashMap<>();
        RealmQuery<TianpingEntity> tianpingEntityRealmQuery = RealmQuery.createQuery(Realm.getDefaultInstance(), TianpingEntity.class);
        RealmResults<TianpingEntity> tianpingEntities = tianpingEntityRealmQuery.findAll();
        ProductCategoryEntity productCategoryEntity = productCategoryEntities.first();
        Realm realm = Realm.getDefaultInstance();
        List<TianpingEntity> data = realm.copyFromRealm(tianpingEntities);
        RealmList<RealmString> realmStrings = productCategoryEntity.getFilter();
        Set<String> filterOptionSet;
        ArrayList<String> filterOptionArray;
        Map<String, Map<String, ArrayList<String>>> filterOfAll = new LinkedHashMap<>();
        Map<String, ArrayList<FilterOptionModel>> filterOptionsOfAll = new LinkedHashMap<>();
        for (RealmString r : realmStrings
                ) {
            String key = r.getVal();
            filterOptionSet = new HashSet<>();
            for (TianpingEntity t : data
                    ) {
                try {
                    String jsonStr = gson.toJson(t, TianpingEntity.class);
                    JSONObject j = new JSONObject(jsonStr);
                    String value = j.optString(key);
                    filterOptionSet.add(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            filterOptionArray = new ArrayList<>(filterOptionSet);
            Collections.sort(filterOptionArray);
            filterOptions.put(key, filterOptionArray);
        }
        filterOptionsOfAll.put(CATEGORY_FILTER_ALL,convertToFilterOptionsArray(StringUtils.getEntityNameByType(productCategoryEntity.getSubCategory()),filterOptions));

        for (ProductCategoryEntity p :productCategoryEntities) {
            filterOptions = new LinkedHashMap<>();
            for (RealmString r : realmStrings
                    ) {
                String key = r.getVal();
                filterOptionSet = new HashSet<>();
                for (TianpingEntity t : data
                        ) {
                    if (p.getName().equalsIgnoreCase(t.getProductCategoryEntity().getName())) {
                        try {
                            String jsonStr = gson.toJson(t, TianpingEntity.class);
                            JSONObject j = new JSONObject(jsonStr);
                            String value = j.optString(key);
                            filterOptionSet.add(value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                filterOptionArray = new ArrayList<>(filterOptionSet);
                Collections.sort(filterOptionArray);
                filterOptions.put(key, filterOptionArray);
            }
            filterOfAll.put(p.getName(),filterOptions);
        }
        for (Map.Entry<String,Map<String,ArrayList<String>>> filter :filterOfAll.entrySet()
                ) {
            filterOptionsOfAll.put(filter.getKey(),convertToFilterOptionsArray(StringUtils.getEntityNameByType(productCategoryEntity.getSubCategory()),filter.getValue()));
        }
        return filterOptionsOfAll;
    }
}
