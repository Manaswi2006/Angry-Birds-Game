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

public class GameLostScreen implements Screen {

    //ATTRIBUTES
    private Profile profile;
    private Angry_Birds_Game game;
    private int level;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle MainMenuButtonBounds;
    private final Rectangle ReplayButtonBounds;

    //CONSTRUCTOR
    public GameLostScreen(Angry_Birds_Game _game, int _level, Profile profile){
        this.game = _game;
        this.level = _level;
        this.profile = profile;
        texture = new Texture("GameLost.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        MainMenuButtonBounds = new Rectangle(748, 1024 - 780 - 112, 296, 112);
        ReplayButtonBounds = new Rectangle(730, 1024 - 342 - 139, 326, 139);
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
            // Check if the touch is within the level1 button bounds
            if (MainMenuButtonBounds.contains(touchX, touchY)) {
                openLevelMenu(profile); // Navigate to Level1Screen
                dispose();
            }

            else if (ReplayButtonBounds.contains(touchX, touchY)) {
                if (getLevel() == 1){
                    game.setScreen(new Level1Screen(game,profile)); // Navigate to SettingScreen
                    dispose();
                }
                else if (getLevel() == 2){
                    game.setScreen(new Level2Screen(game,profile)); // Navigate to SettingScreen
                    dispose();
                }
                else if (getLevel() == 3){
                    game.setScreen(new Level3Screen(game,profile)); // Navigate to SettingScreen
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

    private void openLevelMenu(Profile profile) {
        int level = profile.getLevel();
        if (level == 1) {
            game.setScreen(new LevelsMenu1Screen(game,profile));
        } else if (level == 2) {
            game.setScreen(new LevelsMenu2Screen(game,profile));
        } else if (level == 3) {
            game.setScreen(new LevelsMenuAllScreen(game,profile));
        }
        dispose();
    }
}
