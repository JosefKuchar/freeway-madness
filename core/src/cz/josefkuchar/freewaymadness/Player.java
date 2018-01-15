package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.physics.box2d.*;

public class Player {
    Car car;
    World world;
    Body body;

    Player(Car car, World world) {
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(car.body.getPosition());

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f, 0.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0;
        fixtureDef.isSensor = true;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        setCar(car);
    }

    public void setCar(Car car) {
        this.car = car;
        this.car.maxSpeed = 10;
    }

    public void update() {
        body.setTransform(car.body.getPosition(), 0);
    }

    public float getCameraPosition() {
        return car.sprite.getY() + car.sprite.getHeight() / 2;
    }

    public void steer(float x) {
        car.body.setTransform(x, car.body.getPosition().y, 0);
    }
}
