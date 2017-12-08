package com.droidrank.tictactoe.model;

import java.util.ArrayList;

/**
 * Created by Prakhar on 12/6/2017.
 */

public class PlayerDeedList {

    public ArrayList<PlayerDeed> getPlayerDeedArrayList() {
        return playerDeedArrayList;
    }

    public void setPlayerDeedArrayList(ArrayList<PlayerDeed> playerDeedArrayList) {
        this.playerDeedArrayList = playerDeedArrayList;
    }

    ArrayList<PlayerDeed> playerDeedArrayList;
}
