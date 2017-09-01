package com.bruce.core.net.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;


public final class SimpleCookieJar implements CookieJar
{
    private final List<Cookie> allCookies = new ArrayList<Cookie>();

    /**
     * 将cookies保存到allCookies
     * @param url
     * @param cookies
     */
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        allCookies.addAll(cookies);
    }

    /**
     * 循环去判断allcookies是否包含传入的url路径，如果有则返回
     * List<Cookie>集合
     * @param url
     * @return
     */
    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url)
    {
        List<Cookie> result = new ArrayList<Cookie>();
        for (Cookie cookie : allCookies)
        {
            if (cookie.matches(url))
            {
                result.add(cookie);
            }
        }
        return result;
    }
}
