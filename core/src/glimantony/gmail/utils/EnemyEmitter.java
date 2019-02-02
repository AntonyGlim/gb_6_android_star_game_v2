package glimantony.gmail.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.math.Rect;
import glimantony.gmail.math.Rnd;
import glimantony.gmail.pool.EnemyPool;
import glimantony.gmail.sprites.game.Enemy;

/**
 * предназначен для генерации вражеских кораблей
 */
public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f; //размеры маленького корабля
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f; //размеры пули
    private static final float ENEMY_SMALL_BULLET_SPEED_Y = -0.3f; //пуля летит вниз
    private static final int ENEMY_SMALL_DAMAGE = 1; //урон пули
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f; //интервал стрельбы
    private static final int ENEMY_SMALL_HP = 3; //количество жизней

    private Vector2 enemySmallSpeed = new Vector2(0, -0.2f); //скорость корабля
    private TextureRegion[] enemySmallRegions; //текстуры корабля
    private TextureRegion bulletRegion; //текстура пули

//    private TextureAtlas atlas;
    private EnemyPool enemyPool;

    private float generateInterval = 4f; //раз в 4 секунды появиться новый корабль
    private float generateTimer; //таймер

    private Rect worldBounds; //границы мира

    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {
//        this.atlas = atlas;
        this.enemyPool = enemyPool;
        TextureRegion textureRegion = atlas.findRegion("enemy0");
        this.enemySmallRegions = Regions.split(textureRegion, 1, 2, 2); //режем текстуру на части
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.worldBounds = worldBounds;
    }

    public void generate(float delta){
        generateTimer += delta;
        if (generateTimer >= generateInterval){ //значит генерируем новый корабль
            generateTimer = 0f;
            Enemy enemy = enemyPool.obtain(); //достаем из пула овый корабль
            enemy.set(
                    enemySmallRegions,
                    enemySmallSpeed,
                    bulletRegion,
                    ENEMY_SMALL_BULLET_HEIGHT,
                    ENEMY_SMALL_BULLET_SPEED_Y,
                    ENEMY_SMALL_DAMAGE,
                    ENEMY_SMALL_RELOAD_INTERVAL,
                    ENEMY_SMALL_HEIGHT,
                    ENEMY_SMALL_HP
            ); //настройка корабля
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth()); //позицыя по Х
            enemy.setBottom(worldBounds.getTop()); //позицыя по Y
        }
    }
}
