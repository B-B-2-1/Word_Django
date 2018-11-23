package com.forfun.febie.word_django;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Game_5x5 extends AppCompatActivity {

    TinyDB tinyDB;                                                   //Declaring tinyDb object
    int hiscore3x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_5x5);

        ///////////////////////////////////////////////////////////////Initializing tinyDB object

        tinyDB = new TinyDB(this);
        hiscore3x3=tinyDB.getInt("hiscore3x3");
    }
}
