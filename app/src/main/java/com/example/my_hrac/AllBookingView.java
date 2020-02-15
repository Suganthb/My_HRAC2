package com.example.my_hrac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;

public class AllBookingView extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TextView mDisplayDate;
    private TextView ABookView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private BottomNavigationView mMainNav;

    private searchDate sd;
    private ConfirmedBooking cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_booking_view);


        mMainNav = (BottomNavigationView)findViewById(R.id.main_nav);
        mMainNav.setSelectedItemId(R.id.nav_B4);

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

        mDisplayDate =(TextView) findViewById(R.id.tvDate);

        ABookView =(TextView)  findViewById(R.id.BookView);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day   = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AllBookingView.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month= month + 1;

                    String date = year + "-" + month + "-" + day;
                    mDisplayDate.setText(date);
                    if(sd==null) sd=new searchDate();
                    sd.setDay(String.valueOf(day));
                    sd.setMonth(String.valueOf(month));
                    sd.setYear(String.valueOf(year));

                    checkDatabase();
            }
        };




    }

    private void checkDatabase(){

        db.collection("confirmed_bookings")
                .whereEqualTo("selectDate",mDisplayDate.getText().toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String Data ="";
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            try {
                                cb = documentSnapshot.toObject(ConfirmedBooking.class);

                                String Reason       = cb.getReason();
                                Date RequestDate  = cb.getRequestedDate();
                                String St_Time      = cb.getStartingtime();
                                String Ed_Time      = cb.getEndingtime();

                                Data += "Reason" + Reason + "\n" + "RequestDate" + RequestDate + "\n" + "starting time" + St_Time + "\n" + "Ending time" + Ed_Time + "\n\n";

                                //checking is empty or not
                                if (Data.isEmpty()) {
                                    Data += "NO bookings Available";
                                } else {
                                    ABookView.setText(Data);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("DATABASE_CALL",e.getLocalizedMessage());
            }
        });
    }
}
