package com.example.candace.pslapplication;

/* Dependencies */
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private Button homefslday_button;
    private Button homefav_button;
    private Button homedict_button;
    private Button homecat_button;
    private Button homequiz_button;
    private TextView homefsl_text;
    private WordModel todaysFavoriteWord;

    /* For the Navigation Menu */
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private ArrayList<WordModel> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        homefsl_text = findViewById(R.id.homefsl_text);

        initializeFirebaseData();

        words = new ArrayList<WordModel>();

        initializeItemsWithButtons();
        initializeNavigationMenu();
    }

    public void initializeItemsWithButtons(){
        homefslday_button = findViewById(R.id.homefslday_button);
        homefslday_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), WordActivity.class);
                intent.putExtra("WordModelObject", todaysFavoriteWord);
                MainActivity.this.startActivity(intent);
            }
        });
        homefav_button = findViewById(R.id.homefav_button);
        homefav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        homedict_button = findViewById(R.id.homedict_button);
        homedict_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DictionaryActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        homecat_button = findViewById(R.id.homecat_button);
        homecat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        homequiz_button = findViewById(R.id.homequiz_button);
        homequiz_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizzesActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public void initializeNavigationMenu(){
        drawerLayout = findViewById(R.id.activity_main);
        actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawer);
        actionBarDrawer.syncState();

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayHomeAsUpEnabled(true);
            myActionBar.setTitle("Home");
        }

        navView = findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.home:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.favorites:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                                MainActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 100);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.dictionary:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), DictionaryActivity.class);
                                MainActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 100);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.categories:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                                MainActivity.this.startActivity(intent);
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
                                MainActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about: {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                                MainActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 100);
                        drawerLayout.closeDrawers();
                        return true;
                    }
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

    /* For the Firebase Database */
    public void initializeFirebaseData(){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("words");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    WordModel word = dataSnapshotChild.getValue(WordModel.class);
                    words.add(word);
                }
                storageContainer(words);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
    /* For the Firebase Database */
    public void storageContainer(ArrayList<WordModel> words){
        this.words = words;

        ArrayList<WordModel> favoritesChosenArray = new ArrayList<WordModel>();
        ArrayList<WordModel> othersNotChosenArray = new ArrayList<WordModel>();
        for (int i=0; i<words.size(); i++){
            if (words.get(i).getCategory().equals("weekday") || words.get(i).getCategory().equals("basicgreetings"))
                favoritesChosenArray.add(words.get(i));
            else
                othersNotChosenArray.add(words.get(i));
        }

        favoritesChosenArray.addAll(othersNotChosenArray);

        Calendar today = Calendar.getInstance();
        int daytoday = today.get(Calendar.DAY_OF_MONTH);

        todaysFavoriteWord = favoritesChosenArray.get(daytoday-1);
        homefsl_text.setText("FSL OF THE DAY: " + todaysFavoriteWord.getWord());
    }
}