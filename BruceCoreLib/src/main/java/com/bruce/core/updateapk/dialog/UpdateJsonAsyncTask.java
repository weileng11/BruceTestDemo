package com.bruce.core.updateapk.dialog;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.bruce.core.updateapk.dialog.UpdataAppContants.update_describe;
import static com.bruce.core.updateapk.dialog.UpdataAppContants.update_versionCode;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Author:Bruce
 * Package:com.bruce.core.updateapk.dialog
 * time:2017/9/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class UpdateJsonAsyncTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection connection = null;
        try {
            URL url_version = new URL(params[0]);
            connection = (HttpURLConnection) url_version.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);

            InputStream in = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));

            Log.e(TAG, "bufferReader读到的数据--" + reader);

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {             //回到主线程，更新UI

        Log.e(TAG, "异步消息处理反馈--" + s);
        try {
            JSONObject object = new JSONObject(s);

            UpdataAppContants.urlApk=object.getString("url");
            UpdataAppContants.update_versionCode = object.getInt("versionCode");
            UpdataAppContants.update_describe = object.getString("updateMessage");

            Log.e(TAG, "新版本号--" + update_versionCode);
            Log.e(TAG, "新版本描述--\n" + update_describe);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (update_versionCode > UpdateAppManager.getCurrentVersion()) {

            Log.e(TAG, "提示更新！");
            UpdateAppManager.showNoticeDialog();
        } else {
            Log.e(TAG, "已是最新版本！");
        }
    }
}

