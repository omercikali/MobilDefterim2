package com.omercikali.mobildefterim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IscilerAdapter extends  RecyclerView.Adapter<IscilerAdapter.CardTasarimTutucu>{
    private Context mcontext;
    private List<WorkerModel> workerliste;

    public IscilerAdapter(Context mcontext, List<WorkerModel> workerliste) {
        this.mcontext = mcontext;
        this.workerliste = workerliste;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {

        WorkerModel workerModel=workerliste.get(position);
        holder.card_tasarim_isciadi_Tw.setText(workerModel.getIsci_ismi());
       holder.card_tasarim_gunlukucret_Tw.setText(workerModel.getGunluk_calisma_ucreti()+"");
        holder.card_tasarim_tarih_Tw.setText(""+ workerModel.getTarih());

        holder.isci_cardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "seçtiğiniz kişi: "+workerModel.getIsci_ismi(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return workerliste.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView card_tasarim_isciadi_Tw;
        private TextView card_tasarim_gunlukucret_Tw;
        private TextView card_tasarim_tarih_Tw;
        private CardView isci_cardi;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            card_tasarim_gunlukucret_Tw=itemView.findViewById(R.id.tw_wp_ct_ucret);
            card_tasarim_isciadi_Tw=itemView.findViewById(R.id.card_tasarim_isciadi_Tw);
            card_tasarim_tarih_Tw=itemView.findViewById(R.id.tw_wp_ct_tarih);
            isci_cardi=itemView.findViewById(R.id.isci_cardi);
        }
    }
}
