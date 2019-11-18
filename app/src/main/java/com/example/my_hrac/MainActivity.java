package com.example.my_hrac;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity {


    private static final String KEY_ID = "ID";


    private EditText editTextTitle;
    private InRfid id;

    long currentDate =0;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDate =new Date().getTime();
        editTextTitle = findViewById(R.id.name);
        //editTextTitle.setVisibility(View.GONE);
    }
    ArrayList<String> StuIndex=new ArrayList<>();


    public void btnInsert(View v) {
        String title = editTextTitle.getText().toString();


        Map<String, Object> note = new HashMap<>();
        note.put(KEY_ID, title);



        db.collection("studentIndex")
                .whereEqualTo("RFIDNumber",title)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                id = document.toObject(InRfid.class);
                                //Log.d("HALL_NUMBER", id.toString());
                                //indeo.add(id.getIndexNumber());
                                //String  index=id.getIndexNumber();
                                //Index+=index;
                            }
                        }
                    }
                });
        StuIndex.add(id.getIndexNumber());



        db.collection("RFID_NO").add(note);


        //search tiemtable db to lecture hall
        db.collection("Timetable")
                //.whereEqualTo("lectureHall", zzzzz)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {


                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            try {
                                Timetabe tt = documentSnapshot.toObject(Timetabe.class);
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
                                        if(Utilities.isTimeBetween(St_Time,Ed_Time,Utilities.getTime(currentDate))){
                                           //todo add Attendance of the student
                                            Toast.makeText(MainActivity.this, "Mark Attendance", Toast.LENGTH_SHORT).show();
                                            Attendance at = new Attendance();
                                            at.setDate(Utilities.getDate(currentDate));
                                            at.setAttended(true);
                                            at.setIndexNumber(id != null ? id.getIndexNumber() : "");
//                                            at.setIndexNumber(editTextTitle.getText().toString());


                                        db.collection(tt.getSubjectCode()).add(at);

                                        }
                                        else{
                                            Toast.makeText(MainActivity.this, "U dont have a lectures in this time", Toast.LENGTH_SHORT).show();
                                        }
                                        data += "SubCode:" + SubCode + "LecName:" + LecName +"starting time" + St_Time +"Ending time" + Ed_Time + "\n\n";

                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "U dont have a lectures in this date", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                finish();

                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }

                    }
                });

    }
}