package glimantony.gmail.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

/**
 * Базовый класс в котором храниться поведение всех прочих экранов.
 * (базовая реализация)
 * interface Screen - включает методы для отрисовки экрана
 * interface InputProcessor - для перехвата кликов, тачей и клавиш (системы пользовательских событий)
 *
 * Необходимо помнить, что координаты системы событий и системы отрисовки - разные
 */

public class Base2DScreen implements Screen, InputProcessor {

//Методы относятся к interface Screen (методы для отрисовки экрана)
    @Override
    public void show() { //Показать экран
        System.out.println("show()"); //залогируем для информации о вызове метода
        Gdx.input.setInputProcessor(this); //изменение инпут процессора на этот экран

    }

    @Override
    public void render(float delta) { //Отрисовка 60 раз в секунду

    }

    @Override
    public void resize(int width, int height) { //Изменение размеров окна приложения
        System.out.println("resize() : width = " + width + "; height = " + height); //залогируем для информации о вызове метода
    }

    @Override
    public void pause() { //Свернули экран
        System.out.println("pause()"); //залогируем для информации о вызове метода
    }

    @Override
    public void resume() { //Развернули экран (вернулись в приложение)
        System.out.println("resume()"); //залогируем для информации о вызове метода
    }

    @Override
    public void hide() { //Закрыли экран
        System.out.println("hide()"); //залогируем для информации о вызове метода
        dispose();
    }

    @Override
    public void dispose() { //освобождаем ресурсы (вызываем из hide())
        System.out.println("dispose()"); //залогируем для информации о вызове метода
    }



//Методы относятся к interface InputProcessor (системы пользовательских событий)
    @Override
    public boolean keyDown(int keycode) { //нажали клавишу
        System.out.println("keyDown() : keycode = " + keycode); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean keyUp(int keycode) { //отпустили клавишу
        System.out.println("keyUp() : keycode = " + keycode); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean keyTyped(char character) { //нажали клавишу с определенным символом который возвращается
        System.out.println("keyTyped() : symbol = " + character); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { //коснулись экрана
        System.out.println("touchDown() : screenX = " + screenX +
                "; screenY = " + screenY +
                "; pointer = " + pointer +
                "; button = " + button); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { //отпустили экран
        System.out.println("touchUp() : screenX = " + screenX +
                "; screenY = " + screenY +
                "; pointer = " + pointer +
                "; button = " + button); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { //провели по экрану
        System.out.println("touchDragged() : screenX = " + screenX +
                "; screenY = " + screenY +
                "; pointer = " + pointer); //залогируем для информации о вызове метода
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) { //движение мыши
        return false;
    }

    @Override
    public boolean scrolled(int amount) { //скрол
        return false;
    }

}
