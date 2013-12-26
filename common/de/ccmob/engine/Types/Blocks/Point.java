package de.ccmob.engine.Types.Blocks;

import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.Random;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Point {

	float	 x	= 0, y = 0, z = 0;
	Random	r	= new Random();

	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point() {

		x = r.nextFloat();
		y = r.nextFloat();
		z = r.nextFloat() * 20;
	}

	public void render() {
		glBegin(GL_POINTS);
		glColor3f(r.nextFloat(), r.nextFloat(), r.nextFloat());
		glVertex3f(x, y, z);
		glEnd();
	}

}
