package glimantony.gmail.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * В классе расширины некоторые функции базового класса для работы со шрифтами
 */
public class Font extends BitmapFont {
    /**
     * Работает с:
     * @param fontFile - в нем заданы координаты расположения каждой буквы
     * @param imageFile - сами буквы
     */
    public Font(String fontFile, String imageFile) {
        super(Gdx.files.internal(fontFile), Gdx.files.internal(imageFile), false, false);
    }

    /**
     * Метод меняет размеры шрифта в пределах нашей кординатной сетки
     */
    public void setSize (float size){
        getData().setScale(size / getCapHeight()); //getCapHeight() - величина заглавной буквы
    }
}
