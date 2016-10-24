package com.shankes.util;

import android.util.Log;

/**
 * Logͳһ������
 * <p>
 * Created by shankes on 2016/9/4.
 */
public class LogUtil {

	private LogUtil() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;// �Ƿ���Ҫ��ӡbug��������application��onCreate���������ʼ��
	private static final String TAG = "shankes";

	// �����ĸ���Ĭ��tag�ĺ���
	public static void i(String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.i(TAG, msg);
			} else {
				Log.e(TAG, "msgΪ��");
			}
	}

	public static void d(String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.d(TAG, msg);
			} else {
				Log.e(TAG, "msgΪ��");
			}
	}

	public static void e(String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.e(TAG, msg);
			} else {
				Log.e(TAG, "msgΪ��");
			}
	}

	public static void v(String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.v(TAG, msg);
			} else {
				Log.e(TAG, "msgΪ��");
			}
	}

	// �����Ǵ����Զ���tag�ĺ���
	public static void i(String tag, String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.i(tag, msg);
			} else {
				Log.e(tag, "msgΪ��");
			}
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.d(tag, msg);
			} else {
				Log.e(tag, "msgΪ��");
			}
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.e(tag, msg);
			} else {
				Log.e(tag, "msgΪ��");
			}
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			if (msg != null && !"".equalsIgnoreCase(msg)) {
				Log.v(tag, msg);
			} else {
				Log.e(tag, "msgΪ��");
			}
	}
}
