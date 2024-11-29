package com.badlogic.drop.Sprites;

import com.badlogic.gdx.physics.box2d.*;

public class Ground {
    private Body groundBody;
    private static final float PPM = 100f;

    // Collision categories
    public static final short CATEGORY_GROUND = 0x0008;
    public static final short CATEGORY_BLOCK = 0x0002;
    public static final short CATEGORY_PIG = 0x0004;
    public static final short CATEGORY_BIRD = 0x0001;

    public Ground(World world, float x, float y, float width, float height) {
        createGround(world, x, y, width, height);
    }

    private void createGround(World world, float x, float y, float width, float height) {
        // Define the body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        groundBodyDef.position.set(x/ PPM, y/ PPM);

        // Create the ground body in the world
        groundBody = world.createBody(groundBodyDef);
        groundBody.setUserData(this);  // Set UserData

        // Define the shape (Rectangle for the ground)
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width / 2f / PPM, height / 2f/ PPM);

        // Create the fixture for the ground
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.friction = 0.5f;

        // Collision filtering
        groundFixture.filter.categoryBits = CATEGORY_GROUND;
        groundFixture.filter.maskBits = CATEGORY_BLOCK | CATEGORY_PIG | CATEGORY_BIRD;

        // Attach the fixture to the ground body
        groundBody.createFixture(groundFixture);

        // Dispose the shape after it's used
        groundShape.dispose();
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public void dispose() {
        if (groundBody != null) {
            groundBody.getWorld().destroyBody(groundBody);
        }
    }
}
