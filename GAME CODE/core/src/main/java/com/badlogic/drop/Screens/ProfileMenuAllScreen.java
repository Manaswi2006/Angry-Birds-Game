package com.badlogic.drop.Screens;

import com.badlogic.drop.Angry_Birds_Game;
import com.badlogic.drop.Screens.Profile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ProfileMenuAllScreen implements Screen {

    private Angry_Birds_Game game;
    private Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private final Rectangle User1ButtonBounds;
    private final Rectangle User2ButtonBounds;
    private final Rectangle User3ButtonBounds;
    private final Rectangle GoBackButtonBounds;
    private BitmapFont font;
    private Profile[] profiles;
    private static final String PROFILE_FILE = "profiles.txt";

    public ProfileMenuAllScreen(Angry_Birds_Game _game) {
        this.game = _game;
        texture = new Texture("ProfileMenuAll.png");
        gamecam = new OrthographicCamera();
        gameport = new FitViewport(1792, 1024, gamecam);
        gamecam.position.set(1792 / 2f, 1024 / 2f, 0);

        // Button bounds
        User1ButtonBounds = new Rectangle(693, 1024 - 467 - 104, 426, 104);
        User2ButtonBounds = new Rectangle(693, 1024 - 605 - 104, 426, 104);
        User3ButtonBounds = new Rectangle(693, 1024 - 744 - 104, 426, 104);
        GoBackButtonBounds = new Rectangle(35, 1024 - 35 - 135, 134, 135);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);

        // Load profiles or create new ones
        profiles = new Profile[] {
            Profile.loadProfile(PROFILE_FILE + "_1"),
            Profile.loadProfile(PROFILE_FILE + "_2"),
            Profile.loadProfile(PROFILE_FILE + "_3")
        };
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i] == null) {
                profiles[i] = new Profile("User " + (i + 1), 1);
                Profile.saveProfiles(profiles[i], PROFILE_FILE + "_" + (i + 1));
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw texture and profile names
        game.getBatch().setProjectionMatrix(gamecam.combined);
        game.getBatch().begin();
        game.getBatch().draw(texture, 0, 0);

        // Draw profile names and levels
        font.draw(game.getBatch(), profiles[0].getName(), 800, 1024 - 467 - 35);
        font.draw(game.getBatch(), profiles[1].getName(), 800, 1024 - 605 - 35);
        font.draw(game.getBatch(), profiles[2].getName(), 800, 1024 - 744 - 35);
        game.getBatch().end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            touchY = gameport.getScreenHeight() - touchY;

            if (User1ButtonBounds.contains(touchX, touchY)) {
                openLevelMenu(profiles[0]);
            } else if (User2ButtonBounds.contains(touchX, touchY)) {
                openLevelMenu(profiles[1]);
            } else if (User3ButtonBounds.contains(touchX, touchY)) {
                openLevelMenu(profiles[2]);
            } else if (GoBackButtonBounds.contains(touchX, touchY)) {
                game.setScreen(new FirstScreen(game));
                dispose();
            }
        }
    }

    private void openLevelMenu(Profile profile) {
        int level = profile.getLevel();
//        if (level == 1) {
        game.setScreen(new LevelsMenu1Screen(game, profile));
//        } else if (level == 2) {
//            game.setScreen(new LevelsMenu2Screen(game, profile));
//        } else if (level == 3) {
//            game.setScreen(new LevelsMenuAllScreen(game, profile));
//        }
        dispose();
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

        // Save profiles on exit
        for (int i = 0; i < profiles.length; i++) {
            Profile.saveProfiles(profiles[i], PROFILE_FILE + "_" + (i + 1));
        }
    }
}
