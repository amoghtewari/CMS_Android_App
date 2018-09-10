package com.example.atpc.cms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
	{

		TextView phno1;
		TextView phno2;
		TextView phno3;
		Button setphnos;



		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main2);

				phno1 = (TextView) findViewById(R.id.ed1);
				phno2 = (TextView) findViewById(R.id.ed2);
				phno3 = (TextView) findViewById(R.id.ed3);
				setphnos = (Button) findViewById(R.id.button);
				SharedPreferences sharedpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

				String one = sharedpref.getString("Name1", "");
				String two = sharedpref.getString("Name2", "");
				String three = sharedpref.getString("Name3", "");

				phno1.setText(one);
				phno2.setText(two);
				phno3.setText(three);

			}

		/*public void savephnos(View view)
			{
				SharedPreferences sharedpref = getSharedPreferences("pn", Context.MODE_PRIVATE);

				SharedPreferences.Editor editor = sharedpref.edit();
				editor.putString("PhoneNo1", phno1.getText().toString());
				editor.putString("PhoneNo2", phno2.getText().toString());
				editor.putString("PhoneNo3", phno3.getText().toString());
				editor.apply();
				Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

			}*/

		/*public void showphnos(View view)
			{
				SharedPreferences sharedpref = getApplicationContext().getSharedPreferences("pn", getApplicationContext().MODE_PRIVATE);

				String one = sharedpref.getString("PhoneNo1", "");
				String two = sharedpref.getString("Name2", "");
				String three = sharedpref.getString("Name3", "");

				phno1.setText(one);
				phno2.setText(two);
				phno3.setText(three);
			}*/

		public void readContact1(View view)
			{
				try
					{

						Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
						intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
						startActivityForResult(intent, 1);


					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		public void readContact2(View view)
			{
				try
					{

						Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
						intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
						startActivityForResult(intent, 2);


					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}

		public void readContact3(View view)
			{
				try
					{

						Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
						intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
						startActivityForResult(intent, 3);


					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
			{
				super.onActivityResult(requestCode, resultCode, data);
				SharedPreferences sharedpref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				switch (requestCode)

					{
						case (1):
							if (resultCode == Activity.RESULT_OK)
								{
									Uri contactData = data.getData();
									String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY};


									Cursor c = getContentResolver().query(contactData,projection, null, null, null);

									if (c.moveToFirst())
										{

											SharedPreferences.Editor editor = sharedpref1.edit();
											editor.putString("PhoneNo1", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
											editor.putString("Name1", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));
											editor.apply();
											editor.commit();

											phno1.setText(c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));
											//Toast.makeText(this, c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))+"hello1.2", Toast.LENGTH_LONG).show();
											Toast.makeText(getApplicationContext(),sharedpref1.getString("PhoneNo1","No Number"),Toast.LENGTH_LONG).show();

										}

								}
							break;
						case (2):
							if (resultCode == Activity.RESULT_OK)
								{
									Uri contactData = data.getData();
									String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY};

									Cursor c = getContentResolver().query(contactData,projection, null, null, null);

									if (c.moveToFirst())
										{

											SharedPreferences.Editor editor = sharedpref1.edit();
											editor.putString("PhoneNo2", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
											editor.putString("Name2", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));

											editor.commit();
											phno2.setText(c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));
											//Toast.makeText(this, c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))+"hello1.2", Toast.LENGTH_LONG).show();
											Toast.makeText(getApplicationContext(),sharedpref1.getString("PhoneNo2","No Number"),Toast.LENGTH_LONG).show();

										}

								}
							break;
						case (3):
							if (resultCode == Activity.RESULT_OK)
								{
									Uri contactData = data.getData();
									String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY};

									Cursor c = getContentResolver().query(contactData,projection, null, null, null);

									if (c.moveToFirst())
										{

											SharedPreferences.Editor editor = sharedpref1.edit();
											editor.putString("PhoneNo3", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
											editor.putString("Name3", c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));

											editor.commit();
											phno3.setText(c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)));
											//Toast.makeText(this, c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))+"hello1.2", Toast.LENGTH_LONG).show();

											Toast.makeText(getApplicationContext(),sharedpref1.getString("PhoneNo3","No Number"),Toast.LENGTH_LONG).show();
										}

								}
							break;




					}
			}

		public void remove1(View view){
		SharedPreferences sharedpref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = sharedpref1.edit();
		editor.remove("Name1");
		editor.remove("PhoneNo1");
			editor.commit();
			phno1.setText("");
	}

		public void remove2(View view){
			SharedPreferences sharedpref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences.Editor editor = sharedpref1.edit();
			editor.remove("Name2");
			editor.remove("PhoneNo2");
			editor.commit();
			phno2.setText("");
		}

		public void remove3(View view){
			SharedPreferences sharedpref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences.Editor editor = sharedpref1.edit();
			editor.remove("Name3");
			editor.remove("PhoneNo3");
			editor.commit();
			phno3.setText("");
		}


		public void Done(View view)
			{
				finish();
			}
	}
