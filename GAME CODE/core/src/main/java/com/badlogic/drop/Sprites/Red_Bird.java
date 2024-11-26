package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.physics.box2d.*;

public class Red_Bird extends Bird{
    private Body body;
    public static final float PPM = 100.0f;
    public Red_Bird(Angry_Birds_Game game, float x, float y) {
        super(game, x, y, "Red_Bird.png");
    }
    // Method to create the Box2D body
    public void createBirdBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Make the bird static initially
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(20 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.6f;

        body.createFixture(fixtureDef);
        circle.dispose();
    }
    public void launch() {
        if (body != null) {
            body.setType(BodyDef.BodyType.DynamicBody);
        }
    }


    @Override
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

}
