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
    private ExplosionPool explosionPool;
    private MainShip mainShip;

    public MeteorPool(Rect worldBounds, ExplosionPool explosionPool, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
        this.mainShip = mainShip;
    }

    @Override
    protected Meteor newObject() {
        return new Meteor(explosionPool, worldBounds, mainShip);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
