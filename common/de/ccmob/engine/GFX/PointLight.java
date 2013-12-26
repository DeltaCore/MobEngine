package de.ccmob.engine.GFX;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class PointLight extends Light {

	private float	range	= 1.0f;

	public PointLight(int intensity, Vector3f pos) {
		super(intensity, pos);
	}

	public PointLight(int intensity, Vector3f pos, float range) {
		super(intensity, pos);
		this.setRange(range);
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

}
