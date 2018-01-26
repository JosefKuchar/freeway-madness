package cz.josefkuchar.freewaymadness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;
import cz.josefkuchar.freewaymadness.FreewayMadness;

public class EndScreen implements Screen {
    public Stage stage;
    private Viewport viewport;
    private FreewayMadness game;

    public EndScreen(FreewayMadness game) {
        this.game = game;
        stage = new Stage();
        Gdx.app.log("created", "created");
    }

    @Override
    public void show() {
        Gdx.app.log("show", "show");
        game.setScreen(new PlayScreen(game));
        dispose();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
    }
}
