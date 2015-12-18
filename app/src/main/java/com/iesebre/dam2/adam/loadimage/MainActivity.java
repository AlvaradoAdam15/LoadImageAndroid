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
        new DownloadImageAsyncTask(mImageView).execute("http://www.techotopia.com/images/2/21/Android_process_priorities.png");
    }
}
