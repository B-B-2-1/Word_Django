package com.forfun.febie.word_django;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import spencerstudios.com.bungeelib.Bungee;

public class Game_5x5 extends AppCompatActivity {

    TinyDB tinyDB;                                                   //Declaring tinyDb object
    int highscore,score=0;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.zoom(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_5x5);

        ///////////////////////////////////////////////////////////////Initializing tinyDB object

        tinyDB = new TinyDB(this);
        highscore=tinyDB.getInt("highscore5x5");
        TextView HighscoreTextView=findViewById(R.id.TV_highscore);
        HighscoreTextView.setText(Integer.toString(highscore));
    }
}
