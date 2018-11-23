package com.forfun.febie.word_django;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Game_3x3 extends AppCompatActivity {

TinyDB tinyDB;                                                         //Declaring tinyDb object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_3x3);

        ///////////////////////////////////////////////////////////////Initializing tinyDB object
        tinyDB = new TinyDB(this);
    }
}
