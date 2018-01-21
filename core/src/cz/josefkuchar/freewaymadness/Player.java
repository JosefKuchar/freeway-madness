package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Player {
    Car car;
    World world;
    Body body;
    FreewayMadness game;
    Sprite sprite;

    Player(Car car, World world, FreewayMadness game) {
        this.world = world;
        this.game = game;

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
        body.setUserData(this);

        setCar(car);
    }

    public void setCar(Car car) {
        this.car = car;
        this.car.maxSpeed = 10;
        this.car.initalAngle = 0;
        game.gestureListener.status = Status.RELOCATE;
        game.gestureListener.x = -1;
    }

    public void update() {
        if (game.gestureListener.status != Status.JUMP) {
            body.setTransform(car.body.getPosition(), 0);
            body.setLinearVelocity(car.body.getLinearVelocity());
        } else {
            //body.setLinearVelocity(game.gestureListener.direction.x * 12f, game.gestureListener.direction.y * -12f);
            body.applyForceToCenter(game.gestureListener.direction.x * 200f, game.gestureListener.direction.y * -200f, false);
        }
    }

    public float getCameraPosition() {
        return car.sprite.getY() + car.sprite.getHeight() / 2;
    }

    public void steer(float x) {
        //car.body.setTransform(x, car.body.getPosition().y, 0);
        car.steer(x);
    }
}
