package net.ccmob.engine.types;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Transform {

	private Vector3f	position;
	private Rotation	rotation;

	public Transform(Vector3f position, Rotation rotation) {
		this.setPosition(position);
		this.setRotation(rotation);
	}

	public Transform() {
		this.setPosition(new Vector3f());
		this.setRotation(new Rotation());
	}

	/**
	 * @return the position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * @param position
	 *          the position to set
	 */
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	/**
	 * @return the rotation
	 */
	public Rotation getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 *          the rotation to set
	 */
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	public void translate() {
		GL11.glRotatef(this.getRotation().getAngelX(), 1, 0, 0);
		GL11.glRotatef(this.getRotation().getAngelY(), 0, 1, 0);
		GL11.glRotatef(this.getRotation().getAngelZ(), 0, 0, 1);
		GL11.glTranslatef(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
	}

}
