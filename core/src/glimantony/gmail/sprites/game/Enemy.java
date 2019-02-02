package glimantony.gmail.sprites.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.pool.BulletPool;

public class Enemy extends Ship {

    private Vector2 speed0 = new Vector2();

    public Enemy(Sound shootSound, BulletPool bulletPool) {
        super();
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
        this.speed.set(speed0);
        this.bulletSpeed = new Vector2();
    }

    public void set(
            TextureRegion[] regions, //т.к. 1 класс на все вражеские корабли
            Vector2 speed0, //начальная скорость
            TextureRegion bulletRegion, //текстуры вражеских кораблей будут отличаться
            float bulletHeight, //размеры пули
            float bulletSpeedY, //скорость пули в направлении оси Y
            int bulletDamage, //урон пули
            float reloadInterval, //скорость перезарядки
            float height, //размер корабля
            int hp //количество жизней
    ){
        this.regions = regions;
        this.speed0 = speed0;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletSpeed.set(0, bulletSpeedY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        setHeightProportion(height);
        this.hp = hp;

        reloadTimer = reloadInterval; //корабль начнет стрелять при появлении
        speed.set(speed0);
    }

    @Override
    public void update(float delta) { //позволит кораблю лететь по экрану
        super.update(delta);
        this.pos.mulAdd(speed, delta);
    }
}
