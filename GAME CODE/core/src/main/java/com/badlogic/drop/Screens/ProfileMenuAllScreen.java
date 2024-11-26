package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ProfileMenuAllScreen implements Screen {

    // ATTRIBUTES
    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle User1ButtonBounds;
    private final Rectangle User2ButtonBounds;
    private final Rectangle User3ButtonBounds;
    private final Rectangle GoBackButtonBounds;
    private BitmapFont font;
    private String[] profiles = {"User 1", "User 2", "User 3"};

    // CONSTRUCTOR
    public ProfileMenuAllScreen(Angry_Birds_Game _game) {
        this.game = _game;
        texture = new Texture("ProfileMenuAll.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792, 1024, gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);

        // Button bounds
        User1ButtonBounds = new Rectangle(693, 1024 - 467 - 104, 426, 104);
        User2ButtonBounds = new Rectangle(693, 1024 - 605 - 104, 426, 104);
        User3ButtonBounds = new Rectangle(693, 1024 - 744 - 104, 426, 104);
        GoBackButtonBounds = new Rectangle(35, 1024 - 35 - 135, 134, 135);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
    }

    // GETTERS AND SETTERS
    public Angry_Birds_Game getGame() {
        return game;
    }

    public void setGame(Angry_Birds_Game _game) {
        this.game = _game;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        this.texture = _texture;
    }

    // SCREEN METHODS
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw texture and profile names
        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);

        // Draw profile names on respective buttons
        font.draw(game.getBatch(), profiles[0], 850, 1024 - 467 - 35);
        font.draw(game.getBatch(), profiles[1], 850, 1024 - 605 - 35);
        font.draw(game.getBatch(), profiles[2], 850, 1024 - 744 - 35);
        game.getBatch().end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check for button interactions
            if (User1ButtonBounds.contains(touchX, touchY)) {
                //game.setActiveProfile(profiles[0]);
                game.setScreen(new LevelsMenuAllScreen(game));
                dispose();
            } else if (User2ButtonBounds.contains(touchX, touchY)) {
                //game.setActiveProfile(profiles[1]);
                game.setScreen(new LevelsMenuAllScreen(game));
                dispose();
            } else if (User3ButtonBounds.contains(touchX, touchY)) {
                //game.setActiveProfile(profiles[2]);
                game.setScreen(new LevelsMenuAllScreen(game));
                dispose();
            } else if (GoBackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new FirstScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        texture.dispose();
        font.dispose();
    }
}
