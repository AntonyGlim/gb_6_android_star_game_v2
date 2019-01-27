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

    public static final float V_LEN = 0.5f; //величина вектора скорости

    Texture img;
    Texture background;

    Vector2 imgPosition; //позицыя картинки
    Vector2 imgSpeed; //скорость картинки


    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");

        imgPosition = new Vector2(0, 0);
        imgSpeed = new Vector2(0.001f, 0.001f);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f); //с шириной и высотой картинки
        batch.draw(img, imgPosition.x, imgPosition.y, 0.5f, 0.5f); //с шириной и высотой картинки
        batch.end();

        imgPosition.add(imgSpeed);

    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose(); //Вызывается в конце
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }
}
