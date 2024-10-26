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

//import java.awt.*;

public class Termsandcondition implements Screen {
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle GoBackButtonBounds;
    public Termsandcondition(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("terms.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
       // gamecam.update();
        GoBackButtonBounds = new Rectangle(35 , 1024 -35 -134 ,133 ,134);
    }
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
    public void render(float v) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);
        game.getBatch().end();
       handleInput();


    }
    private void handleInput(){
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check if the touch is within the settings button bounds


            if (GoBackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new SettingScreen(game)); // Navigate to Level1Screen
                dispose();
            }
        }

    }


    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        //haha

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
        game.dispose();

    }
}
