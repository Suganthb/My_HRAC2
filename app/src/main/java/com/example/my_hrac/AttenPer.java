package com.example.my_hrac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.core.Chart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AttenPer extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BottomNavigationView mMainNav;

    private EditText CourseNo,IndexNo;
    private Button Find;
    private TextView AttendanceV;

    private AddCheck ad;

    //AnyChartView anyChartView;

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
        AttendanceV = (TextView)findViewById(R.id.atV);
        //anyChartView =(AnyChartView)findViewById(R.id.any_chart_view);

        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateC_I()==true){
                    checkAttendance(CourseNo,IndexNo);
                }
                else{
                    Toast.makeText(AttenPer.this, "InValid ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void checkAttendance(EditText courseNo, EditText indexNo) {
        String cNOInput =courseNo.getText().toString().trim();
        String iNOInput =indexNo.getText().toString().trim();


        db.collection(cNOInput)
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AttenPer.this, "We dont have an Subject code like this ", Toast.LENGTH_SHORT).show();
                    }
                });

        Log.d("Index",cNOInput+iNOInput);
        db.collection(cNOInput)
                .whereEqualTo("indexNumber",iNOInput)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data="";
                        Integer aPer=0,naPer=0,tot=0;
                        Double atper=0.00;
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                            ad = documentSnapshot.toObject(AddCheck.class);
                            ad.setDocumentId(documentSnapshot.getId());

                            Log.d("aper",String.valueOf(documentSnapshot.get("attended")));

                            if(Objects.equals(String.valueOf(documentSnapshot.get("attended")),"true")) {
                                aPer = aPer + 1;

                            } else {
                                naPer = naPer + 1;
                            }
                        }
                        tot = naPer + aPer;
                        atper = (new Double(aPer) / tot) * 100;
                        data += "NO of lectures arrived :" + aPer +"\n" +"NO of lectures not arrived"+ naPer + "\n" + "Total No of lectures" + tot + "\n" + "Attendance Percentage : " + atper + "%" + "\n\n";

                        AttendanceV.setText(data);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AttenPer.this, "We dont have an index number like this ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean validateC_I(){
        String cNOInput =CourseNo.getText().toString().trim();
        String iNOInput =IndexNo.getText().toString().trim();

        String cPattern ="^[A-Z]{3}+[0-9]{4}";
        String iPattern ="^[1-9][0-9]{7}$";

        Integer vali=1;
        if(cNOInput.isEmpty()){
            CourseNo.requestFocus();
            CourseNo.setError("Course name can't be empty");
            vali=0;
        }
        if(iNOInput.isEmpty()){
            IndexNo.requestFocus();
            IndexNo.setError("Index number can't be empty");
            vali=0;
        }
        if(!cNOInput.matches(cPattern)){
            CourseNo.requestFocus();
            CourseNo.setError("You entered invalid course number");
            vali=0;
        }
        if(!iNOInput.matches(iPattern)){
            IndexNo.requestFocus();
            IndexNo.setError("You entered invalid index number");
        }

        if(vali==1){
            return true;
        }
        else{
            return false;
        }
    }
}
