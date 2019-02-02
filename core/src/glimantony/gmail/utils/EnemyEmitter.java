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

    //сложность корабля 1 (SMALL)
    private static final float ENEMY_SMALL_HEIGHT = 0.1f; //размеры маленького корабля
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f; //размеры пули
    private static final float ENEMY_SMALL_BULLET_SPEED_Y = -0.3f; //пуля летит вниз
    private static final int ENEMY_SMALL_DAMAGE = 1; //урон пули
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f; //интервал стрельбы
    private static final int ENEMY_SMALL_HP = 3; //количество жизней

    //сложность корабля 2 (MIDDLE)
    private static final float ENEMY_MIDDLE_HEIGHT = 0.15f; //размеры маленького корабля
    private static final float ENEMY_MIDDLE_BULLET_HEIGHT = 0.02f; //размеры пули
    private static final float ENEMY_MIDDLE_BULLET_SPEED_Y = -0.25f; //пуля летит вниз
    private static final int ENEMY_MIDDLE_DAMAGE = 5; //урон пули
    private static final float ENEMY_MIDDLE_RELOAD_INTERVAL = 4f; //интервал стрельбы
    private static final int ENEMY_MIDDLE_HP = 5; //количество жизней

    //сложность корабля 3 (LARGE)
    private static final float ENEMY_LARGE_HEIGHT = 0.17f; //размеры маленького корабля
    private static final float ENEMY_LARGE_BULLET_HEIGHT = 0.03f; //размеры пули
    private static final float ENEMY_LARGE_BULLET_SPEED_Y = -0.2f; //пуля летит вниз
    private static final int ENEMY_LARGE_DAMAGE = 10; //урон пули
    private static final float ENEMY_LARGE_RELOAD_INTERVAL = 5f; //интервал стрельбы
    private static final int ENEMY_LARGE_HP = 10; //количество жизней

    private Vector2 enemySmallSpeed = new Vector2(0, -0.2f); //скорость корабля
    private Vector2 enemyMiddleSpeed = new Vector2(0, -0.15f); //скорость корабля
    private Vector2 enemyLargeSpeed = new Vector2(0, -0.1f); //скорость корабля

    private TextureRegion[] enemySmallRegions; //текстуры корабля
    private TextureRegion[] enemyMiddleRegions; //текстуры корабля
    private TextureRegion[] enemyLargeRegions; //текстуры корабля

    private TextureRegion bulletRegion; //текстура пули

    private EnemyPool enemyPool;

    private float generateInterval = 4f; //раз в 4 секунды появиться новый корабль
    private float generateTimer; //таймер

    private Rect worldBounds; //границы мира

    public EnemyEmitter(TextureAtlas atlas, EnemyPool enemyPool, Rect worldBounds) {

        this.enemyPool = enemyPool;

        TextureRegion textureRegion0 = atlas.findRegion("enemy0");
        this.enemySmallRegions = Regions.split(textureRegion0, 1, 2, 2); //режем текстуру на части
        TextureRegion textureRegion1 = atlas.findRegion("enemy1");
        this.enemyMiddleRegions = Regions.split(textureRegion1, 1, 2, 2); //режем текстуру на части
        TextureRegion textureRegion2 = atlas.findRegion("enemy2");
        this.enemyLargeRegions = Regions.split(textureRegion2, 1, 2, 2); //режем текстуру на части

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
