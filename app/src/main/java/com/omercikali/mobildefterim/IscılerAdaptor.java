package com.omercikali.mobildefterim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IscılerAdaptor extends RecyclerView.Adapter<IscılerAdaptor.CardTasarimTutucu> {
    private Context context;
    private List<WorkerModel> iscilerliste;

    public IscılerAdaptor(Context context, List<WorkerModel> iscilerliste) {
        this.context = context;
        this.iscilerliste = iscilerliste;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent, false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        WorkerModel workerModel = iscilerliste.get(position);
        holder.card_tasarim_isciadi_Tw.setText(workerModel.getIsci_ismi());
        holder.tw_wp_ct_ucret.setText(workerModel.getGunluk_calisma_ucreti()+"");
        holder.tw_wp_ct_tarih.setText(workerModel.getTarih()+"");

        holder.isci_cardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return iscilerliste.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private TextView card_tasarim_isciadi_Tw, tw_wp_ct_ucret, tw_wp_ct_tarih;
        private CardView isci_cardi;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            card_tasarim_isciadi_Tw = itemView.findViewById(R.id.card_tasarim_isciadi_Tw);
            tw_wp_ct_ucret = itemView.findViewById(R.id.tw_wp_ct_ucret);
            tw_wp_ct_tarih = itemView.findViewById(R.id.tw_wp_ct_tarih);
            isci_cardi = itemView.findViewById(R.id.isci_cardi);
        }
    }


}
