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

public class Maps implements Screen {

    //ATTRIBUTES
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle Level1ButtonBounds;
    private final Rectangle Level2ButtonBounds;
    private final Rectangle Level3ButtonBounds;
    private final Rectangle GobackButtonBounds;

    //CONSTRUCTOR
    public Maps(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("map.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        Level1ButtonBounds = new Rectangle(655,  1024 - 431 - 170 , 153, 170);
        Level2ButtonBounds = new Rectangle(950, 1024 - 313 - 182, 164 , 182);
        Level3ButtonBounds = new Rectangle(896 , 1024 - 34 - 137 , 123 , 137);
        GobackButtonBounds = new Rectangle(35, 1024 - 35 - 135 , 134 , 135);
        //GobackButtonBounds = new Rectangle(187, 1024 - 190 - 165  , 158 , 165);
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
            if (Level1ButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new Level1Screen(game,new Profile("user1",3))); // Navigate to SettingScreen
                dispose();
            }
            // Check if the touch is within the level1 button bounds
            else if (Level2ButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new Level2Screen(game,new Profile("user1",3))); // Navigate to Level1Screen
                dispose();
            }

            // Check if the touch is within the level1 button bounds
            else if (Level3ButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new Level3Screen(game,new Profile("user1",3))); // Navigate to Level1Screen
                dispose();
            }

            else if (GobackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new SettingScreen(game)); // Navigate to  first
                dispose();
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
