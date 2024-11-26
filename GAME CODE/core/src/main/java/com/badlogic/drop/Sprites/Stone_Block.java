package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public abstract class Stone_Block extends Blocks {
    private final int hits = 2;
    private final double strength = 10.0;
    public Stone_Block(Angry_Birds_Game game, float x, float y, String texturePath) {
        super(game, x, y, texturePath);
    }
}
