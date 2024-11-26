package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.*;
import java.util.ArrayList;

public class Playnewgame implements Screen {
    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle GoBackButtonBounds;
    private final Rectangle nextButtonBounds;
    private BitmapFont font;
    private String playerName = "";
    private static final String PROFILE_FILE = "C://Users//Manaswi//OneDrive//Desktop//Game Dev//ANGRY BIRDS//Angry-Birds-Game//profiles.txt";
    private ArrayList<Profile> profiles;

    public Playnewgame(Angry_Birds_Game _game) {
        this.game = _game;
        texture = new Texture("newprofile.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792, 1024, gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);
        GoBackButtonBounds = new Rectangle(35, 1024 - 35 - 135, 134, 135);
        nextButtonBounds = new Rectangle(1615, 1024 - 847 - 149, 148, 149);
        font = new BitmapFont();
        font.getData().setScale(2);

        // Load profiles from file
        profiles = loadProfiles();
        if (profiles == null) {
            profiles = new ArrayList<>();
        }
    }

    private void promptName() {
        Gdx.input.getTextInput(new TextInputListener() {
            @Override
            public void input(String s) {
                playerName = s;
            }

            @Override
            public void canceled() {
                playerName = "";
            }
        }, "Enter your name:", "", "Name");
    }

    private void saveProfiles() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PROFILE_FILE))) {
            out.writeObject(profiles);
        } catch (IOException e) {
            Gdx.app.log("ERROR", "Failed to save profiles: " + e.getMessage());
        }
    }

    private ArrayList<Profile> loadProfiles() {
        File profileFile = new File(PROFILE_FILE);

        if (!profileFile.exists()) {
            Gdx.app.log("INFO", "No profiles found. Creating new profile file.");
            return new ArrayList<>();  // Return an empty list if the file doesn't exist
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(profileFile))) {
            return (ArrayList<Profile>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Gdx.app.log("INFO", "Failed to load profiles: " + e.getMessage());
            return new ArrayList<>();  // Return an empty list in case of failure
        }
    }

    @Override
    public void show() {
        promptName();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);
        font.draw(game.getBatch(), "Name: " + playerName, 508, 405);
        game.getBatch().end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            touchY = gameport.getScreenHeight() - touchY;

            if (GoBackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new FirstScreen(game));
                dispose();
            } else if (nextButtonBounds.contains(touchX, touchY)) {
                if (!playerName.isEmpty()) {
                    updateProfiles(playerName);
                    saveProfiles();
                }
                game.setScreen(new ProfileMenuAllScreen(game));
                dispose();
            }
        }
    }

    private void updateProfiles(String newPlayerName) {
        // Add or update profile
        if (profiles.size() < 3) {
            profiles.add(new Profile(newPlayerName, 1)); // Default level is 1
        } else {
            // Replace the first profile (user1) in a circular manner
            profiles.set(0, new Profile(newPlayerName, 1));
        }
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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

    // Nested class for profiles
    private static class Profile implements Serializable {
        private String name;
        private int level;

        public Profile(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
