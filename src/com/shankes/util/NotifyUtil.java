package com.shankes.util;

import java.util.ArrayList;

import com.shankes.turing.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NotifyUtil {

	private static final int FLAG = Notification.FLAG_INSISTENT;
	int requestCode = (int) SystemClock.uptimeMillis();
	private int NOTIFICATION_ID;
	private NotificationManager nm;
	private Notification notification;
	private NotificationCompat.Builder cBuilder;
	private Notification.Builder nBuilder;
	private Context mContext;

	// TODO add by shankes
	private static NotifyUtil instance;

	public static NotifyUtil getInstance(Context context, int ID) {
		if (instance == null) {
			instance = new NotifyUtil(context, ID);
		}
		return instance;
	}

	public NotifyUtil(Context context, int ID) {
		this.NOTIFICATION_ID = ID;
		mContext = context;
		// ��ȡϵͳ��������ʼ������
		nm = (NotificationManager) mContext.getSystemService(Activity.NOTIFICATION_SERVICE);
		cBuilder = new NotificationCompat.Builder(mContext);
	}

	/**
	 * �����ڶ���֪ͨ���еĸ�����Ϣ
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 */
	private void setCompatBuilder(PendingIntent pendingIntent, int smallIcon, String ticker, String title,
			String content, boolean sound, boolean vibrate, boolean lights) {
		// // �����ǰActivity������ǰ̨���򲻿����µ�Activity��
		// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		// //
		// ����������PendingIntent.FLAG_UPDATE_CURRENT���������ʱ�򣬳���ʹ�õ��֪ͨ��ûЧ��������Ҫ��notification����һ����һ�޶���requestCode
		// // ��Intent��װ��PendingIntent�У����֪ͨ����Ϣ�󣬾ͻ�������Ӧ�ĳ���
		// PendingIntent pIntent = PendingIntent.getActivity(mContext,
		// requestCode, intent, FLAG);

		cBuilder.setContentIntent(pendingIntent);// ��֪ͨҪ������Intent
		if (smallIcon != 0) {
			cBuilder.setSmallIcon(smallIcon);// ���ö���״̬����Сͼ��
		} else {
			cBuilder.setSmallIcon(R.drawable.ic_launcher);
		}
		cBuilder.setTicker(ticker);// �ڶ���״̬���е���ʾ��Ϣ

		cBuilder.setContentTitle(title);// ����֪ͨ���ĵı���
		cBuilder.setContentText(content);// ����֪ͨ�����е�����
		cBuilder.setWhen(System.currentTimeMillis());

		/*
		 * ��AutoCancel��Ϊtrue�󣬵�����֪ͨ����notification�������Զ���ȡ����ʧ,
		 * �����õĻ������Ϣ��Ҳ������������Ի���ɾ��
		 */
		cBuilder.setAutoCancel(true);
		// ��Ongoing��Ϊtrue ��ônotification�����ܻ���ɾ��
		// notifyBuilder.setOngoing(true);
		/*
		 * ��Android4.1��ʼ������ͨ�����·���������notification�����ȼ���
		 * ���ȼ�Խ�ߵģ�֪ͨ�ŵ�Խ��ǰ�����ȼ��͵ģ��������ֻ������״̬����ʾͼ��
		 */
		cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
		/*
		 * Notification.DEFAULT_ALL�����������⡢�𶯾�ϵͳĬ�ϡ�
		 * Notification.DEFAULT_SOUND��ϵͳĬ��������
		 * Notification.DEFAULT_VIBRATE��ϵͳĬ���𶯡�
		 * Notification.DEFAULT_LIGHTS��ϵͳĬ�����⡣
		 * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
		 */
		int defaults = 0;

		if (sound) {
			defaults |= Notification.DEFAULT_SOUND;
		}
		if (vibrate) {
			defaults |= Notification.DEFAULT_VIBRATE;
		}
		if (lights) {
			defaults |= Notification.DEFAULT_LIGHTS;
		}

		cBuilder.setDefaults(defaults);
	}

	/**
	 * ����builder����Ϣ�����ô��ı�ʱ���õ����
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 */
	private void setBuilder(PendingIntent pendingIntent, int smallIcon, String ticker, boolean sound, boolean vibrate,
			boolean lights) {
		nBuilder = new Notification.Builder(mContext);
		// �����ǰActivity������ǰ̨���򲻿����µ�Activity��
		// intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		// PendingIntent pIntent = PendingIntent.getActivity(mContext,
		// requestCode, intent, FLAG);
		nBuilder.setContentIntent(pendingIntent);

		if (smallIcon != 0) {
			nBuilder.setSmallIcon(smallIcon);
		} else {
			nBuilder.setSmallIcon(R.drawable.ic_launcher);
		}

		nBuilder.setTicker(ticker);
		nBuilder.setWhen(System.currentTimeMillis());
		nBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

		int defaults = 0;

		if (sound) {
			defaults |= Notification.DEFAULT_SOUND;
		}
		if (vibrate) {
			defaults |= Notification.DEFAULT_VIBRATE;
		}
		if (lights) {
			defaults |= Notification.DEFAULT_LIGHTS;
		}

		nBuilder.setDefaults(defaults);
	}

	/**
	 * ��ͨ��֪ͨ
	 * <p/>
	 * 1. �໬����ʧ������֪ͨ�˵�����֪ͨ�˵���ʾ
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 * @param title
	 * @param content
	 */
	public void notify_normal_singline(PendingIntent pendingIntent, int smallIcon, String ticker, String title,
			String content, boolean sound, boolean vibrate, boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
		sent();
	}

	/**
	 * ���ж������õ�֪ͨ(��С�����ƺ��������ô�ͼ�꣬ϵͳĬ�ϴ�ͼ��ΪӦ��ͼ��)
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 * @param title
	 * @param content
	 */
	public void notify_mailbox(PendingIntent pendingIntent, int smallIcon, int largeIcon,
			ArrayList<String> messageList, String ticker, String title, String content, boolean sound, boolean vibrate,
			boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights);

		// ��Ongoing��Ϊtrue ��ônotification�����ܻ���ɾ��
		// cBuilder.setOngoing(true);

		/**
		 * // ɾ��ʱ Intent deleteIntent = new Intent(mContext,
		 * DeleteService.class); int deleteCode = (int)
		 * SystemClock.uptimeMillis(); // ɾ��ʱ����һ������ PendingIntent
		 * deletePendingIntent = PendingIntent.getService(mContext, deleteCode,
		 * deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		 * cBuilder.setDeleteIntent(deletePendingIntent);
		 **/

		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), largeIcon);
		cBuilder.setLargeIcon(bitmap);

		cBuilder.setDefaults(Notification.DEFAULT_ALL);// ����ʹ��Ĭ�ϵ�����
		// cBuilder.setVibrate(new long[]{0, 100, 200, 300});// �����Զ������
		cBuilder.setAutoCancel(true);
		// builder.setSound(Uri.parse("file:///sdcard/click.mp3"));

		// ����֪ͨ��ʽΪ�ռ�����ʽ,��֪ͨ��������ָ�������������ܳ��߸������ݣ����Ǻ��ټ�
		// cBuilder.setNumber(messageList.size());
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		for (String msg : messageList) {
			inboxStyle.addLine(msg);
		}
		inboxStyle.setSummaryText("[" + messageList.size() + "��]" + title);
		cBuilder.setStyle(inboxStyle);
		sent();
	}

	/**
	 * �Զ�����ͼ��֪ͨ
	 * 
	 * @param remoteViews
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 */
	public void notify_customview(RemoteViews remoteViews, PendingIntent pendingIntent, int smallIcon, String ticker,
			boolean sound, boolean vibrate, boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, null, null, sound, vibrate, lights);

		notification = cBuilder.build();
		notification.contentView = remoteViews;
		// ���͸�֪ͨ
		nm.notify(NOTIFICATION_ID, notification);
	}

	/**
	 * �������ɶ�����ʾ�ı���֪ͨ��Ϣ (��Ϊ�ڸ߰汾��ϵͳ�в�֧�֣�����Ҫ�����ж�)
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker �ڶ���״̬���е���ʾ��Ϣ
	 * @param title
	 * @param content
	 */
	public void notify_normail_moreline(PendingIntent pendingIntent, int smallIcon, String ticker, String title,
			String content, boolean sound, boolean vibrate, boolean lights) {

		final int sdk = Build.VERSION.SDK_INT;
		if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
			notify_normal_singline(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
			Toast.makeText(mContext, "�����ֻ�����Android 4.1.2����֧�ֶ���֪ͨ��ʾ����", Toast.LENGTH_SHORT).show();
		} else {
			setBuilder(pendingIntent, smallIcon, ticker, true, true, false);
			nBuilder.setContentTitle(title);
			nBuilder.setContentText(content);
			nBuilder.setPriority(Notification.PRIORITY_HIGH);
			notification = new Notification.BigTextStyle(nBuilder).bigText(content).build();
			// ���͸�֪ͨ
			nm.notify(NOTIFICATION_ID, notification);
		}
	}

	/**
	 * �н�������֪ͨ����������Ϊģ�����Ȼ��߾�ȷ����
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 * @param title
	 * @param content
	 */
	public void notify_progress(PendingIntent pendingIntent, int smallIcon, String ticker, String title,
			String content, boolean sound, boolean vibrate, boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
		/*
		 * ��Ϊ������Ҫʵʱ����֪ͨ��Ҳ��˵Ҫ���ϵķ����µ���ʾ���������ﲻ���鿪��֪ͨ������
		 * ��������Ϊ����������ҽ�����ԭ�����Է���֪ͨ���������ε�֪ͨ������
		 */

		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				for (incr = 0; incr <= 100; incr += 10) {
					// ������1.�����ȣ� 2.��ǰ���ȣ� 3.�Ƿ���׼ȷ�Ľ�����ʾ
					cBuilder.setProgress(100, incr, false);
					// cBuilder.setProgress(0, 0, true);
					sent();
					try {
						Thread.sleep(1 * 500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// �������˺�������ʾ��Ϣ
				cBuilder.setContentText("�������").setProgress(0, 0, false);
				sent();
			}
		}).start();
	}

	/**
	 * ���ɴ�ͼƬ��֪ͨ
	 * 
	 * @param pendingIntent
	 * @param smallIcon
	 * @param ticker
	 * @param title
	 * @param bigPic
	 */
	public void notify_bigPic(PendingIntent pendingIntent, int smallIcon, String ticker, String title, String content,
			int bigPic, boolean sound, boolean vibrate, boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, title, null, sound, vibrate, lights);
		NotificationCompat.BigPictureStyle picStyle = new NotificationCompat.BigPictureStyle();
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = true;
		options.inSampleSize = 2;
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), bigPic, options);
		picStyle.bigPicture(bitmap);
		picStyle.bigLargeIcon(bitmap);
		cBuilder.setContentText(content);
		cBuilder.setStyle(picStyle);
		sent();
	}

	/**
	 * ������������ť��֪ͨ
	 * 
	 * @param smallIcon
	 * @param leftbtnicon
	 * @param lefttext
	 * @param leftPendIntent
	 * @param rightbtnicon
	 * @param righttext
	 * @param rightPendIntent
	 * @param ticker
	 * @param title
	 * @param content
	 */
	public void notify_button(int smallIcon, int leftbtnicon, String lefttext, PendingIntent leftPendIntent,
			int rightbtnicon, String righttext, PendingIntent rightPendIntent, String ticker, String title,
			String content, boolean sound, boolean vibrate, boolean lights) {

		requestCode = (int) SystemClock.uptimeMillis();
		setCompatBuilder(rightPendIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
		cBuilder.addAction(leftbtnicon, lefttext, leftPendIntent);
		cBuilder.addAction(rightbtnicon, righttext, rightPendIntent);
		sent();
	}

	public void notify_HeadUp(PendingIntent pendingIntent, int smallIcon, int largeIcon, String ticker, String title,
			String content, int leftbtnicon, String lefttext, PendingIntent leftPendingIntent, int rightbtnicon,
			String righttext, PendingIntent rightPendingIntent, boolean sound, boolean vibrate, boolean lights) {

		setCompatBuilder(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights);
		cBuilder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), largeIcon));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			cBuilder.addAction(leftbtnicon, lefttext, leftPendingIntent);
			cBuilder.addAction(rightbtnicon, righttext, rightPendingIntent);
		} else {
			Toast.makeText(mContext, "�汾����Andriod5.0���޷�����HeadUp��ʽ֪ͨ", Toast.LENGTH_SHORT).show();
		}
		sent();
	}

	/**
	 * ����֪ͨ
	 */
	private void sent() {
		notification = cBuilder.build();
		// ���͸�֪ͨ
		nm.notify(NOTIFICATION_ID, notification);
	}

	/**
	 * ����id���֪ͨ
	 */
	public void clear() {
		// ȡ��֪ͨ
		nm.cancelAll();
	}
}
