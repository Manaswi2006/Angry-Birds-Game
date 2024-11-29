
package com.badlogic.drop.Screens;

import com.badlogic.drop.Sprites.Bird;
import com.badlogic.drop.Sprites.Blocks;
import com.badlogic.drop.Sprites.Ground;
import com.badlogic.drop.Sprites.Pig;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class WorldContactListener implements ContactListener {
    WorldManager worldManager = new WorldManager();
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

        // Bird hits block or pig
        if (isBirdHitBlock(fixA, fixB)) {
            handleBlockCollision(fixA, fixB);
        } else if (isBirdHitPig(fixA, fixB)) {
            handlePigCollision(fixA, fixB);
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

    private void handleBlockCollision(Fixture fixture, Fixture fixB) {
        if (fixture.getUserData() instanceof Blocks) {
            Blocks block = (Blocks) fixture.getUserData();
            if (!block.isDestroyed()) {
                Gdx.app.log("WorldContactListener", "Marking block for destruction: " + block);
                block.setDestroyed(true);
                //world.destroyBody(body);// Set destroyed to true to avoid multiple hits
                worldManager.addToDestroyQueue(block.getBody());
            }
        }
    }

    private void handlePigCollision(Fixture fixture, Fixture fixB) {
        if (fixture.getUserData() instanceof Pig) {
            Pig pig = (Pig) fixture.getUserData();
            if (!pig.isDestroyed()) {
                Gdx.app.log("WorldContactListener", "Marking pig for destruction: " + pig);
                pig.setDestroyed(true);  // Set destroyed to true to avoid multiple hit
                worldManager.addToDestroyQueue(pig.getBody());
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

class WorldManager {
    private Array<Body> bodiesToDestroy;

    public WorldManager() {
        bodiesToDestroy = new Array<>();
    }

    public void addToDestroyQueue(Body body) {
        bodiesToDestroy.add(body);
    }

    public void processDestroyQueue(World world) {
        for (Body body : bodiesToDestroy) {
            if (body != null) {
                world.destroyBody(body);
            }
        }
        bodiesToDestroy.clear(); // Clear the queue after processing
    }
}

