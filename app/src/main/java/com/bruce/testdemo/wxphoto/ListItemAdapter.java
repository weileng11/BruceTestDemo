package com.bruce.testdemo.wxphoto;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bruce.core.view.CircleImageView;
import com.bruce.core.view.MyGridView;
import com.bruce.testdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * 首页ListView的数据适配器
 * 
 * @author Administrator
 * 
 */
public class ListItemAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ItemEntity> items;

	public ListItemAdapter(Context ctx, ArrayList<ItemEntity> items) {
		this.mContext = ctx;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_list, null);
			holder.iv_avatar = (CircleImageView) convertView.findViewById(R.id.iv_avatar);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
//			holder.rb_count = (RatingBar) convertView.findViewById(R.id.rb_count);
			holder.gridview = (MyGridView) convertView.findViewById(R.id.gridview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ItemEntity itemEntity = items.get(position);
		holder.tv_title.setText(itemEntity.getTitle());
		holder.tv_date.setText("201777");
//		holder.rb_count.setClickable(false);
//		holder.rb_count.setStar(3.6f);
		holder.tv_content.setText(itemEntity.getContent());//具体内容

		// 使用Gilde加载网络图片  第一次默认显示 placeholder(R.mipmap.ic_launcher)
		Glide.with(mContext).load(itemEntity.getAvatar()).error(R.mipmap.ic_launcher).into(holder.iv_avatar);
		final ArrayList<String> imageUrls = itemEntity.getImageUrls();
		if (imageUrls == null || imageUrls.size() == 0) { // 没有图片资源就隐藏GridView
			holder.gridview.setVisibility(View.GONE);
		} else {
			holder.gridview.setAdapter(new NoScrollGridAdapter(mContext, imageUrls));
		}
		// 点击回帖九宫格，查看大图
		holder.gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				imageBrower(position, imageUrls);
			}
		});
		return convertView;
	}

	/**
	 * 打开图片查看器
	 * 
	 * @param position
	 * @param urls2
	 */
	protected void imageBrower(int position, ArrayList<String> urls2) {
		Intent intent = new Intent(mContext, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		mContext.startActivity(intent);
	}

	/**
	 * listview组件复用，防止“卡顿”
	 * 
	 * @author Administrator
	 * 
	 */
	class ViewHolder {
		private CircleImageView iv_avatar;
		private TextView tv_title;
		private TextView tv_date;
		private TextView tv_content;
		private RatingBar rb_count;
		private MyGridView gridview;
	}
}
