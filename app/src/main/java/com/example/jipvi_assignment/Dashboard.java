package com.example.jipvi_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    private ImageView bmi_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        bmi_img = findViewById(R.id.bmi_img);



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        bmi_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Bmi.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Log.d("NAV", "Item clicked: " + id);
        if (id == R.id.nav_dash) {
            Log.d("NAV", "Navigating to Dashboard");
            startActivity(new Intent(this, Dashboard.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_user) {
            Log.d("NAV", "Navigating to PersonalInfo");
            startActivity(new Intent(this, PersonalInfo.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_about) {
            Log.d("NAV", "Navigating to About");
            startActivity(new Intent(this, About.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_logout) {
            Log.d("NAV", "Logging out");
            logoutMenu(Dashboard.this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutMenu(Dashboard mainActivity) {
        new AlertDialog.Builder(mainActivity)
                .setTitle("LOGOUT")
                .setMessage("Are You Sure?")
                .setPositiveButton("YES", (dialogInterface, i) -> finish())
                .setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}