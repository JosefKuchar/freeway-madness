package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MyGestureListener implements GestureDetector.GestureListener {
    float x;
    boolean touching;
    float touchDuration;
    Vector2 direction;

    MyGestureListener() {
        touching = true;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        Gdx.app.log("longpress", "");
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        //TODO: Implement this

        if (Math.abs(velocityX) > 1000 || Math.abs(velocityY) > 1000) {
            direction = new Vector2(velocityX, velocityY).nor();
            Gdx.app.log("fling", String.valueOf(direction));
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (touchDuration > 200) {
            this.x = x;
            this.touching = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        this.touching = false;
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {}

    public void update() {
        if (Gdx.input.isTouched()) {
            touchDuration += 1000f / 60f;
        } else {
            if (touchDuration != 0f)
                Gdx.app.log("a", String.valueOf(touchDuration));
            touchDuration = 0;
        }
    }
}
