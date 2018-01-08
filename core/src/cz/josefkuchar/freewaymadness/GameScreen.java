package cz.josefkuchar.freewaymadness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import cz.josefkuchar.freewaymadness.Constants;

public class GameScreen implements Screen {
    final FreewayMadness game;
    OrthographicCamera camera;
    Car car;
    Car car2;
    Texture background;
    World world;
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;

    public GameScreen(FreewayMadness game) {
        this.game = game;

        camera = new OrthographicCamera();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera.setToOrtho(false, 128, 128 * (h / w));

        world = new World(new Vector2(0, 0), true);

        Texture texture = new Texture(Gdx.files.internal("car2.png"));
        background = new Texture(Gdx.files.internal("road3.png"));

        car = new Car(world, new Sprite(texture), new Vector2(2, 0));
        car2 = new Car(world, new Sprite(texture), new Vector2(2f, 3));
        //car.body.setLinearVelocity(0, 0);

        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        world.step(1f/60f, 6, 2);

        car.update();
        car2.update();

        debugMatrix = game.batch.getProjectionMatrix().cpy().scale(Constants.PIXELS_TO_METERS, Constants.PIXELS_TO_METERS, 0);

        game.batch.begin();
        for (int i = closest((int)(camera.position.y - camera.viewportHeight / 2), 8); i < camera.viewportHeight / 2 + camera.position.y; i += 8) {
            game.batch.draw(background, 0, i);
        }
        closest((int)(camera.position.y - camera.viewportHeight / 2), 8);
        car.sprite.draw(game.batch);
        car2.sprite.draw(game.batch);
        game.batch.end();

        camera.position.y = car.sprite.getY() + car.sprite.getHeight() / 2;

        //car.setY(car.getY() + 1);


        debugRenderer.render(world, debugMatrix);

        //bod.setCenterX(game.gestureListener.x / Gdx.graphics.getWidth() * 64);
        car.body.setTransform(game.gestureListener.x / Gdx.graphics.getWidth() * 8, car.body.getPosition().y, 0);
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
    }

    public int closest(int n, int m) {
        //FIXME: Negative numbers
        int q = n / m;
        int n1 = m * q;
        return n1;
    }
}
