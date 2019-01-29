package glimantony.gmail.pool;

import glimantony.gmail.base.SpritesPool;
import glimantony.gmail.sprites.game.Bullet;

/**
 * Pool наших пуль
 */
public class BulletPool extends SpritesPool <Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
