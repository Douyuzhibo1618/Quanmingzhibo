package com.example.administrator.quanmingzhibo.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

/**
 * 1.图片缓存的工具类
 * 2.单例模式
 * 3.内部使用 lruCache
 * 4.放图片 和 取图的方法
 */
public class LruCacheUtils {
    
	private LruCache<String, Bitmap> lruCache; //放数据的集合
	
	private static LruCacheUtils utils = new LruCacheUtils();
	
	private LruCacheUtils(){
		//获取运行内存的 8分之一
		int maxSize = (int) (Runtime.getRuntime().maxMemory()/1024/8);
		
		lruCache = new LruCache<String, Bitmap>(maxSize){
			
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getRowBytes()*value.getHeight()/1024;
			}
			
		};
		
	};
	
	//暴漏静态方法 向其他类提供对象
	public static LruCacheUtils getInstance(){
		return utils;
	}
	
	//放图片
	public void  putBitmap(String url,Bitmap bitmap){
		if (!TextUtils.isEmpty(url) && bitmap != null) {
			lruCache.put(url, bitmap); //防止数据
		}
	}
	
	//获取容器中的数据
	public Bitmap getBitmap(String url){
		
		Bitmap bitmap = null;
		
		if (!TextUtils.isEmpty(url)) {
			bitmap = lruCache.get(url);
		}
		
		return bitmap;
	}
	
	
	
}
