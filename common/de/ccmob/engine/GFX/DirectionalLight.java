package de.ccmob.engine.GFX;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class DirectionalLight extends Light {

	private Vector3f	direction;

	public DirectionalLight(int intensity, Vector3f pos, Vector3f direction) {
		super(intensity, pos);
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}

}
