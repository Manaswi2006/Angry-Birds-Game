package com.badlogic.drop.Sprites;

import com.badlogic.drop.Angry_Birds_Game;
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
 private World world ;

    public Blocks(Angry_Birds_Game game, float x, float y, String texturePath) {
        this.batch = game.getBatch();
        Texture blockTexture = new Texture(texturePath);
        this.blockSprite = new Sprite(blockTexture);
        this.blockSprite.setPosition(x, y);

    }


    public void createBlockBody(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody; // Blocks remain static unless they are supposed to fall
        bodyDef.position.set(x / PPM, y / PPM);

        body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(blockSprite.getWidth() / 2 / PPM, blockSprite.getHeight() / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this); // This ensures the fixture references its associated block

        box.dispose();
    }

    public void render() {
        blockSprite.draw(batch);
    }

    public void dispose() {
        blockSprite.getTexture().dispose();
    }

    public Sprite getBlockSprite() {
        return this.blockSprite;
    }

    public void destroyBody() {
        if (body != null && world != null) {
            world.destroyBody(body);  // Removes the body from the physics world
            body = null;  // Avoids potential null pointer issues later
        }
    }
}
