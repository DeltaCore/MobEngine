package de.ccmob.engine.Types.Player;

import de.ccmob.engine.Types.Models.Model;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Player {

	private float	x	         = 0.0f, y = 0.0f, z = 0.0f;
	private float	width	     = 1.0f;
	private float	height	   = 2.0f;
	private float	headHeight	= 1.7f;
	private Model	model;

	public Player() {

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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHeadHeight() {
		return headHeight;
	}

	public void setHeadHeight(float headHeight) {
		this.headHeight = headHeight;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
