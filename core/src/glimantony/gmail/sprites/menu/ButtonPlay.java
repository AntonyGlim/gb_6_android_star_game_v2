package glimantony.gmail.sprites.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import glimantony.gmail.base.ScaledTouchUpButton;
import glimantony.gmail.math.Rect;
import glimantony.gmail.screen.GameScreen;

public class ButtonPlay extends ScaledTouchUpButton {

    private Game game;

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay")); //в файле menuAtlas.tpack
        this.game = game; //получаем игру
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
        game.setScreen(new GameScreen()); //получаем новый экран
    }
}
