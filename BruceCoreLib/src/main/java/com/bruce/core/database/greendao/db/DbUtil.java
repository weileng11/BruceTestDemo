package com.bruce.core.database.greendao.db;


import com.bruce.core.database.greendao.dao.StudentDao;
import com.bruce.core.database.greendao.dao.UserDao;

/**
 * Created by Bruce on 2017/8/17.
 *
 * function: 获取表 Helper 的工具类,每次都要添加
 */

public class DbUtil {

    private static StudentHelper sStudentHelper;
    private static UserHelper mUserHelper;

    public static UserDao getUserDao() {
        return DbCore.getDaoSession().getUserDao();
    }

    public static UserHelper getUserHelper(){
        if (mUserHelper == null){
            mUserHelper = new UserHelper(getStudentDao());
        }
        return mUserHelper;
    }

    private static StudentDao getStudentDao(){
        return DbCore.getDaoSession().getStudentDao();
    }

    public static StudentHelper getStudentHelper(){
        if (sStudentHelper == null){
            sStudentHelper = new StudentHelper(getStudentDao());
        }
        return sStudentHelper;
    }
}
