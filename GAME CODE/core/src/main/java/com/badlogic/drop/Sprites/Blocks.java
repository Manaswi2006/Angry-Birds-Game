package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.drop.Sprites.Bird.PPM;


public abstract class Blocks {
    public Sprite blockSprite;
    protected SpriteBatch batch;
    private Body body;
    private World world;
    private boolean destroyed = false;
    // Constants for collision categories
    public static final short CATEGORY_BIRD = 0x0001;  // 0000000000000001
    public static final short CATEGORY_BLOCK = 0x0002; // 0000000000000010
    public static final short CATEGORY_PIG = 0x0004;   // 0000000000000100

    // Collision bits
    public static final short MASK_BIRD = CATEGORY_BLOCK | CATEGORY_PIG;  // Birds collide with blocks and pigs
    public static final short MASK_BLOCK = CATEGORY_BIRD;                 // Blocks collide with birds
    public static final short MASK_PIG = CATEGORY_BIRD;                   // Pigs collide with birds


    public Blocks(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture blockTexture = new Texture(texturePath);
        this.blockSprite = new Sprite(blockTexture);
        this.blockSprite.setPosition(x, y);
    }

    public void createBlockBody(World world, float x, float y) {
        this.world  = world ;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody  ; // Blocks remain static unless they are supposed to fall
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);
        body.setUserData(this);  // Set the UserData to reference the Block instance

        PolygonShape box = new PolygonShape();
        box.setAsBox(blockSprite.getWidth() / 2 / PPM, blockSprite.getHeight() / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.1f;
        fixtureDef.filter.categoryBits = CATEGORY_BLOCK;
        fixtureDef.filter.maskBits = MASK_BLOCK;


        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this); // This ensures the fixture references its associated block

        box.dispose();
        Gdx.app.log("Blocks", "Created block with UserData: " + this);

    }

    public void render(SpriteBatch batch) {
        if (!destroyed) {
            blockSprite.draw(batch);  // Use blockSprite instead of sprite
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

    public void destroyBody() {
        if (body != null && world != null) {
            Gdx.app.log("Blocks", "Destroying block body: " + this);
            world.destroyBody(body);
            body = null;
            destroyed = true;
        }
    }

}
