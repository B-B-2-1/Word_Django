package com.forfun.febie.word_django;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import spencerstudios.com.bungeelib.Bungee;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        //////////////////////////////////////////////////////////////

        Button button3x3=findViewById(R.id.but3x3);
        button3x3.setOnClickListener(this);
        Button button4x4=(Button) findViewById(R.id.but4x4);
        button4x4.setOnClickListener(this);
        Button button5x5=(Button) findViewById(R.id.but5x5);
        button5x5.setOnClickListener(this);
        Button buttonLeaderboard = findViewById(R.id.but_leaderboard);
        buttonLeaderboard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.but3x3:

                Intent intent = new Intent(this,Game_3x3.class);
                startActivity(intent);
                Bungee.shrink(this);break;
            case R.id.but4x4:
                intent = new Intent(this, Game_4x4.class);
                startActivity(intent);
                Bungee.shrink(this);break;
            case R.id.but5x5:
                intent = new Intent(this,Game_5x5.class);
                startActivity(intent);
                Bungee.shrink(this);break;
            case R.id.but_leaderboard:
                intent = new Intent(this,Leaderboard.class);
                startActivity(intent);
                Bungee.split(this);break;
        }

    }
}
