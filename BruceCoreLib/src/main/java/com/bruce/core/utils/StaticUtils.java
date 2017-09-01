package com.bruce.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by lt on 2016/5/19.
 */
public class StaticUtils {

    /**
     * 随机生成uuid
     */
    public static String GetRandomUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号 
        return  s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    };

    /**
     * 判断当前Context是否合法
     */
    public static boolean isValidContext(Context c) {
        if (c instanceof Activity) {
            Activity a = (Activity) c;

            if (a.isFinishing()) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 最省内存的方式读取本地图片sp
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片  
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    /**
     * 读取配置文件 3.20
     */
    public static String getFormInfo(Class c, int i) {
        String frominfo = "";
        Properties prop = new Properties();
        try {
            prop.load(c.getResourceAsStream("/assets/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String from[] = ((String) prop.get("from")).split("[|]");
        frominfo = from[i];
        return frominfo;

    }

    /**
     * 获得网络连接是否可用
     *
     * @param context
     * @return
     */
    public static boolean hasNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo workinfo = con.getActiveNetworkInfo();
        if (workinfo == null || !workinfo.isAvailable()) {
            //showNetDialog(context);
            return false;
        }
        return true;
    }

    /**
     * 安装一个apk文件
     *
     * @param file
     */
    public static void installApk(File file, Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取当前应用版本号
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String getVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 获取SDK版本
     */
    public static int getSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
        }
        return version;
    }


    /**
     * 获取当前时间
     *
     * @return 时间字符串 24小时制
     * @author drowtram
     */
    public static String getStringTime(String type) {
        Time t = new Time();
        t.setToNow(); // 取得系统时间。
        String hour = t.hour < 10 ? "0" + (t.hour) : t.hour + ""; // 默认24小时制
        String minute = t.minute < 10 ? "0" + (t.minute) : t.minute + "";
        return hour + type + minute;
    }

    /**
     * 获取当前时间
     *
     * @return 时间字符串 24小时制
     * @author drowtram
     */
    public static long getCurrentTime() {

        return System.currentTimeMillis();
    }


    /**
     * 转换播放时间
     *
     * @param milliseconds 传入毫秒值
     * @return 返回 hh:mm:ss或mm:ss格式的数据
     */
    public static String getShowTime(long milliseconds) {
        // 获取日历函数
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        SimpleDateFormat dateFormat = null;
        // 判断是否大于60分钟，如果大于就显示小时。设置日期格式
        if (milliseconds / 60000 > 60) {
            dateFormat = new SimpleDateFormat("hh:mm:ss");
        } else {
            dateFormat = new SimpleDateFormat("mm:ss");
        }
        return dateFormat.format(calendar.getTime());
    }

    /**************************回看工具函数*******************************/


    /**
     * @param strLink 链接地址
     * @return false     该地址非空
     * @brief 判断该链接是否为空
     * @author joychang
     */
    public static boolean isValidLink(String strLink) {
        Log.d("UiUtil", "isValidLink() start.");
        boolean result = false;
        if (strLink != null && strLink.length() > 0) {
            URL url;
            try {
                url = new URL(strLink);
                HttpURLConnection connt = (HttpURLConnection) url.openConnection();
                connt.setConnectTimeout(5 * 1000);
                connt.setRequestMethod("HEAD");
                int code = connt.getResponseCode();
                if (code == 404) {
                    result = true;
                }
                connt.disconnect();
            } catch (Exception e) {
                result = true;
            }
        } else {
            result = true;
        }
        Log.d("UiUtil", "isValidLink() end.");
        return result;
    }


    /**
     * @return UTF8形式字符串。
     * @throws UnsupportedEncodingException 不支持的字符集
     * @brief 中文字符串转换函数。
     * @author joychang
     * @param[in] str 要转换的字符串。
     * @param[in] charset 字符串编码。
     * @note 将str中的中文字符转换为UTF8编码形式。
     */
    public static String encode(String str, String charset) throws UnsupportedEncodingException {
        Log.d(TAG, "_encode() start");

        String result = null;

        if ((str != null) && (charset != null)) {
            try {
                Pattern p = Pattern.compile(DEF_ZH_PATTERN, 0);
                Matcher m = p.matcher(str);

                StringBuffer b = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
                }

                m.appendTail(b);

                result = b.toString();
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            if (str == null) {
                Log.e(TAG, "encode(): str is null");
            }

            if (charset == null) {
                Log.e(TAG, "encode(): charset is null");
            }
        }

        Log.d(TAG, "encode() end");

        return result;
    }


    /**
     * 根据日期获取对应的星期
     *
     * @param mdate
     * @return 星期
     */
    public static String getWeekToDate(String mdate) {
        String week = null;
        mdate = mdate.replace("/", "-");
//    	mdate = mdate.replace("月", "-");
//    	mdate = mdate.replace("日", "");
//    	mdate = "2014-"+mdate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(mdate);
            week = getWeek(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }


    /**
     * 从Assets目录下拷贝文件到指定目录
     *
     * @param context  上下文对象
     * @param fileName Assets目录下的指定文件名
     * @param path     要拷贝到的目录
     * @return true 拷贝成功  false 拷贝失败
     * @author drowtram
     */
    public static boolean copyApkFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete(); //如果存在这个文件，则删除重新拷贝
            }
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }

    /**
     * 获取一个路径的文件名
     *
     * @param urlpath
     * @return
     */
    public static String getFilename(String urlpath) {
        return urlpath
                .substring(urlpath.lastIndexOf("/") + 1, urlpath.length());
    }


    /***************************点播工具***********************************/


    /**
     * 筛选条件编码
     *
     * @param filter
     * @return
     */
    public static String getEcodString(String filter) {
        String s = "";
        try {
            s = URLEncoder.encode(filter, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 删除指定目录下所有apk文件
     *
     * @param dir
     * @author drowtram
     */
    public static void deleteAppApks(String dir) {
        File file = new File(dir);
        try {
            if (file.exists() && file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    String fileName = f.getName();
                    if (f.isFile() && fileName.endsWith(".apk")) {
                        if (f.delete()) {
                            Log.d("zhouchuan", "delete the " + fileName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 安装apk文件
     *
     * @param fileName
     * @author drowtram
     */
    public static void installApk(Context context, String fileName) {
        if (getUninatllApkInfo(context, fileName)) {
            File updateFile = new File(fileName);
            try {
                String[] args2 = {"chmod", "604", updateFile.getPath()};
                Runtime.getRuntime().exec(args2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*------------------------*/
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(updateFile),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
//			File file = new File(fileName);
//			Intent intent = new Intent();
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			intent.setAction(Intent.ACTION_VIEW);     //浏览网页的Action(动作)
//			String type = "application/vnd.android.package-archive";
//			intent.setDataAndType(Uri.fromFile(file), type);  //设置数据类型
//			context.startActivity(intent);
        } else {
            Toast.makeText(context, "文件还没下载完成，请耐心等待。", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断apk文件是否可以安装
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean getUninatllApkInfo(Context context, String filePath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            Log.e("zhouchuan", "*****  解析未安装的 apk 出现异常 *****" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取当前日期，包含星期几
     *
     * @return 日期字符串 xx月xx号 星期x
     * @author drowtram
     */
    public static String getStringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份  
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码  
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "日";
        } else if ("2".equals(mWay)) {
            mWay = "一";
        } else if ("3".equals(mWay)) {
            mWay = "二";
        } else if ("4".equals(mWay)) {
            mWay = "三";
        } else if ("5".equals(mWay)) {
            mWay = "四";
        } else if ("6".equals(mWay)) {
            mWay = "五";
        } else if ("7".equals(mWay)) {
            mWay = "六";
        }
        return mMonth + "月" + mDay + "日\n" + "星期" + mWay;
    }

    /**
     * 获取IP
     *
     * @return
     */
    public static String localipget() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
        }
        return null;
    }

    /**
     * 根据apk路径获取包名
     *
     * @param context
     * @param strFile apk路径
     * @return apk包名
     */
    public static String getPackageName(Context context, String strFile) {
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(strFile, PackageManager.GET_ACTIVITIES);
        String mPackageName = null;
        if (packageInfo != null) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            mPackageName = applicationInfo.packageName;
        }
        return mPackageName;
    }

    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getAppInfo(Context context) {
        try {
            String pkName = context.getPackageName();
            return pkName;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 根据包名获取apk名称
     *
     * @param context
     * @param packageName 包名
     * @return apk名称
     */
    public static String getApkName(Context context, String packageName) {
        String apkName = null;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (packageInfo != null) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                apkName = pm.getApplicationLabel(applicationInfo).toString();
                // int lable = applicationInfo.labelRes;
                // apkName = context.getResources().getString(lable);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return apkName;
    }

    /**
     * 根据apk文件获取app应用名称
     *
     * @param context
     * @param apkFilePath apk文件路径
     * @return
     */
    public static String getAppNameByApkFile(Context context, String apkFilePath) {
        String apkName = null;
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES);
        if (packageInfo != null) {
            apkName = pm.getApplicationLabel(packageInfo.applicationInfo).toString();
        }
        return apkName;
    }

    public static File getOwnCacheDirectory(Context context, String path) {
        File filesDir = context.getExternalCacheDir();
        File cacheFile = new File(filesDir, path);
        if (!cacheFile.exists()) {
            Log.i(TAG, "getOwnCacheDirectory: 创建缓存目录");
            cacheFile.mkdir();
        }
        return cacheFile;

    }

    /**
     * 获取屏幕的宽度 像素
     *
     * @param context
     * @return
     */
    public static int getDisplayMetricsWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        manager.getDefaultDisplay().getWidth();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度,像素
     *
     * @param context
     * @return
     */
    public static int getDisplayMetricsHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕的高度,像素
     *
     * @param context
     * @return
     */
    public static float getDisplay(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }


    private final static String DEF_ZH_PATTERN = "[\u4e00-\u9fa5]+";

    /**
     * @brief TAG
     */
    private static final String TAG = "Utils";


    public static void setDefaultfocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();
    }
}
