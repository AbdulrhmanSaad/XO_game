package com.example.xogame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play;
    TextView player1,player2;
    int flag=0;
    int isTie=0;
    String massage = "";
    boolean gameOver=false;
    int[][]winning={{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    //-1: empty  0: yellow 1: red
    int[] gameState={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    public void imageClicked(View v){
        ImageView counter=(ImageView) v;
        int tapped=Integer.parseInt(counter.getTag().toString());

        if(gameState[tapped]==-1&&gameOver==false) {
            gameState[tapped] = flag;
            isTie++;
            counter.setTranslationY(-1500);
            if (flag == 0) {
                counter.setImageResource(R.drawable.o);
                flag = 1;
            } else {
                counter.setImageResource(R.drawable.redx);
                flag = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] win : winning) {
                if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != -1) {
                    //some one win!
                    gameOver=true;
                    if (flag == 0)
                        massage = "red";
                    else
                        massage = "yellow";
                    player1=findViewById(R.id.player1);
                    player2=findViewById(R.id.player2);
                    int state1,state2;
                    state1=Integer.parseInt(player1.getText().toString());
                    state2=Integer.parseInt(player2.getText().toString());
                    if(massage.equals("red")) {
                        state1 += 1;
                        player1.setText("" + state1);
                    }
                    else if(massage.equals("yellow")){
                        state2+=1;
                        player2.setText("" + state2);
                    }

                    new AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.btn_star)
                            .setTitle("wining")
                            .setMessage(massage+" is win")
                            .setPositiveButton("play again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    GridLayout gridLayout=findViewById(R.id.gridLayout);
                                    for (int ii = 0; ii < gridLayout.getChildCount(); ii++) {
                                        ImageView t = (ImageView) gridLayout.getChildAt(ii);
                                        t.setImageDrawable(null);
                                    }
                                    flag=0;
                                    isTie=0;
                                    gameOver=false;
                                    for (int ii=0 ;ii<gameState.length;ii++) {
                                        gameState[ii] = -1;
                                    }
                                }
                            })
                            .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            }) .show();
                }
            }
            if(isTie==9&&gameOver==false){
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.btn_star)
                        .setTitle("Tie")
                        .setMessage("No one win")
                        .setPositiveButton("play again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                GridLayout gridLayout=findViewById(R.id.gridLayout);
                                for (int ii = 0; ii < gridLayout.getChildCount(); ii++) {
                                    ImageView t = (ImageView) gridLayout.getChildAt(ii);
                                    t.setImageDrawable(null);
                                }
                                flag=0;
                                isTie=0;
                                gameOver=false;
                                for (int ii=0 ;ii<gameState.length;ii++) {
                                    gameState[ii] = -1;
                                }
                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }) .show();
            }
        }
    }
    public void clicked(View v){
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView t = (ImageView) gridLayout.getChildAt(i);
            t.setImageDrawable(null);
        }
        flag=0;
        isTie=0;
        gameOver=false;
        for (int i=0 ;i<gameState.length;i++) {
            gameState[i] = -1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}