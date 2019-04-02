package com.pawszo.lata.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pawszo.lata.Lata;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		config.title = "Lata";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new Lata(), config);
	}
}
