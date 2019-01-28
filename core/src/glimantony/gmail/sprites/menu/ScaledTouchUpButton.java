package glimantony.gmail.sprites.menu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;

/**
 * Абстрактный класс описывает поведение кнопок
 */
public abstract class ScaledTouchUpButton extends Sprite {

    private static final float PRESS_SCALE = 0.9f; //масштаб изменения кнопки
    private int pointer; //номер пальца
    private boolean isPressed; //кнопка нажата?

    /**
     * Принимает на вход одну текстуру
     * @param region
     */
    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (isPressed || !isMe(touch)) return false; //если кнопка нажата или нажатие не по кнопке
        this.pointer = pointer; //палец, которым была нажата кнопка
        this.scale = PRESS_SCALE; //кнопка уменьшается на 10%
        this.isPressed = true;
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !isPressed) return false; //отпустил не тот палец, или кнопка не нажата
        if (isMe(touch)){ //пользователь попал по кнопке
            action();
        }
        this.isPressed = false; //кнопка отпкщена
        this.scale = 1f; //возвращаем исходный размер
        return super.touchUp(touch, pointer);
    }

    public abstract void action(); //реализуем в подклассах
}
