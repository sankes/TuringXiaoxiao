package com.shankes.turing.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.shankes.util.LogUtil;
import com.shankes.util.SleepThreadUtil;
import com.shankes.util.SleepThreadUtil.SleepThreadCallBack;
import com.shankes.turing.util.PostServer.HttpPostCallBack;

;

/**
 * ͼ�������
 * 
 * @author shankes
 * 
 * @api:http://www.tuling123.com
 */
public class TuringUtil {

	public static final String HTTP_URL = "http://www.tuling123.com/openapi/api";

	protected static final String TAG = "TURING";
	public static final String APIKEY = "43be4b5743ad4c5aa8895399a00f3e33";
	public static final String SECRET = "6e245327f9c7a409";

	// * ע��֮���ڻ����˽���ҳ����
	private static String key;// * APIKEY
	// * �������ݣ����뷽ʽΪUTF-8����1-30
	private static String info;// * ����������ô��
	private static String loc;// �������йش塱��
	// �����߸��Լ����û������Ψһ��־����Ӧ�Լ���ÿһ���û���
	// abc123��֧��0-9��a-z,A-Z��ϣ����ܰ��������ַ���
	private static String userid;// 123456

	// ƴ�Ӳ���
	private static StringBuffer HTTP_ARG = null;

	/**
	 * @param secret
	 *            ͼ����վ�ϵ�secret
	 * @param apiKey
	 *            ͼ����վ�ϵ�apiKey
	 * @param infoValue
	 *            ���͵�����
	 */
	private static String getParam(String secret, String apiKey, String infoValue) {
		// �����ܵ�json����
		String data = "{\"key\":\"" + apiKey + "\",\"info\":\"" + infoValue + "\"}";
		// ��ȡʱ���
		String timestamp = String.valueOf(System.currentTimeMillis());
		// ������Կ
		String keyParam = secret + timestamp + apiKey;
		String key = Md5.MD5(keyParam);
		// ����
		Aes mc = new Aes(key);
		data = mc.encrypt(data);
		// ��װ�������
		JSONObject json = new JSONObject();
		try {
			json.put("key", apiKey);
			json.put("timestamp", timestamp);
			json.put("data", data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * @param infoValue
	 *            �������ݣ����뷽ʽΪUTF-8����1-30,����:����������ô��
	 * @param locValue
	 *            �ص�
	 * @param useridValue
	 *            �����߸��Լ����û������Ψһ��־����Ӧ�Լ���ÿһ���û���
	 * @param textView
	 *            ��ʾ������ı���
	 */
	public static void turingPost(final Handler handler, String infoValue, String locValue, String useridValue) {
		if (TextUtils.isEmpty(infoValue)) {
			infoValue = "������Ϣʧ��";
		}
		try {
			// ����ͼ��api
			// String result = PostServer.SendPost(json.toString(),
			// "http://www.tuling123.com/openapi/api");
			// System.out.println(result);
			// post����
			String jsonString = getParam(SECRET, APIKEY, infoValue);
			PostServer.doPostAsyn(HTTP_URL, jsonString, new HttpPostCallBack() {
				@Override
				public void onRequestComplete(String r) {
					try {
						LogUtil.e(r);
						if (TextUtils.isEmpty(r)) {
							return;
						}
						final String result = r;
						final JSONObject jsonObject = new JSONObject(result);
						long sleepTime = jsonObject.getString("text").length() * 200;
						final Message msg = new Message();
						msg.what = jsonObject.getInt("code");
						Bundle bundle = new Bundle();
						bundle.putString("result", result);
						msg.setData(bundle);
						new SleepThreadUtil(sleepTime, TuringCodeType.getValueCode(TuringCodeType.RECEIVE_RESULT),
								null, new SleepThreadCallBack() {
									@Override
									public void onSleepComplete() {
										handler.sendMessage(msg);
									}
								}, bundle);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
			/*
			 * HttpUtils.doPostAsyn(HTTP_URL, HTTP_ARG.toString(), new
			 * CallBack() {
			 * 
			 * @Override public void onRequestComplete(String result) {
			 * textView.setText(result); } });
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}