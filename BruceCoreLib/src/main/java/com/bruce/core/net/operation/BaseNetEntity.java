package com.bruce.core.net.operation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bruce.core.dialog.LoadingDialog;
import com.bruce.core.net.OkHttpUtils;
import com.bruce.core.net.builder.PostFormBuilder;
import com.bruce.core.net.callback.BitmapCallback;
import com.bruce.core.net.callback.FileCallBack;
import com.bruce.core.utils.StaticUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * operation 只是操作类，可更改，其他已经封装好的，暂时不动
 * okhttps网络请求工具类
 */
public class BaseNetEntity {

    /**
     * post方式以传入对象方式请求网络
     *
     * @param context
     * @param url
     * @param beanClass
     * @param stringCallback
     * @param <T>
     */
    public static <T> void sendPostJson(final Context context, final String url, final String message, T beanClass, OkHttpResponseCallback stringCallback, boolean isShowDialog) {
        if (isShowDialog && StaticUtils.isValidContext(context)) {
            LoadingDialog ld = LoadingDialog.createDialog(context);
            if (TextUtils.isEmpty(message)) {
                ld.setMessage("正在加载数据");
            } else {
                ld.setMessage(message);
            }
            ld.show();
            //跳转到返回字符串界面关闭loading
            stringCallback.setLoadingDialog(ld);
        }
//        String strMd5 = safeVerificationMd5();
        //User-Agent  
        OkHttpUtils
                .postString()
                .url(url)
                //加入head，并且加密
//                .addHeader("md5", strMd5)
                .tag(context)
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
                .content(getSendData(beanClass))
                .build()
                .execute(stringCallback);
    }

//    /**
//     * Md5加密 （安全验证）
//     * @return
//     */
//    public static String safeVerificationMd5() {
//        //分配的spid
//        String spid = "1111";
//        //MD5密钥 默认keyde 长度必须大于8
//        String key = "2222123123123123";
//        //日期时间，格式：yyyyMMddHHmmss
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//        String time = formatter.format(curDate);
//        String strMd5 = KeysUtil.desEncrypt(spid + "|" + key + "|" + time, key);
//        LoggerUtils.i(TAG, strMd5);
//        return strMd5;
//    }
//

    /**
     * 将对象转换成字符串
     *
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> String getSendData(T beanClass) {
        if (beanClass == null) {
            return "";
        }
        T bean = beanClass;
        Gson gson = new Gson();
        String str = gson.toJson(bean);
        System.err.println("########" + str);
        return str;
    }

    /**
     * 将字符串转换成对象(图片转换)
     */
    public static <T> T JSONToObject(String json, Class<T> beanClass) {
        Gson gson = new Gson();
        T res = gson.fromJson(json, beanClass);
        return res;
    }


    /**
     * post 以Map键值对方式操作网络请求
     *
     * @param activity
     * @param url
     * @param params
     * @param stringCallback
     */
    public static void post(Activity activity, final String url, final List<Map<String, String>> params, OkHttpResponseCallback stringCallback) {
        if (params == null) {
            return;
        }
        PostFormBuilder builder = OkHttpUtils
                .post()
                .url(url)
                .tag(activity);

        for (int i = 0; i < params.size(); i++) {
            Map<String, String> map = params.get(i);
            for (String key : map.keySet()) {
                builder.addParams(key, map.get(map.keySet()));
            }
        }
        builder.build()
                .execute(stringCallback);

    }

    /**
     * get 网络请求方式
     *
     * @param activity
     * @param url
     * @param stringCallback
     */
    public static <T> void get(Activity activity, String url, final String message, boolean isShowDialog, OkHttpResponseCallback stringCallback) {
        if (isShowDialog && StaticUtils.isValidContext(activity)) {
            LoadingDialog ld = LoadingDialog.createDialog(activity);
            if (TextUtils.isEmpty(message)) {
                ld.setMessage("正在加载数据");
            } else {
                ld.setMessage(message);
            }
            ld.show();
            //跳转到返回字符串界面关闭loading
            stringCallback.setLoadingDialog(ld);
        }
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(activity)
                .build()
                .execute(stringCallback);
    }

