package com.shutup.ohaus_app.common;

/**
 * Created by shutup on 2016/9/28.
 */

public class StringUtils {
    public static String cleanPriceStr(String str) {
        return str.replaceAll("\\D","");
    }
}
