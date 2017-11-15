package com.lanbaoapp.daynightdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.lanbaoapp.daynightdemo.base.BaseActivity;
import com.lanbaoapp.daynightdemo.type.DayNight;
import com.lanbaoapp.daynightdemo.utils.DayNightHelper;

public class MainActivity extends BaseActivity {

    private SwitchButton mSwitchButton;
    private DayNightHelper mDayNightHelper;
    private RelativeLayout mRltContent;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        mRltContent = (RelativeLayout) findViewById(R.id.rlt_content);
        mSwitchButton = (SwitchButton) findViewById(R.id.switchBtn);
        initData();
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showAnimation();
                toggleThemeSetting();
                refreshUI();
            }
        });
        mRltContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    private void refreshUI() {
        TypedValue background = new TypedValue(); //背景色
        TypedValue textColor = new TypedValue(); //字体颜色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        mRltContent.setBackgroundResource(background.resourceId);
        for (int i = 0; i < mRltContent.getChildCount(); i++) {
            if (mRltContent.getChildAt(i) instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) mRltContent.getChildAt(i);
                viewGroup.setBackgroundResource(background.resourceId);
            }else if(mRltContent.getChildAt(i) instanceof TextView){
                TextView text = (TextView) mRltContent.getChildAt(i);
                text.setBackgroundResource(background.resourceId);
                Resources resources = getResources();
                text.setTextColor(resources.getColor(textColor.resourceId));
            }
        }
        refreshStatusBar();
    }

    /**
     * 刷新 StatusBar
     */
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    private void toggleThemeSetting() {
        if (mDayNightHelper.isDay()) {
            mDayNightHelper.setMode(DayNight.NIGHT);
            setTheme(R.style.NightTheme);
        } else {
            mDayNightHelper.setMode(DayNight.DAY);
            setTheme(R.style.DayTheme);
        }
    }

    /**
     * 初始化日夜模式的帮助类
     */
    private void initData() {
        mDayNightHelper = new DayNightHelper(this);
        mSwitchButton.setChecked(mDayNightHelper.isDay());
    }

    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }


    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }
}
