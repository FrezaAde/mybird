package com.mybird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.KotaAdapter;
import model.Ongkir;

import static retrofit.ApiEndPoint.ApiKey;
import static retrofit.ApiEndPoint.BaseUrl;
import static retrofit.ApiEndPoint.City;

public class KotaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    KotaAdapter kotaAdapter;
    final static String TAG = KotaActivity.class.getSimpleName();
    String province, province_id, name, username, email, telepon, alamat, kodepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kota);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            province = extras.getString("province");
            province_id = extras.getString("province_id");
            name = extras.getString("name");
            username = extras.getString("username");
            email = extras.getString("email");
            telepon = extras.getString("telepon");
            alamat = extras.getString("alamat");
            kodepos = extras.getString("kodepos");

        }
        getCity();
    }

    private void getCity() {
        AndroidNetworking.get(BaseUrl + City)
                .addQueryParameter("prov", province_id)
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
                            for (int i = 0; i < jsonArray.length(); i++){
                                Ongkir ongkir = new Ongkir();
                                JSONObject object = jsonArray.getJSONObject(i);
                                ongkir.city_id = object.getLong("city_id");
                                ongkir.city_name = object.getString("city_name");
                                ongkirs.add(ongkir);
                            }

                            recyclerView = (RecyclerView) findViewById(R.id.rcData);
                            kotaAdapter = new KotaAdapter(ongkirs, R.layout.list_data, getApplicationContext(), KotaActivity.this);
                            recyclerView.setAdapter(kotaAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public String getProvince() {
        return province;
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
