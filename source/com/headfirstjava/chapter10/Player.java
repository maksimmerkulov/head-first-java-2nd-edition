package com.headfirstjava.chapter10;

class Player {
    static int playerCount;
    private String name;
    public Player(String n) {
        name = n;
        playerCount++;
    }
}
