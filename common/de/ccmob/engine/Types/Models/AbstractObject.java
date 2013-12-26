package de.ccmob.engine.Types.Models;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord3f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import de.ccmob.engine.Types.Models.Materials.Material;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class AbstractObject {

	private String	         name	        = "";

	protected List<Vector3f>	vertecies	  = new ArrayList<Vector3f>();
	protected List<Vector3f>	normals	    = new ArrayList<Vector3f>();
	protected List<Vector3f>	textures	  = new ArrayList<Vector3f>();
	protected List<Face>	   face	        = new ArrayList<Face>();
	private boolean	         BNormals	    = false;
	private boolean	         BTextures	  = false;
	protected boolean	       renderFlag	  = false;
	protected Vector3f	     vec	        = new Vector3f();
	private String	         materialName	= "";
	private Material	       material	    = null;

	public AbstractObject(String name) {
		this.setName(name);
	}

	public AbstractObject(String name, ArrayList<Vector3f> verts) {
		this.setName(name);
		this.setVertecies(verts);
	}

	public AbstractObject(String name, ArrayList<Vector3f> verts, List<Vector3f> textscoords) {
		this.setName(name);
		this.setVertecies(verts);
		this.setTextures(textscoords);
		this.setTextures(true);
	}

	public AbstractObject(String name, ArrayList<Vector3f> verts, ArrayList<Vector3f> norms) {
		this.setName(name);
		this.setVertecies(verts);
		this.setNormals(norms);
		this.setNormals(true);
	}

	public AbstractObject(String name, ArrayList<Vector3f> verts, ArrayList<Vector3f> norms, List<Vector3f> textscoords) {
		this.setVertecies(verts);
		this.setNormals(norms);
		this.setTextures(textscoords);
		this.setNormals(true);
		this.setTextures(true);
	}

	public List<Vector3f> getVertecies() {
		return vertecies;
	}

	public void setVertecies(List<Vector3f> vertecies) {
		this.vertecies = vertecies;
	}

	public List<Vector3f> getNormals() {
		return normals;
	}

	public void setNormals(List<Vector3f> normals) {
		this.normals = normals;
	}

	public List<Vector3f> getTextures() {
		return textures;
	}

	public void setTextures(List<Vector3f> textures) {
		this.textures = textures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Face> getFaces() {
		return face;
	}

	public void setFace(List<Face> face) {
		this.face = face;
	}

	public boolean hasNormals() {
		return BNormals;
	}

	private void setNormals(boolean bNormals) {
		BNormals = bNormals;
	}

	public boolean hasTextures() {
		return BTextures;
	}

	private void setTextures(boolean bTextures) {
		BTextures = bTextures;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public void renderImmediate() {
		this.bindMaterial();
		for (Face f : this.getFaces()) {
			if (f.is4f()) {
				glBegin(GL_QUADS);
				vec = vertecies.get((int) (f.getVertex4f().x - 1));
				glTexCoord3f(textures.get((int) f.getTexture4f().x - 1).x, textures.get((int) f.getTexture4f().x - 1).y, textures.get((int) f.getTexture4f().x - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().y - 1));
				glTexCoord3f(textures.get((int) f.getTexture4f().y - 1).x, textures.get((int) f.getTexture4f().y - 1).y, textures.get((int) f.getTexture4f().y - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().z - 1));
				glTexCoord3f(textures.get((int) f.getTexture4f().z - 1).x, textures.get((int) f.getTexture4f().z - 1).y, textures.get((int) f.getTexture4f().z - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().w - 1));
				glTexCoord3f(textures.get((int) f.getTexture4f().w - 1).x, textures.get((int) f.getTexture4f().w - 1).y, textures.get((int) f.getTexture4f().w - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
			} else {
				glBegin(GL_TRIANGLES);
				vec = vertecies.get((int) (f.getVertex().x - 1));
				glTexCoord3f(textures.get((int) f.getTexture().x - 1).x, textures.get((int) f.getTexture().x - 1).y, textures.get((int) f.getTexture().x - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex().y - 1));
				glTexCoord3f(textures.get((int) f.getTexture().y - 1).x, textures.get((int) f.getTexture().y - 1).y, textures.get((int) f.getTexture().y - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex().z - 1));
				glTexCoord3f(textures.get((int) f.getTexture().z - 1).x, textures.get((int) f.getTexture().z - 1).y, textures.get((int) f.getTexture().z - 1).z);
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		glEnd();
	}

	public void bindMaterial() {
		if (this.getMaterial() != null) {
			this.getMaterial().bindTexture();
		}
	}

	public void unbindMaterial() {
		if (this.getMaterial() != null) {
			this.getMaterial().unbindTexture();
		}
	}

}
