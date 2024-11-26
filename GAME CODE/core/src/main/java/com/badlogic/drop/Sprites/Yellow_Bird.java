package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public class Yellow_Bird extends Bird{
    private final double speed = 10.0;
    public Yellow_Bird(Angry_Birds_Game game, float x, float y) {
        super(game, x, y, "Yellow_Bird.png");
    }

    public double getSpeed() {
        return speed;
    }
}
