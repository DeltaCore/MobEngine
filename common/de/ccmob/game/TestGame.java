package de.ccmob.game;

import net.ccmob.engine.core.MobEngine;
import net.ccmob.engine.types.Dimension;
import net.ccmob.engine.types.WindowConfiguration;
import net.ccmob.engine.types.Models.ObjModel;
import net.ccmob.engine.types.Models.Loader.ObjLoader;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class TestGame extends MobEngine {

	public static TestGame	instance;
	Texture	               t;
	ObjLoader	             modelLoader	       = new ObjLoader();
	ObjModel	             level;

	WindowConfiguration	   windowConfiguration	= new WindowConfiguration(new Dimension(800, 600), 180);

	public TestGame() {
		windowConfiguration.enableAntiAlising();
		this.init("Test", windowConfiguration);
		this.setHandleRootUpdate(true);
		level = new ObjModel("house.obj");
		level.getTransform().setPosition(new Vector3f(0, 0, -12));
		level.debug = true;
		this.getRootObject().addChild(level);
	}

	@Override
	public void render() {

	}

	public static void main(String[] args) {
		instance = new TestGame();
		instance.start();
	}

	@Override
	public void update() {

	}

	@Override
	public void input() {
	}

	@Override
	public void postInit() {

	}

}
