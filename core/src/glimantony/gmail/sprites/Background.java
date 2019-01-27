package glimantony.gmail.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.math.Rect;

public class Background extends Sprite {

    /**
     * Принимает на вход одну текстуру
     * @param region
     */
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        pos.set(worldBounds.pos); //отцентровываем фон по центру экрана
        setHeightProportion(worldBounds.getHeight());//задаем размеры о-ту (через высоту)
    }
}
