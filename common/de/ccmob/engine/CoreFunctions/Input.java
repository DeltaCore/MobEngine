package de.ccmob.engine.CoreFunctions;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Input {

	private float	    x	          = 0, y = 0, z = 0.5f, angelX = 0, angelY = 0;
	private float	    mouse_scale	= 0.2f;
	private float	    speed	      = 0.1f;
	private MobEngine	engine;

	public void update() {
		if (Mouse.isGrabbed()) {
			angelX -= Mouse.getDY() * mouse_scale;
			angelY += Mouse.getDX() * mouse_scale;
		}
		if (!Mouse.isGrabbed() && Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setGrabbed(false);
		}
		if (angelX >= 90.0f) {
			angelX = 90.0F;
		}
		if (angelX <= -90.0f) {
			angelX = -90.0F;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && speed == 0.1f) {
			speed = 1f;
		} else {
			speed = 0.1f;
		}

		if (Mouse.isButtonDown(1)) {
			speed = 0.01f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.getEngine().getPlayer().setX((float) (this.getEngine().getPlayer().getX() + Math.sin(Math.toRadians(angelY)) * speed));
			this.getEngine().getPlayer().setY((float) (this.getEngine().getPlayer().getY() - Math.cos(Math.toRadians(angelY)) * speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.getEngine().getPlayer().setX((float) (this.getEngine().getPlayer().getX() + Math.sin(Math.toRadians(angelY + 180f)) * speed));
			this.getEngine().getPlayer().setY((float) (this.getEngine().getPlayer().getY() - Math.cos(Math.toRadians(angelY + 180f)) * speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.getEngine().getPlayer().setX((float) (this.getEngine().getPlayer().getX() + Math.sin(Math.toRadians(angelY + -90f)) * speed));
			this.getEngine().getPlayer().setY((float) (this.getEngine().getPlayer().getY() - Math.cos(Math.toRadians(angelY + -90f)) * speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.getEngine().getPlayer().setX((float) (this.getEngine().getPlayer().getX() + Math.sin(Math.toRadians(angelY + 90f)) * speed));
			this.getEngine().getPlayer().setY((float) (this.getEngine().getPlayer().getY() - Math.cos(Math.toRadians(angelY + 90f)) * speed));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			this.getEngine().getPlayer().setZ(this.getEngine().getPlayer().getZ() - speed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			this.getEngine().getPlayer().setZ(this.getEngine().getPlayer().getZ() + speed);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
		}
	}

	public float getAngelX() {
		return angelX;
	}

	public void setAngelX(float angelX) {
		this.angelX = angelX;
	}

	public float getAngelY() {
		return angelY;
	}

	public void setAngelY(float angelY) {
		this.angelY = angelY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getMouse_scale() {
		return mouse_scale;
	}

	public void setMouse_scale(float mouse_scale) {
		this.mouse_scale = mouse_scale;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public MobEngine getEngine() {
		return engine;
	}

	public void setEngine(MobEngine engine) {
		this.engine = engine;
	}
}
