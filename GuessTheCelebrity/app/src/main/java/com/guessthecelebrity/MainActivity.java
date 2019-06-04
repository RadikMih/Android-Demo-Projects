package com.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    List<String> celebURLs = new ArrayList<>();
    List<String> celebName = new ArrayList<>();
    int chosenCeleb = 0;
    ImageView imageView;
    int correctAnswerPosition = 0;
    String[] answers = new String[4];

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void celebChosen(View view) {
        if (view.getTag().toString().equals(Integer.toString(correctAnswerPosition))) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! It was " + celebName.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }

        createNewQuestion();
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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url;
            HttpURLConnection connection;

            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        DownloadTask task = new DownloadTask();
        String result;

        try {
            result = task.execute("http://www.posh24.se/kandisar").get();
            String[] splitResult = result.split("<div class=\"sidebarContainer\">");

            Pattern pattern = Pattern.compile("<img src\"(.*?)\"");
            Matcher matcher = pattern.matcher(splitResult[0]);

            while (matcher.find()) {
                // celebURLs.add(matcher.group(1));
            }

            pattern = Pattern.compile("alt=\"(.*?)\"");
            matcher = pattern.matcher(splitResult[0]);

            while (matcher.find()) {
                // celebName.add(matcher.group(1));
            }




        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createNewQuestion();
    }

    public void createNewQuestion() {
        Random random = new Random();
        //celebURLs.size
        chosenCeleb = random.nextInt((50));

        ImageDownloader imageTask = new ImageDownloader();
        Bitmap celebImage;
        try {
            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();

            imageView.setImageBitmap(celebImage);
            correctAnswerPosition = random.nextInt(4);
            int incorrectPosition;
            for (int i = 0; i < 4; i++) {
                if (i == correctAnswerPosition) {
                    answers[i] = celebName.get(chosenCeleb);
                } else {
                    incorrectPosition = random.nextInt(50);

                    while (incorrectPosition == correctAnswerPosition) {
                        incorrectPosition = random.nextInt(50);
                    }

                    answers[i] = celebName.get(incorrectPosition);
                }
            }

            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
