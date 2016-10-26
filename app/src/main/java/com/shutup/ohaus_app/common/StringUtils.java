package com.shutup.ohaus_app.common;

import com.shutup.ohaus_app.api.TianpingEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shutup on 2016/9/28.
 */

public class StringUtils {

    private static Map<String,Integer> entityNameMapType = new HashMap<>();
    private static Map<String,Class<?>> entityNameMapClassName = new HashMap<>();
    private static Map<String,String> tianPinFilterName = new HashMap<>();

    static {
        entityNameMapType.put("分析精密天平", 1);
        entityNameMapClassName.put("分析精密天平",TianpingEntity.class);
        tianPinFilterName.put("readable","精度");
        tianPinFilterName.put("maxRange","最大量程");
        tianPinFilterName.put("adjust","校准方式");
        tianPinFilterName.put("originImport","进口");
        tianPinFilterName.put("autoWindCapGate","autoWindCapGate");
    }

    public static String cleanPriceStr(String str) {
        return str.replaceAll("\\D","");
    }

    public static Integer getEntityNameByType(String type) {
        if (entityNameMapType.containsKey(type)) {
            return entityNameMapType.get(type);
        }else {
            return 0;
        }
    }
    public static Class<?> getEntityClassByName(String name) {
        return entityNameMapClassName.get(name);
    }

    public static String getTianpinFilterOptionName(String name) {
        return tianPinFilterName.get(name);
    }
}
