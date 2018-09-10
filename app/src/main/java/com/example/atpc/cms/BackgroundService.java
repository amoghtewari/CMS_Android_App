package com.example.atpc.cms;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by USER on 06/7/2016.
 */
public class BackgroundService extends Service
	{
		//public static volatile boolean shouldContinue = false;
		HeadsetBroadcastReceiver headsetBroadcastReceiver;

		@Nullable
		@Override
		public IBinder onBind(Intent intent)
			{
				return null;
			}

		@Override
		public void onCreate()
			{
				headsetBroadcastReceiver = new HeadsetBroadcastReceiver();
				final IntentFilter filter = new IntentFilter();
				filter.addAction(Intent.ACTION_HEADSET_PLUG);
				registerReceiver(headsetBroadcastReceiver, filter);


			}


		@Override
		public int onStartCommand(Intent intent, int flags, int startId)
			{
				return START_STICKY;
			}

		@Override
		public void onDestroy()
			{
				unregisterReceiver(headsetBroadcastReceiver);


			}
	}
