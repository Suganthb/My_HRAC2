package com.example.my_hrac;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Date;

public class BookView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //firebase connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    long currentDate = 0;
    private BottomNavigationView BtmNav;
    private Spinner betterSpinner;

    private TextView textViewData, etDate, etTime;
    private Button ScanNow;

    //class assign
    Timetabe tt;
    Hall h;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingview);

        //get current date and time
        currentDate = new Date().getTime();

        //Bottom navigator view
        BtmNav = (BottomNavigationView)findViewById(R.id.btm_nav);
        BtmNav.setSelectedItemId(R.id.nav_B2);

        BtmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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


        //spinner
        betterSpinner = findViewById(R.id.android_material_design_spinner);

        //text view data
        textViewData = findViewById(R.id.text_view_data);
        textViewData.setMovementMethod(new ScrollingMovementMethod());

        //edit text
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);

        //button open activity scan(Main activity)
        ScanNow = findViewById(R.id.btnScan);
        ScanNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        //display current date and time
        etDate.setText(Utilities.getDate(currentDate));
        etTime.setText(Utilities.getTime(currentDate));

        betterSpinner.setOnItemSelectedListener(this);


        updateHalls();
    }

    private void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    ArrayList<Hall> hals = new ArrayList<>();
    ArrayList<String> halNames = new ArrayList<>();

    private void updateHalls() {
        db.collection("Halls")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                h = document.toObject(Hall.class);
                                hals.add(h);
                                Log.d("HALL_NUMBER", h.toString());
                                halNames.add(h.getHallNumber());
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BookView.this,
                                    android.R.layout.simple_list_item_1, halNames);
                            betterSpinner.setAdapter(arrayAdapter);
                        }
                    }
                });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String  item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected Hall :" + item, Toast.LENGTH_LONG).show();


        //search tiemtable db to lecture hall
        db.collection("Timetable")
                .whereEqualTo("lectureHall",item)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            try {
                                 tt = documentSnapshot.toObject(Timetabe.class);
                                for (CustomDate date : tt.getDates()) {
                                    int d, m, y;
                                    d = Utilities.getDay(currentDate);
                                    m = Utilities.getMonth(currentDate);
                                    y = Utilities.getYear(currentDate);
                                    if (date.compareDate(d, m, y)) {
                                        String SubCode = tt.getSubjectCode();
                                        String LecName = tt.getLecturer();
                                        String St_Time = tt.getStartingtime();
                                        String Ed_Time = tt.getEndingtime();

                                        data += "SubCode:" + SubCode + "\n" + "LecName:" + LecName + "\n" + "starting time" + St_Time + "\n" + "Ending time" + Ed_Time + "\n\n";


                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                        //checking is empty or not
                        if (data.isEmpty()) {
                            data += "NO bookings Available";
                            textViewData.setText(data);
                        } else {
                            textViewData.setText(data);
                            data = "NO bookings Available";
                        }

                    }
                });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
