package com.bruce.core.net.operation;

import com.bruce.core.dialog.LoadingDialog;
import com.bruce.core.net.callback.StringCallback;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lt on 2016/5/4.
 * http请求数据接口回调
 */
public abstract class OkHttpResponseCallback<T> extends StringCallback {

    /**
     * 加载框
     */
    protected LoadingDialog ld;
    public Class<T> beanClass;
    public OkHttpResponseCallback(Class<T> beanClass){
        this.beanClass=beanClass;
    }

    public void setLoadingDialog(LoadingDialog ld){
        this.ld = ld;
    }

    public LoadingDialog getLoadingDialog(){
        return this.ld;
    }

    public abstract  void onSuccess(T bean);
    //请求失败参数返回
    public abstract  void onFailError(String e);
    //解析失败的情况返回
    public void onResponseJsonFail(RespnoseResultBean bnBean){
        //解析失败具体的操作
        System.out.print("INFO"+"解析失败");

    }
    
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return super.parseNetworkResponse(response);
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
    }

    @Override
    public void onAfter() {
        super.onAfter();
    }

    @Override
    public void inProgress(float progress) {
        super.inProgress(progress);
    }

    @Override
    public void onError(Call call, Exception e) {
        System.out.print("###########"+e);
         //请求服务器或者数据失败错误提示处理
        if(ld != null && ld.isShowing()){
            ld.dismiss();
        }
         onFailError(e.getMessage());
    }

    @Override
    public void onResponse(String response) {
        if(ld != null && ld.isShowing()){
            ld.dismiss();
        }
        //将字符串转换成对象形式
        if(response!=null&&!response.equals("")){
            String joStr = response.toString();
            System.out.print("###########"+joStr);
            Gson gson = new Gson();
            T bean = gson.fromJson(joStr, beanClass);
            if(bean instanceof RespnoseResultBean){
                RespnoseResultBean bnBean = (RespnoseResultBean)bean;
                if(bnBean.data.Stater != 1){
                    //失败返回参数处理
                    onResponseJsonFail(bnBean);
                    return;
                }
            }
            //gson转换成功
            onSuccess(bean);
        }
    }





}
