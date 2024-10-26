package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slingshot {
    private Sprite SlingSprite;
    private SpriteBatch batch;

    public Slingshot(Angry_Birds_Game game, float x, float y) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture("Slingshot.png");  // Replace with actual bird image path
        SlingSprite = new Sprite(birdTexture);
        SlingSprite.setPosition(x, y);  // Set initial coordinates
    }

    // Renders the bird on the screen using the provided SpriteBatch
    public void render() {
        SlingSprite.draw(batch);
    }

    // Releases resources used by the bird
    public void dispose() {
        SlingSprite.getTexture().dispose();
    }
}
