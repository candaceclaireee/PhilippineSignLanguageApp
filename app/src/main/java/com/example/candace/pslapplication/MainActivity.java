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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* For the Navigation Menu */
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawer;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        /* For The Navigation Menu */
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        actionBarDrawer = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawer);
        actionBarDrawer.syncState();

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayHomeAsUpEnabled(true);
            myActionBar.setTitle("Home");
        }

        navView = (NavigationView) findViewById(R.id.nv);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.home:
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
                        Toast.makeText(MainActivity.this, "Mini Games",Toast.LENGTH_SHORT).show(); break;
                    case R.id.about: {
                        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                        MainActivity.this.startActivity(intent);
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