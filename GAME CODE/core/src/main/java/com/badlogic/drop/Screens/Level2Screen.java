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

public class Level2Screen implements Screen {

    //ATTRIBUTES
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle GoBackButton;
    private final Rectangle GiveUpButtonBounds;
    private final Rectangle pause;


    //CONSTRUCTOR
    public Level2Screen(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("Level2.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        GoBackButton = new Rectangle(19, 1024 - 35 - 136 , 133 , 133);
        GiveUpButtonBounds = new Rectangle(1521 , 1024 -47 -110 ,228 ,110);
        pause = new Rectangle(209 , 1024 - 25 - 171, 164 , 171);
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

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
        // Resize your screen here. The parameters represent the new window size.
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check if the touch is within the settings button bounds


            if (GoBackButton.contains(touchX, touchY)) {
                game.setScreen(new LevelsMenuAllScreen(game)); // Navigate to Level1Screen
                dispose();
            }
            else if (GiveUpButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new GameLostScreen(game,2)); // Navigate to Level1Screen
                dispose();
            } else if (pause.contains(touchX,touchY)) {
                game.setScreen(new Pause2screen(game));
                dispose();

            } else{
                game.setScreen(new GameWonScreen(game,2)); // Navigate to Level1Screen
                dispose();
            }
        }
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
