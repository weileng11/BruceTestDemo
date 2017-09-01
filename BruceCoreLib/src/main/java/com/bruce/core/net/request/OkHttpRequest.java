package com.bruce.core.net.request;


import com.bruce.core.net.callback.Callback;
import com.bruce.core.net.utils.Exceptions;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by zhy on 15/11/6.
 */
public abstract class OkHttpRequest
{

    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;


    protected Request.Builder builder = new Request.Builder();

    /**
     * OkHttpRequest 构造方法来初始化数据
     * @param url 路径
     * @param tag 标签
     * @param params 
     * @param headers
     */
    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers)
    {
        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;

        if (url == null)
        {
            Exceptions.illegalArgument("url can not be null.");
        }
    }


    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    /**
     * 返回RequestCall 
     * @param builder
     * @param requestBody
     * @return
     */
    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }

    /**
     * 返回Request 对象
     * @param callback
     * @return
     */
    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }

    /**
     * 设置路径和标签
     * 请求的头部信息
     */
    private void prepareBuilder()
    {
        builder.url(url).tag(tag);
        appendHeaders();
    }


    /**
     * 将头部的信息以键值对的形式设置到Builder
     */
    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    /**
     * 返回一个字符串
     * @return
     */
    @Override
    public String toString()
    {
        return "OkHttpRequest{" +
                "url='" + url + '\'' +
                ", tag=" + tag +
                ", params=" + params +
                ", headers=" + headers +
                '}';
    }
}
