package de.ccmob.engine.CoreFunctions;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import de.ccmob.engine.Types.Player.Player;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class MobEngine {

	private int	    SCREEN_WIDTH	= 1280;
	private int	    SCREEN_HEIGHT	= 720;
	private boolean	vsync	        = true;
	private int	    fps	          = 60;
	private String	title	        = "MobEngine - Test";
	private Input	  input	        = new Input();
	private Player	player;
	private boolean	fullscreen	  = false;

	public MobEngine(int width, int height, int fps, boolean fullscreen) {
		this.setTitle(title);
		this.setVsync(false);
		this.setFps(fps);
		this.setScreenHeight(width);
		this.setScreenHeight(height);
		this.getInput().setEngine(this);
		this.setFullscreen(fullscreen);
	}

	public MobEngine(int width, int height, boolean vsync, boolean fullscreen) {
		this.setTitle(title);
		this.setVsync(true);
		this.setFps(60);
		this.setScreenWidth(width);
		this.setScreenHeight(height);
		this.getInput().setEngine(this);
		this.setFullscreen(fullscreen);
	}

	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
			Display.setTitle(title);
			Display.setVSyncEnabled(vsync);
			Display.setFullscreen(this.isFullscreen());
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(90.0F, (float) this.getScreenWidth() / this.getScreenHeight(), 0.1F, 10000.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 1.0f, 0.5f);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_FASTEST);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_FASTEST);
		GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
	}

	public void update() {
		this.getInput().update();
	}

	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		GL11.glRotatef(input.getAngelX(), 1, 0, 0);
		GL11.glRotatef(input.getAngelY(), 0, 1, 0);
		GL11.glTranslatef(this.getPlayer().getX() * -1, this.getPlayer().getZ() * -1, this.getPlayer().getY() * -1);
		// this.getPlayer().getModel().render();
	}

	public void renderHud() {

	}

	public int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	public void setScreenWidth(int ScreenWidth) {
		SCREEN_WIDTH = ScreenWidth;
	}

	public int getScreenHeight() {
		return SCREEN_HEIGHT;
	}

	public void setScreenHeight(int ScreenHeight) {
		SCREEN_HEIGHT = ScreenHeight;
	}

	public boolean isVsync() {
		return vsync;
	}

	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void start() {
		new Core(this);
	}

}
