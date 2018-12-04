package com.example.candace.pslapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class QuizzesActivity extends AppCompatActivity {

    /* For the Navigation Menu */
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;
    private int jobID=0;
    private static final int TIME = 2000;
    private boolean stop = false;
    public static final String UI_UPDATE_TAG = "Project.ActivityReceiver";
   // private BroadcastReceiver receiver = new ActivityReceiver();
    private ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizzes);

        /*
        banner = findViewById(R.id.banner);
        IntentFilter filter = new IntentFilter();
        filter.addAction(UI_UPDATE_TAG);
        registerReceiver(receiver, filter);
        triggerAlarm();
        */
        initializeNavigationMenu();
    }

    /* For Timer */
  /*  public void triggerAlarm(){

        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
        alarmIntent.putExtra("ID", jobID);
        jobID++;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1000000+jobID, alarmIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TIME, pendingIntent);
    }

    public void stopAlarm(View view){
        stop = true;
    }

    public void makeService(){
        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
        alarmIntent.putExtra("ID", jobID);
        jobID++;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1000000+jobID, alarmIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TIME, pendingIntent);
    }

    public class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            *//*try {

            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }*//*
        }
    }*/

    /*                                             */
    /* For Navigation Menu */
    public void initializeNavigationMenu(){
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_quizzes);
        actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawer);
        actionBarDrawer.syncState();

        quizDialog();

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayHomeAsUpEnabled(true);
            myActionBar.setTitle("Quizzes");
        }

        navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.home:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                QuizzesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 100);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.favorites:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                                QuizzesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.dictionary:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), DictionaryActivity.class);
                                QuizzesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.categories:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                                QuizzesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.quizzes:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                                QuizzesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }


    private void quizDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_quiz_dialog, null);
        builder.setView(v);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /* For Navigation Menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawer.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
