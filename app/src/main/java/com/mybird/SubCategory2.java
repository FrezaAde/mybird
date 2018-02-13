package com.mybird;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.APIClient;
import retrofit.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubCategory2 extends AppCompatActivity {

    ArrayList<SubCatModel> listTeam;
    private LinearLayout Linearlayout;
    ListView lv;
    ArrayList<SubCatModel> cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Linearlayout = (LinearLayout) findViewById(R.id.LinSub);
        getDataSubCatGory();

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();

        ImageView backnews = (ImageView) findViewById(R.id.backsub2);
        backnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    private void getDataSubCatGory() {

        final ProgressDialog loading = ProgressDialog.show(SubCategory2.this, "Loading", "Sedang Proses...");

        APIService api = APIClient.getInstanceRetrofit();
        Call<ArrayList<SubCatModel>> call = api.ambilData();
        call.enqueue(new Callback<ArrayList<SubCatModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SubCatModel>> call, Response<ArrayList<SubCatModel>> response) {

                loading.dismiss();
                Integer statusCode = response.code();
                Log.v("Status Code :" , statusCode.toString());

                listTeam = response.body();

//                for (SubCatModel s : listTeam){
//                    if (s.getName().isEmpty() && s.getName().contains("Sangkar")){
//                        Log.d("Response ", "" + s.getName());
//
//                       Toast.makeText(getApplicationContext(), s.getName(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }
                for (int i = 0 ; i < listTeam.size(); i++) {

                    Log.d("Response ", "" + listTeam.get(i).getIdSubCat());

                    if (listTeam.get(i).getName().equalsIgnoreCase(getIntent().getStringExtra("pos"))) {


                    LayoutInflater inflat = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View addView = inflat.inflate(R.layout.row_subcategory, null);

//                        Toast.makeText(SubCategory2.this,getIntent().getStringExtra("pos") , Toast.LENGTH_SHORT).show();
                    TextView text = (TextView) addView.findViewById(R.id.nameSub);
                    text.setText(listTeam.get(i).getNamaSub());

                    final String idkat = listTeam.get(i).getIdKategori();
                    final String idsub = listTeam.get(i).getIdSubCat();
                    final String nama = listTeam.get(i).getName();
                    final String namasub = listTeam.get(i).getNamaSub();


                        CardView cardView = (CardView) addView.findViewById(R.id.cvSub);
                        cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("idkat", idkat);
                                bundle.putString("idsub", idsub);
                                bundle.putString("nama", nama);
                                bundle.putString("namasub", namasub);


                                Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
//
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                            Bundle bu = new Bundle();
//                            bu.putString("idkat", idkat);
//                            bu.putString("idsub", idsub);
//
//                            Intent in = new Intent(getApplicationContext(), ProductsActivity.class);
//                            in.putExtras(bu);
//                            startActivity(in);
//                        }
//                    });
                        Linearlayout.addView(addView);
                }

                    }
//                    for (SubCatModel s : listTeam) {
//                        if (s.getName() != null && s.getName().contains("Sangkar")) {
//                            LayoutInflater inflat = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                            View addView = inflat.inflate(R.layout.row_subcategory, null);
//
//                            final TextView nameSub = (TextView) addView.findViewById(R.id.nameSub);
//                            nameSub.setText(s.getName());
//
////                        }
////                    }
//               }


            }

            @Override
            public void onFailure(Call<ArrayList<SubCatModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Silahkan Cek Koneksi Internet Anda ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
