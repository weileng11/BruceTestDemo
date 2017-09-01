package com.bruce.testdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bruce.core.utils.uploadhead.UploadHeadExplain;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.bruce.core.utils.uploadhead.UploadHeadExplain.tempFile;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.image)
    ImageView image;
    int selectMode = 1;
    private static final int REQUEST_CODE = 10086;
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

    }

    @OnClick(R.id.image)
    public void onClick() {
        UploadHeadExplain.type = 2;
        UploadHeadExplain.uploadHeadImage(MainActivity.this,R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case UploadHeadExplain.REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
                    Log.d("evan", "**********camera path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, Uri.fromFile(tempFile)));
                    UploadHeadExplain.gotoClipActivity(Uri.fromFile(tempFile),MainActivity.this);
                }
                break;
            case UploadHeadExplain.REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    Log.d("evan", "**********pick path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, uri));
                    UploadHeadExplain.gotoClipActivity(uri,MainActivity.this);
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


}
