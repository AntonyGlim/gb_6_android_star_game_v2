package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import glimantony.gmail.base.ScaledTouchUpButton;
import glimantony.gmail.screen.GameScreen;

public class StartNewGameButton extends ScaledTouchUpButton {

    private GameScreen gameScreen; //ссылка нужна, чтобы достать метод startNewGame();

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public StartNewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(0.2f);
        setTop(-0.12f);
        this.gameScreen = gameScreen;
    }

    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
