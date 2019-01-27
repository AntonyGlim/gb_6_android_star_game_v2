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


    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");

        imgPosition = new Vector2(0, 0);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch.begin();
        batch.draw(background, -0.5f, -0.5f, 1f, 1f); //с шириной и высотой картинки
        batch.draw(img, 0, 0, 0.5f, 0.5f); //с шириной и высотой картинки
        batch.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose(); //Вызывается в конце
    }

    //Переопределили метод так, чтобы получить координаты системы событий
    // в соответствии с координатами системы отрисовки
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown() (MenuScreen): screenX = " + screenX +
                "; screenY = " + (Gdx.graphics.getHeight() - screenY) +
                "; pointer = " + pointer +
                "; button = " + button); //залогируем для информации о вызове метода
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
