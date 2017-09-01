package com.bruce.core.utils;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {
    private static int GRAVITY = Gravity.CENTER;
    private static boolean isShow = true;
    private static Toast toast;

    /**
     * 普通弹出toast
     *
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, String message) {
        showToastCenter(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 普通弹出toast
     *
     * @param context
     * @param message
     */
    public static void showToastTest(Context context, String message) {
        if (isShow)
            showToastCenter(context, message, Toast.LENGTH_LONG);
    }

    public static void showToastShort(Context context, String message) {
        showToastCenter(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 带布局文件toast
     *
     * @param context
     * @param textId
     */
    public static void showToastLong(Context context, int textId) {
        showToastCenter(context, textId, Toast.LENGTH_LONG);
    }

    public static void showToastShort(Context context, int textId) {
        showToastCenter(context, textId, Toast.LENGTH_SHORT);
    }

    /**
     * 居中，设置时长
     *
     * @param context
     * @param text
     * @param duration
     */
    public static void showToastCenter(Context context, String text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(GRAVITY, 80, 80);
        toast.show();
    }

    /**
     * 带布局文件居中，设置时长
     *
     * @param context
     * @param textId
     * @param duration
     */
    public static void showToastCenter(Context context, int textId, int duration) {
        Toast toast = Toast.makeText(context, textId, duration);
        toast.setGravity(GRAVITY, 80, 80);
        toast.show();
    }


    public static void showView(Context context, View inflate) {
        if (toast == null)
            toast = new Toast(context);
        toast.setView(inflate);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showCaCheView() {
        toast.cancel();
        toast = null;
    }
}
