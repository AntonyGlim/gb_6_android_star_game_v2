package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;

public class MainShip extends Sprite {

    private final Vector2 speedDelta = new Vector2(0.05f, 0f); //величина скорости
    private Vector2 speed = new Vector2(); //скорость корабля

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2); //усовершенствованый конструктор
        setHeightProportion(0.12f); //Размеры корабля
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    /**
     * В методе будет осуществляться движение корабля
     * @param delta
     */
    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public boolean keyDown(int keycode) { //нажали клавишу
        return false;
    }

    public boolean keyUp(int keycode) { //отпустили клавишу
        return false;
    }
}
