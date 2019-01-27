package glimantony.gmail.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Base2DScreen;

/**
 * Класс отвечает за стартовое меню приложения
 */
public class MenuScreen extends Base2DScreen {

    public static final float V_LEN = 0.001f; //величина вектора скорости

    Texture img;
    Texture background;

    Vector2 imgPosition; //позицыя картинки
    Vector2 imgSpeed; //скорость картинки

    Vector2 touch; //поместим сюда touch из суперкласса
    Vector2 copyTouch; //копия того места, куда нажали ЛКМ


    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");

        imgPosition = new Vector2(0, 0);
        imgSpeed = new Vector2(0, 0);

        touch = new Vector2();
        copyTouch = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        copyTouch.set(touch);
        if (copyTouch.sub(imgPosition).len() > V_LEN){
            imgPosition.add(imgSpeed);
        } else {
            imgPosition.set(touch);
        }

        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f); //с шириной и высотой картинки
        batch.draw(img, imgPosition.x, imgPosition.y, 0.5f, 0.5f); //с шириной и высотой картинки
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose(); //Вызывается в конце
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        imgSpeed.set(touch.cpy().sub(imgPosition).setLength(V_LEN)); //находим расстояние между О-ом и нажатием и устонавливаем ск-ть (setLength)
        return super.touchDown(touch, pointer);
    }
}
