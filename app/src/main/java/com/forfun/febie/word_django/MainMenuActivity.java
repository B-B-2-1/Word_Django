package com.forfun.febie.word_django;

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

        ///////////////////////////////////////////////////////////////
        Button button3x3=findViewById(R.id.but3x3);
        button3x3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.but3x3:

                Intent intent = new Intent(this,Game_3x3.class);
                startActivity(intent);
        }

    }
}
