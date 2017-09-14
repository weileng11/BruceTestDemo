package com.bruce.testdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bruce.core.updateapk.dialog.UpdateAppManager;
import com.bruce.core.utils.banner.GlideImageLoader;
import com.bruce.core.utils.uploadhead.UploadHeadExplain;
import com.bruce.testdemo.QQ.QQBottomAct;
import com.bruce.testdemo.bugly.BugLyTestClass;
import com.bruce.testdemo.imagewatcher.ImagewatcherAct3;
import com.bruce.testdemo.pagerslidingtabstrip.PagerSlidingtabStripActivity;
import com.bruce.testdemo.recycleviewanim.PullToRefreshActivity;
import com.bruce.testdemo.recycleviewnoanim.CollapsingToolbarLayoutActivity;
import com.bruce.testdemo.wxphoto.wxActivity;
import com.bruce.testdemo.wxphotorecyclerview.WxRecyclerViewActivity;
import com.socks.library.KLog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.bruce.core.utils.uploadhead.UploadHeadExplain.tempFile;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.image)
    ImageView image;
    int selectMode = 1;
    private static final int REQUEST_CODE = 10086;
    @InjectView(R.id.imagewatcher)
    Button imagewatcher;
    @InjectView(R.id.imagewatcher_photo)
    Button imagewatcherPhoto;
    @InjectView(R.id.imagewatcher_photo_rv)
    Button imagewatcherPhotoRv;
    @InjectView(R.id.qq_index)
    Button qqIndex;
    @InjectView(R.id.rv_sx)
    Button rvSx;
    @InjectView(R.id.rv_anim_sx)
    Button rvAnimSx;
    @InjectView(R.id.pagerslidingtastip)
    Button pagerslidingtastip;
    @InjectView(R.id.bugly)
    Button bugly;
    @InjectView(R.id.banner)
    Banner banner;
    private UpdateAppManager manager;
    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();
    /*
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        manager = new UpdateAppManager(this);
        manager.getUpdateMsg();//检查更新
        //设置图片轮播
        setBannerShow();
        KLog.v("Logcat");
        KLog.d("Logcat");
        KLog.i("Logcat");
        KLog.w("Logcat");
        KLog.e("Logcat");
        KLog.a("Logcat");
//        KLog.json("");
    }

    /**
     * 图片轮播
     *
     * @return
     */
    public void setBannerShow() {
        //该数据只进行测试
        String[] urls = this.getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        String[] title = getResources().getStringArray(R.array.title);
        List titlelist = Arrays.asList(title);
        titles = new ArrayList(titlelist);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置图片点击监听
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(MainActivity.this, "你点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //设置标题的颜色

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @OnClick(R.id.image)
    public void onClick() {
        UploadHeadExplain.type = 2;
        UploadHeadExplain.uploadHeadImage(MainActivity.this, R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case UploadHeadExplain.REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                    Log.d("evan", "**********camera path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, Uri.fromFile(tempFile)));
                    UploadHeadExplain.gotoClipActivity(Uri.fromFile(tempFile), MainActivity.this);
                }
                break;
            case UploadHeadExplain.REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    Log.d("evan", "**********pick path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, uri));
                    UploadHeadExplain.gotoClipActivity(uri, MainActivity.this);
                }
                break;
            case UploadHeadExplain.REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = UploadHeadExplain.getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (UploadHeadExplain.type == 1) {
                        image.setImageBitmap(bitMap);
                    } else {
                        image.setImageBitmap(bitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }


    @OnClick({R.id.imagewatcher, R.id.imagewatcher_photo, R.id.imagewatcher_photo_rv,
            R.id.qq_index, R.id.rv_sx, R.id.rv_anim_sx, R.id.pagerslidingtastip, R.id.bugly})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imagewatcher:
                showActivity(this, ImagewatcherAct3.class);
                break;
            case R.id.imagewatcher_photo:
                showActivity(this, wxActivity.class);
                break;
            case R.id.imagewatcher_photo_rv:
                showActivity(this, WxRecyclerViewActivity.class);
                break;
            case R.id.qq_index:
                showActivity(this, QQBottomAct.class);
                break;
            case R.id.rv_sx:
                showActivity(this, CollapsingToolbarLayoutActivity.class);
                break;
            case R.id.rv_anim_sx:
                showActivity(this, PullToRefreshActivity.class);
                break;
            case R.id.pagerslidingtastip:
                showActivity(this, PagerSlidingtabStripActivity.class);
                break;
            case R.id.bugly:
                showActivity(this, BugLyTestClass.class);
                break;
        }
    }

}
