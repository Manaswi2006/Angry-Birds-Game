package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SettingScreen implements Screen {

    private final Rectangle terms;
    private final Rectangle back;
    //ATTRIBUTES
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle map;

    //CONSTRUCTOR
    public SettingScreen(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("SETTINGS.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        map = new Rectangle(955,1024 - 481 - 299 , 322 , 299);
        terms = new Rectangle(1367,1024 -481 - 299 , 322 ,299);
        back = new Rectangle(187 , 1024 - 190 - 165 , 158 , 165);
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

    //METHODS
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


            if (map.contains(touchX, touchY)) {
                game.setScreen(new Maps(game)); // Navigate to Level1Screen
                dispose();
            }
            else if (terms.contains(touchX, touchY)) {
                game.setScreen(new Termsandcondition(game)); // Navigate to Level1Screen
                dispose();
            }
            else if (back.contains(touchX, touchY)) {
                game.setScreen(new FirstScreen(game));// Navigate to Level1Screen
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
