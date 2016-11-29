package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * ViewPager 
 * 
 *    pagerAdapter
 *    FragmentPagerAdapter
 *    FragmentStateAdapter
 * 
 * @author Administrator
 *
 */
public class MyAdapter extends PagerAdapter{

	private int[] data;
	private Context context;

	public MyAdapter(int[] data, Context context) {
		this.data = data;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data!=null?data.length:0;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		return super.instantiateItem(container, position);
	}
}
