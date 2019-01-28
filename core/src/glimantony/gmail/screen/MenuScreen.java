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
import glimantony.gmail.sprites.menu.ButtonExit;
import glimantony.gmail.sprites.menu.ButtonPlay;

/**
 * Класс отвечает за стартовое меню приложения
 */
public class MenuScreen extends Base2DScreen {

    public static final float V_LEN = 0.001f; //величина вектора скорости

    private TextureAtlas atlas;
    private Texture bg; //фон
    private Background background; //обертка для фона
    private Star[] stars; //массив звезд

    private ButtonExit buttonExit; //кнопка выхода
    private ButtonPlay buttonPlay; //кнопка играть

    @Override
    public void show() { //проводим всю инициализацию
        super.show();
        bg = new Texture("textures/backgrounds/spase_stars_background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack"); //инициализируем конфиг для атласа
        stars = new Star[256]; //звезда настраивает себя сама
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        buttonExit = new ButtonExit(atlas); //кнопка сама вытащит необходимую текстуру
        buttonPlay = new ButtonPlay(atlas); //кнопка сама вытащит необходимую текстуру
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    /**
     * Метод как подметод render(float delta)
     * @param delta
     */
    public void update(float delta){
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    /**
     * Метод как подметод render(float delta)
     * Отвечает за всю отресовку
     */
    public void draw(){

        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch); //фон сам знает как себя нарисовать
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        buttonExit.draw(batch); //рисуем кнопку
        buttonPlay.draw(batch); //рисуем кнопку
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);//передаем новые границы мира, после их изменения (он сам изменит свои размеры)
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose(); //Вызывается в конце
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer); //сообщаем кнопке, что произошло это событие(обработка в ScaledTouchUpButton)
        buttonPlay.touchDown(touch, pointer); //сообщаем кнопке, что произошло это событие(обработка в ScaledTouchUpButton)
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer); //сообщаем кнопке, что произошло это событие(обработка в ScaledTouchUpButton)
        buttonPlay.touchUp(touch, pointer); //сообщаем кнопке, что произошло это событие(обработка в ScaledTouchUpButton)
        return super.touchUp(touch, pointer);
    }
}
