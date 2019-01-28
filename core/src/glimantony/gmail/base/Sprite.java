package glimantony.gmail.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.math.Rect;

/**
 * Класс более простой чем стандартные.
 * Заточен под наши цели.
 * В Rect будем оборачивать текстуры;
 */
public class Sprite extends Rect {

    protected float angle; //угол поворота
    protected float scale = 1f; //угол поворота
    protected TextureRegion[] regions; //массив текстур для атласа
    protected int frame; //номер кадра для покадровой анимации

    /**
     * Принимает на вход одну текстуру
     * @param region
     */
    public Sprite(TextureRegion region){
        if (region == null) throw new NullPointerException("region == null");
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    /**
     * Метод для пересчета ширины, в зависимости от высоты
     */
    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height * aspect);
    }

    /**
     * Определение границ мира
     * @param worldBounds - границы мира
     */
    public void resize(Rect worldBounds){
    }

    /**
     * Метод для реализации движений и прочего экшена
     * @param delta
     */
    public void update(float delta){
    }

    /**
     * Коснулись экрана
     * @param touch - принимает на вход координаты в мировой си-ме
     * @param pointer
     * @return
     */
    public boolean touchDown(Vector2 touch, int pointer) { //коснулись экрана
        return false;
    }

    /**
     * Отпустили экран
     * @param touch - принимает на вход координаты в мировой си-ме
     * @param pointer
     * @return
     */
    public boolean touchUp(Vector2 touch, int pointer) { //коснулись экрана
        return false;
    }

    /**
     * Рисование самого себя. Отрисовка каждого о-та реализуется в самом о-те
     * @param batch
     */
    public void draw(SpriteBatch batch){
        batch.draw(
                regions[frame], //номер региона в текущем кадре
                getLeft(), getBottom(), //координаты точки отрисовки (рисунок сместиться на половину ширины и высоты)
                halfWidth, halfHeight, //координаты точки вращения (по центру)
                getWidth(), getHeight(), //ширина и высота
                scale, scale, //масштабирование по x и y (одинаковое)
                angle //угол на который мы можем повернуть наш спрайт
        );
    }

    public float getAngle() {
        return angle;
    }

    public float getScale() {
        return scale;
    }
}