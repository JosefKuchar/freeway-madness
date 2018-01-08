package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;

public class FreewayMadness extends Game {
	SpriteBatch batch;
	MyGestureListener gestureListener;

	public void create () {
		batch = new SpriteBatch();
		gestureListener = new MyGestureListener();
		GestureDetector gd = new GestureDetector(gestureListener);
		Gdx.input.setInputProcessor(gd);
		this.setScreen(new GameScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		batch.dispose();
	}
}
