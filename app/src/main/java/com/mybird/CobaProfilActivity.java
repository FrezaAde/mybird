package com.mybird;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CobaProfilActivity extends AppCompatActivity {

    String province, city_id, city, name, username, email, telepon, alamat, kodepos;
    final static String TAG = CobaProfilActivity.class.getSimpleName();
    TextView tvProvinsi, tvKota;
    EditText etName, etUsername, etEmail, etTelepon, etAlamat, etKodepos;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_profil);

        tvKota = (TextView) findViewById(R.id.tv_kota);
        tvProvinsi = (TextView) findViewById(R.id.tv_provinsi);
        etName = (EditText) findViewById(R.id.et_username);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email);
        etAlamat = (EditText) findViewById(R.id.et_alamat);
        etTelepon = (EditText) findViewById(R.id.et_telepon);
        etKodepos = (EditText) findViewById(R.id.et_kodepos);

    }
}
