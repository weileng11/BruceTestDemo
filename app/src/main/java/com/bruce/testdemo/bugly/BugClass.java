package com.bruce.testdemo.bugly;

import android.util.Log;

/**
 * Created by Widsom Zhang on 2017/2/7.
 */

public class BugClass {

    public String bug() {
        String str = "This is a bug";
//       String str = null;
        Log.e("zhang", "BugClass --> bug--> str length :" + str.length());
//        return str + "  , fixed!!!";
        return "总会过去的33333333333333333333333333333xxx";
    }
}
