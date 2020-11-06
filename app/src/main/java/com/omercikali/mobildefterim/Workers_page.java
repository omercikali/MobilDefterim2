package com.omercikali.mobildefterim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Workers_page extends AppCompatActivity {
    ImageView add_worker, calendarIm;
    EditText isci_Adi_Et, ne_kadar_calisti_ET, saatli_ucret_Et;
    DatePicker datePicker;
    Button ekle_Btn, vazgec_Btn;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);
        ekle_Btn = findViewById(R.id.ekle_Btn);
        vazgec_Btn = findViewById(R.id.vazgec_Btn);
        isci_Adi_Et = findViewById(R.id.isci_adi_ET);
        ne_kadar_calisti_ET = findViewById(R.id.ne_kadar_calisti_ET);
        saatli_ucret_Et = findViewById(R.id.saatlik_ucret_ET);
        datePicker = findViewById(R.id.datePicker);
        add_worker = findViewById(R.id.add_worker);
        calendarIm = findViewById(R.id.calendar_im);

        new AlertDialog.Builder(Workers_page.this);

        add_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(Workers_page.this);
                dialog.setTitle("İşçi Ekle");
                dialog.setContentView(R.layout.add_worker_dialog);
                dialog.show();
            }
        });

    }

    public void ekleBtn(View view) {
        dialog.dismiss();

    }

    public void vazgecBtn(View view) {
        dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 