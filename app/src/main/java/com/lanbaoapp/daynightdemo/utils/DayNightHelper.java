package com.lanbaoapp.daynightdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lanbaoapp.daynightdemo.type.DayNight;

/**
 * Created by lx on 2017/11/15.
 * 日夜模式的帮助类
 */

public class DayNightHelper {

    private final static String FILE_NAME = "settings";
    private final static String MODE = "day_night_mode";

    private SharedPreferences mSharedPreferences;

    public DayNightHelper(Context context){
        this.mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存模式设置
     */
    public boolean setMode(DayNight mode){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(MODE, mode.getName());
        return editor.commit();
    }

    /**
     * 夜间模式
     */
    public boolean isNight(){
        String mode = mSharedPreferences.getString(MODE, DayNight.DAY.getName());
        if (DayNight.NIGHT.getName().equals(mode)) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * 日间模式
     */
    public boolean isDay(){
        String mode = mSharedPreferences.getString(MODE, DayNight.DAY.getName());
        if (DayNight.DAY.getName().equals(mode)) {
            return true;
        }else{
            return false;
        }
    }


}
