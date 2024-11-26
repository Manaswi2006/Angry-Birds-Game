package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;

public abstract class Wood_Block extends Blocks {
    private final int hits = 1;
    public Wood_Block(Angry_Birds_Game game, float x, float y, String texturePath) {
        super(game, x, y, texturePath);
    }
}
