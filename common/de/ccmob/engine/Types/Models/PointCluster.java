package de.ccmob.engine.Types.Models;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.awt.Font;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.TrueTypeFont;

import de.ccmob.engine.Types.Blocks.Point;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class PointCluster {

	public int	     points_amount	= 100;
	ArrayList<Point>	points	     = new ArrayList<Point>();
	int	             handle	       = 0;
	public int	     pol	         = 5;
	Font	           awtFont;
	TrueTypeFont	   font;

	public PointCluster() {
		awtFont = new Font("Arial", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		FloatBuffer b = BufferUtils.createFloatBuffer(points_amount * pol);
		Random r = new Random();
		for (int i = 0; i < points_amount; i++) {
			points.add(new Point());
			b.put(r.nextFloat() * 5).put(r.nextFloat() * 5).put(r.nextFloat() * 5);
		}
		b.flip();
		handle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, handle);
		glBufferData(GL_ARRAY_BUFFER, b, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void recreate() {
		FloatBuffer b = BufferUtils.createFloatBuffer(points_amount * pol);
		Random r = new Random();
		for (int i = 0; i < points_amount; i++) {
			points.add(new Point());
			b.put(r.nextFloat() * 5).put(r.nextFloat() * 5).put(r.nextFloat() * 5);
		}
		b.flip();
		glBindBuffer(GL_ARRAY_BUFFER, handle);
		glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(1).put(0), GL_STATIC_DRAW);
		glBufferData(GL_ARRAY_BUFFER, b, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void render() {
		glEnableClientState(GL_VERTEX_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, handle);
		glVertexPointer(3, GL_FLOAT, 0, 0L);
		// glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glDrawArrays(GL_POLYGON, pol, points.size());

		glDisableClientState(GL_VERTEX_ARRAY);
		/*
		 * for(Point p : points){ p.render(); }
		 */
	}

	public void showInfo() {
		glDisable(GL_DEPTH_TEST);
		font.drawString(0, 0, "Points : " + points_amount);
		glEnable(GL_DEPTH_TEST);
	}

}
