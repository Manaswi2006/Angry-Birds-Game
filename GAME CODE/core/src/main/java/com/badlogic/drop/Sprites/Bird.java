package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public abstract class Bird {
    private Sprite birdSprite;
    private SpriteBatch batch;
    private boolean isReadyToLaunch = false;
    private Body body;
    public static final float PPM = 100.0f;

    public Bird(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture(texturePath);  // Replace with actual bird image path
        birdSprite = new Sprite(birdTexture);
        birdSprite.setPosition(x, y);  // Set initial coordinates

    }

    public void moveToSlingshot(float x, float y) {
        if (body != null) {
            body.setTransform(x / PPM, y / PPM, 0); // Move to slingshot position
            body.setType(BodyDef.BodyType.StaticBody); // Set to static while preparing
        }
        isReadyToLaunch = true;
    }

    public void releaseBird(float forceX, float forceY) {
        if (isReadyToLaunch && body != null) {
            body.setType(BodyDef.BodyType.DynamicBody); // Allow movement
            body.applyLinearImpulse(new Vector2(forceX / PPM, forceY / PPM), body.getWorldCenter(), true);
            isReadyToLaunch = false;
        }
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
    public void dragBird(float x, float y) {
        if (body != null) {
            body.setTransform(x / PPM, y / PPM, 0);  // Set the position of the bird body
        }
    }

}
