package com.forfun.febie.word_django;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button button4x4=(Button) findViewById(R.id.but4x4);
        button4x4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but4x4:
            v.setBackgroundColor(540);
            Intent intent = new Intent(this, Game_4x4.class);
            startActivity(intent);break;
        }
    }
}
