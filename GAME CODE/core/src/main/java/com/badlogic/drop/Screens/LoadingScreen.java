package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class LoadingScreen implements Screen {
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
   // private final Rectangle GoBackButtonBounds;
    public  LoadingScreen(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("loadingscreenn.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);



    }
    @Override
    public void show() {

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
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the camera and batch projection
        game.getBatch().setProjectionMatrix(gamecam.combined);

        // Draw the loading screen texture
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);
        game.getBatch().end();

        // Handle input: Move to FirstScreen if ENTER is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new FirstScreen(game));
            dispose();

        }
    }
    @Override
    public void resize(int width, int height) {
            gameport.update(width,height);

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
