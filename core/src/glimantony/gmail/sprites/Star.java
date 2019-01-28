package glimantony.gmail.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;
import glimantony.gmail.math.Rnd;

public class Star extends Sprite {

    private Vector2 speed = new Vector2(); //ск-ть звезды
    private Rect worldBounds; //границы мира
    private float size = 0.004f; //размеры звезды

    /**
     * Принимает на вход атлас
     * @param atlas
     */
    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(size);
        speed.set(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f, -0.0001f)); //звежды летт вниз
    }

    /**
     * Используем метод для движения звезды
     * @param delta
     */
    @Override
    public void update(float delta) {
        pos.mulAdd(speed, delta); //mulAdd() - сложение в-ов и умножение на скаляр (привязываем движение к кадру)
        checkAndHandleBounds(); //все время проверяем
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight()); //звезда сгенерируется в произвольм месте на нашем поле
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop()); //звезда сгенерируется в произвольм месте на нашем поле
        pos.set(posX, posY);
    }

    /**
     * Проверка условия, чтобы звезда не вылетала за пределы экрана
     */
    private void checkAndHandleBounds(){
        if (getRight() < worldBounds.getLeft()) setLeft(worldBounds.getRight()); //звезда улетела влево, появилась справа
        if (getLeft() > worldBounds.getRight()) setRight(worldBounds.getLeft());
        if (getTop() < worldBounds.getBottom()) setBottom(worldBounds.getTop());
        if (getBottom() > worldBounds.getTop()) setTop(worldBounds.getBottom());

    }
}
