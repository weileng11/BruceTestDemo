package com.bruce.core.net.request;


import com.bruce.core.net.utils.Exceptions;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by zhy on 15/12/14.
 */
public class PostFileRequest extends OkHttpRequest
{
    //网络请求需要的类型
    private static MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");

    private File file;
    private MediaType mediaType;

    /**
     * 初始化数据
     * @param url 路径
     * @param tag 标签
     * @param params 参数
     * @param headers 头部信息
     * @param file  文件
     * @param mediaType 请求需要的类型
     */
    public PostFileRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, File file, MediaType mediaType)
    {
        super(url, tag, params, headers);
        this.file = file;
        this.mediaType = mediaType;

        if (this.file == null)
        {
            Exceptions.illegalArgument("the file can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_STREAM;
        }

    }

    /**
     * 通过创建一个mediatype和file
     * 返回一个requestbody 对象
     * @return
     */
    @Override
    protected RequestBody buildRequestBody()
    {
        return RequestBody.create(mediaType, file);
    }

    /**
     * 返回Request 对象
     * @param builder
     * @param requestBody
     * @return
     */
    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody)
    {
        return builder.post(requestBody).build();
    }

    /**
     * 返回字符串＋文件的路径地址
     * @return
     */
    @Override
    public String toString()
    {
        return super.toString() + ", requestBody{uploadfilePath=" + file.getAbsolutePath() + "} ";
    }


}
