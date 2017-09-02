package com.bruce.testdemo.wxphoto;

import com.bruce.core.view.MyListview;
import com.bruce.testdemo.BaseActivity;
import com.bruce.testdemo.R;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Author:Bruce
 * Package:com.bruce.testdemo.wxphoto
 * time:2017/9/2.
 * contact：weileng143@163.com
 *
 * @description  http://blog.csdn.net/allen315410/article/details/40264551/
 */

public class wxActivity extends BaseActivity {
    @InjectView(R.id.lv_evaluate)
    MyListview lvEvaluate;

    /**
     * 初始化界面布局
     */
    @Override
    protected void initView() {
        setContentView(R.layout.wx_act);
    }

    /**
     * 初始化界面数据
     */
    @Override
    protected void initData() {
        ArrayList<ItemEntity> itemEntities=ItemEntityData.initTestData();
        lvEvaluate.setAdapter(new ListItemAdapter(this, itemEntities));
    }

}
