package com.bruce.testdemo;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Author:Bruce
 * Package:com.lovetoothalliance.app.ui
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description
 */

public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getSimpleName();
    private BGABanner mBackgroundBanner;
    private BGABanner mForegroundBanner;
    /**
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.activity_guide);
        mBackgroundBanner = (BGABanner) findViewById(R.id.banner_guide_background);
        mForegroundBanner = (BGABanner) findViewById(R.id.banner_guide_foreground);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        initView();
        setListener();
        processLogic();
    }

    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    public static List<?> images = new ArrayList<>();
    public static List<String> titles = new ArrayList<>();

    private void processLogic() {
//       ///该数据只进行测试
//        String[] urls = this.getResources().getStringArray(R.array.url);
//        List list = Arrays.asList(urls);
//        images = new ArrayList(list);
//        String[] title = this.getResources().getStringArray(R.array.title);
//        List titlelist = Arrays.asList(title);
//        titles = new ArrayList(titlelist);
//        mBackgroundBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
//            @Override
//            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
//                Glide.with(GuideActivity.this)
//                        .load(model)
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(itemView);
//            }
//        });
//
//        mBackgroundBanner.setData(Arrays.asList(images.get(0),images.get(1)),Arrays.asList(titles.get(0),titles.get(1)));
        // 设置数据源
        mBackgroundBanner.setData(R.drawable.uoko_guide_background_1, R.drawable.uoko_guide_background_2, R.drawable.uoko_guide_background_3);

        mForegroundBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
