package com.forfun.febie.word_django;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText signupName = findViewById(R.id.username_signup);
        Button but_usernameOk = findViewById(R.id.but_signup);
        but_usernameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_new = signupName.getText().toString();

            }
        });

    }
}
