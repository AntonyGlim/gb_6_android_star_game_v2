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

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 imgPosition; //позицыя картинки
    Vector2 imgSpeed; //скорость картинки

    Vector2 userTouch; //координаты, куда нажал пользователь
    Vector2 userTouchCopy; //координаты, куда нажал пользователь (копия для метода render)

    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");

        imgPosition = new Vector2(0, 0);
        imgSpeed = new Vector2(0, 0);
        userTouch = new Vector2(0, 0);
        userTouchCopy = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*Если расстояние между картинкой и местом нажатия ЛКМ больше в-ра ск-ти
        * userTouch.cpy() - создает новые экземпляры, в методе render он не допустим.
        * Вместо него используем userTouchCopy*/
        userTouchCopy.set(userTouch);
        if (userTouchCopy.sub(imgPosition).len() > V_LEN){
            imgPosition.add(imgSpeed); //изменение позиции на величину скорости
        }
        else {
            imgPosition.set(userTouch); //картинка остановиться в указанных координатах
        }

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, imgPosition.x, imgPosition.y);
        batch.end();

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
        userTouch.set(screenX, (Gdx.graphics.getHeight() - screenY)); //запоминаем куда нажал пользователь
        imgSpeed.set(userTouch.cpy().sub(imgPosition).setLength(V_LEN)); //устанавливаем вектор скорости
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
