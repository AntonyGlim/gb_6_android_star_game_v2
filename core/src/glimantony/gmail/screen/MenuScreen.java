package glimantony.gmail.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import glimantony.gmail.base.Base2DScreen;
import glimantony.gmail.math.Rect;
import glimantony.gmail.sprites.Background;
import glimantony.gmail.sprites.Star;

/**
 * Класс отвечает за стартовое меню приложения
 */
public class MenuScreen extends Base2DScreen {

    public static final float V_LEN = 0.001f; //величина вектора скорости

    private TextureAtlas atlas;
    private Texture bg;
    private Background background;
    private Star star;

    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        bg = new Texture("textures/backgrounds/spase_stars_background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack"); //инициализируем конфиг для атласа
        star = new Star(atlas); //звезда настраивает себя сама
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch); //фон сам знает как себя нарисовать
        star.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);//передаем новые границы мира, после их изменения (он сам изменит свои размеры)
        star.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose(); //Вызывается в конце
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        return super.touchDown(touch, pointer);
    }
}
