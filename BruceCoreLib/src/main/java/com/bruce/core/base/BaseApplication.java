package com.bruce.core.base;

import android.app.Application;

import com.bruce.core.database.greendao.db.DbCore;
import com.bruce.core.database.ormlite.BaseDao;
import com.bruce.core.database.ormlite.DatabaseHelper;
import com.bruce.core.database.ormlite.IOperateType;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.socks.klog.BuildConfig;
import com.socks.library.KLog;

/**
 * Author:Bruce
 * time:2017/8/31.
 * contact：weileng143@163.com
 * @description
 */

public class BaseApplication extends Application {
    private volatile static BaseApplication baseApplicationContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplicationContext = this;
//        greenDaoInit();//greenDao数据库初始化
    }

    public static BaseApplication getInstance() {
        if(baseApplicationContext==null){
            synchronized (BaseApplication.class){
                if(baseApplicationContext==null){
                    baseApplicationContext=new BaseApplication();
                }
            }
        }
        return (BaseApplication) baseApplicationContext;
    }

    /**
     * greenDao数据库初始化
     */
    public void greenDaoInit(){
        //初始化数据库
        DbCore.getInstance().init(this);
        //开启调试log
        DbCore.enableQueryBuilderLog();

        //log管理
        KLog.init(BuildConfig.DEBUG);
    }

    //=================================ormlite数据库初始化================//
    /**
     * 得到操作数据库的dao
     *
     * @param clazz
     * @return
     */
    public/*protected*/ <T extends IOperateType> BaseDao<T> getDao(final Class<T> clazz) {
        BaseDao<T> baseDao = new BaseDao<T>() {

            @Override
            public Dao<T, Integer> getDao() throws java.sql.SQLException {
                // TODO Auto-generated method stub
                return getHelper().getDao(clazz);
            }
        };
        return baseDao;
    }

    private DatabaseHelper dataHelper = null;

    private DatabaseHelper getHelper() {
        if (dataHelper == null) {
            dataHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return dataHelper;
    }

    private void replaceHelper() {
        if (dataHelper != null) {
            OpenHelperManager.releaseHelper();
            dataHelper = null;
        }
    }
    //=================================ormlite数据库初始化================//
}
