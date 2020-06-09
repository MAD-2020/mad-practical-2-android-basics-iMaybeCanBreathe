package com.example.madpractical2_androidbasic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - Feel free to modify the function to suit your program.
    */

    final String TAG = "Whackamole";
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Button> buttonList = new ArrayList<Button>();
    int currentMole;
    int score;
    TextView scoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "OnCreate");

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        Log.v(TAG, "Buttons set!");

        for (Button button: buttonList) {
            button.setOnClickListener(moleListener);
        }
        Log.v(TAG, "Listeners set!");

        scoreText = findViewById(R.id.scoreText);
        score = 10;
        Log.v(TAG, "set score");
        scoreText.setText(Integer.toString(score));

        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        currentMole = setNewMole();
        Log.v(TAG, "Starting GUI!");
    }

    public int setNewMole()
    {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        buttonList.get(randomLocation).setText("*");
        return randomLocation;
    }

    View.OnClickListener moleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //if correct mole
            if (v.getId() == buttonList.get(currentMole).getId()){
                //set all moles to "O"
                for (Button button: buttonList) {
                    button.setText("O");
                }
                //set new mole
                currentMole = setNewMole();
            }
            else{
                //deduct score
                score -= 1;
                scoreText.setText(Integer.toString(score));
            }
        }
    };
}
