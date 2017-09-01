package com.bruce.core.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class DensityUtil {

	/**
	 * dp转px
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static int dp2px(Context context, float dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;

		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		System.out.println("######### px=" + pxValue);
		System.out.println("######### dp=" + pxValue / scale + 0.5f);
		return (int) (pxValue / scale + 0.5f);
	}
	
	  /** 
     * 将px值转换为sp值，保证文字大小不变 
     */  
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     */  
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    } 

	/**
	 * 根据手机的分辨率计算表情可显示的列数
	 */
	public static int getFaceColumn(Context context) {
		int column = 0;
		WindowManager wm = (WindowManager) context.getSystemService(
				Context.WINDOW_SERVICE);

		int width = wm.getDefaultDisplay().getWidth();
//		int height = wm.getDefaultDisplay().getHeight();
		column = (int) Math.floor((width-36)/130);
		
		System.out.println("########### width="+width);
		System.out.println("########### column="+column);
		
		return column;
	}
	/**
	 * 获取屏幕宽度
	 */
	public static int getWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(
				Context.WINDOW_SERVICE);
		
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
	
	/**
	 * 获取屏幕宽度
	 */
	public static int getHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(
				Context.WINDOW_SERVICE);
		
		int height = wm.getDefaultDisplay().getHeight();
		return height;
	}
	
	public static float getDensity(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		display.getMetrics(displayMetrics);
		float density = displayMetrics.density; //得到密度
		return density;
	}

	
	
}