package com.iesebre.dam2.adam.loadimage;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by adam on 18/12/15.
 */
public class DownloadImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private ImageView mImageView;

    private Activity activity;

    private ProgressDialog progress;

    public static final int progress_bar_type = 0;

    public DownloadImageAsyncTask (Activity activity, ImageView mImageView) {
        this.activity = activity;
        this.mImageView = mImageView;
    }

    private Bitmap loadImageFromNetwork(String url){
        try {
            URL urls = new URL(url);
            HttpURLConnection httpCon = (HttpURLConnection) urls.openConnection();

            InputStream is = httpCon.getInputStream();
            int fileLength = httpCon.getContentLength();
            progress.setMax(fileLength);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead, totalBytesRead = 0;
            byte[] data = new byte[2048];

            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
                totalBytesRead += nRead;
                publishProgress(totalBytesRead);
            }

            byte[] image = buffer.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                progress = new ProgressDialog(activity);
                progress.setMessage("Downloading file. Please wait...");
                progress.setIndeterminate(false);
                progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setCancelable(true);
                progress.show();
                return progress;
            default:
                return null;
        }
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
        onCreateDialog(progress_bar_type);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return loadImageFromNetwork(params[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... proc) {
        progress.setProgress(proc[0]);
    }

    protected void onPostExecute(Bitmap result) {
        progress.dismiss();
        mImageView.setImageBitmap(result);
    }
}


