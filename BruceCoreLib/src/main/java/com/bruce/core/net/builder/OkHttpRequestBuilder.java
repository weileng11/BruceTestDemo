package com.bruce.core.net.builder;


import com.bruce.core.net.request.RequestCall;

import java.util.Map;

/**
 * Created by zhy on 15/12/14.
 * abstract 网络请求抽象类的一些方法
 */
public abstract class OkHttpRequestBuilder
{
    protected String url;
    protected Object tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    //url
    public abstract OkHttpRequestBuilder url(String url);
    //标签
    public abstract OkHttpRequestBuilder tag(Object tag);
    //参数
    public abstract OkHttpRequestBuilder params(Map<String, String> params);
    //保存参数
    public abstract OkHttpRequestBuilder addParams(String key, String val);
    //头部
    public abstract OkHttpRequestBuilder headers(Map<String, String> headers);
    //添加header
    public abstract OkHttpRequestBuilder addHeader(String key, String val);
    //RequestCall对象
    public abstract RequestCall build();



}
