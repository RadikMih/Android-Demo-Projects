package com.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView timerTextView;
    TextView sumTextView;
    ConstraintLayout gameLayout;
    List<Integer> answers = new ArrayList<>();

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        playAgain(playAgainButton);
    }

    public void generateQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(a + " + " + b);
        locationOfCorrectAnswer = random.nextInt(4);
        answers.clear();

        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
        } else {
          resultTextView.setText("Wrong");
        }
        numberOfQuestion++;
        pointsTextView.setText(score + "/" + numberOfQuestion);
        generateQuestion();
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestion = 0;

        timerTextView.setText("30 s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + " s");

            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your score is: " + score + "/" + numberOfQuestion);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resrultTestView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView =  findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);


    }
}
