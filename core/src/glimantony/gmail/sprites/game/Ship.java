package glimantony.gmail.sprites.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;

public class Ship extends Sprite {

    protected Rect worldBounds; //границы мира (иниц. в resize)

    protected Vector2 speed = new Vector2(); //скорость корабля

    protected BulletPool bulletPool; //для передачи в конструктор
    protected TextureRegion bulletRegion; //текстура пули

    protected float reloadInterval; //интервал стрельбы (время перезарядки)
    protected float reloadTimer; //для отсчета времени

    protected Sound shootSound; //звук выстрела

    protected Vector2 bulletSpeed; //пули летят в разные стороны у нас и у кораблей противников
    protected float bulletHeight; //размеры пули

    protected int bulletDamage; //урон наносимый пулей
    protected int hp; //количество жизней корабля

    /**
     * конструктор для нашего корабля
     */
    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
    }

    public void shoot(){ //Стрельба
        shootSound.play(0.3f);
        Bullet bullet = bulletPool.obtain(); //
        bullet.set(this, bulletRegion, pos,bulletSpeed, bulletHeight, worldBounds, bulletDamage);
    }

    public void dispose() {
        shootSound.dispose(); //освобождаем ресурсы
    }
}
