package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Pig {
    private Sprite PigSprite;
    private SpriteBatch batch;

    public Pig(Angry_Birds_Game game, float x, float y,String texturepath) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture(texturepath);  // Replace with actual bird image path
        PigSprite = new Sprite(birdTexture);
        PigSprite.setPosition(x, y);  // Set initial coordinates
    }

    // Renders the bird on the screen using the provided SpriteBatch
    public void render() {
        PigSprite.draw(batch);
    }

    // Releases resources used by the bird
    public void dispose() {
        PigSprite.getTexture().dispose();
    }
}
