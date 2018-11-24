package com.forfun.febie.word_django;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;


public class Leaderboard extends AppCompatActivity implements View.OnClickListener {

    public void initializeScoreBoardWith(String whichscoreboard,String heading)
    {
        final TinyDB tinyDB = new TinyDB(this);
        final String Playername=tinyDB.getString("Playername");
        Log.d("Playername", Playername);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();                           //Firebase
        final DatabaseReference ref = database.getReference(whichscoreboard);

        TextView headingTextview = findViewById(R.id.TV_leaderboardHeading);
        headingTextview.setText(heading);
        final TextView loadingTV = findViewById(R.id.Loading);
        loadingTV.setVisibility(View.VISIBLE);


        ////////////////////////////////////////////////////////////////////////////////////////////
        final HashMap<Integer,String> testscoreHashmap= new HashMap<Integer, String>();

        ref.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j =(int) dataSnapshot.getChildrenCount();
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                int i=0,PlayerIndex=0;
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
                String PlayerPositionString="",Top3PosString="";

                for( i=PlayerIndex-4;testscoreHashmap.containsKey(i) && i<=PlayerIndex;i++)
                {
                    PlayerPositionString= PlayerPositionString + Integer.toString(i) + ". "+testscoreHashmap.get(i)+"\n";
                    Log.d(Integer.toString(i), testscoreHashmap.get(i));

                }
                for (i=1;i<3 && testscoreHashmap.containsKey(i);i++)
                {
                    Top3PosString = Top3PosString + Integer.toString(i) + ". " + testscoreHashmap.get(i) + "\n";
                    Log.d(Integer.toString(i), testscoreHashmap.get(i));
                }
                Log.d("TAGGG", PlayerPositionString);
                TextView PlayerPosition_textview = findViewById(R.id.TV_5_leaderboard_before_user);
                PlayerPosition_textview.setText(PlayerPositionString);
                TextView Top3_textview = findViewById(R.id.TV_top3);
                Top3_textview.setText(Top3PosString);

                loadingTV.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        initializeScoreBoardWith("UserScore/3x3","3x3 Leaderboard");

        Button x3Leaderboardbutton= findViewById(R.id.but_to_3x3_leaderboard);
        x3Leaderboardbutton.setOnClickListener(this);
        Button x4Leaderboardbutton= findViewById(R.id.but_to_4x4_leaderboard);
        x4Leaderboardbutton.setOnClickListener(this);
        Button x5Leaderboardbutton= findViewById(R.id.but_to_5x5_leaderboard);
        x5Leaderboardbutton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.but_to_3x3_leaderboard:
                initializeScoreBoardWith("UserScore/3x3","3x3 LeaderBoard");break;
            case R.id.but_to_4x4_leaderboard:
                initializeScoreBoardWith("UserScore/4x4","4x4 LeaderBoard");break;
            case R.id.but_to_5x5_leaderboard:
                initializeScoreBoardWith("UserScore/5x5","5x5 LeaderBoard");break;
        }

    }
}
