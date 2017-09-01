package com.bruce.core.net.request;


import com.bruce.core.net.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by zhy on 15/12/14.
 */
public class PostStringRequest extends OkHttpRequest
{
    //设置编码格式
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;

    /**
     * 设置需要传入的参数，处理网络请求
     * @param url
     * @param tag
     * @param params
     * @param headers
     * @param content
     * @param mediaType
     */
    public PostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType)
    {
        super(url, tag, params, headers);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null)
        {
            Exceptions.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    /**
     * 编码格式和具体的内容
     * @return
     */
    @Override
    protected RequestBody buildRequestBody()
    {
        return RequestBody.create(mediaType, content);
    }

    /**
     * post形式
     * @param builder
     * @param requestBody
     * @return
     */
    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }

    @Override
    public String toString()
    {
        return super.toString() + ", requestBody{content=" + content + "} ";
    }

}
