package com.example.rehber;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jovanovic.stefan.rehber.R;

public class GüncellemeIslemi extends AppCompatActivity {

    EditText ad_input, soyad_input, adres_input, mail_input, tel_input;
    Button guncelleme_button, sil_button;

    String id, ad, soyad, adres, mail, tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelleme);

        ad_input = findViewById(R.id.ad_input2);
        soyad_input = findViewById(R.id.soyad_input2);
        adres_input = findViewById(R.id.adres_input2);
        mail_input = findViewById(R.id.mail_input2);
        tel_input = findViewById(R.id.telefon_input2);
        guncelleme_button = findViewById(R.id.guncelleme_button);
        sil_button = findViewById(R.id.silme_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(ad);
        }

        guncelleme_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseYardimcisi myDB = new DatabaseYardimcisi(GüncellemeIslemi.this);
                ad = ad_input.getText().toString().trim();
                soyad = soyad_input.getText().toString().trim();
                tel = tel_input.getText().toString().trim();
                mail = mail_input.getText().toString().trim();
                adres = adres_input.getText().toString().trim();
                myDB.updateData(id, ad, soyad, adres, mail, tel);
            }
        });
        sil_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id")
                && getIntent().hasExtra("ad")
                && getIntent().hasExtra("soyad")
                && getIntent().hasExtra("tel")
                && getIntent().hasExtra("mail")
                && getIntent().hasExtra("adres")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            ad = getIntent().getStringExtra("ad");
            soyad = getIntent().getStringExtra("soyad");
            tel = getIntent().getStringExtra("tel");
            mail = getIntent().getStringExtra("mail");
            adres = getIntent().getStringExtra("adres");

            //Setting Intent Data
            ad_input.setText(ad);
            soyad_input.setText(soyad);
            tel_input.setText(tel);
            mail_input.setText(mail);
            adres_input.setText(adres);
            Log.d("stev", ad + " " + soyad + " " + tel + " " + mail + " " + adres);
        } else {
            Toast.makeText(this, "Rehber Boş\nKişi Ekleyiniz", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sil -> " + ad + " ?");
        builder.setMessage("Silmek istediğine emin misin -> " + ad + " ?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseYardimcisi myDB = new DatabaseYardimcisi(GüncellemeIslemi.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
