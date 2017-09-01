package com.bruce.core.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bruce.core.callback.PermissionsResultListener;
import com.bruce.core.utils.PermissionUtils;

import butterknife.ButterKnife;

/**
 * Author:Bruce
 * Package:com.bruce.core.base
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description 基础Fragment，所有Fragment必须继承此Fragment
 */

public  abstract class BaseFragment extends Fragment{

    protected View mFragmentView; // 本Fragment对应的View
    protected BaseActivity mActivity; // 本Fragment依附的Activity
    private PermissionsResultListener mPermissionListener;  // 权限申请之后的监听
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.inject(this,  mFragmentView);
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        initData();
    }


    //获取布局文件ID
    protected abstract int getLayoutId();
    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 根据控件ID获取控件对应的View对象
     */
    protected View findViewById(int id) {
        return mFragmentView.findViewById(id);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //销毁定位
        ButterKnife.reset(this);
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
                PermissionUtils.requestEachPermissions(mActivity, desc, permissions, 2);
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

}
