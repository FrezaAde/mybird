package com.mybird;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.BrosurModel;
import retrofit.APIClient;
import retrofit.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrosurActivity extends AppCompatActivity {

    private LinearLayout div;
    ArrayList<BrosurModel> brosurModels;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brosur);
        getSupportActionBar().hide();
        initView();

        ImageView backdetail = (ImageView) findViewById(R.id.backbrosur);
        backdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        apiService = APIClient.getInstanceRetrofit();
        brosurModels = new ArrayList<>();
        getBrosur();


    }

    private void getBrosur() {
        final ProgressDialog loading = ProgressDialog.show(BrosurActivity.this, "Loading", "Sedang Proses...");
        APIService apiService = APIClient.getInstanceRetrofit();
        Call<ArrayList<BrosurModel>> call = apiService.ambilBrosur();
        call.enqueue(new Callback<ArrayList<BrosurModel>>() {
            private void initView(View view) {

                TextView judulbrosur = (TextView) view.findViewById(R.id.namebrosur);

            }
            @Override
            public void onResponse(Call<ArrayList<BrosurModel>> call, Response<ArrayList<BrosurModel>> response) {
                loading.dismiss();
                brosurModels = response.body();
                for (int i = 0; i < brosurModels.size(); i++) {
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.row_brosur, null);

                    TextView judulbrosur = (TextView) view.findViewById(R.id.namebrosur);
                    judulbrosur.setText(brosurModels.get(i).getJudul());

                    final CardView cvklik = (CardView) view.findViewById(R.id.cvbrosur);

                    final String judul = brosurModels.get(i).getJudul();
                    final String image = brosurModels.get(i).getImage();

                    cvklik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("judul", judul);
                            bundle.putString("image", image);
                            Intent intent = new Intent(getApplicationContext(), BrosurDetail.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    div.addView(view);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<BrosurModel>> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(BrosurActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
