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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class LevelActivity extends AppCompatActivity {

    /* QUIZ VARIABLES */
    private int points=0;
    private int lives=10;
    private TextView pts;
    private String ans;
    private String filAns;
    private Random rand;
    private TextView lvs;
    private LevelActivity activity;

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
        rand = new Random();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level);

        activity = this;

        lvs = findViewById(R.id.live);
        pts = findViewById(R.id.points);
        gifHolder = findViewById(R.id.signImage);
        submit = findViewById(R.id.submit);
        answer = findViewById(R.id.editText);

        Intent intent = getIntent();
        String level = intent.getStringExtra("LEVEL");
        list = (ArrayList<LevelModel>)intent.getSerializableExtra("LEVEL_LIST");

        index = rand.nextInt(list.size());
        /* FOR ALARM SCHEDULE */
        jobID = 1;
        IntentFilter filter = new IntentFilter();
        filter.addAction(UI_UPDATE_TAG);
        registerReceiver(receiver, filter);

        triggerAlarm();
        /*--------------------*/
        initializeNavigationMenu(level);

        /* For the GIF Viewer */
        game();

        lvs.setText("Lives: "+lives);
        pts.setText("Points: "+points);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ans = list.get(index).getWord();
            filAns = list.get(index).getWordFilipino();

            if((lives>1 && lives<=10) && (answer.getText().toString().equalsIgnoreCase(ans) || answer.getText().toString().equalsIgnoreCase(filAns) ) ){
                index = rand.nextInt(list.size());
                points+=2;
                correctDialog();
                game();
            }
            else if((lives>1 && lives<=10) && (!(answer.getText().toString().equals(ans)) || !(answer.getText().toString().equalsIgnoreCase(filAns)))){
                lives--;
                index = rand.nextInt(list.size());
                tryDialog();
                game();
            }
            else if(lives==1) {
                wrongDialog();
                submit.setEnabled(false);
                Intent intent = new Intent(activity.getApplicationContext(), QuizzesActivity.class);
               // intent.putExtra("POINTS", String.valueOf(points));
                LevelActivity.this.startActivity(intent);

            }

            pts.setText("Points: "+points);
            lvs.setText("Lives: "+lives);

            }
        });

    }

    public void game(){
        index = rand.nextInt(list.size());
        submit.setEnabled(true);
        Glide.with(this.getApplicationContext()).load(list.get(index).getLink()).into(gifHolder);
        Log.d("DEBUGGING", list.get(index).getWord());
        answer.setText(null);
        Log.d("DEBUGGING", String.valueOf(index));
    }
    /* ------------------------------ For Alarm -------------------------------- */
    public void triggerAlarm(){
        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
        alarmIntent.putExtra("ID", jobID);
        jobID++;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,10000+jobID, alarmIntent, 0);
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

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 10000+jobID, alarmIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + TIME, pendingIntent);
    }

    public class ActivityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
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
        final AlertDialog dialog1 = builder.create();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                dialog1.dismiss();
            }
        }, 2000);
        dialog1.show();
    }

    private void startDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("START");
        final AlertDialog dialog = builder1.create();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                dialog.dismiss();
            }
        }, 1000);
        dialog.show();
    }

    private void tryDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("WRONG ANSWER, Try Again!");
        final AlertDialog dialog = builder1.create();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                dialog.dismiss();
            }
        }, 1000);
        dialog.show();

    }

    private void wrongDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("YOU RAN OUT OF LIVES! ");
        final AlertDialog dialog = builder1.create();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                dialog.dismiss();
            }
        }, 1000);
        dialog.show();
    }
    private void correctDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("CORRECT ANSWER");
        final AlertDialog dialog = builder1.create();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                dialog.dismiss();
            }
        }, 1000);
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
