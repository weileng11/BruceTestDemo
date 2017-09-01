package com.bruce.core.net.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *     OkHttpClient client = new OkHttpClient.Builder()
 *             .cookieJar(new JavaNetCookieJar(new CookieManager(
 *                     new PersistentCookieStore(getApplicationContext()),
 *                             CookiePolicy.ACCEPT_ALL))
 *             .build();
 *
 * </pre>
 * <p/>
 * from http://stackoverflow.com/questions/25461792/persistent-cookie-store-using-okhttp-2-on-android
 * <p/>
 * <br/>
 * A persistent cookie store which implements the Apache HttpClient CookieStore interfaceable.
 * Cookies are stored and will persist on the user's device between application sessions since they
 * are serialized and stored in SharedPreferences. Instances of this class are
 * designed to be used with AsyncHttpClient#setCookieStore, but can also be used with a
 * regular old apache HttpClient/HttpContext if you prefer.
 * 
 * 持久化cookie存储,实现了Apache HttpClient CookieStore接口。
 * cookie存储和将持续对用户应用程序会话,因为他们之间的设备
 *在SharedPreferences序列化和存储。这个类的实例
 *设计用于与AsyncHttpClient # setCookieStore,但是也可以使用
 *定期老apache HttpClient / HttpContext如果你喜欢。
 */
public class PersistentCookieStore implements CookieStore
{

    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private static final String COOKIE_NAME_PREFIX = "cookie_";

    private final HashMap<String, ConcurrentHashMap<String, HttpCookie>> cookies;
    private final SharedPreferences cookiePrefs;

    /**
     * Construct a persistent cookie store.
     *
     * @param context Context to attach cookie store to
     *                构建一个持久的cookies
     */
    public PersistentCookieStore(Context context)
    {
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        cookies = new HashMap<String, ConcurrentHashMap<String, HttpCookie>>();

        // Load any previously stored cookies into the store
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet())
        {
            if (((String) entry.getValue()) != null && !((String) entry.getValue()).startsWith(COOKIE_NAME_PREFIX))
            {
                String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
                for (String name : cookieNames)
                {
                    String encodedCookie = cookiePrefs.getString(COOKIE_NAME_PREFIX + name, null);
                    if (encodedCookie != null)
                    {
                        HttpCookie decodedCookie = decodeCookie(encodedCookie);
                        if (decodedCookie != null)
                        {
                            if (!cookies.containsKey(entry.getKey()))
                                cookies.put(entry.getKey(), new ConcurrentHashMap<String, HttpCookie>());
                            cookies.get(entry.getKey()).put(name, decodedCookie);
                        }
                    }
                }

            }
        }
    }

    /**
     * 保存cookie持久性存储
     * @param uri 路径
     * @param cookie 
     */
    @Override
    public void add(URI uri, HttpCookie cookie)
    {
        String name = getCookieToken(uri, cookie);

        // Save cookie into local store, or remove if expired
        if (!cookie.hasExpired())
        {
            if (!cookies.containsKey(uri.getHost()))
                cookies.put(uri.getHost(), new ConcurrentHashMap<String, HttpCookie>());
            cookies.get(uri.getHost()).put(name, cookie);
        } else
        {
            if (cookies.containsKey(uri.toString()))
                cookies.get(uri.getHost()).remove(name);
        }

        // Save cookie into persistent store
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.putString(uri.getHost(), TextUtils.join(",", cookies.get(uri.getHost()).keySet()));
        prefsWriter.putString(COOKIE_NAME_PREFIX + name, encodeCookie(new SerializableHttpCookie(cookie)));
        prefsWriter.commit();
    }

    /**
     * 返回cookie的名称
     * @param uri
     * @param cookie
     * @return
     */
    protected String getCookieToken(URI uri, HttpCookie cookie)
    {
        return cookie.getName() + cookie.getDomain();
    }

    /**
     * cookies 包含uri当中gethost
     * @param uri
     * @return
     */
    @Override
    public List<HttpCookie> get(URI uri)
    {
        ArrayList<HttpCookie> ret = new ArrayList<HttpCookie>();
        if (cookies.containsKey(uri.getHost()))
            ret.addAll(cookies.get(uri.getHost()).values());
        return ret;
    }

    /**
     * 移除保存在SharedPreferences的数据
     * prefsWriter清除
     * cookies 也清除
     * @return
     */
    @Override
    public boolean removeAll()
    {
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.commit();
        cookies.clear();
        return true;
    }

    /**
     * 如果cookies包含uri.gethost()并且包含name
     * 移除uri.getHost()包含的remove，cookiePrefs包含COOKIE_NAME_PREFIX+name
     * 移除COOKIE_NAME_PREFIX+name,保存uri.gethost到Sp
     * @param uri  路径
     * @param cookie cookie 请求cookie
     * @return
     */
    @Override
    public boolean remove(URI uri, HttpCookie cookie)
    {
        String name = getCookieToken(uri, cookie);

        if (cookies.containsKey(uri.getHost()) && cookies.get(uri.getHost()).containsKey(name))
        {
            cookies.get(uri.getHost()).remove(name);

            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            if (cookiePrefs.contains(COOKIE_NAME_PREFIX + name))
            {
                prefsWriter.remove(COOKIE_NAME_PREFIX + name);
            }
            prefsWriter.putString(uri.getHost(), TextUtils.join(",", cookies.get(uri.getHost()).keySet()));
            prefsWriter.commit();

            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 获取list<HttpCookie>集合数据
     * @return
     */
    @Override
    public List<HttpCookie> getCookies()
    {
        ArrayList<HttpCookie> ret = new ArrayList<HttpCookie>();
        for (String key : cookies.keySet())
            ret.addAll(cookies.get(key).values());

        return ret;
    }

    /**
     * 返回List<URI>集合
     * @return
     */
    @Override
    public List<URI> getURIs()
    {
        ArrayList<URI> ret = new ArrayList<URI>();
        for (String key : cookies.keySet())
            try
            {
                ret.add(new URI(key));
            } catch (URISyntaxException e)
            {
                e.printStackTrace();
            }

        return ret;
    }

    /**
     * Serializes Cookie object into String
     * 对象序列化Cookie字符串
     * @param cookie cookie to be encoded, can be null
     * @return cookie encoded as String
     */
    protected String encodeCookie(SerializableHttpCookie cookie)
    {
        if (cookie == null)
            return null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e)
        {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }

        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * Returns cookie decoded from cookie string
     * 返回从cookie字符串解码
     * @param cookieString string of cookie as returned from http request
     * @return decoded cookie or null if exception occured
     */
    protected HttpCookie decodeCookie(String cookieString)
    {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        HttpCookie cookie = null;
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((SerializableHttpCookie) objectInputStream.readObject()).getCookie();
        } catch (IOException e)
        {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e)
        {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }

        return cookie;
    }

    /**
     * Using some super basic byte array &lt;-&gt; hex conversions so we don't have to rely on any
     * large Base64 libraries. Can be overridden if you like!
     *  传入一个bytes 字节数，返回一个字符串
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    protected String byteArrayToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes)
        {
            int v = element & 0xff;
            if (v < 16)
            {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * Converts hex values from strings to byte arra
     * 将十六进制值的字符串转换成字节arra
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    protected byte[] hexStringToByteArray(String hexString)
    {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}