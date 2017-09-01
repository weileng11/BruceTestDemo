package com.bruce.core.net.builder;


import com.bruce.core.net.request.PostFormRequest;
import com.bruce.core.net.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhy on 15/12/14.
 * 网络请求上传文件名字，文件，具体名称请求类
 */
public class PostFormBuilder extends OkHttpRequestBuilder
{
    private List<FileInput> files = new ArrayList<FileInput>();

    /**
     * 返回RequestCall对象
     * @return
     */
    @Override
    public RequestCall build()
    {
        return new PostFormRequest(url, tag, params, headers, files).build();
    }

    /**
     * 添加文件
     * @param name 名字
     * @param filename 文件名字
     * @param file 文件
     * @return
     */
    public PostFormBuilder addFile(String name, String filename, File file)
    {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput
    {
        public String key;
        public String filename;
        public File file;

        /**
         * 构造方法初始化数据
         * @param name
         * @param filename
         * @param file
         */
        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        /**
         * 返回FileInput里面的长串字符串集
         * @return
         */
        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    //url
    @Override
    public PostFormBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    //标签
    @Override
    public PostFormBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    //参数
    @Override
    public PostFormBuilder params(Map<String, String> params)
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
    public PostFormBuilder addParams(String key, String val)
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
    public PostFormBuilder headers(Map<String, String> headers)
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
    public PostFormBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(key, val);
        return this;
    }


}
