package glimantony.gmail.sprites.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;
import glimantony.gmail.pool.ExplosionPool;

public class Enemy extends Ship {

    private enum State {DESCENT, FIGHT} //состояние нашего корабля DESCENT - выползает на экран, FIGHT - ведет бой
    private State state;
    private Vector2 descentSpeed = new Vector2(0, -0.5f); //на время появления, у корабля будет скорость выше

    private Vector2 speed0 = new Vector2();

    private MainShip mainShip; //для того, чтобы наносить урон при столкновении

    public Enemy(Sound shootSound, BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, MainShip mainShip) {
        super();
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
        this.bulletPool = bulletPool;
        this.speed.set(speed0);
        this.bulletSpeed = new Vector2();
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
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
        speed.set(descentSpeed);
        state = State.DESCENT; //изначально корабль в состоянии подхода к экрану
    }

    @Override
    public void update(float delta) { //позволит кораблю лететь по экрану
        super.update(delta);
        this.pos.mulAdd(speed, delta);
        switch (state){ //корабль в режиме боя, или только появляется на экран7
            case DESCENT:
                if (getTop() <= worldBounds.getTop()){ //если нос корабля
                    speed.set(speed0);
                    state = State.FIGHT;
                }
                break;
            case FIGHT:
                reloadTimer += delta; //научим корабль стрелять
                if (reloadTimer >= reloadInterval){
                    reloadTimer = 0f;
                    shoot();
                }
                if (getBottom() < worldBounds.getBottom()){ //по достижению нижней границы экрана
                    mainShip.damage(this.bulletDamage); //кораблю наноситься урон
                    destroy(); //корабль исчезнет
                }
                break;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom(); //взрыв корабля
    }

    /**
     * Метод усовершенствует взаимодействие корабля и вражеской пули.
     * Пуля попала в корабль, когда достигает его центра текстуры
     * @return
     */
    public boolean isBulletCollision(Rect bullet){
        return !(bullet.getRight() < getLeft() //право пули меньше чем лево корабля
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y //пуля долетит до центра корабля
        );
    }
}
