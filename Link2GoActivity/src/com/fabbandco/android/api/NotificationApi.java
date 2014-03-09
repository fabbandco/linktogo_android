package com.fabbandco.android.api;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.fabbandco.android.link2go.R;
import com.fabbandco.android.link2go.activity.Link2GoActivity;
import com.fabbandco.common.PrivateFabbandcoActivity;

public class NotificationApi extends PrivateFabbandcoActivity{

	
	@SuppressLint("NewApi")
	public boolean makeNotification(String titre ,String message) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.cloud_notif)
		.setContentTitle(titre)
		.setContentText(message);
		Intent resultIntent = new Intent(this, Link2GoActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(Link2GoActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//		mNotificationManager.notify(mId, mBuilder.build());
		return true;
	}
}
