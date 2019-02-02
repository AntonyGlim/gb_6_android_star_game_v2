package glimantony.gmail.sprites.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;

/**
 * Класс описывает наш игровой кораль
 */
public class MainShip extends Ship {

    private static final int ABSTRACT_POINTER = -1; //некий не существующий номер пальца

    private final Vector2 speedDelta = new Vector2(0.5f, 0f); //величина скорости

    private boolean isPressedLeft; //хранит состояние нажатой кнопки
    private boolean isPressedRight; //хранит состояние нажатой кнопки



    private int leftPointer = ABSTRACT_POINTER; //в переменных будут храниться номера пальцев
    private int rightPointer = ABSTRACT_POINTER;



    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2); //усовершенствованый конструктор
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.reloadInterval = 0.3f; //интервал стрельбы
        setHeightProportion(0.12f); //Размеры корабля
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.mp3")); //звук
        this.bulletPool = bulletPool; //пул пуль
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
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
        reloadTimer += delta; //увеличиваем значение таймера на
        if (reloadTimer >= reloadInterval){ //
            reloadTimer = 0f; //обнуляем таймер
            shoot();
        }
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
        if(touch.x < worldBounds.pos.x){ //проверяем положение корабля по Х (плохо для мультитача)
            if (leftPointer != ABSTRACT_POINTER) return false;//проверим, не нажал-ли пользователь на другую часть экрана
            leftPointer = pointer; //присваиваем номер пальца которым был нажат экран
            moveLeft();
        }
        else {
            if (rightPointer != ABSTRACT_POINTER) return false;
            rightPointer = pointer;
            moveRight();
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {//если отпустил левый палец
            leftPointer = ABSTRACT_POINTER;
            if (rightPointer != ABSTRACT_POINTER){//если на экране еще находиться другой палец
                moveRight();
            } else { //пользователь отпустил все пальцы
                stop();
            }
        } else if (pointer == rightPointer){ //то-же для другой стороны
            rightPointer = ABSTRACT_POINTER;
            if (leftPointer != ABSTRACT_POINTER){
                moveLeft();
            } else {
                stop();
            }
        }
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

    public void dispose() {
        shootSound.dispose(); //освобождаем ресурсы
    }
}
