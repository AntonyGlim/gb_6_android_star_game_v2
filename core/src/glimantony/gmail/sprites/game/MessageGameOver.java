package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import glimantony.gmail.base.Sprite;

public class MessageGameOver extends Sprite {
    public MessageGameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.02f); //процент от высоты экрана
        setBottom(0.02f); //смещение
    }
}
