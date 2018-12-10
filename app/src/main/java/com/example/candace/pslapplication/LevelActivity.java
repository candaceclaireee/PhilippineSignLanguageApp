package com.example.candace.pslapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LevelActivity extends AppCompatActivity {

    /* QUIZ VARIABLES */
    private int points=0;
    private int tries=2;

    /* For the Navigation Menu */
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;

    /* For Alarm */
    private int jobID;
    private boolean stop = false;
    private static final int TIME = 1000;
    int a=0;
    public static final String UI_UPDATE_TAG =
            "fsl_app";
    private BroadcastReceiver receiver = new ActivityReceiver();

    /* For level list*/
    private ArrayList<LevelModel> list;
    int index=0;
    /* For dialog */
    private ImageView gifHolder;
    private Button submit;
    private EditText answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);

        gifHolder = findViewById(R.id.signImage);
        submit = findViewById(R.id.submit);
        answer = findViewById(R.id.editText);

        Intent intent = getIntent();
        String level = intent.getStringExtra("LEVEL");
        list = (ArrayList<LevelModel>)intent.getSerializableExtra("LEVEL_LIST");


        /* FOR ALARM SCHEDULE */
        jobID = 1;
        IntentFilter filter = new IntentFilter();
        filter.addAction(UI_UPDATE_TAG);
        registerReceiver(receiver, filter);

        triggerAlarm();
        /*--------------------*/
        initializeNavigationMenu(level);

        /* For the GIF Viewer */
        Glide.with(this.getApplicationContext()).load(list.get(index).getLink()).into(gifHolder);
        Log.d("DEBUGGING", list.get(index).getWord());
        final String ans = list.get(index).getWord();
        final String filAns = list.get(index).getWordFilipino();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* For the GIF Viewer */
                Log.d("DEBUGGING - ANSWER", answer.getText().toString());
                if((tries>=2 && tries>0) && (answer.getText().toString().contentEquals(ans) || answer.getText().toString().contentEquals(filAns) ) ){
                    index++;
                    points+=2;
                    correctDialog();
                    Log.d("DEBUGGING", "CORRECT");
                }
                else if((tries<=2 && tries>0) && !(answer.getText().toString().contentEquals(ans) || answer.getText().toString().contentEquals(filAns))){
                    Log.d("DEBUGGING", "WRONG");
                    tries--;
                    wrongDialog();
                }
                else if(tries==0)
                    wrongDialog();

            }
        });

    }

    /* ------------------------------ For Alarm -------------------------------- */
    public void triggerAlarm(){
        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
        alarmIntent.putExtra("ID", jobID);
        jobID++;

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1000000+jobID, alarmIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TIME, pendingIntent);
    }

    public void stopAlarm(){
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
            Log.d("ALARM AND BROADCAST","ActivityReceiver onReceive");
            int id = intent.getExtras().getInt("ID");
            if(stop==false)
            {
                readyDialog();
                makeService();
                stopAlarm();
            }
            else if(stop==true)
                startDialog();

        }
    }

    /* --------------------------------------------------------------------------------------------- */

    /* Dialog */

    private void readyDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("READY");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("START");
        AlertDialog dialog = builder1.create();
        dialog.show();
    }

    private void wrongDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("WRONG ANSWER!");
        AlertDialog dialog = builder1.create();
        dialog.show();
    }
    private void correctDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("CORRECT ANSWER");
        AlertDialog dialog = builder1.create();
        dialog.show();
    }





    /* --------------------------------------------------------------------------------------------- */

    /* For Navigation Menu */
    public void initializeNavigationMenu(String level){
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_level);
        actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawer);
        actionBarDrawer.syncState();

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayHomeAsUpEnabled(true);
            myActionBar.setTitle("LEVEL " + level);
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
                                LevelActivity.this.startActivity(intent);
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
                                LevelActivity.this.startActivity(intent);
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
                                LevelActivity.this.startActivity(intent);
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
                                LevelActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.quizzes:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), QuizzesActivity.class);
                                LevelActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                                LevelActivity.this.startActivity(intent);
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

    /* For Navigation Menu */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawer.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
