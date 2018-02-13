package com.mybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import Config.Config;
import model.NewsModel;
import uk.co.senab.photoview.PhotoViewAttacher;

import static com.mybird.R.id.backdetail;
import static com.mybird.R.id.image;
import static com.mybird.R.id.newsimage;
import static com.mybird.R.id.thumbnaildetail;
import static com.paypal.android.sdk.dc.i;

public class NewsDetail extends AppCompatActivity {


    private ImageView thumbnaildetail;
    private TextView DetailTitle,deskripsinews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        getSupportActionBar().hide();

        ImageView backdetail = (ImageView) findViewById(R.id.backdetail);
        backdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(Config.BUNDLE_TITLE);
        String image = bundle.getString(Config.BUNDLE_IMAGE);
        String content = bundle.getString(Config.BUNDLE_CONTENT);



        thumbnaildetail = (ImageView) findViewById(R.id.thumbnaildetail);
        PhotoViewAttacher photoView = new PhotoViewAttacher(thumbnaildetail);
        photoView.update();
        DetailTitle = (TextView) findViewById(R.id.DetailTitle);
        deskripsinews = (TextView) findViewById(R.id.deskripsinews);

        Glide.with(getApplicationContext()).load("http://mybird.silobur.com/userfiles/news/big/" + image)
                .thumbnail(1f)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnaildetail);

        DetailTitle.setText(title);
        deskripsinews.setText(content);
    }
}
