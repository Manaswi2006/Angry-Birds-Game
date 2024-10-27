package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Blocks {
    public Sprite blockSprite;
    protected SpriteBatch batch;

    public Blocks(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture blockTexture = new Texture(texturePath);
        this.blockSprite = new Sprite(blockTexture);
        this.blockSprite.setPosition(x, y);
    }

    public void render() {
        blockSprite.draw(batch);
    }

    public void dispose() {
        blockSprite.getTexture().dispose();
    }

    public Sprite getBlockSprite() {
        return this.blockSprite;
    }
}
