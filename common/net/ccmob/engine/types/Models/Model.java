package net.ccmob.engine.types.Models;


import java.nio.FloatBuffer;
import java.util.ArrayList;

import net.ccmob.engine.types.GameObject;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


/**
 * 
 * @author Marcel Benning
 * 
 */

public class Model extends GameObject {

	ArrayList<Vector3f>	vertecies	  = new ArrayList<Vector3f>();
	ArrayList<Vector2f>	textureCoords	= new ArrayList<Vector2f>();
	ArrayList<Vector3f>	normals	      = new ArrayList<Vector3f>();
	ArrayList<SubModel>	subModels	  = new ArrayList<SubModel>();
	ArrayList<String>	subModelNames	= new ArrayList<String>();
	boolean	            vbo	          = false;
	int	                vboHandle	  = 0;
	int	                normalHandle	= 0;
	int	                textureHandle	= 0;

	private MaterialLib	materials	  = new MaterialLib();

	public Model(String path) {
		new ModelLoader(this, path);
	}

	public void makeVBO() {

		FloatBuffer vertexData = BufferUtils.createFloatBuffer(this.getVertecies().size() * 3);
		FloatBuffer normalData = BufferUtils.createFloatBuffer(this.getNormals().size() * 3);
		//FloatBuffer textureData = BufferUtils.createFloatBuffer(this.getTextureCoords().size() * 2);
		System.out.println("Converting " + this.getSubModels().get(0).getName() + " into vertex buffer");
		for (SubModel s : this.subModels) {
			
			for (Face f : s.getFaces()) {
				// glColor3f(1, 1, 1);
				if (f.is4f()) {
					FaceIndex index = f.getIndecies().get(0);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}

					index = f.getIndecies().get(1);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}

					index = f.getIndecies().get(2);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}

					index = f.getIndecies().get(0);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}

					index = f.getIndecies().get(2);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}
					
					index = f.getIndecies().get(3);
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
					vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
					if (index.hasNormals())
					{
						normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
						normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
					}
				} else {
					FaceIndex index = null;
					for (int i = 0; i < f.getIndecies().size(); i++) {
						index = f.getIndecies().get(i);
						vertexData.put(this.getVertecies().get(index.getVertexIndex()).getX());
						vertexData.put(this.getVertecies().get(index.getVertexIndex()).getY());
						vertexData.put(this.getVertecies().get(index.getVertexIndex()).getZ());
						if (index.hasNormals())
						{
							normalData.put(this.getNormals().get(index.getNormalIndex()).getX());
							normalData.put(this.getNormals().get(index.getNormalIndex()).getY());
							normalData.put(this.getNormals().get(index.getNormalIndex()).getZ());
						}

					}
				}

			}

		}
		vertexData.flip();
		normalData.flip();
		vboHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		normalHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, normalHandle);
		glBufferData(GL_ARRAY_BUFFER, normalData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		System.out.println("Done.");
		this.vbo = true;
	}

	public void cleanUp(){
		if(vbo){
			glDeleteBuffers(vboHandle);
			glDeleteBuffers(normalHandle);
		}
	}
	
	@Override
	public void render() {
		if (vbo) {
			glBindBuffer(GL_ARRAY_BUFFER, vboHandle);
			glVertexPointer(3, GL_FLOAT, 0, 0L);			
			glBindBuffer(GL_ARRAY_BUFFER, normalHandle);
			glNormalPointer(3, GL_FLOAT, 0);
			glEnableClientState(GL_VERTEX_ARRAY);
			glEnableClientState(GL_NORMAL_ARRAY);
			glDrawArrays(GL_TRIANGLES, 0, 3);
			glDisableClientState(GL_VERTEX_ARRAY);
			glDisableClientState(GL_NORMAL_ARRAY);
		} else {
			for (SubModel m : this.subModels)
				m.render();
		}
		super.render();
	}

	@Override
	public void input() {
		super.input();
	}

	@Override
	public void update() {
		super.update();
	}

	public void addSubModel(SubModel m, String name) {
		this.getSubModels().add(m);
		this.subModelNames.add(name);
	}

	public boolean contains(String subModelName) {
		return this.subModelNames.contains(subModelName);
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
	public ArrayList<Vector2f> getTextureCoords() {
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
	 * @param vertecies
	 *            the vertecies to set
	 */
	public void setVertecies(ArrayList<Vector3f> vertecies) {
		this.vertecies = vertecies;
	}

	/**
	 * @param textureCoords
	 *            the textureCoords to set
	 */
	public void setTextureCoords(ArrayList<Vector2f> textureCoords) {
		this.textureCoords = textureCoords;
	}

	/**
	 * @param normals
	 *            the normals to set
	 */
	public void setNormals(ArrayList<Vector3f> normals) {
		this.normals = normals;
	}

	/**
	 * @param subModels
	 *            the subModels to set
	 */
	public void setSubModels(ArrayList<SubModel> subModels) {
		this.subModels = subModels;
	}

	/**
	 * @return the materials
	 */
	public MaterialLib getMaterials() {
		return materials;
	}

	/**
	 * @param materials
	 *            the materials to set
	 */
	public void setMaterials(MaterialLib materials) {
		this.materials = materials;
	}

}
