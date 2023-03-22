package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    int locationOfCorrectAnswers;
    int score;
    int numberOfQuestions;

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswers).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else {
            resultTextView.setText("Wrong :/");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void playAgain(final View view){
        score=0;
        numberOfQuestions = 0;
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion(){

        Random r = new Random();
        int a = r.nextInt(21);
        int b = r.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswers = r.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i == locationOfCorrectAnswers)
            {
                answers.add(a+b);
            }else {
                int wrongAnswer = r.nextInt(41);
                while(wrongAnswer == a + b) {
                    wrongAnswer = r.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        timerTextView = findViewById(R.id.timerTextView);
        gameLayout = findViewById(R.id.gameLayout);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);







    }
}