package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;

public class Playnewgame implements Screen {
    private Angry_Birds_Game game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle GoBackButtonBounds;
    private final Rectangle nextbuttonbounds;
    private String playername = "";
    private BitmapFont font ;


    public  Playnewgame(Angry_Birds_Game _game){
        this.game = _game;
        texture = new Texture("newprofile.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792,1024,gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        GoBackButtonBounds = new Rectangle(35 , 1024 - 35 - 135 , 134 , 135);
        nextbuttonbounds = new Rectangle(1615, 1024 - 847 - 149 , 148 , 149);
        font = new BitmapFont();
        font.setColor(BLACK);
      //  promptname();




    }
    private void promptname(){
        Gdx.input.getTextInput(new TextInputListener(){
            @Override
            public void input(String s) {
                playername = s;

            }

            @Override
            public void canceled() {
                playername = "";

            }
        },"hey","","op");
    }
    @Override
    public void show() {
        promptname();

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
        font.draw(game.getBatch(),""+playername,508,405);
        game.getBatch().end();

        handleinput();

       // handleKeyboardInput();

        // Handle input: Move to FirstScreen if ENTER is pressed

    }
    private void handleinput(){
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Convert to world coordinates (invert Y-axis)
            touchY = gameport.getScreenHeight() - touchY;

            // Check if the touch is within the settings button bounds
            if (GoBackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new FirstScreen(game)); // Navigate to SettingScreen
                dispose();
            }
            // Check if the touch is within the level1 button bounds
            else if (nextbuttonbounds.contains(touchX, touchY)) {
                if(!playername.isEmpty()){
                    game.addprofile(playername);}
                game.setScreen(new LevelsMenuAllScreen(game)); // Navigate to Level1Screen
                dispose();
            }
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
        font.dispose();

    }


}
