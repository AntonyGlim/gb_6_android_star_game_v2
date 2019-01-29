package glimantony.gmail.sprites.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;

public class MainShip extends Sprite {

    private final Vector2 speedDelta = new Vector2(0.5f, 0f); //величина скорости
    private Vector2 speed = new Vector2(); //скорость корабля

    private boolean isPressedLeft; //хранит состояние нажатой кнопки
    private boolean isPressedRight; //хранит состояние нажатой кнопки

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2); //усовершенствованый конструктор
        setHeightProportion(0.12f); //Размеры корабля
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
}
