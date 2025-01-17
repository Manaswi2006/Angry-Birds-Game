package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Bird {
    private Sprite birdSprite;
    private SpriteBatch batch;
    private boolean isReadyToLaunch = false;
    private Body body;
    public static final float PPM = 100.0f;
    // Constants for collision categories
    public static final short CATEGORY_BIRD = 0x0001;  // 0000000000000001
    public static final short CATEGORY_BLOCK = 0x0002; // 0000000000000010
    public static final short CATEGORY_PIG = 0x0004;   // 0000000000000100

    // Collision bits
    public static final short MASK_BIRD = CATEGORY_BLOCK | CATEGORY_PIG;  // Birds collide with blocks and pigs
    public static final short MASK_BLOCK = CATEGORY_BIRD;                 // Blocks collide with birds
    public static final short MASK_PIG = CATEGORY_BIRD;
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
    public void createBirdBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody ;
        bodyDef.position.set(x / PPM, y / PPM);
        bodyDef.bullet = true;  // Enables continuous collision detection (CCD) for fast-moving bodies


        body = world.createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(20 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;

// Assign collision filtering
        fixtureDef.filter.categoryBits = CATEGORY_BIRD;
        fixtureDef.filter.maskBits = MASK_BIRD;

        Fixture fixture = body.createFixture(fixtureDef);
        // Set bird instance as user data

        body.createFixture(fixtureDef);
        fixture.setUserData(this);
        circle.dispose();
    }
    public void launch(boolean isOnSlingshot, float slingshotX, float slingshotY, float forceX, float forceY) {
        if (body != null) {
            if (isOnSlingshot) {
                // Move the bird to the slingshot
                System.out.println("Placing bird on slingshot at: " + slingshotX + ", " + slingshotY);
                body.setTransform(slingshotX / PPM, slingshotY / PPM, 0); // Move to slingshot position
                body.setType(BodyDef.BodyType.StaticBody); // Static while sitting on slingshot
            } else {
                // Launch the bird with a dynamic body
                System.out.println("Launching bird with force: " + forceX + ", " + forceY);
                body.setType(BodyDef.BodyType.DynamicBody); // Allow movement
                body.applyLinearImpulse(new Vector2(forceX / PPM, forceY / PPM), body.getWorldCenter(), true);
            }
        } else {
            System.out.println("Error: Bird body is null!");
        }
    }

    public void render() {
        if (body == null) {
            System.out.println("Error: Box2D body for the bird is not initialized!");
        } else {
            getBirdSprite().setPosition(
                (body.getPosition().x * PPM) - getBirdSprite().getWidth() / 2,
                (body.getPosition().y * PPM) - getBirdSprite().getHeight() / 2
            );
            getBirdSprite().draw(getBatch()); // Ensure the sprite is drawn
        }
    }


    public Body getBody() {
        return body;
    }
}
