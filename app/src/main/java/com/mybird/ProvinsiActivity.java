package com.mybird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.ProvinsiAdapter;
import model.Ongkir;

import static retrofit.ApiEndPoint.ApiKey;
import static retrofit.ApiEndPoint.BaseUrl;
import static retrofit.ApiEndPoint.Province;

public class ProvinsiActivity extends AppCompatActivity {
    
    RecyclerView recyclerView;
    ProvinsiAdapter provinsiAdapter;
    final static  String TAG = ProvinsiActivity.class.getSimpleName();
    String name, username, email, telepon, alamat, kodepos; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provinsi);
        getProvinsi();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            username = extras.getString("username");
            email = extras.getString("email");
            telepon = extras.getString("telepon");
            alamat = extras.getString("alamat");
            kodepos = extras.getString("kodepos");
        }
    }

    private void getProvinsi() {
        AndroidNetworking.get(BaseUrl + Province)
                .addHeaders("key", ApiKey)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("rajaongkir");
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            List<Ongkir> ongkirs = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Ongkir ongkir = new Ongkir();
                                JSONObject object = jsonArray.getJSONObject(i);
                                ongkir.province_id = object.getLong("province_id");
                                ongkir.province = object.getString("province");
                                ongkirs.add(ongkir);
                            }

                            recyclerView = (RecyclerView) findViewById(R.id.rcData);
                            provinsiAdapter = new ProvinsiAdapter(ongkirs , R.layout.list_data, getApplicationContext(), ProvinsiActivity.this);
                            recyclerView.setAdapter(provinsiAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ProvinsiActivity.this, "cek koneksi anda", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String getName() {
        return  name;
    }
    public String getUsername(){
        return  username;
    }

    public String getEmail() {
        return email;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKodepos() {
        return kodepos;
    }
}
