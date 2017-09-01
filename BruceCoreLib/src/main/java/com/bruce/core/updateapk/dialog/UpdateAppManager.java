package com.bruce.core.updateapk.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import static com.bruce.core.updateapk.dialog.UpdataAppContants.update_describe;


/**
 * Author:Bruce
 * Package:com.bruce.core.updateapk.dialog
 * time:2017/9/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class UpdateAppManager {

    public static Context context;
    //Log日志打印标签
    public static final String TAG = "Update_log";
    // 下载应用的进度条
    public static ProgressDialog progressDialog;
    public UpdateAppManager(Context context) {
        this.context = context;
    }

    /**
     * 获取当前版本号
     */
    public static int getCurrentVersion() {
        try {

            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

            Log.e(TAG, "当前版本名和版本号" + info.versionName + "--" + info.versionCode);

            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();

            Log.e(TAG, "获取当前版本号出错");
            return 0;
        }
    }

    /**
     * 从服务器获得更新信息
     */
    public void getUpdateMsg() {
        new UpdateJsonAsyncTask().execute(UpdataAppContants.version_path);
    }


    /**
     * 显示提示更新对话框
     */
    public static void showNoticeDialog() {
        new AlertDialog.Builder(context)
                .setTitle("检测到新版本！")
                .setMessage(update_describe)
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog();
                    }
                }).setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    /**
     * 显示下载进度对话框
     */
    public static void showDownloadDialog() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("正在下载...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        new DownloadAPK(progressDialog).execute();
    }

}
