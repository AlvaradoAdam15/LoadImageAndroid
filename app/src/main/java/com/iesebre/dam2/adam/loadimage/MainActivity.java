package com.iesebre.dam2.adam.loadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageMostra);
    }

    public void onClick(View v) {
        new DownloadImageAsyncTask(MainActivity.this, mImageView).execute("http://img0.mxstatic.com/wallpapers/f2d80279c0640107927416a56834d521_large.jpeg");
    }
}