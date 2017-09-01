package com.bruce.core.database.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * 
 * @author Bruce  需要继续封装一下，暂时没弄
 * @Description 表配置
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DB_NAME = "sqlite-mk.db";
	public DatabaseHelper(Context context) {
		this(context,1);
	}
	public DatabaseHelper(Context context,int databaseVersion) {
		super(context, DB_NAME, null, databaseVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {
		try {
			//时间戳表必须最先建立
//			TableUtils.createTable(connectionSource, Timestamp.class);

		} catch (android.database.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource connectionSource, int arg2,
			int arg3) {
		System.out.println("update  db");
		//Toast.makeText(MsApplication.applicationContext, "数据库升级", 0).show();
		try {
//			TableUtils.dropTable(connectionSource, Member.class, true);

			onCreate(arg0, connectionSource);
		} catch (android.database.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
