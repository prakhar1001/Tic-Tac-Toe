package com.droidrank.tictactoe.model;

/**
 * Created by Prakhar on 12/6/2017.
 */

public class PlayerDeed {

    public char getPlayerBadge() {
        return playerBadge;
    }

    public void setPlayerBadge(char playerBadge) {
        this.playerBadge = playerBadge;
    }

    public int getDeedPosition() {
        return deedPosition;
    }

    public void setDeedPosition(int deedPosition) {
        this.deedPosition = deedPosition;
    }

    char playerBadge;
    int deedPosition;


}
