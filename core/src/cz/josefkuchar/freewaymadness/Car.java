package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Car {
    Body body;
    Sprite sprite;
    World world;

    int health;

    Car(World world, Sprite sprite, Vector2 position) {
        this.sprite = sprite;
        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1000;
        body.createFixture(fixtureDef);
        shape.dispose();
        body.setLinearVelocity(0, 5);
        this.health = 100;
    }

    public void update() {
        sprite.setPosition(body.getPosition().x * Constants.PIXELS_TO_METERS - sprite.getWidth() / 2, body.getPosition().y * Constants.PIXELS_TO_METERS - sprite.getHeight() / 2);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));
    }

    public void dispose() {
        world.destroyBody(body);
    }
}
