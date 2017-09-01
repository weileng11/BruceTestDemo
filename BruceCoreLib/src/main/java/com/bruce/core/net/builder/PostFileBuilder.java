package com.bruce.core.net.builder;


import com.bruce.core.net.request.PostFileRequest;
import com.bruce.core.net.request.RequestCall;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;


/**
 * Created by zhy on 15/12/14.
 * 网络请求以文件形式请求类
 */
public class PostFileBuilder extends OkHttpRequestBuilder
{
    private File file;
    private MediaType mediaType;

    //文件
    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }
    //mediaType
    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * 返回RequestCall对象
     * @return
     */
    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType).build();
    }

    @Override
    public PostFileBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PostFileBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFileBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    /**
     * 将键值对设置到LinkedHashMap当中，返回GetBuilder对象
     * @param key
     * @param val
     * @return
     */
    @Override
    public PostFileBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<String, String>();
        }
        params.put(key, val);
        return this;
    }
    /**
     * 头部
     * @param headers
     * @return
     */
    @Override
    public PostFileBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }

    /**
     * 将headers键值对设置到LinkedHashMap当中，返回GetBuilder对象
     * @param key
     * @param val
     * @return
     */
    @Override
    public PostFileBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(key, val);
        return this;
    }
}
