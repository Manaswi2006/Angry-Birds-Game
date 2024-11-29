package com.badlogic.drop.Screens;

import com.badlogic.drop.Sprites.Bird;
import com.badlogic.drop.Sprites.Blocks;
import com.badlogic.drop.Sprites.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {

   // @Override
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        // Log the user data for debugging
        Gdx.app.log("Contact", "Begin contact between " + fixA.getUserData() + " and " + fixB.getUserData());

        // Check if bird hits a block
        if (isBirdHitBlock(fixA, fixB)) {
            handleBlockCollision(fixB);
        } else if (isBirdHitBlock(fixB, fixA)) {
            handleBlockCollision(fixA);
        }

        // Check if bird hits a pig
        if (isBirdHitPig(fixA, fixB)) {
            handlePigCollision(fixB);
        } else if (isBirdHitPig(fixB, fixA)) {
            handlePigCollision(fixA);
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

    private void handleBlockCollision(Fixture fixture) {
        if (fixture.getUserData() instanceof Blocks) {
            Blocks block = (Blocks) fixture.getUserData();
            if (!block.isDestroyed()) {
                Gdx.app.log("WorldContactListener", "Destroying block: " + block);
                block.destroyBody();  // Destroy block and mark it
            }
        } else {
            Gdx.app.log("WorldContactListener", "Fixture is not a block. UserData: " + fixture.getUserData());
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
