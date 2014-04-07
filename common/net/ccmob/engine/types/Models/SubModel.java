package net.ccmob.engine.types.Models;


import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;

import net.ccmob.engine.types.GameObject;


public class SubModel extends GameObject implements Cloneable {

	private Model	        parent;
	private ArrayList<Face>	faces	     = new ArrayList<Face>();
	private String	        materialName	= "";
	private String	        name	     = "";

	public SubModel(Model m) {
		this.parent = m;
	}

	@Override
	public void render() {
		this.parent.getMaterials().getMaterial(getMaterialName()).bindTexture();
		for (Face f : faces) {
			glColor3f(1, 1, 1);
			if (f.is4f()) {
				glBegin(GL_QUADS);
			} else {
				glBegin(GL_TRIANGLES);
			}

			FaceIndex index = null;
			for (int i = 0; i < f.getIndecies().size(); i++) {
				index = f.getIndecies().get(i);
				glVertex3f(parent.getVertecies().get(index.getVertexIndex()).getX(), parent.getVertecies().get(index.getVertexIndex()).getY(),
				        parent.getVertecies().get(index.getVertexIndex()).getZ());
				if (index.hasNormals())
					glNormal3f(parent.getNormals().get(index.getNormalIndex()).getX(), parent.getNormals().get(index.getNormalIndex()).getY(),
					        parent.getNormals().get(index.getNormalIndex()).getZ());

				if (index.hasTexturCoords())
					glTexCoord2f(parent.getTextureCoords().get(index.getTextureIndex()).getX(), parent.getTextureCoords().get(index.getTextureIndex()).getY());
			}
			glEnd();

			glColor3f(0, 0, 0);
			/*glBegin(GL_LINES);
			index = null;
			for (int i = 0; i < f.getIndecies().size(); i++) {
				index = f.getIndecies().get(i);
				glVertex3f(parent.getVertecies().get(index.getVertexIndex()).getX(), parent.getVertecies().get(index.getVertexIndex()).getY(),
				        parent.getVertecies().get(index.getVertexIndex()).getZ());
				if (index.hasNormals())
					glNormal3f(parent.getNormals().get(index.getNormalIndex()).getX(), parent.getNormals().get(index.getNormalIndex()).getY(),
					        parent.getNormals().get(index.getNormalIndex()).getZ());

				if (index.hasTexturCoords())
					glTexCoord2f(parent.getTextureCoords().get(index.getTextureIndex()).getX(), parent.getTextureCoords().get(index.getTextureIndex()).getY());
			}
			index = f.getIndecies().get(0);
			glVertex3f(parent.getVertecies().get(index.getVertexIndex()).getX(), parent.getVertecies().get(index.getVertexIndex()).getY(),
			        parent.getVertecies().get(index.getVertexIndex()).getZ());
			if (index.hasNormals())
				glNormal3f(parent.getNormals().get(index.getNormalIndex()).getX(), parent.getNormals().get(index.getNormalIndex()).getY(),
				        parent.getNormals().get(index.getNormalIndex()).getZ());

			if (index.hasTexturCoords())
				glTexCoord2f(parent.getTextureCoords().get(index.getTextureIndex()).getX(), parent.getTextureCoords().get(index.getTextureIndex()).getY());
			glEnd();*/
		}

		super.render();
	}

	@Override
	protected SubModel clone() throws CloneNotSupportedException {
		SubModel model = new SubModel(parent);
		model.faces = new ArrayList<Face>();
		for (Face f : this.faces) {
			model.faces.add(f.clone());
		}
		model.setMaterialName(materialName);
		model.setName(name);
		return model;
	}

	/**
	 * @return the faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	/**
	 * @param faces
	 *            the faces to sets a
	 */
	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}

	/**
	 * @return the materialName
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * @param materialName
	 *            the materialName to set
	 */
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
