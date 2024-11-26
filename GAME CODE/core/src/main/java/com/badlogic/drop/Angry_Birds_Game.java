package com.badlogic.drop;

import com.badlogic.drop.Screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    private Music backgroundMusic;
    private Level1Screen savedLevel1Screen;
    private Level2Screen savedLevel2Screen;
    private Level3Screen savedLevel3Screen;

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

    // Getter and Setter for saved Level1Screen
    public Level1Screen getSavedLevel1Screen() {
        return savedLevel1Screen;
    }

    public void setSavedLevel1Screen(Level1Screen screen) {
        this.savedLevel1Screen = screen;
    }

    // Getter and Setter for saved Level1Screen
    public Level2Screen getSavedLevel2Screen() {
        return savedLevel2Screen;
    }

    public void setSavedLevel2Screen(Level2Screen screen) {
        this.savedLevel2Screen = screen;
    }

    // Getter and Setter for saved Level1Screen
    public Level3Screen getSavedLevel3Screen() {
        return savedLevel3Screen;
    }

    public void setSavedLevel3Screen(Level3Screen screen) {
        this.savedLevel3Screen = screen;
    }

    //METHODS
    @Override
    public void create() {
        this.setBatch(new SpriteBatch());
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundMusic.dispose();
        super.dispose();
    }

    public void setActiveprofile(String profile) {
        this.activeprofileis = profile;
    }
    public String getActiveprofileis(){
        return activeprofileis;
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }


}
