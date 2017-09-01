package com.bruce.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;



import butterknife.ButterKnife;

/**
 * Author:lt
 * time:2017/7/17.
 * contact：weileng143@163.com
 * @description
 */
public abstract class BaseActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        ButterKnife.inject(this);
        initData();
    }

    /**
     * 初始化界面布局
     */
    protected abstract void initView();

    /**
     * 初始化界面数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 返回键
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * 跳转后，当前界面自销毁
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * Activity跳转
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

}
