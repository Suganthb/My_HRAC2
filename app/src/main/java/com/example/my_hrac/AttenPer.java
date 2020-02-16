package com.example.my_hrac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AttenPer extends AppCompatActivity {


    private BottomNavigationView mMainNav;

    private EditText CourseNo,IndexNo;
    private Button Find;

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

        CourseNo = (EditText)findViewById(R.id.cNo);
        IndexNo = (EditText)findViewById(R.id.iNo);
        Find = (Button)findViewById(R.id.btnFind);

        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateC_I(CourseNo,IndexNo)==true){

                }

            }
        });


    }

    private boolean validateC_I(EditText courseNo,EditText indexNo){
        String cNOInput =courseNo.getText().toString();
        String iNOInput =indexNo.getText().toString();

//        if ()

        return false;

    }
}
