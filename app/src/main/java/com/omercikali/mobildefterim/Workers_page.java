package com.omercikali.mobildefterim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Workers_page extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference mref;
    private Button button;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);

        auth=FirebaseAuth.getInstance();

        button=findViewById(R.id.addrecord);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser=auth.getCurrentUser();
                String useremail=firebaseUser.getEmail();
             useremail=   useremail.replace(".","!");
                System.out.println(useremail);

                database = FirebaseDatabase.getInstance();
                mref = database.getReference(useremail).child("kişiler");
                Isciler isci = new Isciler("", "ali", "20");
                Isciler isci2 = new Isciler("", "mehmet", "50");
                Isciler isci3 = new Isciler("", "kerem", "21");
                Isciler isci4 = new Isciler("", "bilal", "40");
                Isciler isci5 = new Isciler("", "hasan", "27");
                Isciler isci6 = new Isciler("", "muzaffer", "30");
                Isciler isci7 = new Isciler("", "talip", "25");
                mref.push().setValue(isci);
                mref.push().setValue(isci2);
                mref.push().setValue(isci3);
                mref.push().setValue(isci4);
                mref.push().setValue(isci5);
                mref.push().setValue(isci6);
                mref.push().setValue(isci7);
            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 