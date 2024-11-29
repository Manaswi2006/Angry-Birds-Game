package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.drop.Sprites.Bird.PPM;

public abstract class Pig {
    private Sprite PigSprite;  // Correct sprite instance is already initialized
    private SpriteBatch batch;
    private Body body;
    private boolean destroyed = false;
    // Constants for collision categories
    public static final short CATEGORY_BIRD = 0x0001;  // 0000000000000001
    public static final short CATEGORY_BLOCK = 0x0002; // 0000000000000010
    public static final short CATEGORY_PIG = 0x0004;   // 0000000000000100

    // Collision bits
    public static final short MASK_BIRD = CATEGORY_BLOCK | CATEGORY_PIG;  // Birds collide with blocks and pigs
    public static final short MASK_BLOCK = CATEGORY_BIRD;                 // Blocks collide with birds
    public static final short MASK_PIG = CATEGORY_BIRD;                   // Pigs collide with birds


    public Pig(Angry_Birds_Game game, float x, float y, String texturepath) {
        this.batch = game.getBatch();
        Texture birdTexture = new Texture(texturepath);  // Load texture for the pig
        PigSprite = new Sprite(birdTexture);
        PigSprite.setPosition(x, y);  // Set initial position of the pig
    }

    public void createPigBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;  // Pigs should react to forces
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(15 / PPM);  // Adjust size as needed

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f;


        body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void render(SpriteBatch batch) {
        if (!destroyed) {
            PigSprite.draw(batch);  // Use PigSprite instead of sprite
        }
    }

    public void dispose() {
        PigSprite.getTexture().dispose();  // Properly dispose of texture
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    public Body getBody() {
        return body;
    }
    public void destroyBody(World world) {
        if (body != null) {
            world.destroyBody(body);  // Remove the physics body from the world
            body = null;
            destroyed = true;  // Mark as destroyed to prevent rendering
        }
    }
}
