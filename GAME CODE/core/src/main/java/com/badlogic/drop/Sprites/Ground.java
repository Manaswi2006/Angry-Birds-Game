package com.badlogic.drop.Sprites;
import com.badlogic.gdx.physics.box2d.*;

public class Ground {
    private Body groundBody;

    public Ground(World world, float x, float y, float width, float height) {
        createGround(world, x, y, width, height);
    }

    private void createGround(World world, float x, float y, float width, float height) {
        // Define the body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;  // Static body doesn't move
        groundBodyDef.position.set(x, y);  // Position of the ground

        // Create the ground body in the world
        groundBody = world.createBody(groundBodyDef);

        // Define the shape (Rectangle for the ground)
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(width / 2f, height / 2f);  // Size of the ground (half-width, half-height)

        // Create the fixture for the ground
        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.friction = 0.5f;  // Adjust friction as needed

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
