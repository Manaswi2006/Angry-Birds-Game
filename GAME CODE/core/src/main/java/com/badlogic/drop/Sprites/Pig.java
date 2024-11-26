package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.drop.Sprites.Bird.PPM;

public abstract class Pig {
    private Sprite PigSprite;
    private SpriteBatch batch;
    private Body body;

    public Pig(Angry_Birds_Game game, float x, float y,String texturepath) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture(texturepath);  // Replace with actual bird image path
        PigSprite = new Sprite(birdTexture);
        PigSprite.setPosition(x, y);  // Set initial coordinates
    }
    public void createPigBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody; // Pigs should move when hit
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(15 / PPM); // Smaller radius for pigs

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        circle.dispose();
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
