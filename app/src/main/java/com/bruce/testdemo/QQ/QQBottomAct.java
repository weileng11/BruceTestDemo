package com.bruce.testdemo.QQ;

import android.view.View;
import android.widget.Toast;

import com.bruce.core.utils.qqnaviview.QQNaviView;
import com.bruce.testdemo.BaseActivity;
import com.bruce.testdemo.R;

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
                mBubbleView.setBigIcon(R.drawable.bubble_big);
                mBubbleView.setSmallIcon(R.drawable.bubble_small);
                showToast("bubble");
                break;
            case R.id.qq_view_person:
                mPersonView.setBigIcon(R.drawable.person_big);
                mPersonView.setSmallIcon(R.drawable.person_small);
                showToast("person");
                break;
            case R.id.qq_view_star:
                mStarView.setBigIcon(R.drawable.star_big);
                mStarView.setSmallIcon(R.drawable.star_small);
                showToast("star");
                break;
        }
    }
}
