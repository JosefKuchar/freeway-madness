package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import cz.josefkuchar.freewaymadness.Constants;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    final FreewayMadness game;
    OrthographicCamera camera;
    Texture background;
    Texture texture;
    World world;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    BitmapFont font;
    ArrayList<Car> cars;
    Player player;
    Random random;

    float lastCameraPosition;
    float positionBuffer = 0;

    final int[] spawnPoints = {7, 26, 45, 67, 86, 105};

    public GameScreen(FreewayMadness game) {
        this.game = game;

        camera = new OrthographicCamera();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_WIDTH * (h / w));

        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new MyContactListener());

        texture = new Texture(Gdx.files.internal("car.png"));
        background = new Texture(Gdx.files.internal("road.png"));

        cars = new ArrayList<Car>();

        cars.add(new Car(world, new Sprite(texture), new Vector2(2, 0)));
        cars.add(new Car(world, new Sprite(texture), new Vector2(2, 3)));

        player = new Player(cars.get(0), world, game);
        player.car.body.applyLinearImpulse(new Vector2(0,2000), player.car.body.getWorldCenter(), false);

        //car.body.setLinearVelocity(0, 6);

        debugRenderer = new Box2DDebugRenderer();

        font = new BitmapFont();

        random = new Random();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Update physics and HUD
        update();

        // Clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update camera
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Debug matrix init
        debugMatrix = game.batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS, Constants.PIXELS_TO_METERS, 0);

        // Draw scene
        game.batch.begin();
        for (int i = closest((int)(camera.position.y - camera.viewportHeight / 2), Constants.TILE_HEIGHT); i < camera.viewportHeight / 2 + camera.position.y; i += Constants.TILE_HEIGHT) {
            game.batch.draw(background, 0, i);
        }

        for (Car car: cars) {
            car.sprite.draw(game.batch);
        }
        game.batch.end();

        // Debug render
        debugRenderer.render(world, debugMatrix);
    }

    public void update() {
        game.gestureListener.update();
        positionBuffer += camera.position.y - lastCameraPosition;
        while (positionBuffer > 200) {
            cars.add(new Car(world, new Sprite(texture), new Vector2(spawnPoints[random.nextInt(spawnPoints.length)] / Constants.PIXELS_TO_METERS + 0.5f, (camera.position.y + camera.viewportHeight / 2) / Constants.PIXELS_TO_METERS + 1)));
            positionBuffer -= 200;
        }

        lastCameraPosition = camera.position.y;

        if (game.gestureListener.status == Status.DRIVE)
            world.step(1f/60f, 6, 2);
        else
            world.step(1f/480f, 6, 2);

        for (int i = cars.size() - 1; i >= 0; i--) {
            Car car = cars.get(i);
            car.update();
            if (car.sprite.getY() + car.sprite.getHeight() < camera.position.y - camera.viewportHeight / 2) {
                car.dispose();
                cars.remove(i);
            }
        }


        player.update();
        if (game.gestureListener.x != -1)
            player.steer(game.gestureListener.x / Gdx.graphics.getWidth() * camera.viewportWidth / Constants.PIXELS_TO_METERS);

        camera.position.y = player.getCameraPosition();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    public int closest(int n, int m) {
        int q = n / m;
        if (n >= 0) {
            return m * q;
        } else {
            return m * (q - 1);
        }
    }
}
