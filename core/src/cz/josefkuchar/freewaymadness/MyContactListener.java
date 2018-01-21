package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Jump from car to car
        if (fixtureA.isSensor() || fixtureB.isSensor()) {
            Car car;
            Player player;

            if (fixtureB.isSensor()) {
                car = (Car)fixtureA.getBody().getUserData();
                player = (Player)fixtureB.getBody().getUserData();
            } else {
                car = (Car)fixtureB.getBody().getUserData();
                player = (Player)fixtureA.getBody().getUserData();
            }

            player.setCar(car);

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (!fixtureA.isSensor() && !fixtureB.isSensor()) {
            float strength = 0;
            for (int i = 0; i < impulse.getCount(); i++) {

                strength += impulse.getNormalImpulses()[i];
            }
            strength /= impulse.getCount();
            strength /= 100;
            //Gdx.app.log("a", String.valueOf(strength));

            Car carA = (Car)fixtureA.getBody().getUserData();
            Car carB = (Car)fixtureB.getBody().getUserData();

            carA.health -= strength;
            carB.health -= strength;
        }
    }
}
