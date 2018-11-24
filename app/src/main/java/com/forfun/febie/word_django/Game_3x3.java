package com.forfun.febie.word_django;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Game_3x3 extends AppCompatActivity {

TinyDB tinyDB;                                                                                      //Declaring tinyDb object
 int highscore,score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_3x3);

        ///////////////////////////////////////////////////////////////Initializing tinyDB object
        tinyDB = new TinyDB(this);
        highscore=tinyDB.getInt("highscore3x3");
        TextView HighscoreTextView=findViewById(R.id.TV_highscore);
        HighscoreTextView.setText(Integer.toString(highscore));


    }
}
