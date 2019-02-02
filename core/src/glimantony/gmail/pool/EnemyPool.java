package glimantony.gmail.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.math.Rect;
import glimantony.gmail.sprites.game.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    private Sound shootSound; //звук выстрела
    private BulletPool bulletPool;
    private Rect worldBounds;
    ExplosionPool explosionPool;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, ExplosionPool explosionPool) {
        shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.mp3")); //звук
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(shootSound, bulletPool, explosionPool, worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        shootSound.dispose();
    }
}
