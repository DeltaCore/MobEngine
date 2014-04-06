package net.ccmob.engine.core;


import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import net.ccmob.engine.types.Camera;
import net.ccmob.engine.types.GameObject;
import net.ccmob.engine.types.WindowConfiguration;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;


public abstract class MobEngine {

	private Camera	            camera	            = new Camera();
	private GameObject	        rootObject	        = new GameObject();
	private WindowConfiguration	windowConfiguration	= null;
	private String	            windownTitle	    = "DevGenSoft J|Engine 0.0.1";
	private boolean	            init	            = false;
	private boolean	            handleRootUpdate	= true;

	public void init(String windowTitle, WindowConfiguration configuration) {
		this.setWindowConfiguration(configuration);
		this.setWindownTitle(windowTitle);
		this.setInit(true);
		//this.getRootObject().addComponent(camera);
	}

	public abstract void postInit();
	
	private boolean createWindow() throws Exception {
		if (!this.isInit()) {
			throw new Exception("You need to init first the engine");
		}
		try {
			Display.setDisplayMode(new DisplayMode(this.getWindowConfiguration().getSize().getX(), this.getWindowConfiguration().getSize().getY()));
		} catch (LWJGLException e1) {
			// e1.printStackTrace();
			System.err.println("Failed to set display size : " + '\n' + e1.getMessage());
		}
		Display.setTitle(this.getWindownTitle());
		try {
			Display.setFullscreen(this.getWindowConfiguration().isFullscreeen());
		} catch (LWJGLException e) {
			// e.printStackTrace();
			System.err.println("Failed to enter fullscreen mode.");
		}
		Display.setVSyncEnabled(this.getWindowConfiguration().isVsync());
		try {
			Display.create(new PixelFormat(8, 8, 0, this.getWindowConfiguration().getMultiSampling()));
		} catch (LWJGLException e) {
			System.err.println("Failed to create Window : " + '\n' + e.getMessage());
			System.exit(1);
		}
		/* OpenGL init */
		glEnable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		GLU.gluPerspective(90.0F, (float) this.getWindowConfiguration().getSize().getX() / this.getWindowConfiguration().getSize().getY(), 0.001F, 1000000.0F);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glClearColor(0.1f, 0.1f, 0.1f, 1f);
		//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//glEnable(GL_BLEND);
		//glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
		//glEnable(GL_LINE_SMOOTH);
		//glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);
		//glEnable(GL_POLYGON_SMOOTH);
		//glEnable(GL_CULL_FACE);
		return false;
	}

	/**
	 * @return the windownTitle
	 */
	public String getWindownTitle() {
		return windownTitle;
	}

	/**
	 * @param windownTitle
	 *            the windownTitle to set
	 */
	public void setWindownTitle(String windownTitle) {
		this.windownTitle = windownTitle;
	}

	/**
	 * @return the windowConfiguration
	 */
	public WindowConfiguration getWindowConfiguration() {
		return windowConfiguration;
	}

	/**
	 * @param windowConfiguration
	 *            the windowConfiguration to set
	 */
	public void setWindowConfiguration(WindowConfiguration windowConfiguration) {
		this.windowConfiguration = windowConfiguration;
	}

	/**
	 * @return the init
	 */
	private boolean isInit() {
		return init;
	}

	/**
	 * @param init
	 *            the init to set
	 */
	private void setInit(boolean init) {
		this.init = init;
	}

	/**
	 * @return the rootObject
	 */
	public GameObject getRootObject() {
		return rootObject;
	}

	/**
	 * @param rootObject
	 *            the rootObject to set
	 */
	public void setRootObject(GameObject rootObject) {
		this.rootObject = rootObject;
	}
	
	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * @param camera
	 *            the camera to set
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**
	 * @return the handleRootUpdate
	 */
	public boolean isHandleRootUpdate() {
		return handleRootUpdate;
	}

	/**
	 * @param handleRootUpdate
	 *            the handleRootUpdate to set
	 */
	public void setHandleRootUpdate(boolean handleRootUpdate) {
		this.handleRootUpdate = handleRootUpdate;
	}

	public void updateDisplay() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		this.getCamera().update();
		this.render();
	}

	public abstract void render();

	public abstract void update();

	public abstract void input();

	public void start() {
		try {
			this.createWindow();
			try {
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
			this.postInit();
			while (!Display.isCloseRequested()) {
				Display.update();
				
				this.getCamera().input();
				this.input();
				if(this.isHandleRootUpdate())
					this.getRootObject().input();
				
				this.update();
				if(this.isHandleRootUpdate())
					this.getRootObject().update();
				
				this.updateDisplay();
				if(this.isHandleRootUpdate())
					this.getRootObject().render();

				
				if (!this.getWindowConfiguration().isVsync()) {
					Display.sync(this.getWindowConfiguration().getFps());
				}
			}
			
			Display.destroy();
			System.exit(0);
		} catch (Exception e1) {
			e1.printStackTrace();
			Display.destroy();
			Keyboard.destroy();
			Mouse.destroy();
			System.exit(1);
		}
	}
}
