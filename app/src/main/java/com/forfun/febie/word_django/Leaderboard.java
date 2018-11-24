package com.forfun.febie.word_django;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;

public class Leaderboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        final TinyDB tinyDB = new TinyDB(this);
        final String Playername=tinyDB.getString("Playername");
        Log.d("Playername", Playername);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();                           //Firebase
        final DatabaseReference ref = database.getReference("UserScore/3x3");

        TextView headingTextview = findViewById(R.id.TV_leaderboardHeading);
        headingTextview.setText("3x3 Leaderboard");


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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////

    }
}
