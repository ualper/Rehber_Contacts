package com.example.rehber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jovanovic.stefan.rehber.R;

public class EklemeIslemi extends AppCompatActivity {

    EditText ad_input, soyad_input, adres_input, mail_input, tel_input;
    Button ekle_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekleme);

        ad_input = findViewById(R.id.ad_input);
        soyad_input = findViewById(R.id.soyad_input);
        tel_input = findViewById(R.id.tel_input);
        mail_input = findViewById(R.id.mail_input);
        adres_input = findViewById(R.id.adres_input);
        ekle_button = findViewById(R.id.kisi_ekle_button);
        ekle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseYardimcisi myDB = new DatabaseYardimcisi(EklemeIslemi.this);
                myDB.addKisi(ad_input.getText().toString().trim(),
                        soyad_input.getText().toString().trim(),
                        tel_input.getText().toString().trim(),
                        mail_input.getText().toString().trim(),
                        adres_input.getText().toString().trim()
                );
            }
        });
    }
}

