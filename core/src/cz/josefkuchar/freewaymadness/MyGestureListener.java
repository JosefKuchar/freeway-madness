package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MyGestureListener implements GestureDetector.GestureListener {
    float x;
    boolean touching;

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
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        //TODO: Implement this
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        this.x = x;
        this.touching = true;
        return true;
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
}
