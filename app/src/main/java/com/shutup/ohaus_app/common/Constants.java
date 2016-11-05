package com.shutup.ohaus_app.common;

/**
 * Created by shutup on 16/8/1.
 */
public interface Constants {
    int ACTIVITY_NORMAL = 1;
    int ACTIVITY_EDIT = 2;
    int BANNER_CHANGE_DELAY = 3000;

    int LOAD_FROM_DB = 1;
    int REFRESH_UI = 2;
    int REFRESH_FILTER_UI = 3;

    String BASE_URL = "http://ohaus.greenicetech.cn/";

    String USER_PHONE = "USER_PHONE";
    String USER_PASSWORD = "USER_PASSWORD";
    String IS_LOGIN = "IS_LOGIN";
    int IS_LOGIN_OK = 1;
    int IS_LOGIN_FAIL = 2;

    String FieldProductCategoryEntity = "ProductCategoryEntity";
    String FieldFilterOptionModelArray = "FieldFilterOptionModelArray";

    String PRODUCTION_TYPE = "PRODUCTION_TYPE";
    int TYPE_FXJMTP = 1;

    String CATEGORY_FILTER_ALL = "CATEGORY_FILTER_ALL";
}
