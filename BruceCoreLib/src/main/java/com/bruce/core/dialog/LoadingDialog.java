package com.bruce.core.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bruce.core.R;


/**
 * @author qiwenming
 * @date 2015-10-29 下午4:02:38 
 * @ClassName LoadingDialog 
 * @Package com.cshtong.app.dialog 
 * @Description:  网络正在加载中 dialog
 */
public class LoadingDialog extends Dialog {

	private Context context;
	private static LoadingDialog lodingDialog;

	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}

	public LoadingDialog(Context context, boolean cancelable,
                         OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
	}

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (lodingDialog == null) {
			return;
		}

		ImageView imageView = (ImageView) lodingDialog
				.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();//开始动画
	}

	/**
	 * 创建dialog
	 * 
	 * @param context
	 * @return
	 */
	public static LoadingDialog createDialog(Context context) {
		lodingDialog = new LoadingDialog(context, R.style.MyDialog_two);
		lodingDialog.setCancelable(false);
		lodingDialog.setContentView(R.layout.loading_dialog_layout);
		lodingDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return lodingDialog;
	}

	/**
	 * 设置消息 内容
	 * 
	 * @param strMessage
	 */
	public void setMessage(String strMessage) {
		TextView tvMsg = (TextView) lodingDialog
				.findViewById(R.id.id_tv_loadingmsg);

		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}

	}
}
