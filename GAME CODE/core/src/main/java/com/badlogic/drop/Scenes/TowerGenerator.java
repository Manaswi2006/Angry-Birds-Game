package com.badlogic.drop.Scenes;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.*;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import java.util.Random;

public class TowerGenerator {
    private static final float PPM = 100.0f;
    private Angry_Birds_Game game;
    private Array<Blocks> blockTower;
    private Array<Pig> pigs;

    // Constructor
    public TowerGenerator(Angry_Birds_Game game) {
        this.game = game;
        blockTower = new Array<>();
        pigs = new Array<>();
    }

    // Method to generate a tower
    public void generateTower(float baseX, float baseY, World world) {
        Random random = new Random();
        int towerWidth = random.nextInt(3) + 2; // Random width of 2 to 5 blocks

        // Generate blocks for each column in the tower with varying heights
        for (int i = 0; i < towerWidth; i++) {
            int columnHeight = random.nextInt(3) + 2; // Random height of 1 to 5 blocks for each column
            for (int j = 0; j < columnHeight; j++) {
                Blocks block = createRandomBlock(baseX + i * 80, baseY + j * 70, random);
                block.createBlockBody(world, baseX + i * 80, baseY + j * 70);
                blockTower.add(block);
            }
        }

        // Place multiple pigs on random blocks
        // Place multiple pigs on random blocks
        int numPigs = random.nextInt(3) + 2; // Place between 2 and 4 pigs
        for (int k = 0; k < numPigs; k++) {
            int pigPosition = random.nextInt(blockTower.size);
            Blocks pigBlock = blockTower.get(pigPosition);

            // Generate a random pig
            Pig pig = createRandomPig(pigBlock.getBlockSprite().getX() + 15, pigBlock.getBlockSprite().getY() + 18, random);
            pigs.add(pig);
            pig.createPigBody(world, pigBlock.getBlockSprite().getX() + 15, pigBlock.getBlockSprite().getY() + 18); // Create Box2D body
        }
    }

    // Helper method to create a random pig type
    private Pig createRandomPig(float x, float y, Random random) {
        int pigType = random.nextInt(3);
        switch (pigType) {
            case 0:
                return new Pig_1(game, x, y);
            case 1:
                return new Pig_2(game, x, y);
            case 2:
                return new Pig_3(game, x, y);
            default:
                return new Pig_1(game, x, y); // Default to Pig_1 if something unexpected occurs
        }
    }


    // Helper method to create a random block type
    private Blocks createRandomBlock(float x, float y, Random random) {
        int blockType = random.nextInt(10); // Expanded range to give preference to cases 0 and 1
        switch (blockType) {
            case 0:
            case 1:
            case 2: // More likely to be Wood_Block_1
                return new Wood_Block_1(game, x, y);
            case 3:
            case 4: // More likely to be Wood_Block_2
                return new Wood_Block_2(game, x, y);
            case 5:
                return new Ice_Block_1(game, x, y);
            case 6:
                return new Ice_Block_3(game, x, y);
            case 7:
                return new Stone_Block_1(game, x, y);
            case 8:
                return new Stone_Block_2(game, x, y);
            default:
                return new Stone_Block_3(game, x, y);
        }
    }


    // Render method for blocks and pigs
    public void render() {
        for (Blocks block : blockTower) {
            if (!block.isDestroyed()) {  // Only render blocks that are not destroyed
                block.render(game.getBatch());
            }
        }
        for (Pig pig : pigs) {
            if (!pig.isDestroyed()) {  // Same logic for pigs
                pig.render(game.getBatch());
            }
        }
    }


    // Dispose resources for all blocks and pigs
    public void dispose() {
        for (Blocks block : blockTower) {
            block.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
    }
}
