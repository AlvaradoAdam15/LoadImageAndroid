package com.iesebre.dam2.adam.loadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by adam on 18/12/15.
 */
public class DownloadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView mImageView;

    public DownloadImageAsyncTask (ImageView mImageView) {
        this.mImageView = mImageView;
    }

    private Bitmap loadImageFromNetwork(String url){
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return loadImageFromNetwork(params[0]);
    }

    protected void onPostExecute(Bitmap result) {
        mImageView.setImageBitmap(result);
    }
}

