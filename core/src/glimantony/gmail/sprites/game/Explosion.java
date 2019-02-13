package glimantony.gmail.sprites.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;

/**
 * Класс описывает взрыв
 */
public class Explosion extends Sprite {

    private float animatedInterval; //интервал смены кадров анимации
    private float animatedTimer; //интервал смены кадров анимации
    private Vector2 speed; //скорость движения взрыва

    private Sound explosionSound;

    public Explosion(TextureRegion region, int rows, int cols, int frames, Sound explosionSound, float animatedInterval) {
        super(region, rows, cols, frames);
        this.explosionSound = explosionSound;
        this.animatedInterval = animatedInterval;
    }

    /**
     * Взрыв будем доставать из пула объектов
     * @param height - размеры взрыва в зависимости от размеров корабля
     * @param pos - местонахождение взрыва
     */
    public void set(float height, Vector2 pos, Vector2 speed){
        this.pos.set(pos); //описано в Sprite
        this.speed = speed; //скорость движения взрыва
        setHeightProportion(height); //устанавливаем размеры
        explosionSound.play(0.5f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(speed, delta); //mulAdd() - сложение в-ов и умножение на скаляр (привязываем движение к кадру)
        animatedTimer += delta;
        if (animatedTimer >= animatedInterval){
            animatedTimer = 0f;
            if (++frame == regions.length){ //если последний кадр уже проигран
                destroy(); //убираем с экрана
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0; //чтобы начать с 0 кадра
    }

}
