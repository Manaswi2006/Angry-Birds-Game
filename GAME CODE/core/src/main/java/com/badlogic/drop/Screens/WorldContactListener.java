
package com.badlogic.drop.Screens;

import com.badlogic.drop.Sprites.Bird;
import com.badlogic.drop.Sprites.Blocks;
import com.badlogic.drop.Sprites.Ground;
import com.badlogic.drop.Sprites.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // Log the user data for debugging
        Gdx.app.log("Contact", "Begin contact between " +
            (fixA.getUserData() != null ? fixA.getUserData().getClass().getSimpleName() : "null") +
            " and " +
            (fixB.getUserData() != null ? fixB.getUserData().getClass().getSimpleName() : "null")
        );

        // Bird hits block
        if (isBirdHitBlock(fixA, fixB)) {
            handleBlockCollision(fixB);
        } else if (isBirdHitBlock(fixB, fixA)) {
            handleBlockCollision(fixA);
        }

        // Bird hits pig
        if (isBirdHitPig(fixA, fixB)) {
            handlePigCollision(fixB);
        } else if (isBirdHitPig(fixB, fixA)) {
            handlePigCollision(fixA);
        }

        // Block or pig hits ground
        if (isObjectHitGround(fixA, fixB)) {
            Gdx.app.log("WorldContactListener", "Object hit ground");
        } else if (isObjectHitGround(fixB, fixA)) {
            Gdx.app.log("WorldContactListener", "Object hit ground");
        }
    }

    private boolean isBirdHitBlock(Fixture fixA, Fixture fixB) {
        return (fixA.getUserData() instanceof Bird && fixB.getUserData() instanceof Blocks) ||
            (fixA.getUserData() instanceof Blocks && fixB.getUserData() instanceof Bird);
    }

    private boolean isBirdHitPig(Fixture fixA, Fixture fixB) {
        return (fixA.getUserData() instanceof Bird && fixB.getUserData() instanceof Pig) ||
            (fixA.getUserData() instanceof Pig && fixB.getUserData() instanceof Bird);
    }

    private boolean isObjectHitGround(Fixture fixA, Fixture fixB) {
        return (fixA.getUserData() instanceof Blocks || fixA.getUserData() instanceof Pig) &&
            fixB.getUserData() instanceof Ground;
    }

    private void handleBlockCollision(Fixture fixture) {
        if (fixture.getUserData() instanceof Blocks) {
            Blocks block = (Blocks) fixture.getUserData();
            if (!block.isDestroyed()) {
                Gdx.app.log("WorldContactListener", "Destroying block: " + block);
                block.destroyBody();
            }
        }
    }

    private void handlePigCollision(Fixture fixture) {
        if (fixture.getUserData() instanceof Pig) {
            Pig pig = (Pig) fixture.getUserData();
            if (!pig.isDestroyed()) {
                Gdx.app.log("WorldContactListener", "Destroying pig: " + pig);
                pig.destroyBody(pig.getBody().getWorld());
            }
        }
    }

    @Override
    public void endContact(Contact contact) { }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { }
}
