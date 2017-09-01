package com.bruce.core.net.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/**
 * from http://stackoverflow.com/questions/25461792/persistent-cookie-store-using-okhttp-2-on-android
 */
public class SerializableHttpCookie implements Serializable
{
    private static final long serialVersionUID = 6374381323722046732L;

    private transient final HttpCookie cookie;
    private transient HttpCookie clientCookie;

    /**
     * 构造cookie
     * @param cookie
     */
    public SerializableHttpCookie(HttpCookie cookie)
    {
        this.cookie = cookie;
    }

    /**
     * 获取clientCookie
     * @return
     */
    public HttpCookie getCookie()
    {
        HttpCookie bestCookie = cookie;
        if (clientCookie != null)
        {
            bestCookie = clientCookie;
        }
        return bestCookie;
    }

    /**
     * 将cookie写进流当中
     * @param out
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());
        out.writeObject(cookie.getComment());
        out.writeObject(cookie.getCommentURL());
        out.writeObject(cookie.getDomain());
        out.writeLong(cookie.getMaxAge());
        out.writeObject(cookie.getPath());
        out.writeObject(cookie.getPortlist());
        out.writeInt(cookie.getVersion());
        out.writeBoolean(cookie.getSecure());
        out.writeBoolean(cookie.getDiscard());
    }

    /**
     * 读取流当中的数据
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        clientCookie = new HttpCookie(name, value);
        clientCookie.setComment((String) in.readObject());
        clientCookie.setCommentURL((String) in.readObject());
        clientCookie.setDomain((String) in.readObject());
        clientCookie.setMaxAge(in.readLong());
        clientCookie.setPath((String) in.readObject());
        clientCookie.setPortlist((String) in.readObject());
        clientCookie.setVersion(in.readInt());
        clientCookie.setSecure(in.readBoolean());
        clientCookie.setDiscard(in.readBoolean());
    }
}