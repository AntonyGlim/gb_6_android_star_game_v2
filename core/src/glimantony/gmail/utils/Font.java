package glimantony.gmail.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

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
        getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //сглаживание может быть применено и к текстуре (увеличение и уменьшение)
    }

    /**
     * Метод меняет размеры шрифта в пределах нашей кординатной сетки
     */
    public void setSize (float size){
        getData().setScale(size / getCapHeight()); //getCapHeight() - величина заглавной буквы
    }

    /**
     * Метод позволит отрисовывать надпись с выравниванием
     * @param batch
     * @param str
     * @param x
     * @param y
     * @param halign
     * @return
     */
    public GlyphLayout draw(Batch batch, CharSequence str, float x, float y, int halign) {
        return super.draw(batch, str, x, y, 0f, halign, false);
    }
}
