package com.droidrank.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //player 1 = O
    //player 1 = X
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    boolean isWinnerDeclared = false;
    boolean isGameStarted = false;
    int countOfTurns = 0;
    private char[][] board;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewsAndListeners();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        board = new char[3][3];
        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isGameStarted) {
                    final AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Tic-Tac-Toe")
                            .setMessage("Do you want to restart the game?")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    isGameStarted = false;
                                    isWinnerDeclared = false;
                                    countOfTurns = 0;
                                    setNullTextToBlocks();
                                    result.setText("");

                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .show();
                } else {
                    //do nothing
                }

            }
        });

    }

    private void setViewsAndListeners() {
        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);


        block1.setOnClickListener(this);
        block2.setOnClickListener(this);
        block3.setOnClickListener(this);
        block4.setOnClickListener(this);
        block5.setOnClickListener(this);
        block6.setOnClickListener(this);
        block7.setOnClickListener(this);
        block8.setOnClickListener(this);
        block9.setOnClickListener(this);
    }

    private void setNullTextToBlocks() {
        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }


    }


    private void updateButtonText() {
        if (isGameStarted) {
            restart.setText(R.string.restart_game);
        } else {
            restart.setText(R.string.start_new_game);
        }

    }


    @Override
    public void onClick(View view) {
        updateButtonText();
        switch (view.getId()) {
            case R.id.bt_block1:
                displayPlayerBadgeOnBlockClick(0, 0, block1);
                break;
            case R.id.bt_block2:
                displayPlayerBadgeOnBlockClick(0, 1, block2);
                break;
            case R.id.bt_block3:
                displayPlayerBadgeOnBlockClick(0, 2, block3);
                break;
            case R.id.bt_block4:
                displayPlayerBadgeOnBlockClick(1, 0, block4);
                break;
            case R.id.bt_block5:
                displayPlayerBadgeOnBlockClick(1, 1, block5);
                break;
            case R.id.bt_block6:
                displayPlayerBadgeOnBlockClick(1, 2, block6);
                break;
            case R.id.bt_block7:
                displayPlayerBadgeOnBlockClick(2, 0, block7);
                break;
            case R.id.bt_block8:
                displayPlayerBadgeOnBlockClick(2, 1, block8);
                break;
            case R.id.bt_block9:
                displayPlayerBadgeOnBlockClick(2, 2, block9);
                break;
        }
    }

    private void displayPlayerBadgeOnBlockClick(int x, int y, Button block) {
        isGameStarted = true;
        countOfTurns++;

        if (isWinnerDeclared == false) {
            if (countOfTurns % 2 == 0) {
                board[x][y] = 'O';
                block.setText("O");
            } else {
                board[x][y] = 'X';
                block.setText("X");
            }
        } else {
            Toast.makeText(MainActivity.this, "Please Start A New Game", Toast.LENGTH_LONG).show();
        }
        checkForWinner();
    }

    private void checkForWinner() {
        // If the game has drawn
        if (!gameOver(board) && countOfTurns == 9) {
            result.setText("It's a tie");
        } else if (!gameOver(board)) {

        } else {
            // Declare the winner
            declareWinner();
        }
    }


    // A function that returns true if any of the row
    // is crossed with the same player's move
    boolean rowCrossed(char board[][]) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][0] != ' ' && board[i][0] != 0)
                return (true);
        }
        return (false);
    }

    // A function that returns true if any of the column
    // is crossed with the same player's move
    boolean columnCrossed(char board[][]) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i] &&
                    board[0][i] != ' ' && board[0][i] != 0)
                return (true);
        }
        return (false);
    }

    // A function that returns true if any of the diagonal
    // is crossed with the same player's move
    boolean diagonalCrossed(char board[][]) {
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[0][0] != ' ' && board[0][0] != 0)
            return (true);

        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0] &&
                board[0][2] != ' ' && board[0][2] != 0)
            return (true);

        return (false);
    }

    // A function that returns true if the game is over
// else it returns a false
    boolean gameOver(char board[][]) {
        return (rowCrossed(board) || columnCrossed(board)
                || diagonalCrossed(board));
    }

    // A function to declare the winner of the game
    void declareWinner() {
        isWinnerDeclared = true;
        if (countOfTurns % 2 == 0)
            result.setText("Player2 wins ");
        else
            result.setText("Player1 wins ");
    }
}
