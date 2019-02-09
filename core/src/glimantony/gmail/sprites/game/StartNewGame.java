package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.ScaledTouchUpButton;

public class StartNewGame extends ScaledTouchUpButton {

    /**
     * Принимает на вход одну текстуру
     * @param atlas
     */
    public StartNewGame(TextureAtlas atlas) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(0.2f);
        setTop(-0.12f);
    }

    @Override
    public void action() {

    }
}
