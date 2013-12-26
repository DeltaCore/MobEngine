package de.ccmob.engine.GFX;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Light {

	private int	     intensity	= 0;
	private Vector3f	pos;
	private Vector3f	color;

	public Light(int intensity, Vector3f pos) {
		this.setIntensity(intensity);
		this.setPos(pos);
		this.setColor(new Vector3f(1, 1, 1));
	}

	public Light(int intensity, Vector3f pos, Vector3f color) {
		this.setIntensity(intensity);
		this.setPos(pos);
		this.setColor(color);
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

}
