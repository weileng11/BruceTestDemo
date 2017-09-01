package com.bruce.core.net.request;


import com.bruce.core.net.OkHttpUtils;
import com.bruce.core.net.builder.PostFormBuilder;
import com.bruce.core.net.callback.Callback;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFormRequest extends OkHttpRequest
{
    private List<PostFormBuilder.FileInput> files;

    /**
     * 需要的参数
     * @param url 路径
     * @param tag 标签
     * @param params 参数
     * @param headers 头部需要的参数
     * @param files 文件参数
     */
    public PostFormRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, List<PostFormBuilder.FileInput> files)
    {
        super(url, tag, params, headers);
        this.files = files;
    }


    /**
     * 判断文件的名字是否为空
     * 如果不为空，将文件的名字保存在builder当中，返回builder
     * 否则，直接返回Builder
     * @return
     */
    @Override
    protected RequestBody buildRequestBody()
    {
        if (files == null || files.isEmpty())
        {
            FormBody.Builder builder = new FormBody.Builder();
            addParams(builder);
            return builder.build();
        } else
        {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            addParams(builder);

            for (int i = 0; i < files.size(); i++)
            {
                PostFormBuilder.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileInput.filename)), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    /**
     *  设置进度条
      * @param requestBody
     * @param callback
     * @return
     */
    @Override
    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        if (callback == null) return requestBody;
        CountingRequestBody countingRequestBody = new CountingRequestBody(requestBody, new CountingRequestBody.Listener()
        {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength)
            {

                OkHttpUtils.getInstance().getDelivery().post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        callback.inProgress(bytesWritten * 1.0f / contentLength);
                    }
                });

            }
        });
        return countingRequestBody;
    }

    /**
     * 返回request 对象
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
     * 返回MimeType 类型
     * @param path
     * @return
     */
    private String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 增加头部内容信息,并且通过create 创建
     * @param builder
     */
    private void addParams(MultipartBody.Builder builder)
    {
        if (params != null && !params.isEmpty())
        {
            for (String key : params.keySet())
            {
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }
    }

    /**
     * 传入一个builder
     * 在添加到builder
     * @param builder
     */
    private void addParams(FormBody.Builder builder)
    {
        if (params == null || params.isEmpty())
        {
            builder.add("1", "1");
            return;
        }

        for (String key : params.keySet())
        {
            builder.add(key, params.get(key));
        }
    }

    /**
     * 通过StringBuilder添加数据.
     * 如果文件不为null,则将文件的数据也添加到StringBuilder当中
     * 并且返回String类型
     * @return
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (files != null)
        {
            for (PostFormBuilder.FileInput file : files)
            {
                sb.append(file.toString()+"  ");
            }
        }
        return sb.toString();
    }
}
