package glimantony.gmail;

import com.badlogic.gdx.Game;

import glimantony.gmail.screen.MenuScreen;

/**
 * Класс создан как альтернатива классу StarGame.
 * Star2DGame - прописан в лаунчерах.
 * Класс StarGame будет удален и никак не влияет на программу.
 */
public class Star2DGame extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this)); //Устанавливаем начальный экран, передаем ссылку на самого себя
    }
}
