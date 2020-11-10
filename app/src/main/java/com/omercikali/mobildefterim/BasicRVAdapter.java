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

public class BasicRVAdapter extends RecyclerView.Adapter<BasicRVAdapter.CardViewTasarimNesneleriniTutucu> {
    private Context context;
    private List<String> disaridangelen_list;

    public BasicRVAdapter(Context context, List<String> disaridangelen_list) {
        this.context = context;
        this.disaridangelen_list = disaridangelen_list;
    }


    public class CardViewTasarimNesneleriniTutucu extends RecyclerView.ViewHolder {
        public CardView satiCardView;
        public TextView tarih_Card_Tw;
        public TextView isci_ismi_Card_Tw;
        public TextView ucret_Card_Tw;

        public CardViewTasarimNesneleriniTutucu(View view) {
            super(view);

            tarih_Card_Tw = view.findViewById(R.id.tarih_Card_Tw);
            isci_ismi_Card_Tw = view.findViewById(R.id.isciismi_Card_Tw);
            ucret_Card_Tw = view.findViewById(R.id.ucret_Card_Tw);
            satiCardView = view.findViewById(R.id.satir_Carview);

        }
    }

    @NonNull
    @Override
    public CardViewTasarimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent, false);
        return new CardViewTasarimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasarimNesneleriniTutucu holder, int position) {

       final String isci_ekle=disaridangelen_list.get(position);
        holder.isci_ismi_Card_Tw.setText(isci_ekle);
        holder.satiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "seçtiğin işçi: "+isci_ekle, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return disaridangelen_list.size();
    }


}
