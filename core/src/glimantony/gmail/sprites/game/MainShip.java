package glimantony.gmail.sprites.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;

public class MainShip extends Sprite {

    private Rect worldBounds; //границы мира (иниц. в resize)

    private final Vector2 speedDelta = new Vector2(0.5f, 0f); //величина скорости
    private Vector2 speed = new Vector2(); //скорость корабля

    private boolean isPressedLeft; //хранит состояние нажатой кнопки
    private boolean isPressedRight; //хранит состояние нажатой кнопки

    private BulletPool bulletPool; //для передачи в конструктор
    private TextureRegion bulletRegion; //текстура пули

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2); //усовершенствованый конструктор
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        setHeightProportion(0.12f); //Размеры корабля
        this.bulletPool = bulletPool; //пул пуль
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    /**
     * В методе будет осуществляться движение корабля
     * @param delta
     */
    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speed, delta);
        if (getRight() > worldBounds.getRight()) {//корабль улетает за границы?
            setRight(worldBounds.getRight());//ставим корабль в позицыю скраю
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {//корабль улетает за границы?
            setLeft(worldBounds.getLeft());//ставим корабль в позицыю скраю
            stop();
        }
    }

    public boolean keyDown(int keycode) { //нажали клавишу
        switch (keycode){ //если нажата
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                moveLeft(); //движение влево
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = true;
                moveRight(); //движение вправо
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) { //отпустили клавишу
        switch (keycode){ //если нажата
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false; //когда пользовательдержит 2 кнопки, корабль должен продолжать двигаться
                if (isPressedRight){
                    moveRight();
                } else {
                    stop(); //стоп
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = false;
                if (isPressedLeft){
                    moveLeft();
                } else {
                    stop(); //стоп
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x < worldBounds.pos.x) //проверяем положение корабля по Х (плохо для мультитача)
            moveLeft();
        else
            moveRight();
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        stop();
        return super.touchUp(touch, pointer);
    }

    /**
     * Движение корабля
     */
    private void moveRight(){
        speed.set(speedDelta);
    }

    /**
     * Движение корабля
     */
    private void moveLeft(){
        speed.set(speedDelta).rotate(180); //rotate() - поворачивает в-р на градусы
    }

    /**
     * Остановка корабля
     */
    private void stop(){
        speed.setZero(); //обнуляем скорость
    }

    /**
     * Стрельба
     */
    private void shoot(){
        Bullet bullet = bulletPool.obtain(); //
        bullet.set(this, bulletRegion, pos, new Vector2(0, 0.5f), 0.05f, worldBounds, 1);
    }
}
