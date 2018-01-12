package cz.josefkuchar.freewaymadness;

public class Player {
    Car car;

    Player(Car car) {
        setCar(car);
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public float getCameraPosition() {
        return car.sprite.getY() + car.sprite.getHeight() / 2;
    }

    public void steer(float x) {
        car.body.setTransform(x, car.body.getPosition().y, 0);
    }
}
