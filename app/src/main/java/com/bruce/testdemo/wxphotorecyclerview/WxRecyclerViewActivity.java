package com.bruce.testdemo.wxphotorecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bruce.testdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿微信图片测试，原文地址:https://github.com/MrWangChong/DragPhotoView
 */
public class WxRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wxphoto_act);
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<ImageInfo> infos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ImageInfo info = new ImageInfo();
            info.title = "这是一组美女图片" + "---item=" + i;
            info.images = new String[7];
            info.images[0] = "http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg";
            info.images[1] = "http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg";
            info.images[2] = "http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg";
            info.images[3] = "http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg";
            info.images[4] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491733739564&di=0a5eebc3dd3671e8fc56b1c704126b84&imgtype=0&src=http%3A%2F%2Fimg1.7wenta.com%2Fupload%2Fqa_headIcons%2F20140715%2F14054040879164181.jpg";
            info.images[5] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491733773628&di=dee740c0b4699a67763e0549ba638452&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3Dab731cc778f0f736d8fe4c093a54b382%2Fac31272442a7d933c882cecbad4bd11372f001e5.jpg";
            info.images[6] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491733836847&di=f09d674b185d7d5954fd7108af6eacd3&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201312%2F05%2F20131205172036_TWSmB.jpeg";
            infos.add(info);
        }

        recyclerview.setAdapter(new DetailAdapter(this, infos));
    }
}
