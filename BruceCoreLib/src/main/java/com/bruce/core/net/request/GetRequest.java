package com.bruce.core.net.request;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhy on 15/12/14.
 */
public class GetRequest extends OkHttpRequest
{
    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers)
    {
        super(url, tag, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody()
    {
        return null;
    }

    /**
     * 返回request
     * @param builder
     * @param requestBody
     * @return
     */
    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.get().build();
    }




}
