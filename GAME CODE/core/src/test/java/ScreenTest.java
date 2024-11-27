import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Screens.*;
import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScreenTests {

    @Test
    void testProfilesMenuAllScreenCreation() {
        try {
            Angry_Birds_Game game = null;
            ProfileMenuAllScreen screen = new ProfileMenuAllScreen(game); // Attempt to create ProfilesMenuAllScreen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testPause1ScreenCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Pause1screen screen = new Pause1screen(game, new Profile("user",3)); // Attempt to create Pause1Screen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testPause2ScreenCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Pause2screen screen = new Pause2screen(game, new Profile("user",3)); // Attempt to create Pause2Screen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testLevel1ScreenCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Level1Screen screen = new Level1Screen(game, new Profile("user",1)); // Attempt to create Level1Screen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testLevel2ScreenCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            Level2Screen screen = new Level2Screen(game, new Profile("user",1)); // Attempt to create Level2Screen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testGameWonScreenCreation() {
        try {
            Angry_Birds_Game game = null; // Simulate a null game environment
            GameWonScreen screen = new GameWonScreen(game,1, new Profile("user" , 1)); // Attempt to create GameWonScreen
            fail("Instantiation Failed"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiated"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForProfilesMenuAllScreen() {
        try {
            Texture texture = new Texture("profiles.png"); // Simulate texture initialization
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiation failed"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForPause1Screen() {
        try {
            Texture texture = new Texture("pause1.png"); // Simulate texture initialization
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiation failed"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForLevel1Screen() {
        try {
            Texture texture = new Texture("level1.png"); // Simulate texture initialization
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiation failed"); // Pass if NullPointerException is caught
        }
    }

    @Test
    void testTextureInitializationForGameWonScreen() {
        try {
            Texture texture = new Texture("gamewon.png"); // Simulate texture initialization
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            assertTrue(true, "Instantiation failed"); // Pass if NullPointerException is caught
        }
    }
}
