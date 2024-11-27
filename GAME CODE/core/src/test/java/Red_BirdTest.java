import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.Red_Bird;
import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Red_BirdTest {

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
            Texture texture = new Texture("fake_path.png"); // This will fail due to null Gdx.files
            fail("Instantiated"); // Fail if no exception is thrown
        } catch (NullPointerException e) {
            // Test passes because NullPointerException is caught
            assertTrue(true, "Instantiation failed");
        }
    }
}
