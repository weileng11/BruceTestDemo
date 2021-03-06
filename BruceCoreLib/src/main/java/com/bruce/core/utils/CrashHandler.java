package com.bruce.core.utils;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.lang.Thread.UncaughtExceptionHandler;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告. 
 *  
 * @author lt
 *  
 */  
public class CrashHandler implements UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
      
    //系统默认的UncaughtException处理类   
    private UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例  
    private static CrashHandler INSTANCE = new CrashHandler();  
    //程序的Context对象  
    private Context mContext;
    //用来存储设备信息和异常信息  
    private Map<String, String> infos = new HashMap<String, String>();
  
    //用于格式化日期,作为日志文件名的一部分  
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    

    private String logtitle = "qlemonerr";
    
  
    /** 保证只有一个CrashHandler实例 */  
    private CrashHandler() {  
    }  
  
    /** 获取CrashHandler实例 ,单例模式 */  
    public static CrashHandler getInstance() {  
        return INSTANCE;  
    }  
    
    
  
    /** 
     * 初始化 
     *  
     * @param context 
     */  
    public void init(Context context) {
        mContext = context;  
        //获取系统默认的UncaughtException处理器  
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器  
        Thread.setDefaultUncaughtExceptionHandler(this);
    }  
  
    /** 
     * 当UncaughtException发生时会转入该函数来处理 
     */  
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {  
            //如果用户没有处理则让系统默认的异常处理器来处理  
            mDefaultHandler.uncaughtException(thread, ex);  
        } else {  
        	 //清空缓存数据
//            LoveToothApplication.getInstance().clearCacheData();
			//清除sessionId
//            LoveToothApplication.getInstance().clearSession();
            //杀死该应用进程  
        	android.os.Process.killProcess(android.os.Process.myPid());
        }  

    }  
  
    
    Handler h = new Handler(){
    	@Override
    	public void handleMessage(Message msg) {
    		// TODO Auto-generated method stub
//    		Toast.makeText(mContext, "功能出了异常", 0).show();
    		super.handleMessage(msg);
    	}
    };
    
    /** 
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 
     *  
     * @param ex 
     * @return true:如果处理了该异常信息;否则返回false. 
     */  
    private boolean handleException(Throwable ex) {
        if (ex == null) {  
            return false;  
        }  
        //使用Toast来显示异常信息  
        new Thread() {
            @Override
            public void run() {  
//            	h.sendEmptyMessage(0);
                Looper.prepare();
                Toast.makeText(mContext, "功能出了异常", Toast.LENGTH_LONG).show();
                Looper.loop();
            }  
        }.start();  
        //收集设备参数信息   
//        collectDeviceInfo(mContext);  
        //保存日志文件   
//        saveCrashInfo2File(ex);  
        return true;  
    }  
      
  
  
  
}  