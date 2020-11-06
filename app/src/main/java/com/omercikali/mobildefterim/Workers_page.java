package com.omercikali.mobildefterim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Workers_page extends AppCompatActivity {
    ImageView add_worker, calendarIm;


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

        DatePicker datePicker = layout.findViewById(R.id.datePicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setView(layout);
        builder.show();

        final AlertDialog dialog = builder.create();

        ekle_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int gun = datePicker.getDayOfMonth();
                int ay = datePicker.getMonth() + 1;
                int yil = datePicker.getYear();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;

                try {
                    date = df.parse(gun + "/" + ay + "/" + yil);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long tarih = date.getTime();
                String isci_ismi = isci_adi_Et.getText().toString();
                int gunluk_calisma_ucreti= Integer.parseInt(gunluk_calisma_Et.getText().toString());

                System.out.println(tarih);
                System.out.println(isci_ismi);
                System.out.println(gunluk_calisma_ucreti);

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