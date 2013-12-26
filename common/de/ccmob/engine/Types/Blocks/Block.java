package de.ccmob.engine.Types.Blocks;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.ccmob.engine.Types.Models.Model;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Block {

	Model	  model	= Model.getModel("res/models/cube.obj");
	Texture	texture;

	private int	x	= 0, y = 0, z = 0;

	public Block(String texturepath, int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturepath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public void render() {
		model.renderImmediateAt(this.getX(), this.getY(), this.getZ());
	}

}
