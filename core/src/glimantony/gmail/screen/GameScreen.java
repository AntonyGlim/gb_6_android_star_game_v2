package glimantony.gmail.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Base2DScreen;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;
import glimantony.gmail.sprites.Background;
import glimantony.gmail.sprites.Star;
import glimantony.gmail.sprites.game.MainShip;

public class GameScreen extends Base2DScreen {

    private TextureAtlas atlas;
    private Texture bg; //фон
    private Background background; //обертка для фона
    private Star[] stars; //массив звезд

    private MainShip mainShip; //наш корабль

    private BulletPool bulletPool; //пул пуль


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/backgrounds/spase_stars_background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack"); //инициализируем конфиг для атласа
        stars = new Star[64]; //звезда настраивает себя сама
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        bulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        deleteAllDestroied(); //все что вылетело за экран будет перемещаться в список свободных о-ов
        draw();
    }

    /**
     * Метод как подметод render(float delta)
     * @param delta
     */
    public void update(float delta){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta); //чтобы наши пули смогли лететь
    }

    /**
     *
     */
    public void deleteAllDestroied(){
        bulletPool.freeAllDestroiedSprites();
    }

    /**
     * Метод как подметод render(float delta)
     * Отвечает за всю отресовку
     */
    public void draw(){
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch); //фон сам знает как себя нарисовать
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);//передаем новые границы мира, после их изменения (он сам изменит свои размеры)
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds); //передаем кораблю информацию
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode); //передаем кораблю событие
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode); //передаем кораблю событие
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer); //передаем кораблю событие
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer); //передаем кораблю событие
        return super.touchUp(touch, pointer);
    }
}
