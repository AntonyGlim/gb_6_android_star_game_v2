package glimantony.gmail.sprites.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import glimantony.gmail.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    /**
     * Принимает на вход одну текстуру
     * @param atlas - кнопка Exit
     */
    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit")); //в файле menuAtlas.tpack
        setHeightProportion(0.15f); //15% от высоты экрана (размеры)
    }

    /**
     * Здесь будем позицыонировать кнопку на экране
     * @param worldBounds - границы мира
     */
    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }

    @Override
    public void action() {
        Gdx.app.exit(); //выйти из приложения (не подойдет для выхода из игры) запоминает на каком месте вышли
    }
}
