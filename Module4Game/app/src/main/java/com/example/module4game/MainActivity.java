package com.example.module4game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameActive = true;

    // 2 = not played
    int[] cells = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winning = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (cells[tappedCounter] == 2 && gameActive) {
            cells[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000).rotation(270).setDuration(300);

            for (int[] position : winning) {
                if (cells[position[0]] == cells[position[1]] && cells[position[1]] == cells[position[2]] &&
                        cells[position[0]] != 2) {
                    gameActive = false;

                    String winner = "Red";
                    if (cells[position[0]] == 0) {
                        winner = "Yellow";
                    }

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        } else {
            boolean gameIsOver = true;
            for (int cell : cells) {
                if (cell == 2) {
                    gameIsOver = false;
                }
            }

            if (gameIsOver) {
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText("It's a draw!");

                LinearLayout layout = findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view) {
        gameActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for (int i = 0; i < cells.length; i++) {
            cells[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

}
