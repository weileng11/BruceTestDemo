package com.bruce.testdemo.bugly;


import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Author:Bruce
 * Package:com.bruce.testdemo
 * time:2017/9/6.
 * contact：weileng143@163.com
 *
 * @description
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.bruce.testdemo.bugly.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
