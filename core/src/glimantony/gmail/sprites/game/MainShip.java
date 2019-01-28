package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.Sprite;

public class MainShip extends Sprite {
    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2); //усовершенствованый конструктор
        setHeightProportion(0.12f); //Размеры корабля
    }
}
