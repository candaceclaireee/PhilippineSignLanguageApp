package com.example.candace.pslapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    /* For the Navigation Menu */
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;
    private List<WordModel> words;

    /* For the Recycler View */
    private RecyclerView recyclerArea;
    private RecyclerView.LayoutManager manager;
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_categories);

        initializeNavigationMenu();

        /* For the Recycler View */
        recyclerArea = findViewById(R.id.categories_recycler);
        manager = new LinearLayoutManager(this);
        adapter = new CategoriesAdapter(this);
        recyclerArea.setLayoutManager(manager);
        recyclerArea.setAdapter(adapter);
    }

    /* For Navigation Menu */
    public void initializeNavigationMenu(){
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_categories);
        actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawer);
        actionBarDrawer.syncState();

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayHomeAsUpEnabled(true);
            myActionBar.setTitle("Categories");
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
                                CategoriesActivity.this.startActivity(intent);
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
                                CategoriesActivity.this.startActivity(intent);
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
                                CategoriesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.categories:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.quizzes:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), QuizzesActivity.class);
                                CategoriesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 120);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.about:
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                                CategoriesActivity.this.startActivity(intent);
                                finish();
                            }
                        }, 100);
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

    /* For the Firebase Database */
    public void initializeFirebaseData(){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("words");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                words = new ArrayList<>();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    WordModel word = dataSnapshotChild.getValue(WordModel.class);
                    words.add(word);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
