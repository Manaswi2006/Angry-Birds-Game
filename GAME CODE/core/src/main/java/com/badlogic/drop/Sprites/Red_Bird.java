package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public class Red_Bird extends Bird{

    private final double speed = 8.0;
    public Red_Bird(Angry_Birds_Game game, float x, float y) {
        super(game, x, y, "Red_Bird.png");
    }

    public double getSpeed() {
        return speed;
    }
}
