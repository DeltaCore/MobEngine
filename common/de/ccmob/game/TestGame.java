package de.ccmob.game;

import net.ccmob.engine.core.MobEngine;
import net.ccmob.engine.types.Dimension;
import net.ccmob.engine.types.WindowConfiguration;
import net.ccmob.engine.types.Models.Model;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class TestGame extends MobEngine {

	public static TestGame	instance;
	Texture	               t;
	Model level;
	Model monkey6;
	Model monkey5;
	Model monkey4;
	Model monkey3;
	Model monkey2;
	
	WindowConfiguration	   windowConfiguration	= new WindowConfiguration(new Dimension(800, 600), 180);

	public TestGame() {
		windowConfiguration.enableAntiAlising();
		this.init("Test", windowConfiguration);
		this.setHandleRootUpdate(true);
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
	public void cleanup() {
	    monkey2.cleanUp();
	}
	
	@Override
	public void postInit() {
		level = new Model("test_house.obj");
		monkey2 = new Model("monkey2.obj");
		//monkey3 = new Model("monkey3.obj");
		//monkey4 = new Model("monkey4.obj");
		//monkey5 = new Model("monkey5.obj");
		//monkey6 = new Model("monkey6.obj");
		
		//level.getTransform().setPosition(new Vector3f(0, 0, -12));
		this.getRootObject().addChild(level);
		monkey2.makeVBO();
		level.addChild(monkey2);	
	}

}
