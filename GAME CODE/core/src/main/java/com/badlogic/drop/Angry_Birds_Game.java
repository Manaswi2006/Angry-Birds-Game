package com.badlogic.drop;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Angry_Birds_Game extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}