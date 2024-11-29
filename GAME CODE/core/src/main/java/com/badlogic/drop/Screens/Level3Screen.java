package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.*;
import com.badlogic.drop.Scenes.TowerGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level3Screen implements Screen {

    // ATTRIBUTES
    private Profile profile;
    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Red_Bird bird1;
    private Yellow_Bird bird2;
    private Black_Bird bird3;
    private Slingshot slingshot;
    private TowerGenerator towerGenerator;

    private Rectangle goBackButtonBounds;
    private Rectangle giveUpButtonBounds;
    private Rectangle pauseButtonBounds;
    private World world;  // creating world for box2d
    private Box2DDebugRenderer debugRenderer;

    // CONSTRUCTOR
    public Level3Screen(Angry_Birds_Game game, Profile profile) {
        world = new World(new Vector2(0, -9.8f), true);  // (0, -9.8) is the gravity vector
// for gravity
// Initialize the debug renderer to visualize Box2D objects
        this.profile = profile;
        debugRenderer = new Box2DDebugRenderer();
        setGame(game);

        // Initialize texture and camera
        setTexture(new Texture("Level3.png"));
        setGamecam(new OrthographicCamera());
        setGameport(new FitViewport(1792, 1024, getGamecam()));
        getGamecam().position.set(1792 / 2f, 1024 / 2f, 0);

        // Initialize birds and slingshot
        setBird1(new Red_Bird(getGame(), 275, 300));
        setBird2(new Yellow_Bird(getGame(), 360, 300));
        setBird3(new Black_Bird(getGame(), 435, 400));
        setSlingshot(new Slingshot(getGame(), 410, 300));

        // Initialize TowerGenerator and generate tower
        setTowerGenerator(new TowerGenerator(getGame()));
        getTowerGenerator().generateTower(1400, 225, world);

        // Define button bounds
        setGoBackButtonBounds(new Rectangle(35, 1024 - 35 - 134, 133, 134));
        setGiveUpButtonBounds(new Rectangle(1521, 1024 - 47 - 110, 228, 110));
        setPauseButtonBounds(new Rectangle(230, 1024 - 26 - 134, 164, 171));
    }

    // GETTERS AND SETTERS
    public Angry_Birds_Game getGame() {
        return game;
    }

    public void setGame(Angry_Birds_Game game) {
        this.game = game;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public void setGamecam(OrthographicCamera gamecam) {
        this.gamecam = gamecam;
    }

    public Viewport getGameport() {
        return gameport;
    }

    public void setGameport(Viewport gameport) {
        this.gameport = gameport;
    }

    public Bird getBird1() {
        return bird1;
    }

    public void setBird1(Bird bird1) {
        this.bird1 = (Red_Bird) bird1;
    }

    public Bird getBird2() {
        return bird2;
    }

    public void setBird2(Bird bird2) {
        this.bird2 = (Yellow_Bird) bird2;
    }

    public Bird getBird3() {
        return bird3;
    }

    public void setBird3(Bird bird3) {
        this.bird3 = (Black_Bird) bird3;
    }

    public Slingshot getSlingshot() {
        return slingshot;
    }

    public void setSlingshot(Slingshot slingshot) {
        this.slingshot = slingshot;
    }

    public TowerGenerator getTowerGenerator() {
        return towerGenerator;
    }

    public void setTowerGenerator(TowerGenerator towerGenerator) {
        this.towerGenerator = towerGenerator;
    }

    public Rectangle getGoBackButtonBounds() {
        return goBackButtonBounds;
    }

    public void setGoBackButtonBounds(Rectangle goBackButtonBounds) {
        this.goBackButtonBounds = goBackButtonBounds;
    }

    public Rectangle getGiveUpButtonBounds() {
        return giveUpButtonBounds;
    }

    public void setGiveUpButtonBounds(Rectangle giveUpButtonBounds) {
        this.giveUpButtonBounds = giveUpButtonBounds;
    }

    public Rectangle getPauseButtonBounds() {
        return pauseButtonBounds;
    }

    public void setPauseButtonBounds(Rectangle pauseButtonBounds) {
        this.pauseButtonBounds = pauseButtonBounds;
    }

    // METHODS
    @Override
    public void show() {
        // Placeholder for code to execute when screen is shown
    }

    @Override
    public void render(float delta) {
        // Clear screen with black color
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set camera projection and begin rendering
        getGame().getBatch().setProjectionMatrix(getGamecam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().draw(getTexture(), 0, 0);

        // Render birds, slingshot, and tower with pigs

//        getTowerGenerator().render();
//        getSlingshot().render();
//        getBird1().render();
//        getBird2().render();
//        getBird3().render();

        getGame().getBatch().end();

        // Handle user input
        handleInput();
    }

    // Handle input for button clicks
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = getGameport().getScreenHeight() - touchY;

            // Check if the touch is within the defined button bounds
            if (getGoBackButtonBounds().contains(touchX, touchY)) {
                getGame().setScreen(new LevelsMenuAllScreen(getGame(),profile));
                dispose();
            } else if (getGiveUpButtonBounds().contains(touchX, touchY)) {
                getGame().setScreen(new GameLostScreen(getGame(), 3,profile));
                dispose();
            } else if (getPauseButtonBounds().contains(touchX, touchY)) {
                // Save the current level screen in the game instance
                getGame().setSavedLevel3Screen(this);

                // Navigate to the pause screen
                getGame().setScreen(new Pause3screen(getGame(),profile));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        getGameport().update(width, height);
    }

    @Override
    public void pause() {
        // Placeholder for code to execute on pause
    }

    @Override
    public void resume() {
        // Placeholder for code to execute on resume
    }

    @Override
    public void hide() {
        // Placeholder for code to execute when screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose textures and sprites
        getTexture().dispose();
        getBird1().dispose();
        getBird2().dispose();
        getBird3().dispose();
        getSlingshot().dispose();
        getTowerGenerator().dispose();
    }
}
