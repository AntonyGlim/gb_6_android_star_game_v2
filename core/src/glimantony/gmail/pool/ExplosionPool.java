package glimantony.gmail.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.sprites.game.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion region;

    public ExplosionPool(TextureAtlas atlas) {
        this.region = atlas.findRegion("explosion"); //вырезаем из атласа
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(region, 9, 9, 74); //можно убедиться в этом открыв атлас
    }
}
