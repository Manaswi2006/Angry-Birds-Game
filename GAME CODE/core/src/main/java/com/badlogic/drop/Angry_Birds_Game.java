package com.badlogic.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Angry_Birds_Game extends Game {

    //ATTRIBUTES
    private SpriteBatch batch;
    private static final int V_WIDTH = 1792;
    private static final int V_HEIGHT = 1024;

    //GETTERS AND SETTERS
    public SpriteBatch getBatch(){
        return this.batch;
    }

    public void setBatch(SpriteBatch sb){
        this.batch = sb;
    }

    public static int getvHeight() {
        return V_HEIGHT;
    }

    public static int getvWidth() {
        return V_WIDTH;
    }

    //METHODS
    @Override
    public void create() {
        this.setBatch(new SpriteBatch());
        setScreen(new FirstScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }

}
