package com.forfun.febie.word_django;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tooltip.Tooltip;

public class LoginActivity extends AppCompatActivity {

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Tooltip tooltip = new Tooltip.Builder(findViewById(R.id.materialtextfield))
                .setText("This will be your username for listing in the Leaderboard")
                .setBackgroundColor(Color.GREEN)
                .setDismissOnClick(true)
                .setCornerRadius(2f).setGravity(Gravity.TOP)
                .show();

        tinyDB = new TinyDB(this);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref3 = database.getReference("UserScore/3x3");
        final DatabaseReference ref4 = database.getReference("UserScore/4x4");
        final DatabaseReference ref5 = database.getReference("UserScore/5x5");

        final EditText signupName = findViewById(R.id.username_signup);
        Button but_usernameOk = findViewById(R.id.but_signup);
        but_usernameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username_new = signupName.getText().toString();
                ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(username_new))
                        {
                            Toast.makeText(LoginActivity.this, "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            tinyDB.putString("Playername", username_new);

                            ref3.child(signupName.getText().toString() + "/score").setValue(0);
                            ref3.child(signupName.getText().toString() + "/name").setValue(signupName.getText().toString());

                            ref4.child(signupName.getText().toString() + "/score").setValue(0);
                            ref4.child(signupName.getText().toString() + "/name").setValue(signupName.getText().toString());

                            ref5.child(signupName.getText().toString() + "/score").setValue(0);
                            ref5.child(signupName.getText().toString() + "/name").setValue(signupName.getText().toString());

                            Toast.makeText(LoginActivity.this, "Welcome "+signupName.getText().toString(), Toast.LENGTH_SHORT).show();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                    tinyDB.putBoolean("IsItFirstTime",false);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 500);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
