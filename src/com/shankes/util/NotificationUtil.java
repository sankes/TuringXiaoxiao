package com.shankes.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationUtil {

	private static NotificationManager mNotificationManager;
	private static Notification notification;

	/**
	 * ����֪ͨ�� * ���ڵ��ô˷���ʱ�������߳�
	 * 
	 * @param context
	 *            ������
	 * @param icon
	 *            ֪ͨͼƬ
	 * @param tickerText
	 *            ֪ͨδ����������
	 * @param title
	 *            ֪ͨ����
	 * @param content
	 *            ֪ͨ������
	 * @param intent
	 *            ��ͼ
	 * @param id
	 * @param time
	 *            �����߳�˯��ʱ��
	 */
	public static void createNotif(Context context, int icon, String tickerText, String title, String content,
			Intent intent, int id, long time) {
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setAction(Intent.ACTION_MAIN);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
		// Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
		mBuilder.setContentTitle(title).setContentText(content).setContentIntent(pendingIntent).setTicker(tickerText)
				.setWhen(System.currentTimeMillis()).setPriority(Notification.PRIORITY_DEFAULT).setOngoing(false)
				.setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND).setSmallIcon(icon);

		notification = mBuilder.build();

		notification.flags = Notification.FLAG_ONGOING_EVENT;
		notification.flags = Notification.FLAG_AUTO_CANCEL;

		mNotificationManager.notify(id, notification);

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	

}
