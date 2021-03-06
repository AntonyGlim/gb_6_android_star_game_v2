package glimantony.gmail.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс на основе которого будут создаваться пулы о-ов
 * @param <T>
 */
public abstract class SpritesPool <T extends Sprite> {

    protected List<T> activeObjects = new ArrayList<T>(); //список активных о-ов
    protected List<T> freeObjects = new ArrayList<T>(); //список о-ов подлежащих destroy

    /**
     * Метод для инициализации о-та
     */
    protected abstract T newObject();

    /**
     * Метод реализует всю механику пула спрайтов
     * вернет нам о-кт (новый или старый)
     */
    public T obtain(){
        T oject;
        if (freeObjects.isEmpty()){ //если список свободных о-ов пуст
            oject = newObject(); //создаем новый
        } else {
            oject = freeObjects.remove(freeObjects.size() - 1); //берем последний элемент из списка
        }
        activeObjects.add(oject); //добавляем его в список активных
        System.out.println("obtain() : active/free" + activeObjects.size() + "/" + freeObjects.size());
        return oject;
    }

    /**
     * Все активные пули должны лететь
     */
    public void updateActiveSprites(float delta){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.update(delta);
        }
    }

    /**
     * Рисуем о-кт
     * @param batch
     */
    public void drawActiveSprites(SpriteBatch batch) {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.draw(batch);
        }
    }

    /**
     * Рисуем о-кт с вращением
     * @param batch
     */
    public void drawActiveSprites(SpriteBatch batch, float angle) {
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            sprite.draw(batch, angle);
        }
    }

    /**
     * Пули которые улетели за пределы экрана будут ожидать повторного использования
     */
    public void freeAllDestroiedSprites(){
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroied()){
                free(sprite); //освобождаем
                i--; //в activeObjects стало на 1 о-кт меньше
            }
        }
    }

    /**
     * Очистка всех существующих о-ов
     */
    public void freeAllActiveObjects(){
        freeObjects.addAll(activeObjects);
        activeObjects.clear();
    }

    /**
     * Метод освобождает о-ты
     */
    public void free(T object){
        activeObjects.remove(object); //удаляем о-кт из списка активных
        freeObjects.add(object); //добавляем его в список свободных о-ов
        object.unDestroy();
        System.out.println("obtain() : active/free" + activeObjects.size() + "/" + freeObjects.size());
    }

    public List<T> getActiveObjects() {
        return activeObjects;
    }

    /**
     * Очищаем все наши списки
     */
    public void dispose(){
        activeObjects.clear();
        freeObjects.clear();
    }



}
