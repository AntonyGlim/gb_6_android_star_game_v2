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

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 imgPosition; //позицыя картинки
    Vector2 imgSpeed; //скорость картинки

    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");

        imgPosition = new Vector2(10f, 10f);
        imgSpeed = new Vector2(1f, 1f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, imgPosition.x, imgPosition.y);
        batch.end();

        //картинка дойдет до края и остановится
        //Gdx.graphics.getWidth() - ширина экрана
        //256 - высота и ширина картинки
        if(Gdx.graphics.getWidth() - 256 > imgPosition.x &&
                Gdx.graphics.getHeight() - 256 > imgPosition.y) {
            imgPosition.add(imgSpeed); //изменение позиции на величину скорости
        }


    }

    @Override
    public void dispose() {
        batch.dispose();
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
