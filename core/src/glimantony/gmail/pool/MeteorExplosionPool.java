package glimantony.gmail.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import glimantony.gmail.base.Sprite;
import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.sprites.game.Explosion;

public class MeteorExplosionPool extends SpritesPool<Explosion> {
    private Texture texture = new Texture("textures/meteorExplosion.png");
    private TextureRegion region = new TextureRegion(texture);
    private Sound explosionSound;

    public MeteorExplosionPool() {
        this.explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3")); //TODO поменять музыку
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(region, 3, 3, 9, explosionSound, 0.035f); //можно убедиться в этом открыв атлас
    }

    @Override
    public void dispose() {
        texture.dispose();
        explosionSound.dispose();
        super.dispose();
    }
}
