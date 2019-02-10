package glimantony.gmail.sprites.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.math.Rect;
import glimantony.gmail.pool.BulletPool;
import glimantony.gmail.pool.ExplosionPool;

public class Meteor extends Ship /*TODO временная мера, исправить*/ {

    private enum State {DESCENT, FIGHT} //состояние нашего метеора DESCENT - выползает на экран, FIGHT - ведет бой
    private Meteor.State state;
    private Vector2 descentSpeed = new Vector2(0, -0.5f); //на время появления, у метеора будет скорость выше

    private Vector2 speed0 = new Vector2();

    private MainShip mainShip; //для того, чтобы наносить урон при столкновении

    public Meteor(ExplosionPool explosionPool, Rect worldBounds, MainShip mainShip) {
        super();
        this.worldBounds = worldBounds;
        this.speed.set(speed0);
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
    }

    public void set(
            TextureRegion[] regions, //т.к. 1 класс на все вражеские корабли
            Vector2 speed0, //начальная скорость
            float height, //размер метеора
            int hp //количество жизней
    ){
        this.regions = regions;
        this.speed0 = speed0;
        setHeightProportion(height);
        this.hp = hp;

        speed.set(descentSpeed);
        state = Meteor.State.DESCENT; //изначально метеор в состоянии подхода к экрану
    }

    @Override
    public void update(float delta) { //позволит метеору лететь по экрану
        super.update(delta);
        this.pos.mulAdd(speed, delta);
        switch (state){ //корабль в режиме боя, или только появляется на экран7
            case DESCENT:
                if (getTop() <= worldBounds.getTop()){ //если нос корабля
                    speed.set(speed0);
                    state = Meteor.State.FIGHT;
                }
                break;
            case FIGHT:
                if (getBottom() < worldBounds.getBottom()){ //по достижению нижней границы экрана TODO исправить, чтобы урон не наносился
                    mainShip.damage(this.bulletDamage); //кораблю наноситься урон
                    destroy(); //корабль исчезнет
                }
                break;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
//        boom(); //взрыв корабля TODO исправить, чтобы урон не наносился
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
