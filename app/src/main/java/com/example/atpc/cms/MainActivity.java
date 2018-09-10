package com.example.atpc.cms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    public void switchtosettings(){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }

    public void switchtohelp(){
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton switchService = (ToggleButton) findViewById(R.id.toggleButton);
        final Intent startBackgroundService = new Intent(getApplicationContext(), BackgroundService.class);

        switchService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    //BackgroundService.shouldContinue = true;

                    GPSTracker gps = new GPSTracker(getApplicationContext());
                    startService(startBackgroundService);
                    if(gps.canGetLocation())
                        {


                        }

                } else
                {
										/*ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
										List<ActivityManager.RunningAppProcessInfo> runningAppProcess = am.getRunningAppProcesses();
										Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcess.iterator();
										while (iter.hasNext())
											{
												ActivityManager.RunningAppProcessInfo next = iter.next();
												String pricessName = getPackageName() + ":service";
												if (next.processName.equals(pricessName))
													{
														Process.killProcess(next.pid);
														break;
													}
											}*/


                    Intent stopIntent = new Intent(getApplicationContext(), RingtonePlayer.class);
                    getApplicationContext().stopService(stopIntent);
                    stopService(startBackgroundService);
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the camera action
            switchtosettings();
        } else if (id == R.id.nav_help) {
            switchtohelp();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
