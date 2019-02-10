package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Meteor extends Ship /*TODO временная мера, исправить*/ {

    private enum State {DESCENT, FIGHT} //состояние нашего метеора DESCENT - выползает на экран, FIGHT - ведет бой
    private Meteor.State state;
    private Vector2 descentSpeed = new Vector2(0, -0.5f); //на время появления, у метеора будет скорость выше

    private Vector2 speed0 = new Vector2();

    private MainShip mainShip; //для того, чтобы наносить урон при столкновении

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
}
