package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;

public class Bullet extends Sprite {

    private Rect worldBounds; //чтобы пуля не улетала за границы мира - границы мира
    private Vector2 speedBullet = new Vector2(); //скорость пули (бует варьироваться)
    private int demage; //урон, который наносит пуля
    private Object ownerOfBullet; //владелец пули TODO проверить с типом MainShip

    public Bullet() {
        regions = new TextureRegion[1]; //пуля состоит из 1 тестуры
    }


    /**
     * Чтобы пуля могла лететь
     * @param delta
     */
    @Override
    public void update(float delta) {
        pos.mulAdd(speedBullet, delta); //пуля всегда летит
        if (isOutside(worldBounds)){ //если пуля за пределами экрана
            destroy(); //уничтожаем пулю
        }
    }

    /**
     * Настройка пули
     */
    public void set(
            Object ownerOfBullet,
            TextureRegion region,
            Vector2 pos0, //в-р стартовой позицыи
            Vector2 speedBullet0, //в-р начальной скорости
            float height, //размер пули
            Rect worldBounds, //границы мира, чтобы знать когда применить к пуле destroy
            int demage
    ){
        this.ownerOfBullet = ownerOfBullet;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.speedBullet.set(speedBullet0);
        this.setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.demage = demage;
    }

    public int getDemage() {
        return demage;
    }

    public Object getOwnerOfBullet() {
        return ownerOfBullet;
    }
}
