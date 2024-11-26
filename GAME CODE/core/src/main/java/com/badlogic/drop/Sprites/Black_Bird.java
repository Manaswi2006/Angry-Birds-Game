package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public class Black_Bird extends Bird{
    private final double speed = 8.0;
    public Black_Bird(Angry_Birds_Game game, float x, float y) {
        super(game, x, y, "Black_bird.png");
    }

    public double getSpeed() {
        return speed;
    }

}
