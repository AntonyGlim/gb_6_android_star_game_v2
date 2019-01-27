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
    private float size = 0.01f; //размеры звезды

    /**
     * Принимает на вход атлас
     * @param atlas
     */
    public Star(TextureAtlas atlas) {
        super(atlas.findRegion("star"));
        setHeightProportion(size);
        speed.set(Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f)); //звежды летт вниз
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight()); //звезда сгенерируется в произвольм месте на нашем поле
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop()); //звезда сгенерируется в произвольм месте на нашем поле
        pos.set(posX, posY);
    }
}
