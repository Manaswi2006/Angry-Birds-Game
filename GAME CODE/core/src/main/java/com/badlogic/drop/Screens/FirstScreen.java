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


public class FirstScreen implements Screen {

    //ATTRIBUTES
    private Angry_Birds_Game game;
    private int profiles;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Rectangle settingsButtonBounds;
    private Rectangle level1ButtonBounds;
    private Rectangle playnewgame ;


    //CONSTRUCTOR
    public FirstScreen(Angry_Birds_Game _game){
        this.game = _game;
        this.profiles = 0;
        texture = new Texture("Background.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        settingsButtonBounds = new Rectangle(648,  1024 - 775 - 76, 495, 76);
        level1ButtonBounds = new Rectangle(648, 1024 - 653 - 76, 495, 76);
        playnewgame = new Rectangle(648, 1024 - 512 - 77 , 476 , 77);
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

    public void setGameport(Viewport gameport) {
        this.gameport = gameport;
    }

    public Viewport getGameport() {
        return gameport;
    }

    public OrthographicCamera getGamecam() {
        return gamecam;
    }

    public void setGamecam(OrthographicCamera gamecam) {
        this.gamecam = gamecam;
    }

    public int getProfiles() {
        return profiles;
    }

    public Rectangle getLevel1ButtonBounds() {
        return level1ButtonBounds;
    }

    public Rectangle getSettingsButtonBounds() {
        return settingsButtonBounds;
    }

    public void setLevel1ButtonBounds(Rectangle level1ButtonBounds) {
        this.level1ButtonBounds = level1ButtonBounds;
    }

    public void setProfiles(int profiles) {
        this.profiles = profiles;
    }

    public void setSettingsButtonBounds(Rectangle settingsButtonBounds) {
        this.settingsButtonBounds = settingsButtonBounds;
    }

    //METHODS
    @Override
    public void show() {
        // Prepare your screen here.
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check if the touch is within the settings button bounds
            if (settingsButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new SettingScreen(game)); // Navigate to SettingScreen
                dispose();
            }
            // Check if the touch is within the level1 button bounds
            else if (level1ButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new ProfileMenuAllScreen(game)); // Navigate to Level1Screen
                dispose();
            }
            else if(playnewgame.contains(touchX,touchY)){
                game.setScreen(new Playnewgame(game));
                dispose();
            }
        }
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

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
