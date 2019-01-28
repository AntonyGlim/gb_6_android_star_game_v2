package glimantony.gmail.sprites.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.math.Rect;

public class ButtonPlay extends ScaledTouchUpButton {
    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public ButtonPlay(TextureAtlas atlas) {
        super(atlas.findRegion("btPlay")); //в файле menuAtlas.tpack
        setHeightProportion(0.10f); //размер в% от высоты экрана (размеры)
    }

    /**
     * Здесь будем позицыонировать кнопку на экране
     * @param worldBounds - границы мира
     */
    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.03f); //опускаем кнопку вниз с % отступом от нижней границы
        setLeft(worldBounds.getLeft() + 0.03f); //сдвигаем кнопку на % от границы
    }

    @Override
    public void action() {

    }
}
