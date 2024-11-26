package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public abstract class Ice_Block extends Blocks {
    private final int hits = 2;
    private final double strength = 8.0;
    public Ice_Block(Angry_Birds_Game game, float x, float y, String texturePath) {
        super(game, x, y, texturePath);
    }
}
