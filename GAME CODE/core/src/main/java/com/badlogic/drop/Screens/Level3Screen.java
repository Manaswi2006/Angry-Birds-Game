package com.badlogic.drop.Screens;
import com.badlogic.drop.Screens.WorldContactListener;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.*;
import com.badlogic.drop.Scenes.TowerGenerator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level3Screen implements Screen  {

    // ATTRIBUTES
    private Profile profile;
    private boolean isDragging = false;
    private Bird currentBird = null;
    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Black_Bird bird1;
    private Red_Bird bird2;
    private Red_Bird bird3;
    private Slingshot slingshot;
    private TowerGenerator towerGenerator;
    Array<Fixture> fixturtodestroy = new Array<Fixture>(10);
    Array<Body> bodiestodestroy = new Array<Body>(10);
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float startDragX;  // To record initial drag position X
    private float startDragY;  // To record initial drag position Y
    private ShapeRenderer trajectoryRenderer = new ShapeRenderer();



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
        Ground ground = new Ground(world,0,0,4000,325);
        // Initialize texture and camera
        setTexture(new Texture("Level3.png"));
        setGamecam(new OrthographicCamera());
        setGameport(new FitViewport(1792, 1024, getGamecam()));
        getGamecam().position.set(1792 / 2f, 1024 / 2f, 0);

        // Initialize birds and slingshot
        setBird1(new Black_Bird(getGame(), 425, 350));
        setBird2(new Red_Bird(getGame(), 425, 350));
        setBird3(new Red_Bird(getGame(), 425, 350));
        setSlingshot(new Slingshot(getGame(), 400, 240));
        //create bodies for red birds
        bird1.createBirdBody(world , 425, 350);
        bird2.createBirdBody(world, 425, 350);  // Create Box2D body for bird2
        bird3.createBirdBody(world, 425, 350);


        // Initialize TowerGenerator and generate tower
        setTowerGenerator(new TowerGenerator(getGame(), 1, profile));
        getTowerGenerator().generateTower(1200, 225,world);

        // Define button bounds
        setGoBackButtonBounds(new Rectangle(35, 1024 - 35 - 134, 133, 134));
        setGiveUpButtonBounds(new Rectangle(1521, 1024 - 47 - 110, 228, 110));
        setPauseButtonBounds(new Rectangle(230, 1024 - 26 - 134, 164, 171));

        //code for the collision part
        world.setContactListener(new WorldContactListener());

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
        this.bird1 = (Black_Bird) bird1;
    }

    public Bird getBird2() {
        return bird2;
    }

    public void setBird2(Bird bird2) {
        this.bird2 = (Red_Bird) bird2;
    }

    public Bird getBird3() {
        return bird3;
    }

    public void setBird3(Bird bird3) {
        this.bird3 = (Red_Bird) bird3;
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
        // Step the physics world
        world.step(1 / 60f, 6, 2);

        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Remove fixtures queued for destruction
        for (Fixture fixture : fixturtodestroy) {
            if (fixture != null && fixture.getBody() != null) {
                fixture.getBody().destroyFixture(fixture);
            }
        }
        fixturtodestroy.clear();

        // Remove bodies queued for destruction
        for (Body body : bodiestodestroy) {
            if (body != null) {
                world.destroyBody(body);
            }
        }
        bodiestodestroy.clear();

        // SpriteBatch rendering (unchanged)
        getGame().getBatch().setProjectionMatrix(getGamecam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().draw(getTexture(), 0, 0); // Background
        getTowerGenerator().render();                 // Tower
        getSlingshot().render();                      // Slingshot
        bird1.render();                               // Birds
        bird2.render();
        bird3.render();
        getGame().getBatch().end();

        // Debug rendering
        debugRenderer.render(world, gamecam.combined);
        //drawTrajectory();
        // Handle input
        handleInput();
    }
    // Handle input for button clicks


    private void drawTrajectory() {
        if (isDragging && currentBird != null) {
            trajectoryRenderer.setProjectionMatrix(gamecam.combined);
            trajectoryRenderer.begin(ShapeRenderer.ShapeType.Line);
            trajectoryRenderer.setColor(Color.RED);

            Vector2 startPos = new Vector2(currentBird.getBirdSprite().getX(), currentBird.getBirdSprite().getY());
            Vector2 force = new Vector2(startDragX - currentBird.getBirdSprite().getX(), startDragY - currentBird.getBirdSprite().getY()).scl(3);
            Vector2 velocity = new Vector2(force.x, force.y).scl(0.05f);
            Vector2 gravity = world.getGravity().scl(0.05f);

            Vector2 point = new Vector2(startPos);
            for (int i = 0; i < 200; i++) {
                trajectoryRenderer.line(point.x, point.y, point.x + velocity.x, point.y + velocity.y);
                point.add(velocity);
                velocity.add(gravity);
            }

            trajectoryRenderer.end();
        }
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            Vector3 worldTouch = gamecam.unproject(new Vector3(touchX, touchY, 0));

            // Check which bird was touched
            if (bird1.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird1;
                startDragX = worldTouch.x;
                startDragY = worldTouch.y;
            } else if (bird2.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird2;
                startDragX = worldTouch.x;
                startDragY = worldTouch.y;
            } else if (bird3.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird3;
                startDragX = worldTouch.x;
                startDragY = worldTouch.y;
            }
        }

        if (Gdx.input.isTouched() && currentBird != null) {
            isDragging = true;
            float dragX = Gdx.input.getX();
            float dragY = Gdx.input.getY();

            Vector3 worldDrag = gamecam.unproject(new Vector3(dragX, dragY, 0));

            // Limit the dragging so that bird can only be dragged behind the slingshot
            float maxDragDistance = 100f;  // Define a maximum distance for dragging
            Vector2 slingshotPos = new Vector2(slingshot.getSlingSprite().getX(), slingshot.getSlingSprite().getY());
            Vector2 dragPos = new Vector2(worldDrag.x, worldDrag.y);

            if (dragPos.dst(slingshotPos) > maxDragDistance) {
                dragPos = slingshotPos.cpy().add(dragPos.sub(slingshotPos).nor().scl(maxDragDistance));
            }

            currentBird.dragBird(dragPos.x, dragPos.y);  // Move bird to dragged position visually
        }

        if (!Gdx.input.isTouched() && isDragging && currentBird != null) {
            isDragging = false;

            // Calculate force based on start and current position
            float releaseX = currentBird.getBirdSprite().getX();
            float releaseY = currentBird.getBirdSprite().getY();

            float forceX = (startDragX - releaseX) * 3;  // Reverse to launch forward and scale to increase the power of the launch
            float forceY = (startDragY - releaseY) * 3;  // Reverse to launch forward and scale to increase the power of the launch

            // Launch the bird with the calculated force
            currentBird.launch(false, 0, 0, forceX, forceY);
            currentBird = null;  // Clear the current bird after launch
        }

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
                getGame().setScreen(new GameLostScreen(getGame(), 1,profile));
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
        // public void dispose() {
        // Dispose Box2D world
        world.dispose();
        debugRenderer.dispose();
    }
}
