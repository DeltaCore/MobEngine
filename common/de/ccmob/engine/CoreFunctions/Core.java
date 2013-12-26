package de.ccmob.engine.CoreFunctions;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Core {

	public Core(MobEngine engine) {
		engine.init();
		try {
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		while (!Display.isCloseRequested()) {
			Display.update();
			engine.renderHud();
			engine.update();
			engine.render();
			if (!engine.isVsync()) {
				Display.sync(engine.getFps());
			}
		}
		Display.destroy();
		System.exit(0);
	}

}
