package de.ccmob.engine.CoreFunctions;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import de.ccmob.engine.GFX.Light;
import de.ccmob.engine.GFX.Shader;
import de.ccmob.engine.Types.Models.ModelQuad;
import de.ccmob.engine.Types.Models.PointCluster;
import de.ccmob.engine.Types.Player.Player;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Game extends MobEngine {

	Player	     player	= new Player();
	ModelQuad	   plane;
	PointCluster	cluster;
	Texture	     t;
	Light	       light;
	Shader	     shader;

	public Game() {
		super(1280, 720, true, false);
		System.out.println("Super constructor set");
		this.setPlayer(player);
		System.out.println("Player set");
	}

	@Override
	public void init() {
		super.init();
		plane = new ModelQuad("models/box_c4d.obj");
		// shader = new Shader();
		// shader.addVertexShader("shader.vs");
		// shader.addFragmentShader("shader.fs");
		cluster = new PointCluster();
		try {
			Keyboard.create();
			Mouse.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		super.update();
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			while (Keyboard.isKeyDown(Keyboard.KEY_R)) {
				Display.processMessages();
			}
			cluster.recreate();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			if (cluster.points_amount < 5000000) {
				cluster.points_amount += cluster.pol;
				cluster.recreate();
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			if (cluster.points_amount > (100 + cluster.pol)) {
				cluster.points_amount -= cluster.pol;
				cluster.recreate();
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			while (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				Display.update();
				Display.sync(this.getFps());
			}
			if (cluster.pol >= 5) {
				cluster.pol -= 1;
			}
			cluster.recreate();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			while (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				Display.update();
				Display.sync(this.getFps());
			}
			if (cluster.pol <= 50) {
				cluster.pol += 1;
			}
			cluster.recreate();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
			while (Keyboard.isKeyDown(Keyboard.KEY_F)) {
				Display.update();
				Display.sync(this.getFps());
			}
			// plane = new ModelQuad("res/models/box.obj");
		}
	}

	@Override
	public void render() {
		// shader.bind();
		super.render();
		// shader.bind();
		plane.renderImmediate();
		cluster.render();
	}

	@Override
	public void renderHud() {
		// super.renderHud();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
