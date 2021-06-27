package com.example.rehber;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jovanovic.stefan.rehber.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private ArrayList kisi_id, kisi_ad, kisi_soyad, kisi_tel, kisi_mail, kisi_adres;

    CustomAdapter(Activity activity, Context context, ArrayList kisi_id, ArrayList kisi_ad, ArrayList kisi_soyad, ArrayList kisi_tel, ArrayList kisi_mail, ArrayList kisi_adres) {
        this.activity = activity;
        this.context = context;
        this.kisi_id = kisi_id;
        this.kisi_ad = kisi_ad;
        this.kisi_soyad = kisi_soyad;
        this.kisi_tel = kisi_tel;
        this.kisi_mail = kisi_mail;
        this.kisi_adres = kisi_adres;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kisiler, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.kisi_id_txt.setText(String.valueOf(kisi_id.get(position)));
        holder.kisi_ad_txt.setText(String.valueOf(kisi_ad.get(position)));
        holder.kisi_soyad_txt.setText(String.valueOf(kisi_soyad.get(position)));
        holder.kisi_tel_txt.setText(String.valueOf(kisi_tel.get(position)));
        holder.kisi_mail_txt.setText(String.valueOf(kisi_mail.get(position)));
        holder.kisi_adres_txt.setText(String.valueOf(kisi_adres.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, GüncellemeIslemi.class);
                intent.putExtra("id", String.valueOf(kisi_id.get(position)));
                intent.putExtra("ad", String.valueOf(kisi_ad.get(position)));
                intent.putExtra("soyad", String.valueOf(kisi_soyad.get(position)));
                intent.putExtra("tel", String.valueOf(kisi_tel.get(position)));
                intent.putExtra("mail", String.valueOf(kisi_mail.get(position)));
                intent.putExtra("adres", String.valueOf(kisi_adres.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return kisi_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kisi_id_txt, kisi_ad_txt, kisi_soyad_txt, kisi_tel_txt, kisi_mail_txt, kisi_adres_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kisi_id_txt = itemView.findViewById(R.id.id_txt);
            kisi_ad_txt = itemView.findViewById(R.id.kisi_ad_txt);
            kisi_soyad_txt = itemView.findViewById(R.id.kisi_soyad_txt);
            kisi_tel_txt = itemView.findViewById(R.id.telefon_txt);
            kisi_mail_txt = itemView.findViewById(R.id.mail_txt);
            kisi_adres_txt = itemView.findViewById(R.id.adres_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
