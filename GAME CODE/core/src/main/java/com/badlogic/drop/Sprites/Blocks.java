package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.drop.Sprites.Bird.PPM;
import static com.badlogic.drop.Sprites.Ground.CATEGORY_GROUND;

public abstract class Blocks {
    private Sprite blockSprite;
    private SpriteBatch batch;
    private Body body;
    private World world;
    private boolean destroyed = false;

    // Collision categories
    public static final short CATEGORY_BIRD = 0x0001;
    public static final short CATEGORY_BLOCK = 0x0002;
    public static final short CATEGORY_PIG = 0x0004;

    // Collision masks
    public static final short MASK_BIRD = CATEGORY_BLOCK | CATEGORY_PIG| CATEGORY_GROUND;
    public static final short MASK_BLOCK = CATEGORY_BIRD | CATEGORY_GROUND;
    public static final short MASK_PIG = CATEGORY_BIRD | CATEGORY_GROUND;

    public Blocks(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture blockTexture = new Texture(texturePath);
        this.blockSprite = new Sprite(blockTexture);
        this.blockSprite.setPosition(x, y);
    }

    public void createBlockBody(World world, float x, float y) {
        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;  // Changed from KinematicBody to DynamicBody
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);
        body.setUserData(this);  // Critical: Set UserData on body

        PolygonShape box = new PolygonShape();
        box.setAsBox(blockSprite.getWidth() / 2 / PPM, blockSprite.getHeight() / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 1.0f;  // Increase density to make blocks more stable
        fixtureDef.friction = 2.0f;  // Increase friction to prevent sliding

        fixtureDef.restitution = 0.1f;

        // Collision filtering
        fixtureDef.filter.categoryBits = CATEGORY_BLOCK;
        fixtureDef.filter.maskBits = MASK_BLOCK | CATEGORY_GROUND | CATEGORY_BLOCK |CATEGORY_PIG;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);  // Also set UserData on fixture

        box.dispose();
        Gdx.app.log("Blocks", "Created block with UserData: " + this);
    }

    public void render(SpriteBatch batch) {
        if (!destroyed && body != null) {
            blockSprite.setPosition(
                (body.getPosition().x * PPM) - blockSprite.getWidth() / 2,
                (body.getPosition().y * PPM) - blockSprite.getHeight() / 2
            );
            blockSprite.draw(batch);
        }
    }

    public void dispose() {
        blockSprite.getTexture().dispose();
    }

    public Sprite getBlockSprite() {
        return this.blockSprite;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void deactivateBody() {
        if (body != null) {
            body.setActive(false);  // Deactivate the body so it no longer interacts with other objects
            destroyed = true;       // Mark it as destroyed so we don't render it anymore
        }
    }

    public void setDestroyed(boolean b) {
        destroyed = b;
    }

    public Body getBody() {
        return body;
    }
}
