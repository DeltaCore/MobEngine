package net.ccmob.engine.types.Models.Materials;

import java.io.InputStream;

import net.ccmob.engine.core.MobEngine;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Material {

	private Texture	texture	    = null;
	private String	texturePath	= "";
	private String	name	      = "";

	public Material(String name) {
		this.setName(name);
	}

	public Material(String name, String path) {
		this.setName(name);
		try {
			Class<MobEngine> cls = MobEngine.class;
			ClassLoader cLoader = cls.getClassLoader();
			InputStream i = cLoader.getResourceAsStream("textures/" + path);
			this.setTexture(TextureLoader.getTexture("PNG", i));
		} catch (Exception e) {
			System.err.println("Could't find Texture : " + path + ". I got this Exception : " + e.getMessage());
		}
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(String path) {
		this.setTexturePath(path);
		try {
			System.out.println("Texture path : " + path);
			Class<MobEngine> cls = MobEngine.class;
			ClassLoader cLoader = cls.getClassLoader();
			InputStream i = cLoader.getResourceAsStream("textures/" + path);
			this.setTexture(TextureLoader.getTexture("PNG", i));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

	public void bindTexture() {
		if (this.getTexture() != null) {
			texture.bind();
		}
	}

	public void unbindTexture() {
		if (this.getTexture() != null) {
			texture.release();
		}
	}

}
