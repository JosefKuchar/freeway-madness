package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MyGestureListener implements GestureDetector.GestureListener {
    public float x;
    float touchDuration;
    Vector2 direction;
    public Status status;

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        this.x = x;
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        //TODO: Implement this

        if (Math.abs(velocityX) > 1000 || Math.abs(velocityY) > 1000) {
            direction = new Vector2(velocityX, velocityY).nor();
            status = Status.JUMP;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (touchDuration > Constants.FLING_TIME) {
            status = Status.DRIVE;
            this.x = x;
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
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
            if (touchDuration > Constants.FLING_TIME) {
                status = Status.DRIVE;
            }
        } else {
            if (status != Status.JUMP)
                status = Status.RELOCATE;
            if (touchDuration != 0f)
            touchDuration = 0;
        }
    }
}
