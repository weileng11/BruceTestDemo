package com.bruce.core.utils.uploadhead;

/**
 * Author:Bruce
 * Package:com.bruce.core.utils.uploadhead
 * time:2017/9/1.
 * contact：weileng143@163.com
 *
 * @description
 */

public class Explian {

//    @OnClick(R.id.image)
//    public void onClick() {
//        UploadHeadExplain.type = 2;
//        UploadHeadExplain.uploadHeadImage(MainActivity.this, R.layout.activity_main);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        switch (requestCode) {
//            case UploadHeadExplain.REQUEST_CAPTURE: //调用系统相机返回
//                if (resultCode == RESULT_OK) {
//                    Log.d("evan", "**********camera uri*******" + Uri.fromFile(tempFile).toString());
//                    Log.d("evan", "**********camera path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, Uri.fromFile(tempFile)));
//                    UploadHeadExplain.gotoClipActivity(Uri.fromFile(tempFile),MainActivity.this);
//                }
//                break;
//            case UploadHeadExplain.REQUEST_PICK:  //调用系统相册返回
//                if (resultCode == RESULT_OK) {
//                    Uri uri = intent.getData();
//                    Log.d("evan", "**********pick path*******" + UploadHeadExplain.getRealFilePathFromUri(MainActivity.this, uri));
//                    UploadHeadExplain.gotoClipActivity(uri,MainActivity.this);
//                }
//                break;
//            case UploadHeadExplain.REQUEST_CROP_PHOTO:  //剪切图片返回
//                if (resultCode == RESULT_OK) {
//                    final Uri uri = intent.getData();
//                    if (uri == null) {
//                        return;
//                    }
//                    String cropImagePath = UploadHeadExplain.getRealFilePathFromUri(getApplicationContext(), uri);
//                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//                    if (UploadHeadExplain.type == 1) {
//                        image.setImageBitmap(bitMap);
//                    } else {
//                        image.setImageBitmap(bitMap);
//                    }
//                    //此处后面可以将bitMap转为二进制上传后台网络
//                    //......
//
//                }
//                break;
//        }
//    }

//    //此处后面可以将bitMap转为二进制上传后台网络
//    //根据路径上传图片
//    BaseNetEntity baseNetEntity = new BaseNetEntity();
//                    baseNetEntity.uploadOneImg(cropImagePath, ServiceUrl.Upload_More_Pic, new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//
//        }
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            //特别注意:response.body().string()只会被执行一次，在第一次必须要用string接收，否则
//            //会出现null,坑了一波
//            String str = response.body().string();
//            //开始转换
//            u = BaseNetEntity.JSONToObject(str, UploadPictureRespBean.class);
////                            //设置图片参数
////                            uploadSuccessPic.addAll(u.data.data);
//            mHandler.postDelayed(mr, 1000);
//            System.out.println("上传照片成功：response = " + u.data.data.get(0));
//
//        }
//    });

}
