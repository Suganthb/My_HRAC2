package com.example.my_hrac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AttenPer extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private BottomNavigationView mMainNav;

    private EditText CourseNo,IndexNo;
    private Button Find;
    private TextView AttendanceV;

    private AddCheck ad;

    Integer aPer=0,naPer=0,tot=0;
    Double atper=0.00;

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

        Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAttendance(CourseNo,IndexNo);
            }
        });


    }

    private void checkAttendance(EditText courseNo, EditText indexNo) {
        String cNOInput =courseNo.getText().toString();
        String iNOInput =indexNo.getText().toString();


        db.collection(cNOInput)
                .whereEqualTo("indexNumber",indexNo)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data="";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                            ad = documentSnapshot.toObject(AddCheck.class);
                            ad.setDocumentId(documentSnapshot.getId());

                            if (!ad.isAttended()) {
                                naPer++;
                            } else {
                               aPer++;
                            }
                        }
                        tot=naPer+aPer;
                        atper=(new Double(aPer)/tot)*100;
                        data += "NO of lectures arrived :" + aPer + "\n" + "Total No of lectures" + tot + "\n" + "Attendance Percentage : " + atper + "%" + "\n\n";

                        AttendanceV.setText(data);
                    }
                });
    }

   /* private boolean validateC_I(EditText courseNo,EditText indexNo){
        String cNOInput =courseNo.getText().toString();
        String iNOInput =indexNo.getText().toString();

        if (!cNOInput.isEmpty() && )

        return false;

    }*/
}
