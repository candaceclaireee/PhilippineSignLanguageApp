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
import android.view.MenuItem;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    /* For the Navigation Menu */
    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;

    /* For the Firebase Database */
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializeNavigationMenu();

        /* For the Firebase Database */
//      mDatabase = FirebaseDatabase.getInstance().getReference();
//      HashMap<String, String> dataMap = new HashMap<String, String>();
//      dataMap.put("Name", "MOBEBE");
//      dataMap.put("Email", "mobebe@dlsu.edu.ph");
//      mDatabase.push().setValue(dataMap);
//      mDatabase.child("Name").setValue("Candace");
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

        navView = findViewById(R.id.nv);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.home:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.favorites:
                        Toast.makeText(MainActivity.this, "Favorites",Toast.LENGTH_SHORT).show();  break;
                    case R.id.dictionary:
                        Toast.makeText(MainActivity.this, "Dictionary",Toast.LENGTH_SHORT).show();  break;
                    case R.id.tutorials:
                        Toast.makeText(MainActivity.this, "Tutorials",Toast.LENGTH_SHORT).show();  break;
                    case R.id.categories:
                        Toast.makeText(MainActivity.this, "Categories",Toast.LENGTH_SHORT).show();  break;
                    case R.id.quizzes:
                        Toast.makeText(MainActivity.this, "Quizzes",Toast.LENGTH_SHORT).show(); break;
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
                return false;
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