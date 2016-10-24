package com.shankes.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by shankes on 2016/7/19.
 */
public class SleepThread extends Thread {

	private long mSleepTime = 0;// Ĭ��˯��ʱ��Ϊ0
	private int mMsgWhat = 0x10000;// Ĭ�Ϸ��͵���ϢΪ0x10000
	private Handler mHandler = null;// Ĭ��Ϊ��

	public SleepThread(long sleepTime, int msgWhat, Handler handler) {
		this.mSleepTime = sleepTime;
		this.mMsgWhat = msgWhat;
		this.mHandler = handler;
		start();
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		try {
			sleep(mSleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Message msg = new Message();
		msg.what = mMsgWhat;
		if (mHandler != null) {
			mHandler.sendMessage(msg);
		}
	}
}