    /**
     * get 网络请求方式
     *
     * @param activity
     * @param url
     * @param stringCallback
     */
    public void get2(Activity activity, final String message, boolean isShowDialog, String url, OkHttpResponseCallback stringCallback) {
        if (isShowDialog && StaticUtils.isValidContext(activity)) {
            LoadingDialog ld = LoadingDialog.createDialog(activity);
            if (TextUtils.isEmpty(message)) {
                ld.setMessage("正在加载数据");
            } else {
                ld.setMessage(message);
            }
            ld.show();
            //跳转到返回字符串界面关闭loading
            stringCallback.setLoadingDialog(ld);
        }
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(activity)
                .build()
                .execute(stringCallback);
    }


    /**
     * 根据url显示某一张网络图片
     *
     * @param activity
     * @param mImageView
     * @param url
     */
    public static void dispaly(Activity activity, final ImageView mImageView, String url) {
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(activity)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * @param params         上传文件需要的参数
     * @param activity
     * @param filePath       文件路径
     * @param fileName       文件名字
     * @param url            上传路径
     * @param stringCallback 回调
     */
    public void uploadFile(Map<String, String> params, Activity activity, final File filePath, String fileName, String url, OkHttpResponseCallback stringCallback) {
        //Environment.getExternalStorageDirectory()
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            Toast.makeText(activity, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
//        Map<String, String> headers = new HashMap<>();
//        headers.put("APP-Key", "APP-Secret222");
//        headers.put("APP-Secret", "APP-Secret111");
        OkHttpUtils.post()//
                .addFile("mFile", fileName, file)//
                .url(url)//
                .params(params)//
//                .headers(headers)//
                .build()//
                .execute(stringCallback);
    }

    /**
     * 以post方式来提交文件
     *
     * @param activity
     * @param filePath         文件相对路径 如:Environment.getExternalStorageDirectory()
     * @param fileName         文件的名字
     * @param url
     * @param responseCallback 回调
     */
    public void postFile(Activity activity, File filePath, String fileName, String url, OkHttpResponseCallback responseCallback) {
        File file = new File(filePath, fileName);
        if (!file.exists()) {
            Toast.makeText(activity, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .postFile()
                .url(url)
                .tag(activity)
                .file(file)
                .build()
                .execute(responseCallback);

    }

    /**
     * 异步下载文件
     * url:xxx.jpg
     */
    private void downAsynFile(String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File("/sdcard/123.jpg"));
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                } catch (IOException e) {
                    Log.i("InfoMSG", "IOException");
                    e.printStackTrace();
                }
                Log.d("InfoMSG", "文件下载成功");
            }
        });
    }

    /**
     * 异步上传文件
     */
    private static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private void postAsynFile() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        File file = new File("/sdcard/123.txt");
        Request request = new Request.Builder()
                .url("https://www.****.php") //地址
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("InfoMSG", response.body().string());
            }
        });
    }

    /**
     * okhttp3多个文件上传，也可以上传多个图片，但是每次都要addFile
     * 相对来说不是特别的理想
     *
     * @param
     */
    public void multiFileUpload(Activity activity, File filePath, String fileName, String url, OkHttpResponseCallback responseCallback) {
//        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
//        File file2 = new File(Environment.getExternalStorageDirectory(), "test1#.txt");

//        if (!file.exists()) {
//            Toast.makeText(activity, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        Map<String, String> params = new HashMap<>();
//        params.put("username", "张鸿洋");
//        params.put("password", "123");

//        String url = "服务器地址" + "user!uploadFile";
        OkHttpUtils.post()//

//                .addFile("mFile", "messenger_01.png", file)//
//                .addFile("mFile", "test1.txt", file2)//
                .url(url)
//                .params(params)//
                .build()//
                .execute(responseCallback);
    }

    /**
     * 下载文件
     */
    public void downloadFile(String url2) {
        String url = "https://github.com/hongyangAndroid/okhttp-utils/blob/master/okhttputils-2_4_1.jar?raw=true";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "gson-2.2.1.jar") {
                    @Override
                    public void inProgress(float progress) {
//                        mProgressBar.setProgress((int) (100 * progress));
//                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(File response) {
//                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    /**
     * 暂时没有使用okhttp封装自己写的一个多张图片上传
     * 文件形式上传
     */
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void sendMultipart(String updateUrl, List<File> fileList) {
//        File sdcache = sdPath;//sd卡的位置
//        int cacheSize = 10 * 1024 * 1024;
        //设置超时时间及缓存
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
//                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));

        OkHttpClient mOkHttpClient = builder.build();
        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        int i = 0;
        for (File file : fileList) {
            if (file.exists()) {
                Log.i("imageName:", file.getName());//经过测试，此处的名称不能相同，如果相同，只能保存最后一个图片，不知道那些同名的大神是怎么成功保存图片的。
                mbody.addFormDataPart("image" + i, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                i++;
            }
        }

        RequestBody requestBody = mbody.build();
        Request request = new Request.Builder()
//                .header("Authorization", "Client-ID " + "...")
                .url(updateUrl)
                .post(requestBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("InfoMSG", response.body().string());
            }
        });
    }

    private static final MediaType MEDIA_TYPE_PNGIMG = MediaType.parse("image/png");
    private final OkHttpClient client2 = new OkHttpClient();

    /**
     * 上传多张图片（已测试ok）
     *
     * @param mImgUrls 路径集合
     * @param BASE_URL 路径
     * @param callback 返回的resp
     */
    public void uploadImg(List<String> mImgUrls, String BASE_URL, Callback callback) {
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < mImgUrls.size(); i++) {
            File f = new File(mImgUrls.get(i));
            if (f != null) {
                Log.i("imageName:", f.getName());
                builder.addFormDataPart("img" + i, f.getName(), RequestBody.create(MEDIA_TYPE_PNGIMG, f));
            }
        }
        //添加其它信息
//        builder.addFormDataPart("time",takePicTime);
//        builder.addFormDataPart("mapX", SharedInfoUtils.getLongitude());
//        builder.addFormDataPart("mapY",SharedInfoUtils.getLatitude());
//        builder.addFormDataPart("name",SharedInfoUtils.getUserName());

        final MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(BASE_URL)//地址
                .post(requestBody)//添加请求体
                .build();
        client2.newCall(request).enqueue(callback);
    }

    /**
     * 上传一张图片（已测试ok）
     *
     * @param mImgUrl  单个路径
     * @param BASE_URL 路径
     * @param callback 返回的resp
     */
    public void uploadOneImg(String mImgUrl, String BASE_URL, Callback callback) {
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File f = new File(mImgUrl);
        if (f != null) {
            Log.i("imageName:", f.getName());
            builder.addFormDataPart("img", f.getName(), RequestBody.create(MEDIA_TYPE_PNGIMG, f));
        }

        //添加其它信息
//        builder.addFormDataPart("time",takePicTime);
//        builder.addFormDataPart("mapX", SharedInfoUtils.getLongitude());
//        builder.addFormDataPart("mapY",SharedInfoUtils.getLatitude());
//        builder.addFormDataPart("name",SharedInfoUtils.getUserName());

        final MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(BASE_URL)//地址
                .post(requestBody)//添加请求体
                .build();
        client2.newCall(request).enqueue(callback);
    }

    private static final MediaType MEDIA_TYPE_PNGTIME = MediaType.parse("image/*");
    /**
     * 设置时间
     */
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).readTimeout(15, TimeUnit.SECONDS)// 设置读取超时时间
            .writeTimeout(15, TimeUnit.SECONDS)// 设置写的超时时间
            .connectTimeout(15, TimeUnit.SECONDS)// 设置连接超时时间
            .build();

    /**
     * 以键值对的形式传递过来
     *
     * @param map
     * @param url 路径
     */
    private final static void uploadImgAndParameter(Map<String, Object> map, String url) {

        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    if (entry.getValue() instanceof File) {
                        File f = (File) entry.getValue();
                        builder.addFormDataPart(entry.getKey(), f.getName(),
                                RequestBody.create(MEDIA_TYPE_PNGTIME, f));
                    } else {
                        builder.addFormDataPart(entry.getKey(), entry
                                .getValue().toString());
                    }
                }

            }
        }
        // 创建RequestBody
        RequestBody body = builder.build();
        final Request request = new Request.Builder().url(url)// 地址
                .post(body)// 添加请求体
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(Call call, final Response response)
                    throws IOException {
                final String data = response.body().string();
                Log.i(TAG, "上传照片成功-->" + data);
                call.cancel();// 上传成功取消请求释放内存
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                Log.i(TAG, "上传失败-->" + e.getMessage());
                call.cancel();// 上传失败取消请求释放内存
            }

        });

    }

    /**
     * @brief 日志标记。
     */
    private static String TAG = "BaseNetEntity";
    public static String CHARSET_ENCODING = "UTF-8";

    /**
     * 取消tag
     *
     * @param activity
     */
    public static void cancelTag(Activity activity) {
        OkHttpUtils.cancelTag(activity);
    }

    public static void cancelTag(String url) {
        OkHttpUtils.get().url(url).build().cancel();
    }

}
