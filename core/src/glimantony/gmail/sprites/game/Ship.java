package glimantony.gmail.sprites.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;
import glimantony.gmail.pool.ExplosionPool;

public class Ship extends Sprite {

    protected Rect worldBounds; //границы мира (иниц. в resize)

    protected Vector2 speed = new Vector2(); //скорость корабля

    protected BulletPool bulletPool; //для передачи в конструктор
    protected TextureRegion bulletRegion; //текстура пули

    protected float reloadInterval; //интервал стрельбы (время перезарядки)
    protected float reloadTimer; //для отсчета времени

    private float damageInterval = 0.1f; //интервал повреждений
    private float damageTimer = damageInterval; //для отсчета времени

    protected Sound shootSound; //звук выстрела

    protected Vector2 bulletSpeed; //пули летят в разные стороны у нас и у кораблей противников
    protected float bulletHeight; //размеры пули

    protected int bulletDamage; //урон наносимый пулей
    protected int hp; //количество жизней корабля

    protected ExplosionPool explosionPool; //анимация взрывов


    public Ship() {
        super();
    }

    /**
     * конструктор для нашего корабля
     */
    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        damageTimer += delta; //таймер тикает в бесконечность
        if (damageTimer >= damageInterval){ //
            frame = 0; //кадр всегда будет 0, кроме редких случаев, когда мы его сбросим (в корабль попали)
        }
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

    public void boom(){ //взрыв корабля
        Explosion explosion = explosionPool.obtain();
        explosion.set(getHeight(), pos); //размеры корабля и его местоположение
    }

    /**
     * метод описывает повреждения
     * @param damage - количество повреждений
     */
    public void damage(int damage){
        frame = 1; //В корабль попали, меняем текстуру
        damageTimer = 0; //пока таймер не увеличиться до damageInterval в методк update()
        hp -= damage; //уменьшаем кол-во жизней корабля
        if (hp <= 0){
            hp = 0;
            destroy(); //взрываем корабль
        }
    }

    public void dispose() {
        shootSound.dispose(); //освобождаем ресурсы
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public int getHp() {
        return hp;
    }
}
