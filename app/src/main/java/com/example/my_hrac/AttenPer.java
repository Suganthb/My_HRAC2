package com.example.my_hrac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AttenPer extends AppCompatActivity {


    private BottomNavigationView mMainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atten_per);

        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);
        mMainNav.setSelectedItemId(R.id.nav_B5);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.nav_B1:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_B2:
                        startActivity(new Intent(getApplicationContext(),BookView.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_B3:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_B4:
                        startActivity(new Intent(getApplicationContext(),AllBookingView.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_B5:
                        startActivity(new Intent(getApplicationContext(),AttenPer.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
