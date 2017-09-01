package com.bruce.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class SharedPreferencesUtils {
	
	private final static String name = "config";
	private final static int mode = Context.MODE_PRIVATE;
	
	/**
	 * 保存SharedPreference（boolean）类型
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context,String key,boolean value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	/**
	 * 保存SharedPreference（Int）类型
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveInt(Context context,String key,int value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	/**
	 * 保存SharedPreference（String）类型
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveString(Context context,String key,String value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}
	
	
	/**
	 * 获取SharedPreference（String）类型
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context,String key,boolean defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getBoolean(key, defValue);
	}

	/**
	 * 获取SharedPreference（Int）类型
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static int getInt(Context context,String key,int defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getInt(key, defValue);
	}

	/**
	 * 获取SharedPreference（String）类型
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context,String key,String defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getString(key, defValue);
	}

}
