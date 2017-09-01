package com.bruce.core.callback;



public interface PermissionsResultListener {
    /**
     * 获取权限
     */
    void onPermissionGranted();

    /**
     * 权限申请被拒绝
     */
    void onPermissionDenied();
}
