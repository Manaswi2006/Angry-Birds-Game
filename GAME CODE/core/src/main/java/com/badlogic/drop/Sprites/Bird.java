package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Bird {
    private Sprite birdSprite;
    private SpriteBatch batch;

    public Bird(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture(texturePath);  // Replace with actual bird image path
        birdSprite = new Sprite(birdTexture);
        birdSprite.setPosition(x, y);  // Set initial coordinates
    }

    // Renders the bird on the screen using the provided SpriteBatch
    public void render() {
        birdSprite.draw(batch);
    }

    // Releases resources used by the bird
    public void dispose() {
        birdSprite.getTexture().dispose();
    }
    public Sprite getBirdSprite() {
        return birdSprite;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
}
