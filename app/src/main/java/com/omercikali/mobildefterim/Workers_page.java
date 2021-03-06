package com.omercikali.mobildefterim;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workers_page extends AppCompatActivity {

    private static final int DIALOG_ISCI_EKLE = 1;
    private static final int DIALOG_TARIH = 2;
    private RecyclerView rvvW;
    private ArrayList<WorkerModel> workerModelArrayList;
    private IscılerAdaptor isciadapter;
    private Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab));
        //WorkersPage CAMELCASE
       listele();

    }

    private void listele() {

        vt=new Veritabani(this);
        rvvW = findViewById(R.id.rvvW);
        rvvW.setHasFixedSize(true);
        rvvW.setLayoutManager(new LinearLayoutManager(this));
        workerModelArrayList=new IscilerDao().tumisciler(vt);

        isciadapter= new IscılerAdaptor(this,workerModelArrayList);
        rvvW.setAdapter(isciadapter);



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.isci_ekle_M:
                showDialog(DIALOG_ISCI_EKLE);

                return true;
            case R.id.takvim_M:
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;

        switch (id) {
            case DIALOG_ISCI_EKLE:
                dialog = getEkleDialog();


                break;
            case DIALOG_TARIH:
                break;

            default:
                dialog = null;
        }

        return dialog;
    }

    private Dialog getEkleDialog() {
        listele();


        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.add_worker_dialog, null);

        Button kaydetBtn = layout.findViewById(R.id.ekle_Btn);
        Button vazgectn = layout.findViewById(R.id.vazgec_Btn);
         EditText isciismi_Et = layout.findViewById(R.id.isci_adi_ET);
         EditText gunlukucret_Et = layout.findViewById(R.id.gunluk_calisma_Et);
        final DatePicker datePicker = layout.findViewById(R.id.datepicker);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);

        final AlertDialog dialog = builder.create();

        kaydetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


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
                    String isciismi = isciismi_Et.getText().toString();
                    int gunlukucret = Integer.valueOf(gunlukucret_Et.getText().toString());

                    WorkerModel workerModel = new WorkerModel(isciismi, gunlukucret, tarih);

                    Veritabani db = new Veritabani(getApplicationContext());
                    long id = db.KayitEkle(workerModel);

                    if (id == -1) {

                        Toast.makeText(Workers_page.this, "kayıt sırasında bir hata oluştu", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Workers_page.this, "verileriniz kayıt edildi", Toast.LENGTH_SHORT).show();
                        isciismi_Et.setText("");
                        gunlukucret_Et.setText("");
                    }



                    dialog.dismiss();

                } catch (Exception e) {
                    Toast.makeText(Workers_page.this, "ücret kısmı boş geçilemez", Toast.LENGTH_SHORT).show();
                }
            }
        });



        vazgectn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        return dialog;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 