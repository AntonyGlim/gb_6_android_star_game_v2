package glimantony.gmail.sprites.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Ship {

    private Vector2 speed0;

    public Enemy(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        this.speed.set(speed0);
    }

    @Override
    public void update(float delta) { //позволит кораблю лететь по экрану
        super.update(delta);
    }
}
