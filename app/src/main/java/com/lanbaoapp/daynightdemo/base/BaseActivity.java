package com.lanbaoapp.daynightdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.lanbaoapp.daynightdemo.R;
import com.lanbaoapp.daynightdemo.utils.DayNightHelper;

/**
 * Created by lx on 2017/11/15.
 */

public abstract class BaseActivity extends AppCompatActivity{

    public Activity mContext;
    //夜间模式的帮助类
    private DayNightHelper mDayNightHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.mContext = this;
        initDayNightHelper();
        initTheme();
        setContentView(getLayoutRes());
        onCreateActivity(savedInstanceState);
    }

    /**
     * 初始化夜间模式
     */
    private void initTheme() {
        if (mDayNightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        }else{
            setTheme(R.style.NightTheme);
        }
    }

    /**
     * 初始化夜间模式的帮助类
     */
    public void initDayNightHelper(){
        mDayNightHelper = new DayNightHelper(mContext);
    }

    public abstract int getLayoutRes();
    public abstract void onCreateActivity(@Nullable Bundle savedInstanceState);
}
