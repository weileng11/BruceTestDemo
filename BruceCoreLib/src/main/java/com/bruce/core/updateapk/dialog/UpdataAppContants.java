package com.bruce.core.updateapk.dialog;

import android.app.ProgressDialog;
import android.os.Environment;

/**
 * Author:Bruce
 * Package:com.bruce.core.updateapk.dialog
 * time:2017/9/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class UpdataAppContants {
    // 外存sdcard存放路径
    public static final String FILE_PATH = Environment.getExternalStorageDirectory() +"/" + "AutoUpdate" +"/";
    // 下载应用存放全路径
    public static String FILE_NAME = FILE_PATH + "AutoUpdate.apk";
    // 准备安装新版本应用标记
    public static final int INSTALL_TOKEN = 1;

    //获取版本数据的地址
    public static String version_path = "https://raw.githubusercontent.com/feicien/android-auto-update/develop/extras/update.json";
    //获取新版APK的默认地址
    public static String apk_path = "https://raw.githubusercontent.com/feicien/android-auto-update/develop/extras/android-auto-update-v1.1.apk";


    //新版本号和描述语言
    public static int update_versionCode;
    public static String update_describe;
    public static String urlApk;
}
