package glimantony.gmail.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //сглаживание может быть применено и к текстуре
    }

    /**
     * Метод меняет размеры шрифта в пределах нашей кординатной сетки
     */
    public void setSize (float size){
        getData().setScale(size / getCapHeight()); //getCapHeight() - величина заглавной буквы
    }
}
