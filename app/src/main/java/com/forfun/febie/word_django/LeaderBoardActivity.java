package com.forfun.febie.word_django;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.Index;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LeaderBoardActivity extends AppCompatActivity {

    Button FBbut,Getbutton;
    String Playername;
    TinyDB tinyDB;
    int PlayerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        tinyDB = new TinyDB(this);
        Playername=tinyDB.getString("Playername");
        Log.d("Playername", Playername);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();                           //Firebase
        final DatabaseReference ref = database.getReference("UserScore/3x3");                      //Firebase

        final EditText editText=findViewById(R.id.edit_name);
        final EditText editscore = findViewById(R.id.edit_score);


        Button to3x3LBrd = findViewById(R.id.but_to_3x3_leaderboard);
        to3x3LBrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.leaderboard_test);

                final TextView leaderBoardTestTV= findViewById(R.id.TV_leaderBoardTest);

                final HashMap<Integer,String> testscoreHashmap= new HashMap<Integer, String>();

                ref.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int j =(int) dataSnapshot.getChildrenCount();
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        int i=0;
                        while(items.hasNext())
                        {
                            DataSnapshot item = items.next();
                            testscoreHashmap.put(j-i,item.child("name").getValue().toString() + " : " + item.child("score").getValue().toString());
                            if (item.child("name").getValue().toString().equals(Playername))
                            {
                                PlayerIndex=j-i;
                                Log.d("Player Found", "YESSSSSSSSSSSS" + " " + tinyDB.getString("Playername")+ " " + Integer.toString(PlayerIndex));
                            }
                            i++;

                        }
                        String leaderboardTestString="";
                        for( i=PlayerIndex-4;testscoreHashmap.containsKey(i) && i<=PlayerIndex;i++)
                        {
                            leaderboardTestString= leaderboardTestString + Integer.toString(i) + ". "+testscoreHashmap.get(i)+"\n";
                            Log.d(Integer.toString(i), testscoreHashmap.get(i));

                        }
                        Log.d("TAGGG", leaderboardTestString);
                        leaderBoardTestTV.setText(leaderboardTestString);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        FBbut = findViewById(R.id.but_FireBase);
        FBbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child(editText.getText().toString() + "/score").setValue(Integer.parseInt(editscore.getText().toString()));
                ref.child(editText.getText().toString() + "/name").setValue(editText.getText().toString());
            }
        });
        Getbutton=findViewById(R.id.but_FireRetrieve);
        Getbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final HashMap<Integer,String> testscoreHashmap= new HashMap<Integer, String>();

                ref.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                        int i=1;
                        while(items.hasNext())
                        {
                            DataSnapshot item = items.next();
                            testscoreHashmap.put(i,item.child("name").getValue().toString() + " : " + item.child("score").getValue().toString());
                            if (item.child("name").getValue().toString().equals(Playername))
                            {
                                PlayerIndex=i;
                                Log.d("Player Found", "YESSSSSSSSSSSS");
                            }
                            i++;

// Log.d(item.child("score").getValue().toString(), item.child("name").getValue().toString());

                        }
                        for( i=PlayerIndex;testscoreHashmap.containsKey(i) && i<PlayerIndex+5;i++)
                        {
                            Log.d(Integer.toString(i), testscoreHashmap.get(i));
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
