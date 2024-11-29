package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.drop.Sprites.Bird.PPM;
import static com.badlogic.drop.Sprites.Ground.CATEGORY_GROUND;

public abstract class Pig {
    private Sprite pigSprite;
    private SpriteBatch batch;
    private Body body;
    private boolean destroyed = false;

    // Collision categories
    public static final short CATEGORY_BIRD = 0x0001;
    public static final short CATEGORY_BLOCK = 0x0002;
    public static final short CATEGORY_PIG = 0x0004;

    // Collision masks
    public static final short MASK_BIRD = CATEGORY_BLOCK | CATEGORY_PIG| CATEGORY_GROUND;
    public static final short MASK_BLOCK = CATEGORY_BIRD | CATEGORY_GROUND | CATEGORY_BLOCK |CATEGORY_PIG;
    public static final short MASK_PIG = CATEGORY_BIRD | CATEGORY_GROUND | CATEGORY_BLOCK |CATEGORY_PIG;

    public Pig(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture pigTexture = new Texture(texturePath);
        pigSprite = new Sprite(pigTexture);
        pigSprite.setPosition(x, y);
    }

    public void createPigBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);
        body.setUserData(this);  // Critical: Set UserData on body

        CircleShape circle = new CircleShape();
        circle.setRadius(15 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.9f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.5f;

        // Collision filtering
        fixtureDef.filter.categoryBits = CATEGORY_PIG;
        fixtureDef.filter.maskBits = MASK_PIG | CATEGORY_GROUND;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);  // Also set UserData on fixture

        circle.dispose();
    }

    public void render(SpriteBatch batch) {
        if (!destroyed && body != null) {
            pigSprite.setPosition(
                (body.getPosition().x * PPM) - pigSprite.getWidth() / 2,
                (body.getPosition().y * PPM) - pigSprite.getHeight() / 2
            );
            pigSprite.draw(batch);
        }
    }

    public void dispose() {
        pigSprite.getTexture().dispose();
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public Body getBody() {
        return body;
    }

    public void destroyBody(World world) {
        if (body != null) {
            Gdx.app.log("Pig", "Destroying pig body");
            world.destroyBody(body);
            body = null;
            destroyed = true;
        }
    }
}
