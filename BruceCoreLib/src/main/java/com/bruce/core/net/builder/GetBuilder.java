package com.bruce.core.net.builder;


import com.bruce.core.net.request.GetRequest;
import com.bruce.core.net.request.RequestCall;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * GetBuilder 
 * 网络请求以URl,map参数来执行请求类
 */
public class GetBuilder extends OkHttpRequestBuilder {
    /**
     * RequestCall对象
     * @return
     */
    @Override
    public RequestCall build() {
        if (params != null) {
            url = appendParams(url, params);
        }

        return new GetRequest(url, tag, params, headers).build();
    }

    /**
     * 将路径和参数全部加入到StringBuilder当中，在返回一个String
     * @param url  路径
     * @param params  参数
     * @return
     */
    private String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * url
     * @param url
     * @return
     */
    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }

    /**
     * 标签
     * @param tag
     * @return
     */
    @Override
    public GetBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }

    /**
     * 设置map参数
     * @param params
     * @return
     */
    @Override
    public GetBuilder params(Map<String, String> params) {
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
    public GetBuilder addParams(String key, String val) {
        if (this.params == null) {
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
    public GetBuilder headers(Map<String, String> headers) {
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
    public GetBuilder addHeader(String key, String val) {
        if (this.headers == null) {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(key, val);
        return this;
    }
}
