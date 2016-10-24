package com.shankes.turing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * HTTP工具类
 * 
 * @author 图灵机器人
 * 
 */
public class PostServer {
	
	public interface HttpPostCallBack {
		void onRequestComplete(String result);
	}

	/**
	 * 异步的Post请求
	 * 
	 * @param urlStr
	 * @param param jsonString
	 * @param callBack
	 * @throws Exception
	 */
	public static void doPostAsyn(final String urlStr, final String param, final HttpPostCallBack callBack)
			throws Exception {
		new Thread() {
			public void run() {
				try {
					String result = sendPost(urlStr, param);
					if (callBack != null) {
						callBack.onRequestComplete(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			};
		}.start();

	}

	/**
	 * 向后台发送post请求
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendPost(String url, String param) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(50000);
			conn.setReadTimeout(50000);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "token");
			conn.setRequestProperty("tag", "htc_new");

			conn.connect();

			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(param);

			out.flush();
			out.close();
			//
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
