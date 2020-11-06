package com.omercikali.mobildefterim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Workers_page extends AppCompatActivity {
    private ImageView add_worker, calendarIm;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);

        add_worker = findViewById(R.id.add_worker);
        calendarIm = findViewById(R.id.calendar_im);


        new AlertDialog.Builder(Workers_page.this);

        add_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = null;
                dialog = getEkleDialog();
            }
        });

    }

    private Dialog getEkleDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.add_worker_dialog, null);



        Button ekle_Btn = layout.findViewById(R.id.ekle_Btn);
        Button vazgec_Btn = layout.findViewById(R.id.vazgec_Btn);
        EditText isci_adi_Et = layout.findViewById(R.id.isci_adi_ET);
        EditText gunluk_calisma_Et = layout.findViewById(R.id.gunlukcalisma_Et);
        EditText tarih_secimi_Et = layout.findViewById(R.id.tarihsecim_Et);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(layout);
        builder.show();


        ekle_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String isci_ismi = isci_adi_Et.getText().toString();
                int gunluk_calisma_ucreti = Integer.parseInt(gunluk_calisma_Et.getText().toString());


                //add data to firebase
                auth = FirebaseAuth.getInstance();
                firebaseUser = auth.getCurrentUser();
                String useremail = firebaseUser.getEmail();
                useremail = useremail.replace(".", "!");

                database = FirebaseDatabase.getInstance();
                mref = database.getReference(useremail).child("ISCILER");
                Worker_model worker_model=new Worker_model(isci_ismi,gunluk_calisma_ucreti);
                mref.push().setValue(worker_model);


                //  Toast.makeText(getApplicationContext(),"kayıt başarılı",Toast.LENGTH_LONG).show();


                finish();
            }
        });

        vazgec_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return null;


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 