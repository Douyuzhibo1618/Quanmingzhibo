package com.example.administrator.quanmingzhibo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http 加载网络数据
 * @author Administrator
 *
 */
public class HttpUtils {

	/**
	 * 加载网络数据  
	 * @param path  路径
	 * @return  String
	 */
	public static String getStringResult(String path) {
		
		HttpURLConnection conn  = null;
		InputStream is  = null;
		try {
			
			//1, 得到URL 对象
			URL url = new URL(path);
			
			//2, 打开连接
			
			 conn = (HttpURLConnection) url.openConnection();
			
			//3, 设置请求方式
			conn.setRequestMethod("GET");
			
			//4, 连接
			conn.connect();
			
			//5, 判断返回的结果码 (200),得到响应数据
			if(conn.getResponseCode() == 200)
			{
				is = conn.getInputStream();
				
				StringBuilder sBuilder = new StringBuilder();
				
				byte[] buffer  = new  byte[1024];
				
				int len = 0;
				
				while ((len = is.read(buffer))!=-1) {
					
					sBuilder.append(new String(buffer,0,len));
				}
				
				return sBuilder.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(conn!=null)
			{
				conn.disconnect();
			}
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}

	
	/**
	 * 加载网络图片
	 * @param path
	 * @return byte[]
	 */
	public static byte[] getByteResult(String path)
	{
		HttpURLConnection conn = null;
		InputStream is =  null;
		ByteArrayOutputStream baos = null;
		try {
			
			//1, 得到URL 对象
			URL url = new URL(path);
			
			//2, 打开连接
			
			 conn = (HttpURLConnection) url.openConnection();
			
			//3, 设置请求方式
			conn.setRequestMethod("GET");
			
			//4, 连接
			conn.connect();
			
			//5, 判断返回的结果码 (200),得到响应数据
			if(conn.getResponseCode() == 200)
			{
				is = conn.getInputStream();
				
				baos = new ByteArrayOutputStream();
				
				byte[] buffer = new byte[1024];
				
				int len = 0;
				
				while ((len=is.read(buffer))!=-1) {
					
					baos.write(buffer, 0, len);
					baos.flush();
				}
				
				return baos.toByteArray();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(conn!=null)
			{
				conn.disconnect();
			}
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(baos!=null)
			{
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return null;
	}
}
