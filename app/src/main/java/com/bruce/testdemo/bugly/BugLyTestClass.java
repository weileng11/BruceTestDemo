package com.bruce.testdemo.bugly;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bruce.testdemo.BaseActivity;
import com.bruce.testdemo.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Author:Bruce
 * Package:com.bruce.testdemo.bugly
 * time:2017/9/6.
 * contact：weileng143@163.com
 *
 * @description
 */

public class BugLyTestClass extends BaseActivity {
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.activity_main)
    RelativeLayout activityMain;

    /**
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.bugly_testclass_act);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn)
    public void onClick() {
        BugClass bugClass = new BugClass();
        String bug = bugClass.bug();
        Toast.makeText(BugLyTestClass.this, bug, Toast.LENGTH_SHORT).show();
    }
}
