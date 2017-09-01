package com.bruce.core.net.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 回调的实现方法类
 * @param <T>
 */
public abstract class Callback<T>
{
    /**
     * UI Thread
     * 之前
     * @param request
     */
    public void onBefore(Request request)
    {
    }

    /**
     * UI Thread
     * 之后
     * @param
     */
    public void onAfter()
    {
    }

    /**
     * UI Thread
     *  正在刷新进度条..
     * @param progress
     */
    public void inProgress(float progress)
    {

    }
    /**
     * Thread Pool Thread
     *  网络请求返回回调
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;
    //错误
    public abstract void onError(Call call, Exception e);
    //传入对象回调
    public abstract void onResponse(T response);


    public static Callback CALLBACK_DEFAULT = new Callback()
    {

        @Override
        public Object parseNetworkResponse(Response response) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e)
        {

        }

        @Override
        public void onResponse(Object response)
        {

        }
    };

}