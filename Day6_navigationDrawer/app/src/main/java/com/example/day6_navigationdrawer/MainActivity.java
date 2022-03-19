package com.example.day6_navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar
        , R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DoctorsFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_doctors);
        }


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_doctors:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new DoctorsFragment())
                        .commit();
                break;
            case R.id.nav_calculator:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new BMICalculatoFragment())
                        .commit();
                break;
            case R.id.nav_med_records:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new MedRecordsFragment())
                        .commit();
                break;

            case R.id.nav_share_nav:
                Snackbar snackbar = Snackbar
                        .make(drawerLayout, "Share button clicked", Snackbar.LENGTH_LONG)
                        .setAction("Share", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(drawerLayout, "Content will be shared", Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                snackbar.show();
                break;

            case R.id.nav_email_nav:
                Snackbar snackbar3 = Snackbar
                        .make(drawerLayout, "Email button clicked", Snackbar.LENGTH_LONG)
                        .setAction("Share", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar4 = Snackbar.make(drawerLayout, "Email will be sent", Snackbar.LENGTH_SHORT);
                                snackbar4.show();
                            }
                        });

                snackbar3.show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}