package com.bruce.testdemo.pagerslidingtabstrip;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.bruce.core.view.PagerSlidingTabStrip;
import com.bruce.testdemo.BaseActivity;
import com.bruce.testdemo.R;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Author:Bruce
 * Package:com.bruce.testdemo.pagerslidingtabstrip
 * time:2017/9/4.
 * contact：weileng143@163.com
 *
 * @description
 */

public class PagerSlidingtabStripActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @InjectView(R.id.pager)
    ViewPager pager;
    /**
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.pagerslidingtabstrip_act);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        ArrayList<Fragment> pages = new ArrayList<Fragment>();
        String[] titls = {"全部", "待付款", "待使用", "待评价"};
        pages.add(new OneFragment());
        pages.add(new TwoFragment());
        pages.add(new ThreeFragment());
        pages.add(new FourFragment());
        //页面适配器
        AbFragmentPagerAdapter fp = new AbFragmentPagerAdapter(this.getSupportFragmentManager(), pages, titls);
        pager.setAdapter(fp);
        pager.setOffscreenPageLimit(4);
        tabs.setOnPageChangeListener(this);
        tabs.setViewPager(pager);

        setTabsValue();
        //设置小红点,item从0开始计算
//        tabs.setMsgToast(1, true);
        tabs.setMsgToast(2, true);
        tabs.setMsgToast(4, true);
        tabs.setMsgToast(6, true);
    }


    private void setTabsValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置Tab底部选中的指示器Indicator的高度
        tabs.setIndicatorHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab底部选中的指示器 Indicator的颜色
        tabs.setIndicatorColorResource(R.color.colorPrimary);
        //设置指示器Indicatorin是否跟文本一样宽，默认false
        tabs.setIndicatorinFollowerTv(false);
        //设置小红点提示，item从0开始计算，true为显示，false为隐藏
//        tabs.setMsgToast(2, true);
        //设置红点滑动到当前页面自动消失,默认为true
        tabs.setMsgToastPager(true);
        //设置Tab标题文字的颜色
        //tabs.setTextColor(R.color.***);
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, dm));
        // 设置选中的Tab文字的颜色
        tabs.setSelectedTextColorResource(R.color.colorPrimary);
        //设置Tab底部分割线的高度
        tabs.setUnderlineHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, dm));
        //设置Tab底部分割线的颜色
        //tabs.setUnderlineColorResource(R.color.colorGray);
        // 设置点击某个Tab时的背景色,设置为0时取消背景色tabs.setTabBackground(0);
//        tabs.setTabBackground(R.drawable.bg_tab);
        tabs.setTabBackground(0);
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {

    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
