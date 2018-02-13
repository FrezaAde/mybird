package com.mybird;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import model.NewsModel;
import retrofit.APIClient;
import retrofit.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private LinearLayout div;
    ArrayList<NewsModel> newsModels;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().hide();
        initView();

        apiService = APIClient.getInstanceRetrofit();
        newsModels = new ArrayList<>();
        getdata();

        ImageView backnews = (ImageView) findViewById(R.id.backnews);
        backnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void getdata() {
        APIService apiService = APIClient.getInstanceRetrofit();
        Call<ArrayList<NewsModel>> call = apiService.getnews();
        call.enqueue(new Callback<ArrayList<NewsModel>>() {
            private void initView(View view) {
                thumbnail = (LinearLayout) view.findViewById(R.id.thumbnail);
                newsimage = (ImageView) view.findViewById(R.id.newsimage);
                newsTitle = (TextView) view.findViewById(R.id.newsTitle);

            }

            private TextView newsTitle;
            private ImageView newsimage;
            private LinearLayout thumbnail;
            private RelativeLayout relativenews;

            @Override
            public void onResponse(Call<ArrayList<NewsModel>> call, Response<ArrayList<NewsModel>> response) {
                newsModels = response.body();
                for (int i = 0; i < newsModels.size(); i++) {
                    LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = layoutInflater.inflate(R.layout.row_news, null);



                    final ImageView newsimage = (ImageView) view.findViewById(R.id.newsimage);
                    Glide.with(getApplicationContext()).load("http://mybird.silobur.com/userfiles/news/big/" + newsModels.get(i).getImage())
                            .thumbnail(1f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(newsimage);
                    final TextView newsTitle = (TextView) view.findViewById(R.id.newsTitle);
                    newsTitle.setText(newsModels.get(i).getTitle());

                    final CardView cvklik = (CardView) view.findViewById(R.id.cvklik);

                    final String image = newsModels.get(i).getImage();
                    final String title = newsModels.get(i).getTitle();
                    final String content = newsModels.get(i).getContent();

                    cvklik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("image", image);
                            bundle.putString("title", title);
                            bundle.putString("content", content);
                            Intent intent = new Intent(getApplicationContext(), NewsDetail.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

//                            Toast.makeText(NewsActivity.this, "Image  " + image, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(NewsActivity.this, "Titel  " + title, Toast.LENGTH_SHORT).show();
                        }
                    });

                    div.addView(view);

                }

                if (newsModels.size() == 0){
                    relativenews = (RelativeLayout) findViewById(R.id.relativenews);
                    relativenews.setVisibility(View.VISIBLE);
                }else {

                    relativenews = (RelativeLayout) findViewById(R.id.relativenews);
                    relativenews.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<NewsModel>> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        div = (LinearLayout) findViewById(R.id.div);
    }
}
