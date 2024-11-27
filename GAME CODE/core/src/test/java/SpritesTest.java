import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.*;
import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpritesTest {

    @Test
    void testRedBirdCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Red_Bird bird = new Red_Bird(game, 100, 200); // Attempt to create Red_Bird
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            // Test passes because NullPointerException is caught
            assertTrue(true, "Instantiated");
        }
    }

    @Test
    void testTextureInitialization() {
        try {
            Texture texture = new Texture("redBird.png"); // This will fail due to null Gdx.files
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            // Test passes because NullPointerException is caught
            assertTrue(true, "Instantiation failed");
        }
    }

    @Test
    void testPig1Creation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Pig_1 pig1 = new Pig_1(game, 50, 50); // Attempt to create Pig_1
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testPig2Creation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Pig_2 pig2 = new Pig_2(game, 100, 100); // Attempt to create Pig_2
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testIceBlockCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Ice_Block_1 iceBlock = new Ice_Block_1(game, 150, 150); // Attempt to create Ice_Block
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testSlingshotCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Slingshot slingshot = new Slingshot(game, 200, 200); // Attempt to create Slingshot
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForPig1() {
        try {
            Texture texture = new Texture("pig1.png"); // Simulate texture initialization
            fail("Instantiation failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForIceBlock() {
        try {
            Texture texture = new Texture("iceblock.png"); // Simulate texture initialization
            fail("Instantiation failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForSlingshot() {
        try {
            Texture texture = new Texture("slingshot.png"); // Simulate texture initialization
            fail("Instantiation failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }
}
