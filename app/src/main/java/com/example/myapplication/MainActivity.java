package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    //play continue
    boolean playOver = false;
    // 0 = yellow , 1 = red
    int activePlayer = 0;

    boolean hasDrawn =false;
    // 2 = unplayed
    int[] state = {2,2,2,2,2,2,2,2,2};

    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View v) {
        if(!playOver) {
            ImageView counter = (ImageView) v;


            int getTapped = Integer.parseInt(counter.getTag().toString());

            if (state[getTapped] == 2) {
                counter.setTranslationY(-1000f);
                state[getTapped] = activePlayer;
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;

                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;

                }
                counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);

            } else {
                Toast.makeText(this, "illegal move", Toast.LENGTH_SHORT).show();

            }
            for (int i = 0; i < winningPos.length; i++) {

                if (state[winningPos[i][0]] == state[winningPos[i][1]]
                        && state[winningPos[i][0]] == state[winningPos[i][2]]
                        && state[winningPos[i][0]] != 2) {
                    playOver = true;
                    Toast.makeText(this, "Winner Winner Player:  " + state[winningPos[i][0]], Toast.LENGTH_SHORT).show();
                    System.out.println("Winner Winner Player:  " + state[winningPos[i][0]]);
                    LinearLayout l = (LinearLayout) findViewById(R.id.playAgainLayout);
                    l.setVisibility(View.VISIBLE);
                    TextView t = findViewById(R.id.winnerText);
                    if (state[winningPos[i][0]] == 0)
                        t.setText("Yellow won!!");
                    else
                        t.setText("Red won");
                }
                else if(!hasDrawn)
                    {   hasDrawn = true;
                        for(int j =0;j<state.length;j++)
                        {
                            if(state[j]==2)
                                hasDrawn = false;
                        }
                        if(hasDrawn)
                        {
                            LinearLayout l = (LinearLayout) findViewById(R.id.playAgainLayout);
                            l.setVisibility(View.VISIBLE);
                            TextView t = findViewById(R.id.winnerText);
                            t.setText("Its a DRAW!!");
                        }
                    }
                }

            }
        }

    public void playAgain(View v)
    {
        LinearLayout l = (LinearLayout)findViewById(R.id.playAgainLayout);
        l.setVisibility(View.INVISIBLE);
        activePlayer =0;
        playOver=false;
        hasDrawn = false;
        for(int i =0;i<state.length;i++)
            state[i]=2;

      GridLayout g = (GridLayout)findViewById(R.id.gridLayout);
       for(int i =0;i<g.getChildCount();i++)
            ((ImageView)g.getChildAt(i)).setImageResource(0);
    }
        @Override
        protected void onCreate (Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }

}
