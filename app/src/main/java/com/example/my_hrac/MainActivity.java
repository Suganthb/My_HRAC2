package com.example.my_hrac;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity {

    Integer flag = 0;
    private EditText editTextTitle;


    private InRfid id;
    private Attendance at;
    private AddCheck ad;
    private Timetabe tt;

    long currentDate = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private BottomNavigationView mMainNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDate = new Date().getTime();

        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        mMainNav.setSelectedItemId(R.id.nav_B3);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_B1:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_B2:
                        startActivity(new Intent(getApplicationContext(), BookView.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_B3:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_B4:
                        startActivity(new Intent(getApplicationContext(), AllBookingView.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_B5:
                        startActivity(new Intent(getApplicationContext(), AttenPer.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        editTextTitle = findViewById(R.id.name);
        editTextTitle.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, "scan now ", Toast.LENGTH_SHORT).show();

        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) {
                    Toast.makeText(MainActivity.this, "scan now ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 10) {
                    String title = editTextTitle.getText().toString();


                    db.collection("studentIndex")
                            .whereEqualTo("RFIDNumber", title)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            id = document.toObject(InRfid.class);

                                        }
                                        checkTimeTable();
                                    }
                                }
                            });
                }

            }
        });
    }

    private void checkTimeTable() {
        db.collection("Timetable")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            try {

                                tt = documentSnapshot.toObject(Timetabe.class);
                                for (CustomDate date : tt.getDates()) {
                                    int d, m, y;
                                    d = Utilities.getDay(currentDate);
                                    m = Utilities.getMonth(currentDate);
                                    y = Utilities.getYear(currentDate);
                                    if (date.compareDate(d, m, y)) {
                                        String SubCode = tt.getSubjectCode();
                                        String LecName = tt.getLecture();
                                        String St_Time = tt.getStartingtime();
                                        String Ed_Time = tt.getEndingtime();
                                        if (Utilities.isTimeBetween(St_Time, Ed_Time, Utilities.getTime(currentDate))) {

                                            at = new Attendance();
                                            at.setDate(Utilities.getDate(currentDate));
                                            at.setAttended(true);
                                            at.setIndexNumber(id.getIndexNumber());

                                            checkDatabaseandUpdate();

                                        } else {
                                            flag++;
                                            continue;
                                        }

                                    } else {
                                        flag++;
                                        continue;
                                    }

                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                        //same date different time
                        if (flag != 0) {
                            Toast.makeText(MainActivity.this, "U dont have a permission", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("DB_CALL", e.getLocalizedMessage());
            }
        });
    }

    private void checkDatabaseandUpdate() {
        db.collection(tt.getSubjectCode())
                .whereEqualTo("indexNumber", at.getIndexNumber())
                .whereEqualTo("date", at.getDate())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            ad = documentSnapshot.toObject(AddCheck.class);
                            ad.setDocumentId(documentSnapshot.getId());

                            flag = 0;
                            if (!ad.isAttended()) {
                                Toast.makeText(MainActivity.this, "Mark Attendance", Toast.LENGTH_SHORT).show();

                                updateAttendance();
                                break;
                            } else {
                                Toast.makeText(MainActivity.this, "You already made attendance", Toast.LENGTH_SHORT).show();
                                break;
                            }


                        }
                    }
                });
    }

    private void updateAttendance() {
        db.collection(tt.getSubjectCode())
                .document(ad.getDocumentId())
                .update("attended", at.isAttended());
    }
}