package com.bruce.testdemo.QQ;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Toast;

import com.bruce.core.utils.qqnaviview.QQNaviView;
import com.bruce.core.view.HomeViewPager;
import com.bruce.testdemo.BaseActivity;
import com.bruce.testdemo.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:Bruce
 * Package:com.bruce.testdemo.QQ
 * time:2017/9/2.
 * contact：weileng143@163.com
 *
 * @description
 */

public class QQBottomAct extends BaseActivity {
    @InjectView(R.id.qq_view_bubble)
    QQNaviView mBubbleView;
    @InjectView(R.id.qq_view_person)
    QQNaviView mPersonView;
    @InjectView(R.id.qq_view_star)
    QQNaviView mStarView;
    @InjectView(R.id.m_viewpager)
    HomeViewPager mViewpager;

    private TestFragment1 mMessageFg;
    private TestFragment2 mMyFg;
    private TestFragment3 mRdFg;

    private ArrayList<Fragment> fragmentList;

    /**
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.qqbottom_act);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        mBubbleView.setBigIcon(R.drawable.bubble_big);
        mBubbleView.setSmallIcon(R.drawable.bubble_small);


        mMessageFg = new TestFragment1();
        mMyFg = new TestFragment2();
        mRdFg = new TestFragment3();

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mMessageFg);
        fragmentList.add(mRdFg);
        fragmentList.add(mMyFg);

        mViewpager.setAdapter(new MyViewPagerAdapter(this.getSupportFragmentManager()));
//        mViewpager.setOnPageChangeListener(new PageChangeListener());
        mViewpager.setOffscreenPageLimit(3);
    }

    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    private void resetIcon() {
        mBubbleView.setBigIcon(R.drawable.pre_bubble_big);
        mBubbleView.setSmallIcon(R.drawable.pre_bubble_small);

        mPersonView.setBigIcon(R.drawable.pre_person_big);
        mPersonView.setSmallIcon(R.drawable.pre_person_small);

        mStarView.setBigIcon(R.drawable.pre_star_big);
        mStarView.setSmallIcon(R.drawable.pre_star_small);
    }

    private void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.qq_view_bubble, R.id.qq_view_person, R.id.qq_view_star})
    public void onClick(View view) {
        resetIcon();
        switch (view.getId()) {
            case R.id.qq_view_bubble:
                mViewpager.setCurrentItem(0);
                mBubbleView.setBigIcon(R.drawable.bubble_big);
                mBubbleView.setSmallIcon(R.drawable.bubble_small);
                showToast("bubble");
                break;
            case R.id.qq_view_person:
                mViewpager.setCurrentItem(1);
                mPersonView.setBigIcon(R.drawable.person_big);
                mPersonView.setSmallIcon(R.drawable.person_small);
                showToast("person");
                break;
            case R.id.qq_view_star:
                mViewpager.setCurrentItem(2);
                mStarView.setBigIcon(R.drawable.star_big);
                mStarView.setSmallIcon(R.drawable.star_small);
                showToast("star");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
