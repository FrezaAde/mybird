package com.mybird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import Config.Config;
import uk.co.senab.photoview.PhotoViewAttacher;

public class BrosurDetail extends AppCompatActivity {

    private ImageView thumbnaildetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brosur_detail);
        getSupportActionBar().hide();

        ImageView backdetail = (ImageView) findViewById(R.id.backbrosurdetail);
        backdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BrosurActivity.class));
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String title = bundle.getString("judul");
        String image = bundle.getString("image");


        thumbnaildetail = (ImageView) findViewById(R.id.thumbnaildetail);
        PhotoViewAttacher photoview = new PhotoViewAttacher(thumbnaildetail);
        photoview.update();

        Glide.with(getApplicationContext()).load("http://mybird.silobur.com/userfiles/brosur/origional/" + image)
                .thumbnail(1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnaildetail);
    }
}
