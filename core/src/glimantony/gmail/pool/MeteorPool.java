package glimantony.gmail.pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.math.Rect;
import glimantony.gmail.sprites.game.Enemy;
import glimantony.gmail.sprites.game.MainShip;
import glimantony.gmail.sprites.game.Meteor;

public class MeteorPool extends SpritesPool <Meteor> {

    private Rect worldBounds;
    private MeteorExplosionPool meteorExplosionPool;
    private MainShip mainShip;

    public MeteorPool(Rect worldBounds, MeteorExplosionPool meteorExplosionPool, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.meteorExplosionPool = meteorExplosionPool;
        this.mainShip = mainShip;
    }

    @Override
    protected Meteor newObject() {
        return new Meteor(meteorExplosionPool, worldBounds, mainShip);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
