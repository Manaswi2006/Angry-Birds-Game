package com.badlogic.drop.Screens;

import com.badlogic.drop.Sprites.Bird;
import com.badlogic.drop.Sprites.Blocks;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData() instanceof Bird && fixB.getUserData() instanceof Blocks) {
            handleCollision((Bird) fixA.getUserData(), (Blocks) fixB.getUserData());
        } else if (fixA.getUserData() instanceof Blocks && fixB.getUserData() instanceof Bird) {
            handleCollision((Bird) fixB.getUserData(), (Blocks) fixA.getUserData());
        }
        // Add pig collision checks similarly
    }

    private void handleCollision(Bird bird, Blocks block) {
        block.dispose();  // Dispose of block texture
        block.destroyBody();  // Remove from physics world
    }

    @Override
    public void endContact(Contact contact) {
        // This method is required by the ContactListener interface.
        // You can leave it empty if no action is required when contacts end.
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { }
}
