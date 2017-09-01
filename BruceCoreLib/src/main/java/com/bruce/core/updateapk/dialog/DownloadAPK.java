package com.bruce.core.updateapk.dialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author:Bruce
 * Package:com.bruce.core.updateapk.dialog
 * time:2017/9/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class DownloadAPK extends AsyncTask<Void, Integer, Integer> {
    private String TAG="DownloadAPK";
    ProgressDialog progressDialog;

    public DownloadAPK(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }
    @Override
    protected void onPreExecute() {
        Log.e(TAG, "执行至--onPreExecute");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {

        Log.e(TAG, "执行至--doInBackground");

        URL url;
        HttpURLConnection connection = null;
        InputStream in = null;
        FileOutputStream out = null;
        try {
            url = new URL(UpdataAppContants.apk_path);
            connection = (HttpURLConnection) url.openConnection();

            in = connection.getInputStream();
            long fileLength = connection.getContentLength();
            File file_path = new File(UpdataAppContants.FILE_PATH);
            if (!file_path.exists()) {
                file_path.mkdir();
            }

            out = new FileOutputStream(new File(UpdataAppContants.FILE_NAME));//为指定的文件路径创建文件输出流
            byte[] buffer = new byte[1024 * 1024];
            int len = 0;
            long readLength = 0;

            Log.e(TAG, "执行至--readLength = 0");

            while ((len = in.read(buffer)) != -1) {

                out.write(buffer, 0, len);//从buffer的第0位开始读取len长度的字节到输出流
                readLength += len;

                int curProgress = (int) (((float) readLength / fileLength) * 100);

                Log.e(TAG, "当前下载进度：" + curProgress);

                publishProgress(curProgress);

                if (readLength >= fileLength) {

                    Log.e(TAG, "执行至--readLength >= fileLength");
                    break;
                }
            }

            out.flush();
            return UpdataAppContants.INSTALL_TOKEN;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        Log.e(TAG, "异步更新进度接收到的值：" + values[0]);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        progressDialog.dismiss();//关闭进度条
        //安装应用
        installApp();
    }

    /**
     * 安装新版本应用
     */
    private void installApp() {
        File appFile = new File(UpdataAppContants.FILE_NAME);
        if (!appFile.exists()) {
            return;
        }
        // 跳转到新版本应用安装页面
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + appFile.toString()), "application/vnd.android.package-archive");
        UpdateAppManager.context.startActivity(intent);
    }
}
