package com.shankes.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by shankes on 2016/7/19.
 */
public class SleepThreadUtil extends Thread {

	private long mSleepTime = 0;// Ĭ��˯��ʱ��Ϊ0
	private int mMsgWhat = 0x10000;// Ĭ�Ϸ��͵���ϢΪ0x10000
	private Handler mHandler = null;// Ĭ��Ϊ��
	private SleepThreadCallBack mCallBack = null;
	private Bundle mBundle = null;

	public interface SleepThreadCallBack {
		void onSleepComplete();
	}

	/**
	 * ��˯�ߺ�Ļص�����,˯�ߺ󲻷�����Ϣ
	 * 
	 * @param sleepTime
	 *            ˯��ʱ��
	 * @param sleepThreadCallBack
	 *            ˯�ߺ�ص��ĺ���
	 */
	public SleepThreadUtil(long sleepTime, SleepThreadCallBack sleepThreadCallBack) {
		this.mSleepTime = sleepTime;
		this.mCallBack = sleepThreadCallBack;
		start();
	}

	/**
	 * ˯�ߺ�����Ϣ,˯�ߺ�û���лص�����
	 * 
	 * @param sleepTime
	 *            ˯��ʱ��
	 * @param msgWhat
	 *            ˯�ߺ�����Ϣ��ʶ����
	 * @param handler
	 *            ˯�ߺ�����Ϣ��handler,Ϊ���򲻷�����Ϣ
	 * @param bundle
	 *            ˯�ߺ�����Ϣ��Я��������,����Ϊ��
	 */
	public SleepThreadUtil(long sleepTime, int msgWhat, Handler handler, Bundle bundle) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		this.mBundle = bundle;
		start();
	}

	/**
	 * ˯�ߺ��лص�����,������˯�ߺ�����Ϣ
	 * 
	 * @param sleepTime
	 *            ˯��ʱ��
	 * @param msgWhat
	 *            ˯�ߺ�����Ϣ��ʶ����
	 * @param handler
	 *            ˯�ߺ�����Ϣ��handler,Ϊ���򲻷�����Ϣ
	 * @param sleepThreadCallBack
	 *            ˯�ߺ󴥷��ĺ���,Ϊ����Ĭ�ϲ�����
	 * @param bundle
	 *            ˯�ߺ�����Ϣ��Я��������,����Ϊ��
	 */
	public SleepThreadUtil(long sleepTime, int msgWhat, Handler handler, SleepThreadCallBack sleepThreadCallBack,
			Bundle bundle) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		this.mCallBack = sleepThreadCallBack;
		this.mBundle = bundle;
		start();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		try {
			sleep(mSleepTime);
			if (mCallBack != null) {
				mCallBack.onSleepComplete();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (mHandler != null) {
			Message msg = new Message();
			msg.what = mMsgWhat;
			if (mBundle != null) {
				msg.setData(mBundle);
			}
			if (mHandler != null) {
				mHandler.sendMessage(msg);
			}
		}
	}
}
