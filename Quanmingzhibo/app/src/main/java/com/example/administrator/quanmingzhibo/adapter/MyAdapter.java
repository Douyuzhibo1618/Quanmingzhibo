package com.example.administrator.quanmingzhibo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.administrator.quanmingzhibo.R;
import com.example.administrator.quanmingzhibo.utils.LruCacheUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
     
	private String [] images;
	private Context context;
	
	private LruCacheUtils utils;

	public MyAdapter(String[] images, Context context) {
		this.images = images;
		this.context = context;
		utils = LruCacheUtils.getInstance(); //得到缓存工具类
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images != null?images.length:0;
	}
   /*
    * 1.修改返回值
    * 
    * 
    */
	@Override
	public View instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		
		View ret = null;
		
		//创建一个视图  ImageView 
		ImageView imageView = new ImageView(context);
		
		imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		imageView.setScaleType(ScaleType.FIT_XY);
		
		String url = images[position]; //取到网址
		Bitmap bitmap = utils.getBitmap(url); //看下缓存中是否存在图片
		
		if (bitmap == null) {
			//内存没有需要下载
			imageView.setImageResource(R.mipmap.ic_launcher);
			new ImageTask(imageView).execute(url);
			
		}else{
			imageView.setImageBitmap(bitmap);
		}
		
		ret = imageView; 
		//添加到容器中
		container.addView(ret);
		return ret;
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	/**
	 * 1.去掉 super
	 * 2.
	 * 
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	    
		if (object instanceof View) {
			View view = (View) object;
			container.removeView(view); //移除
		}
		
		
	}
    
  //异步任务	
  class ImageTask extends AsyncTask<String, Void, Bitmap>{
		
		private ImageView imageView;
		
		public ImageTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				URL url = new URL(params[0]);
				
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				if (connection.getResponseCode() == 200) {
					
					InputStream inputStream = connection.getInputStream();

					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
					//保存一份
					utils.putBitmap(params[0], bitmap);
					return bitmap;
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
		}
		
   @Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	     
		if (result != null) {
			imageView.setImageBitmap(result);
		}
		
	}
		
		
	}
	
	
}
