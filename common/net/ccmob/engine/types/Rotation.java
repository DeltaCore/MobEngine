package net.ccmob.engine.types;

import org.lwjgl.util.vector.Vector3f;

public class Rotation {

	private Vector3f	rotation	= new Vector3f();

	public Rotation(float xAngel, float yAngel, float zAngel) {
		this.getRotation().setX(xAngel);
		this.getRotation().setY(yAngel);
		this.getRotation().setZ(zAngel);
	}

	public Rotation() {
		this.getRotation().setX(0);
		this.getRotation().setY(0);
		this.getRotation().setZ(0);
	}

	/**
	 * @return the rotation
	 */
	private Vector3f getRotation() {
		return rotation;
	}

	/**
	 * @return the xAngel
	 */
	public float getAngelX() {
		return this.getRotation().getX();
	}

	/**
	 * @param xAngel
	 *          the xAngel to set
	 */
	public void setAngelX(float xAngel) {
		this.getRotation().setX(xAngel);
	}

	/**
	 * @return the yAngel
	 */
	public float getAngelY() {
		return this.getRotation().getY();
	}

	/**
	 * @param yAngel
	 *          the yAngel to set
	 */
	public void setAngelY(float yAngel) {
		this.getRotation().setY(yAngel);
	}

	/**
	 * @return the zAngel
	 */
	public float getAngelZ() {
		return this.getRotation().getZ();
	}

	/**
	 * @param zAngel
	 *          the zAngel to set
	 */
	public void setAngelZ(float zAngel) {
		this.getRotation().setZ(zAngel);
	}

	public void addAngelX(float add) {
		this.setAngelX(getAngelX() + add);
	}

	public void addAngelY(float add) {
		this.setAngelY(getAngelY() + add);
	}

	public void addAngelZ(float add) {
		this.setAngelZ(getAngelZ() + add);
	}

	public void subAngelX(float add) {
		this.setAngelX(getAngelX() - add);
	}

	public void subAngelY(float add) {
		this.setAngelY(getAngelY() - add);
	}

	public void subAngelZ(float add) {
		this.setAngelZ(getAngelZ() - add);
	}

}
