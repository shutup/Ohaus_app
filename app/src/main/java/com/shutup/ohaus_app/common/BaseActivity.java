package com.shutup.ohaus_app.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shutup.ohaus_app.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BaseActivity extends AppCompatActivity {

    public boolean isDark = true;
    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (BuildConfig.DEBUG) Log.d("BaseActivity", getHandSetInfo());
        if (isXiaoMi()) {
            initStatusBar();
//            setMiuiStatusBarDarkMode(this, true);
        } else if (isMeizu()) {
            initStatusBar();
//            setMiuiStatusBarDarkMode(this, true);
        } else {
            initStatusBar();
        }
//        setStatusBarColor(R.color.colorPrimary);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
//        }
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void setStatusBarColor(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);//通知栏所需颜色
    }

    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    public void setGoogleMStatusBarDarkIcon(boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }

    }

    private String getHandSetInfo() {
        String handSetInfo = "手机型号:" + android.os.Build.MODEL
                + "\n系统版本:" + android.os.Build.VERSION.RELEASE
                + "\n产品型号:" + android.os.Build.PRODUCT
                + "\n版本显示:" + android.os.Build.DISPLAY
                + "\n系统定制商:" + android.os.Build.BRAND
                + "\n设备参数:" + android.os.Build.DEVICE
                + "\n开发代号:" + android.os.Build.VERSION.CODENAME
                + "\nSDK版本号:" + android.os.Build.VERSION.SDK_INT
                + "\nCPU类型:" + android.os.Build.CPU_ABI
                + "\n硬件类型:" + android.os.Build.HARDWARE
                + "\n主机:" + android.os.Build.HOST
                + "\n生产ID:" + android.os.Build.ID
                + "\nROM制造商:" + android.os.Build.MANUFACTURER // 这行返回的是rom定制商的名称
                ;
        return handSetInfo;
    }

    private boolean isXiaoMi() {
        return android.os.Build.MANUFACTURER.equalsIgnoreCase("xiaomi");
    }

    private boolean isMeizu() {
        return android.os.Build.MANUFACTURER.equalsIgnoreCase("meizu");
    }
}
