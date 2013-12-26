package de.ccmob.engine.GFX;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class SpotLight extends Light {

	private float	range	= 1.0f;

	public SpotLight(int intensity, Vector3f pos, float circleRange) {
		super(intensity, pos);
		this.setRange(circleRange);
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

}
