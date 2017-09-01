package com.bruce.core.database.greendao.db;

import android.content.Context;

import com.bruce.core.database.greendao.dao.DaoMaster;
import com.bruce.core.database.greendao.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;


/**
 * Created by Bruce on 2017/8/17.
 * <p>
 * function:核心辅助类DbCore, 用于获取DaoMaster和DaoSession
 */

public class DbCore {

    private static final String DEFAULT_DB_NAME = "green-dao3.db"; //默认数据库名称
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static Context mContext;
    private static String DB_NAME; //数据库名称
    private static volatile DbCore sDbCore = new DbCore();  //采用单例模式创建该对象
    private static DaoMaster.OpenHelper sHelper;

    private DbCore() {
    }

    /**
     * function:单例模式获得数据库对象
     * @return
     */
    public static DbCore getInstance() {
        if (sDbCore == null) {
            synchronized (DbCore.class) {
                if (sDbCore == null) {
                    sDbCore = new DbCore();
                }
            }
        }
        return sDbCore;
    }

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    /**
     * function:初始化
     *
     * @param context
     * @param dbName
     */
    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }


    /**
     * function:获取DaoMaster对象
     *
     * @return
     */
    public static com.bruce.core.database.greendao.dao.DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            //此处不可用 DaoMaster.DevOpenHelper, 那是开发辅助类，我们要自定义一个，方便升级
            sHelper = new MyOpenHelper(mContext, DB_NAME);
            mDaoMaster = new com.bruce.core.database.greendao.dao.DaoMaster(sHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }


    /**
     * function:获取DaoSession对象
     *
     * @return
     */
    public static com.bruce.core.database.greendao.dao.DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * function:打印日志
     */
    public static void enableQueryBuilderLog() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * function: 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    private void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }


    private void closeHelper() {
        if (sHelper != null) {
            sHelper.close();
            sHelper = null;
        }
    }

}
