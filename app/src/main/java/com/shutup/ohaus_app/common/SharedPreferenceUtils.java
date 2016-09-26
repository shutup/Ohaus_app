package com.shutup.ohaus_app.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shutup on 2016/9/26.
 */

public class SharedPreferenceUtils {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mSharedPreferencesEditor;
    private SharedPreferenceUtils(){

    }

    public static SharedPreferences getInstance(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mSharedPreferences;
    }

    public static SharedPreferences.Editor getEditerInstance(Context context) {
        if (mSharedPreferencesEditor == null) {
            mSharedPreferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return mSharedPreferencesEditor;
    }


}
