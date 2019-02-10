package glimantony.gmail.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.math.Rect;
import glimantony.gmail.math.Rnd;
import glimantony.gmail.pool.MeteorPool;
import glimantony.gmail.sprites.game.Meteor;

/**
 * Класс предназначен для генерации метеоритов
 */
public class MeteorsEmitter {

    //сложность метеора 0 (EASY)
    private static final float METEOR_EASY_HEIGHT = 0.08f; //размеры маленького метеора
    private static final int METEOR_EASY_HP = 1; //количество жизней

    //сложность метеора 1 (SMALL)
    private static final float METEOR_SMALL_HEIGHT = 0.1f; //размеры маленького метеора
    private static final int METEOR_SMALL_HP = 3; //количество жизней

    //сложность метеора 2 (MIDDLE)
    private static final float METEOR_MIDDLE_HEIGHT = 0.15f; //размеры маленького метеора
    private static final int METEOR_MIDDLE_HP = 5; //количество жизней

    //сложность метеора 3 (LARGE)
    private static final float METEOR_LARGE_HEIGHT = 0.17f; //размеры маленького метеора
    private static final int METEOR_LARGE_HP = 10; //количество жизней

    private Vector2 meteorEasySpeed = new Vector2(0, -0.25f); //скорость метеора
    private Vector2 meteorSmallSpeed = new Vector2(0, -0.2f); //скорость метеора
    private Vector2 meteorMiddleSpeed = new Vector2(0, -0.15f); //скорость метеора
    private Vector2 meteorLargeSpeed = new Vector2(0, -0.1f); //скорость метеора

    private TextureRegion[] meteorEasyRegions; //текстуры метеора
    private TextureRegion[] meteorSmallRegions; //текстуры метеора
    private TextureRegion[] meteorMiddleRegions; //текстуры метеора
    private TextureRegion[] meteorLargeRegions; //текстуры метеора

    private MeteorPool meteorPool;

    private float generateInterval = 5f; //раз в 5 секунды появиться новый метеор
    private float generateTimer; //таймер

    private Rect worldBounds; //границы мира

    private int level; //уровень игры

    public MeteorsEmitter(TextureAtlas atlas, MeteorPool meteorPool, Rect worldBounds) {

        this.meteorPool = meteorPool;

        TextureRegion textureRegion0 = atlas.findRegion("meteor_1");
        this.meteorEasyRegions = Regions.split(textureRegion0, 1, 2, 2); //режем текстуру на части
        TextureRegion textureRegion1 = atlas.findRegion("meteor_2");
        this.meteorSmallRegions = Regions.split(textureRegion1, 1, 2, 2); //режем текстуру на части
        TextureRegion textureRegion2 = atlas.findRegion("meteor_3");
        this.meteorMiddleRegions = Regions.split(textureRegion2, 1, 2, 2); //режем текстуру на части
        TextureRegion textureRegion3 = atlas.findRegion("meteor_4");
        this.meteorLargeRegions = Regions.split(textureRegion3, 1, 2, 2); //режем текстуру на части

        this.worldBounds = worldBounds;
    }

    /**
     *
     * @param delta
     * @param frags - сколько убили врагов
     */
    public void generate(float delta, int frags){
        level = frags / 20 + 1; //расчитываем уровень игры
        generateTimer += delta;
        if (generateTimer >= generateInterval){ //значит генерируем новый метеор
            generateTimer = 0f;
            Meteor meteor = meteorPool.obtain(); //достаем из пула овый метеор
            float meteorType = (float) Math.random(); //случайное число для генерации метеоров
            if (meteorType <= 0.4f) { //вероятность 40% - малый метеор
                meteor.set(
                        meteorEasyRegions,
                        meteorEasySpeed,
                        METEOR_EASY_HEIGHT,
                        METEOR_EASY_HP
                ); //настройка метеора
            } else if (meteorType > 0.4 && meteorType <= 0.65){ //вероятность 25% - средний метеор
                meteor.set(
                        meteorSmallRegions,
                        meteorSmallSpeed,
                        METEOR_SMALL_HEIGHT,
                        METEOR_SMALL_HP
                ); //настройка метеора
            } else if (meteorType > 0.65 && meteorType <= 0.9){ //вероятность 25% - средний метеор
                meteor.set(
                        meteorMiddleRegions,
                        meteorMiddleSpeed,
                        METEOR_MIDDLE_HEIGHT,
                        METEOR_MIDDLE_HP
                ); //настройка метеора
            } else if (meteorType > 0.9 && meteorType <= 0.99){ //вероятность 10% - огромный метеор
                meteor.set(
                        meteorLargeRegions,
                        meteorLargeSpeed,
                        METEOR_LARGE_HEIGHT,
                        METEOR_LARGE_HP
                ); //настройка корабля
            }
            meteor.pos.x = Rnd.nextFloat(worldBounds.getLeft() + meteor.getHalfWidth(), worldBounds.getRight() - meteor.getHalfWidth()); //позицыя по Х
            meteor.setBottom(worldBounds.getTop()); //позицыя по Y
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
