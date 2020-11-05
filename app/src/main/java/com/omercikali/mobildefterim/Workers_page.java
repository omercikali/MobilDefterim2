package com.omercikali.mobildefterim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Workers_page extends AppCompatActivity {
ImageView add_worker,calendarIm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);

        add_worker=findViewById(R.id.add_worker);
        calendarIm=findViewById(R.id.calendar_im);

        new AlertDialog.Builder(Workers_page.this);

add_worker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog dialog=new Dialog(Workers_page.this);
        dialog.setTitle("İşçi Ekle");
        dialog.setContentView(R.layout.add_worker_dialog);
        dialog.show();

    }
});


    }














    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 