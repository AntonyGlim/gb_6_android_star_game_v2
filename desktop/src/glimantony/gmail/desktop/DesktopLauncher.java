package glimantony.gmail.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import glimantony.gmail.Star2DGame;
import glimantony.gmail.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f / 4f;
		config.width = 700;
		config.height = (int) (config.width / aspect);
		config.resizable = false; //Запрет на изменение ширины и высоты экрана
		new LwjglApplication(new Star2DGame(), config);
	}
}
