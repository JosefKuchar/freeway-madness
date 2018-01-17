package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Car {
    Body body;
    Sprite sprite;
    World world;

    int health;
    float maxSpeed;
    float initalDirection;
    boolean owned;

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
        body.setUserData(this);
        shape.dispose();
        //body.applyForceToCenter(0, 10000, false);
        //body.applyLinearImpulse(new Vector2(0,5000), body.getWorldCenter(), false);

        this.health = 100;
        this.initalDirection = 0;
        this.maxSpeed = 5;

        body.setLinearVelocity(0, this.maxSpeed);
    }

    public void update() {
        //TODO: Add friction when car is facing by side

        // Update sprite according to physics body
        sprite.setPosition(body.getPosition().x * Constants.PIXELS_TO_METERS - sprite.getWidth() / 2, body.getPosition().y * Constants.PIXELS_TO_METERS - sprite.getHeight() / 2);
        sprite.setRotation((float)Math.toDegrees(body.getAngle()));

        // Update physics body
        //Gdx.app.log("a", String.valueOf(new Vector2((float)Math.cos(body.getAngle() + Math.PI / 2), (float)Math.sin(body.getAngle() + Math.PI / 2))));
        //Gdx.app.log("a", String.valueOf(body.getAngle()));

        if (calculateSpeed(body.getLinearVelocity()) < this.maxSpeed) {
            //body.applyForce((float)Math.cos(body.getAngle() + Math.PI / 2) * 200, (float)Math.sin(body.getAngle() + Math.PI / 2) * 2000, false);
            Vector2 force = new Vector2((float)Math.cos(body.getAngle() + Math.PI / 2) * 1000, (float)Math.sin(body.getAngle() + Math.PI / 2) * 1000);
            Vector2 point = new Vector2(body.getPosition().x, body.getPosition().y + 0.5f);
            body.applyForce(force, point, true);
        }

        /*
        float change;
        if(initalDirection - body.getAngle() > 0) {
            change = 0.01f;
        } else {
            change = -0.01f;
        }
        body.setTransform(body.getPosition(), body.getAngle() + change);*/
    }

    public void dispose() {
        world.destroyBody(body);
    }

    public double calculateSpeed(Vector2 linearVelocity) {
        return Math.sqrt(Math.pow(linearVelocity.x, 2) + Math.pow(linearVelocity.y, 2));
    }
}
