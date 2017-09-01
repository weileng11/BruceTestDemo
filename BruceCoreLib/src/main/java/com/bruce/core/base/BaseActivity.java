package com.bruce.core.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.bruce.core.callback.PermissionsResultListener;
import com.bruce.core.utils.ActivityCollector;
import com.bruce.core.utils.PermissionUtils;

import butterknife.ButterKnife;

/**
 * Author:Bruce
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description
 */

public abstract class BaseActivity  extends AppCompatActivity{

    protected BaseActivity mActivity; // 标识自己
    private PermissionsResultListener mPermissionListener;  // 权限申请之后的监听
    private boolean mIsDestroyed = false; // 当前activity是否被销毁
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ActivityCollector.put(this);
        setContentView(getContentViewId());
        ButterKnife.inject(this);
        initData();
    }

    /**
     * 获取本activity的布局文件
     */
    protected abstract int getContentViewId();
    /**
     * 初始化界面数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        ActivityCollector.remove(this);
        mIsDestroyed=true;
        super.onDestroy();
    }

    /**
     * 当前activity是否被销毁
     */
    public boolean isDestroyed() {
        return mIsDestroyed;
    }

    /**
     * @param desc        首次申请权限被拒绝后再次申请给用户的描述提示
     * @param permissions 要申请的权限数组
     * @param listener    实现的接口
     */
    protected void requestPermissions(String desc, String[] permissions, PermissionsResultListener listener) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        mPermissionListener = listener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.checkEachSelfPermission(mActivity, permissions)) {// 检查是否声明了权限
                PermissionUtils.requestEachPermissions(mActivity, desc, permissions, 1);
            } else {// 已经申请权限
                if (mPermissionListener != null) {
                    mPermissionListener.onPermissionGranted();
                }
            }
        } else {
            if (mPermissionListener != null) {
                mPermissionListener.onPermissionGranted();
            }
        }
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
