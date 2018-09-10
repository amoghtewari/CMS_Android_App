package com.example.atpc.cms;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by USER on 08/7/2016.
 */




public class RingtonePlayer extends Service
	{
		private Ringtone ringtone;
		private Camera camera;
		private boolean isFlashOn=false;
		private Camera.Parameters params;
		private boolean flash = true;
        TimerTask sendTask;
        final Handler handler = new Handler();


        String Phno;

		public void sendLocation(){
			GPSTracker gps = new GPSTracker(this);

			if(gps.canGetLocation()) {


				final double latitude = gps.getLatitude();
				final double longitude = gps.getLongitude();


				final String Mes = "Lat: " + latitude + " Long: " + longitude;
				final String Mes1 = "http://maps.google.com/maps?q=" + latitude + "," + longitude;

				SharedPreferences sharedpref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String one = sharedpref1.getString("PhoneNo1", "");
                String two = sharedpref1.getString("PhoneNo2", "");
                String three = sharedpref1.getString("PhoneNo3", "");


                String message = Mes1;

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(one, null, message, null, null);
                    // smsManager.sendTextMessage(two, null, Mes1, null, null);
                    // smsManager.sendTextMessage(three, null, Mes1, null, null);
                    // Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                }

                catch (Exception e) {
                    // Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

			}else{
                gps.showSettingsAlert();
            }

		}

		@Nullable
		@Override
		public IBinder onBind(Intent intent)
			{
				return null;
			}

		@Override
		public int onStartCommand(Intent intent, int flags, int startId)
			{
				AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
				int volume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
				if (volume == 0)
					volume = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
				audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
				Uri ringTonePath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
				//Uri ringtoneUri = Uri.parse(intent.getExtras().getString("ringtone-uri"));

				this.ringtone = RingtoneManager.getRingtone(this, ringTonePath);
				if (ringtone != null) {
					ringtone.setStreamType(AudioManager.STREAM_ALARM);
					ringtone.play();
				}
				ringtone.play();
				camera = Camera.open();
				params = camera.getParameters();

				blink(200);

                sendTask = new TimerTask() {
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                sendLocation();
                            }
                        });
                    }};
                new Timer().scheduleAtFixedRate(sendTask, 0, 60000);




				return START_NOT_STICKY;
			}

		@Override
		public void onDestroy()
			{
				ringtone.stop();
				flash = false;
				params = camera.getParameters();
				params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				camera.release();
				sendTask.cancel();


			}

		private void blink(final int delay)
			{
				Thread t = new Thread()
					{
						public void run()
							{
								try
									{

										while (flash)
											{
												if (isFlashOn)
													{
														turnOffFlash();
													} else
													{
														turnOnFlash();
													}
												sleep(delay);
											}

									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					};
				t.start();
			}

		private void turnOnFlash()
			{
				if (!isFlashOn)
					{
						if (camera == null || params == null)
							{
								return;
							}

						params = camera.getParameters();
						params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
						camera.setParameters(params);
						camera.startPreview();
						isFlashOn = true;
					}

			}

		private void turnOffFlash()
			{
				if (isFlashOn)
					{
						if (camera == null || params == null)
							{
								return;
							}
						params = camera.getParameters();
						params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
						camera.setParameters(params);
						camera.stopPreview();
						isFlashOn = false;
					}
			}
	}
