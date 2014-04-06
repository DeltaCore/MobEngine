package net.ccmob.engine.types.Models;

import java.util.ArrayList;
import java.util.List;

import net.ccmob.engine.types.GameObject;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public abstract class AbstractModel extends GameObject{

	private String	             texturePath	= "";
	private String	             modelPath	 = "";
	private List<AbstractObject>	objects	   = new ArrayList<AbstractObject>();
	private boolean	             renderFlag	 = false;
	private Vector3f	           vec;

	AbstractModel(String modelPath) {
		this.setModelPath(modelPath);
	}

	AbstractModel(String modelPath, String texturePath) {
		this.setModelPath(modelPath);
		this.setTexturePath(texturePath);
	}

	abstract void bindTexture();

	abstract void unbindTexture();

	abstract public void init();

	abstract public void renderVBO();

	@Override
	public void render() {
		{
			for (AbstractObject obj : objects) {
				obj.renderImmediate();
			}
		}
		super.render();
	}

	abstract public void renderImmediateBlank();

	abstract public void renderImmediateAt(int x, int y, int z);

	abstract public void renderImmediateAtBlank(int x, int y, int z);

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public List<AbstractObject> getObjects() {
		return objects;
	}

	public void setObjects(List<AbstractObject> objects) {
		this.objects = objects;
	}

}
