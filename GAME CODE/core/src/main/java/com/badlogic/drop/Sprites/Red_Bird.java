package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Red_Bird extends Bird{
    private final double speed = 4.0;// Pigs collide with birds

    public Red_Bird(Angry_Birds_Game game, float x, float y) {
        super(game, x, y, "Red_Bird.png");
    }
    // Method to create the Box2D body

}
