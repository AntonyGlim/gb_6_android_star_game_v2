package glimantony.gmail.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.sprites.game.Explosion;

public class ExplosionPool extends SpritesPool<Explosion> {

    private TextureRegion region;
    private Sound explosionSound;

    public ExplosionPool(TextureAtlas atlas) {
        this.region = atlas.findRegion("explosion"); //вырезаем из атласа
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(region, 9, 9, 74, explosionSound, 0.012f); //можно убедиться в этом открыв атлас
    }

    @Override
    public void dispose() {
        explosionSound.dispose();
        super.dispose();
    }
}
