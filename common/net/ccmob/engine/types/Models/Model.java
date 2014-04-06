package net.ccmob.engine.types.Models;

import java.util.ArrayList;

import net.ccmob.engine.types.GameObject;

import org.lwjgl.util.vector.Vector3f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Model extends GameObject {

	ArrayList<Vector3f>	 vertecies;
	ArrayList<Vector3f>	 textureCoords;
	ArrayList<Vector3f>	 normals;
	ArrayList<SubModel> subModels = new ArrayList<SubModel>();
	
	public Model(String path) {
		new ModelLoader(this, path);
	}
	
	@Override
	public void render() {
	    for(SubModel m : this.subModels)
	    	m.render();
	    super.render();
	}

	
    /**
     * @return the vertecies
     */
    public ArrayList<Vector3f> getVertecies() {
    	return vertecies;
    }

	
    /**
     * @return the textureCoords
     */
    public ArrayList<Vector3f> getTextureCoords() {
    	return textureCoords;
    }

	
    /**
     * @return the normals
     */
    public ArrayList<Vector3f> getNormals() {
    	return normals;
    }

	
    /**
     * @return the subModels
     */
    public ArrayList<SubModel> getSubModels() {
    	return subModels;
    }

	
    /**
     * @param vertecies the vertecies to set
     */
    public void setVertecies(ArrayList<Vector3f> vertecies) {
    	this.vertecies = vertecies;
    }

	
    /**
     * @param textureCoords the textureCoords to set
     */
    public void setTextureCoords(ArrayList<Vector3f> textureCoords) {
    	this.textureCoords = textureCoords;
    }

	
    /**
     * @param normals the normals to set
     */
    public void setNormals(ArrayList<Vector3f> normals) {
    	this.normals = normals;
    }

	
    /**
     * @param subModels the subModels to set
     */
    public void setSubModels(ArrayList<SubModel> subModels) {
    	this.subModels = subModels;
    }
	
}
