package cz.josefkuchar.freewaymadness.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import cz.josefkuchar.freewaymadness.Constants;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    Label scoreLabel;

    public Hud(SpriteBatch batch) {
        //viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_WIDTH * (16 / 9), new OrthographicCamera());
        viewport = new FitViewport(1080 / 4, 1920 / 4, new OrthographicCamera());

        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label("0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void dispose() {
        stage.dispose();
    }
}
