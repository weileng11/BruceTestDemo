package com.bruce.core.net.request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Decorates an OkHttp request body to count the number of bytes written when writing it. Can
 * decorate any request body, but is most useful for tracking the upload progress of large
 * multipart requests.
 * 装修一个OkHttp请求体数写在写的字节数。可以
 *装饰身体任何请求,但最有用的是跟踪上传大的进展
 *多部分请求。
 * @author Leo Nikkilä
 */
public class CountingRequestBody extends RequestBody
{

    protected RequestBody delegate;
    protected Listener listener;

    protected CountingSink countingSink;

    /**
     * 构造初始化数据
     * @param delegate RequestBody对象
     * @param listener listenter 监听
     */
    public CountingRequestBody(RequestBody delegate, Listener listener)
    {
        this.delegate = delegate;
        this.listener = listener;
    }

    /**
     * 返回RequestBody 的type类型
     * @return
     */
    @Override
    public MediaType contentType()
    {
        return delegate.contentType();
    }

    /**
     * 返回连接的长度
     * @return
     */
    @Override
    public long contentLength()
    {
        try
        {
            return delegate.contentLength();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 传入BufferedSink对象，将sink写进去
     * @param sink
     * @throws IOException
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException
    {

        countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);

        delegate.writeTo(bufferedSink);

        bufferedSink.flush();  //写到目标的地点
    }

    /**
     * 字节的长度，通过监听返回Listenter进度条的多少
     */
    protected final class CountingSink extends ForwardingSink
    {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate)
        {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException
        {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            listener.onRequestProgress(bytesWritten, contentLength());
        }

    }

    public static interface Listener
    {
        public void onRequestProgress(long bytesWritten, long contentLength);
    }

}