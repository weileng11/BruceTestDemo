package com.bruce.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 这个重写的listview能解决嵌套到scrollview里以后，显示不全的问题
 */
public class MyListview extends ListView {

	public MyListview(Context context) {
		super(context);
	}

	public MyListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}