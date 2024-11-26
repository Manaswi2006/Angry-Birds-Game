package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Sprites.Bird;
import com.badlogic.drop.Sprites.Ground;
import com.badlogic.drop.Sprites.Red_Bird;
import com.badlogic.drop.Sprites.Slingshot;
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

public class Level1Screen implements Screen {

    // ATTRIBUTES
    private boolean isDragging = false;
    private Red_Bird currentBird = null;
    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Red_Bird bird1;
    private Red_Bird bird2;
    private Red_Bird bird3;
    private Slingshot slingshot;
    private TowerGenerator towerGenerator;
    Array<Fixture> fixturtodestroy = new Array<Fixture>(10);
    Array<Body> bodiestodestroy = new Array<Body>(10);
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Rectangle goBackButtonBounds;
    private Rectangle giveUpButtonBounds;
    private Rectangle pauseButtonBounds;
    private World world;  // creating world for box2d
    private Box2DDebugRenderer debugRenderer;
    // CONSTRUCTOR
    public Level1Screen(Angry_Birds_Game game) {
        world = new World(new Vector2(0, -9.8f), true);  // (0, -9.8) is the gravity vector
// for gravity
// Initialize the debug renderer to visualize Box2D objects
        debugRenderer = new Box2DDebugRenderer();
        setGame(game);
        //Ground ground = new Ground(world,0,0,4000,600);
        // Initialize texture and camera
        setTexture(new Texture("Level1.png"));
        setGamecam(new OrthographicCamera());
        setGameport(new FitViewport(1792, 1024, getGamecam()));
        getGamecam().position.set(1792 / 2f, 1024 / 2f, 0);

        // Initialize birds and slingshot
        setBird1(new Red_Bird(getGame(), 265, 250));
        setBird2(new Red_Bird(getGame(), 350, 250));
        setBird3(new Red_Bird(getGame(), 425, 350));
        setSlingshot(new Slingshot(getGame(), 400, 240));
        //create bodies for red birds
         bird1.createBirdBody(world , 265 , 250);
        bird2.createBirdBody(world, 350, 250);  // Create Box2D body for bird2
        bird3.createBirdBody(world, 425, 350);


        // Initialize TowerGenerator and generate tower
        setTowerGenerator(new TowerGenerator(getGame()));
        getTowerGenerator().generateTower(1200, 225,world);

        // Define button bounds
        setGoBackButtonBounds(new Rectangle(35, 1024 - 35 - 134, 133, 134));
        setGiveUpButtonBounds(new Rectangle(1521, 1024 - 47 - 110, 228, 110));
        setPauseButtonBounds(new Rectangle(230, 1024 - 26 - 134, 164, 171));

    //code for the collision part

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                // Code to handle the beginning of a collision
            }

            @Override
            public void endContact(Contact contact) {
                // Code to handle the end of a collision
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                Fixture fixtureA = contact.getFixtureA(),fixtureB = contact.getFixtureB();
                fixturtodestroy.add(fixtureA);


            }
        });
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
        // Update physics world
        world.step(1 / 60f, 6, 2);

        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // ShapeRenderer for trajectory
        if (isDragging && currentBird != null) {
            shapeRenderer.setProjectionMatrix(gamecam.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(
                slingshot.getSlingSprite().getX(),
                slingshot.getSlingSprite().getY(),
                currentBird.getBirdSprite().getX(),
                currentBird.getBirdSprite().getY()
            );
            shapeRenderer.end();
        }

        // SpriteBatch rendering
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

        // Handle input
        handleInput();
    }

    private void destroy(Fixture fixture){
        fixturtodestroy.add(fixture);
        if(fixture.getBody().getFixtureList().size - 1 <1){
            bodiestodestroy.add(fixture.getBody());

        }    }

    // Handle input for button clicks


    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            Vector3 worldTouch = gamecam.unproject(new Vector3(touchX, touchY, 0));

            // Check which bird was touched
            if (bird1.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird1;
                currentBird.launch(true, slingshot.getSlingSprite().getX(), slingshot.getSlingSprite().getY(), 0, 0);
            } else if (bird2.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird2;
                currentBird.launch(true, slingshot.getSlingSprite().getX(), slingshot.getSlingSprite().getY(), 0, 0);
            } else if (bird3.getBirdSprite().getBoundingRectangle().contains(worldTouch.x, worldTouch.y)) {
                currentBird = bird3;
                currentBird.launch(true, slingshot.getSlingSprite().getX(), slingshot.getSlingSprite().getY(), 0, 0);
            }
        }

        // Handle dragging motion
        if (Gdx.input.isTouched() && currentBird != null) {
            isDragging = true;
            float dragX = Gdx.input.getX();
            float dragY = Gdx.input.getY();

            Vector3 worldDrag = gamecam.unproject(new Vector3(dragX, dragY, 0));
            currentBird.dragBird(worldDrag.x, worldDrag.y);
        }

        // Release bird when touch is lifted
        if (!Gdx.input.isTouched() && isDragging && currentBird != null) {
            isDragging = false;

            // Calculate force based on slingshot position and release point
            float forceX = slingshot.getSlingSprite().getX() - currentBird.getBirdSprite().getX();
            float forceY = slingshot.getSlingSprite().getY() - currentBird.getBirdSprite().getY();
            currentBird.launch(false, 0, 0, forceX * 2, forceY * 3); // Apply scaled force
            currentBird = null;
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
