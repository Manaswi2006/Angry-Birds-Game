package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameWonScreen implements Screen {

    //ATTRIBUTES
    private Angry_Birds_Game game;
    private int level;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle NextLevelButtonBounds;
    private final Rectangle MainMenuButtonBounds;
    private final Rectangle ReplayButtonBounds;

    //CONSTRUCTOR
    public GameWonScreen(Angry_Birds_Game _game, int _level){
        this.game = _game;
        this.level = _level;
        texture = new Texture("GameWon.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        NextLevelButtonBounds = new Rectangle(715,  1024 - 535 - 120, 350, 120);
        MainMenuButtonBounds = new Rectangle(715, 1024 - 672 - 120, 350, 120);
        ReplayButtonBounds = new Rectangle(715, 1024 - 809 - 120, 350, 120);
    }

    //GETTERS AND SETTERS
    public Angry_Birds_Game getGame(){
        return game;
    }

    public void setGame(Angry_Birds_Game _game){
        this.game = _game;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture _texture) {
        this.texture = _texture;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public Rectangle getMainMenuButtonBounds() {
        return MainMenuButtonBounds;
    }

    public Rectangle getNextLevelButtonBounds() {
        return NextLevelButtonBounds;
    }

    public Rectangle getReplayButtonBounds() {
        return ReplayButtonBounds;
    }

    public Viewport getGameport() {
        return gameport;
    }

    public void setGamecam(OrthographicCamera gamecam) {
        this.gamecam = gamecam;
    }

    public void setGameport(Viewport gameport) {
        this.gameport = gameport;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);
        game.getBatch().end();
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check if the touch is within the settings button bounds
            if (NextLevelButtonBounds.contains(touchX, touchY)) {
                if (getLevel() == 1){
                    game.setScreen(new Level2Screen(game)); // Navigate to SettingScreen
                    dispose();
                }
                else if (getLevel() == 2){
                    game.setScreen(new Level3Screen(game)); // Navigate to SettingScreen
                    dispose();
                }

                if (getLevel() == 3){
                    game.setScreen(new GameCompletedScreen(game)); // Navigate to SettingScreen
                    dispose();
                }
            }
            // Check if the touch is within the level1 button bounds
            else if (MainMenuButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new LevelsMenuAllScreen(game)); // Navigate to Level1Screen
                dispose();
            }

            else if (ReplayButtonBounds.contains(touchX, touchY)) {
                if (getLevel() == 1){
                    game.setScreen(new Level1Screen(game)); // Navigate to SettingScreen
                    dispose();
                }
                else if (getLevel() == 2){
                    game.setScreen(new Level2Screen(game)); // Navigate to SettingScreen
                    dispose();
                }
                else if (getLevel() == 3){
                    game.setScreen(new Level3Screen(game)); // Navigate to SettingScreen
                    dispose();
                }
            }
        }
    }
    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
