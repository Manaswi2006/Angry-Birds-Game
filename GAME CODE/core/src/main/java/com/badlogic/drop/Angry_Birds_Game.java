package com.badlogic.drop;

import com.badlogic.drop.Screens.FirstScreen;
import com.badlogic.drop.Screens.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;

import java.util.ArrayList;

public class Angry_Birds_Game extends Game {

    //ATTRIBUTES
    private SpriteBatch batch;
    private static final int V_WIDTH = 1792;
    private static final int V_HEIGHT = 1024;
    private String activeprofileis ;
    private List<String> profiles;
    public Angry_Birds_Game(){
        profiles = new ArrayList<>();
    }
    public void addprofile(String profilename){
        if(!profiles.contains(profilename)){
            profiles.add(profilename);
            setActiveprofileis(profilename);
        }
    }
    public void setActiveprofileis(String profilename){
        this.activeprofileis = profilename;
    }
    public String getactiveprofileis(){
        return activeprofileis;
    }
    public List<String> getProfiles(){
        return profiles;
    }

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
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }


    public void setActiveprofile(String profile) {
        this.activeprofileis = profile;
    }
    public String getActiveprofileis(){
        return activeprofileis;
    }


}
