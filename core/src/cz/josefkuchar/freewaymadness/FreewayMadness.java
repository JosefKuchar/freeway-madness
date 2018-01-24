package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import cz.josefkuchar.freewaymadness.screen.PlayScreen;

public class FreewayMadness extends Game {
	public SpriteBatch batch;
	public MyGestureListener gestureListener;

	public void create () {
		batch = new SpriteBatch();
		gestureListener = new MyGestureListener();
		GestureDetector gd = new GestureDetector(gestureListener);
		Gdx.input.setInputProcessor(gd);
		this.setScreen(new PlayScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		batch.dispose();
	}
}
