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

    private RecyclerView rv;
    private ArrayList<String> isciekle_list;
    private BasicRVAdapter basicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_page);

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(Workers_page.this));
        isciekle_list=new ArrayList<>();
        isciekle_list.add("alimey");
        isciekle_list.add("baban");
        isciekle_list.add("anan");
        isciekle_list.add("nenen");
        isciekle_list.add("babişkon");
        isciekle_list.add("nenişkon");
        isciekle_list.add("dayıoglu");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("alimey");
        isciekle_list.add("son");
        basicRVAdapter=new BasicRVAdapter(Workers_page.this,isciekle_list);
        rv.setAdapter(basicRVAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ab));

        try {
            Listele();
        } catch (Exception e) {
            Toast.makeText(this, "herhangi bir kayıt bulunamadı", Toast.LENGTH_SHORT).show();
        }


    }

    private void Listele() {


        Veritabani db = new Veritabani(Workers_page.this);
        List<WorkerModel> workerModelList = new ArrayList<WorkerModel>();
        workerModelList = db.TumKayitlar();


        for (WorkerModel workerModel : workerModelList) {
            TableRow satir = new TableRow(Workers_page.this);
            satir.setGravity(Gravity.CENTER);
            satir.setOrientation(TableRow.HORIZONTAL);

            TextView tv_tarih = new TextView(Workers_page.this);
            tv_tarih.setPadding(2, 2, 2, 2);
            tv_tarih.setTextColor(Color.BLUE);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date(workerModel.getTarih());
            tv_tarih.setText(df.format(date) + "   ");

            TextView tv_isci_ismi = new TextView(Workers_page.this);
            tv_isci_ismi.setPadding(2, 2, 2, 2);
            tv_isci_ismi.setTextColor(Color.BLUE);
            tv_isci_ismi.setText(workerModel.getIsci_ismi() + "   ");

            TextView tv_gunlukucret = new TextView(Workers_page.this);
            tv_gunlukucret.setPadding(2, 2, 2, 2);
            tv_gunlukucret.setTextColor(Color.BLUE);
            tv_gunlukucret.setText(String.valueOf(workerModel.getGunluk_calisma_ucreti()));

        }


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

        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.add_worker_dialog, null);

        Button kaydetBtn = layout.findViewById(R.id.ekle_Btn);
        Button vazgectn = layout.findViewById(R.id.vazgec_Btn);
        final EditText isciismi_Et = layout.findViewById(R.id.isci_adi_ET);
        final EditText gunlukucret_Et = layout.findViewById(R.id.gunluk_calisma_Et);
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

        Listele();
        return dialog;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.turn_anim_in, R.anim.turn_anim_out);
    }
} 