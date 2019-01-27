package glimantony.gmail.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import glimantony.gmail.base.Base2DScreen;

/**
 * Класс отвечает за стартовое меню приложения
 */
public class MenuScreen extends Base2DScreen {

    SpriteBatch batch;
    Texture img;
    Texture background;

    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture("backgrounds/spase_stars_background.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose(); //Вызывается в конце
    }
}
