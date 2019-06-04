package com.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView downloadedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImage = findViewById(R.id.imageView);
    }

    public void downloadImage(View view) {
        // http://clipart-library.com/img1/1695168.jpg
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute("http://clipart-library.com/img1/1695168.jpg").get();
            downloadedImage.setImageBitmap(myImage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

                return myBitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


    }

}
