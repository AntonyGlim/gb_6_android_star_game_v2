package glimantony.gmail.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import glimantony.gmail.base.Base2DScreen;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;
import glimantony.gmail.pool.EnemyPool;
import glimantony.gmail.pool.ExplosionPool;
import glimantony.gmail.sprites.Background;
import glimantony.gmail.sprites.Star;
import glimantony.gmail.sprites.game.Bullet;
import glimantony.gmail.sprites.game.Enemy;
import glimantony.gmail.sprites.game.Explosion;
import glimantony.gmail.sprites.game.MainShip;
import glimantony.gmail.sprites.game.MessageGameOver;
import glimantony.gmail.utils.EnemyEmitter;

public class GameScreen extends Base2DScreen {

    private enum State {PLAYING, GAME_OVER} //делаем 2 состояния для экрана

    private TextureAtlas atlas;
    private Texture bg; //фон
    private Background background; //обертка для фона
    private Star[] stars; //массив звезд
    private MessageGameOver messageGameOver; //надпись конец игры

    private MainShip mainShip; //наш корабль

    private BulletPool bulletPool; //пул пуль
    private ExplosionPool explosionPool; //пул взрыва
    private EnemyPool enemyPool; //пул вражеских кораблей

    private EnemyEmitter enemyEmitter; //фабрика врагов

    private Music music; //фоновая музыка

    private State state; //для определения в каком режиме находится игра


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
        explosionPool = new ExplosionPool(atlas);
        mainShip = new MainShip(atlas, bulletPool, explosionPool);
        enemyPool = new EnemyPool(bulletPool, worldBounds, explosionPool, mainShip);

        enemyEmitter = new EnemyEmitter(atlas, enemyPool, worldBounds);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music_1.mp3")); //подключаем музыку
        music.setLooping(true); //сделаем повторяющейся
        music.setVolume(0.8f); //громкость музыки
        music.play();

        messageGameOver = new MessageGameOver(atlas);

        startNewGame();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollision(); //проверка а пересечения
        deleteAllDestroied(); //все что вылетело за экран будет перемещаться в список свободных о-ов
        draw();
    }

    /**
     * Метод как подметод render(float delta)
     * @param delta
     */
    private void update(float delta){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
        explosionPool.updateActiveSprites(delta); //чтобы нпроигрывался взрыв
        switch (state){
            case PLAYING:
                mainShip.update(delta);
                bulletPool.updateActiveSprites(delta); //чтобы наши пули смогли лететь
                enemyPool.updateActiveSprites(delta); //обновление пула врагов
                enemyEmitter.generate(delta); //генерация врагов
                break;
            case GAME_OVER:
                break;
        }
    }

    /**
     * Метод проверит, пересекаются-ли графические о-ты
     */
    private void checkCollision(){
        //проверим пересечение нашег корабля с вражеским
        List<Enemy> enemyList = enemyPool.getActiveObjects();//список всех врагов находящихся на экране
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroied()) continue; //если корабль уже уничтожен, то он не принимает участия в игровом процессе
            float minDistanceBetweenShips = enemy.getHalfHeight() + mainShip.getHalfHeight(); //произвольно рассчитываем
            if (enemy.pos.dst2(mainShip.pos) <= minDistanceBetweenShips * minDistanceBetweenShips){
                enemy.destroy();
                mainShip.damage(enemy.getBulletDamage());
                return;
            }
        }

        //проверим пересечение наших пуль с вражескими кораблями
        List<Bullet> bulletList = bulletPool.getActiveObjects();//список всех пуль находящихся на экране
        for (Enemy enemy : enemyList) {
            if (enemy.isDestroied()) continue; //если корабль уже уничтожен, то он не принимает участия в игровом процессе
            for (Bullet bullet : bulletList) {
                if (bullet.getOwnerOfBullet() != mainShip || bullet.isDestroied()){ //если пуля не наша или уничтожена
                    continue;
                }
                if (enemy.isBulletCollision(bullet)){ //если пуля в пределах корабля (по центру)
                    enemy.damage(mainShip.getBulletDamage()); //противнику наноситься урон, он меняет цвет
                    bullet.destroy(); //уничтожаем пулю
                }
            }
        }

        //проверяем пересечение вражеских пуь с нашими кораблями
        for (Bullet bullet : bulletList) {
            if (bullet.getOwnerOfBullet() == mainShip || bullet.isDestroied()){ //если пуля наша или уничтожена
                continue;
            }
            if (mainShip.isBulletCollisionMainShip(bullet)){
                mainShip.damage(bullet.getDemage()); //наносим кораблю урон
                bullet.destroy();
            }
        }
    }

    /**
     *
     */
    private void deleteAllDestroied(){
        bulletPool.freeAllDestroiedSprites();
        explosionPool.freeAllDestroiedSprites();
        enemyPool.freeAllDestroiedSprites();
    }

    /**
     * Метод как подметод render(float delta)
     * Отвечает за всю отресовку
     */
    private void draw(){
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch); //фон сам знает как себя нарисовать
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        if (!mainShip.isDestroied()){ //если наш корабль не уничтожен
            mainShip.draw(batch);
        }
        bulletPool.drawActiveSprites(batch);
        explosionPool.drawActiveSprites(batch);
        enemyPool.drawActiveSprites(batch);
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
        explosionPool.dispose();
        enemyPool.dispose();
        mainShip.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!mainShip.isDestroied()){ //если наш корабль не уничтожен
            mainShip.keyDown(keycode); //передаем кораблю событие
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!mainShip.isDestroied()){ //если наш корабль не уничтожен
            mainShip.keyUp(keycode); //передаем кораблю событие
        }
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (!mainShip.isDestroied()){ //если наш корабль не уничтожен
            mainShip.touchDown(touch, pointer); //передаем кораблю событие
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (!mainShip.isDestroied()){ //если наш корабль не уничтожен
            mainShip.touchUp(touch, pointer); //передаем кораблю событие
        }
        return super.touchUp(touch, pointer);
    }

    /**
     * Метод, в котором мы будем устанавливать все значения по умолчанию
     */
    private void startNewGame(){
        state = State.PLAYING; //режим ИГРА

        mainShip.startNewGame(); //для корабля

        //почистим все активные о-ты в пулах, если они остались
        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        explosionPool.freeAllActiveObjects();
    }
}
