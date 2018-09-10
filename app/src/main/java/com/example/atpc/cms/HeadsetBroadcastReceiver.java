package com.example.atpc.cms;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by USER on 07/7/2016.
 */
public class HeadsetBroadcastReceiver extends BroadcastReceiver
	{
		//RingtoneManager rman;

		@Override
		public void onReceive(Context context, Intent intent)
			{

				//rman = new RingtoneManager(context);

				//Ringtone ringtone = rman.getRingtone(context, ringTonePath);
				//Intent startIntent = new Intent(context, RingtonePlayer.class);


				if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG))
					{
						int state = intent.getIntExtra("state", -1);

						switch (state)
							{
								case 0:            //unplugged



									KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
									if(myKM.inKeyguardRestrictedInputMode())

									//locked phone
									//loop alarm?

									//Toast.makeText(context, "plugged out", Toast.LENGTH_SHORT).show();


									{
											/*if (ringtone != null )

												{

													rman.stopPreviousRingtone();
													ringtone.stop();
													ringtone.setStreamType(AudioManager.STREAM_ALARM);
													ringtone.play();


												}*/

									//startIntent.putExtra("ringtone-uri", ringTonePath);

										Intent startIntent = new Intent(context, RingtonePlayer.class);
										context.startService(startIntent);

									}
									break;
								case 1:            //plugged
									//Toast.makeText(getApplicationContext(), "plugged in", Toast.LENGTH_SHORT).show();
									//if (ringtone.isPlaying())
									//{
									//rman.stopPreviousRingtone();


									//}

									break;
								default:
									//Toast.makeText(getApplicationContext(), "what have you done?", Toast.LENGTH_SHORT).show();
							}
					}

			}


	}
