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

    static {
        entityNameMapType.put("分析精密天平", 1);
        entityNameMapClassName.put("分析精密天平",TianpingEntity.class);
    }

    public static String cleanPriceStr(String str) {
        return str.replaceAll("\\D","");
    }

    public static Integer getEntityNameByType(String type) {
        return entityNameMapType.get(type);
    }
    public static Class<?> getEntityClassByName(String name) {
        return entityNameMapClassName.get(name);
    }
}
