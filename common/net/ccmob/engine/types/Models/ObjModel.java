package net.ccmob.engine.types.Models;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.ArrayList;

import net.ccmob.engine.types.Models.Loader.ObjLoader;
import net.ccmob.engine.types.Models.Loader.obj.Face;
import net.ccmob.engine.types.Models.Loader.obj.FaceIndex;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjModel extends Model {

	private static ObjLoader	  modelLoader;

	private ArrayList<Face>	    faces	            = new ArrayList<Face>();
	private ArrayList<Vector3f>	vertecies	        = new ArrayList<Vector3f>();
	private ArrayList<Vector3f>	noramls	          = new ArrayList<Vector3f>();
	private ArrayList<Vector2f>	textureCodrinates	= new ArrayList<Vector2f>();
	private boolean	            isvbo	            = false;
	private int	                vboHandle	        = -1;
	public boolean	            debug	            = false;

	public ObjModel(String file) {
		if (modelLoader == null)
			modelLoader = new ObjLoader();
		modelLoader.loadModel(file, this);
	}

	public ObjModel() {

	}

	/**
	 * @return the faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}

	/**
	 * @param faces
	 *          the faces to set
	 */
	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
	}

	/**
	 * @return the vertecies
	 */
	public ArrayList<Vector3f> getVertecies() {
		return vertecies;
	}

	/**
	 * @param vertecies
	 *          the vertecies to set
	 */
	public void setVertecies(ArrayList<Vector3f> vertecies) {
		this.vertecies = vertecies;
	}

	/**
	 * @return the noramls
	 */
	public ArrayList<Vector3f> getNoramls() {
		return noramls;
	}

	/**
	 * @param noramls
	 *          the noramls to set
	 */
	public void setNoramls(ArrayList<Vector3f> noramls) {
		this.noramls = noramls;
	}

	/**
	 * @return the textureCodrinates
	 */
	public ArrayList<Vector2f> getTextureCodrinates() {
		return textureCodrinates;
	}

	/**
	 * @param textureCodrinates
	 *          the textureCodrinates to set
	 */
	public void setTextureCodrinates(ArrayList<Vector2f> textureCodrinates) {
		this.textureCodrinates = textureCodrinates;
	}

	public void convertToVBO() {
		this.isvbo = true;

		vboHandle = glGenLists(1);
		glNewList(vboHandle, GL_COMPILE);

		Face f = null;
		FaceIndex index = null;
		for (int i = 0; i < this.getFaces().size(); i++) {
			f = this.getFaces().get(i);
			glBegin(GL_TRIANGLES);
			for (int fi = 0; fi < f.getFacesIndecies().size(); fi++) {
				index = f.getFacesIndecies().get(fi);
				glVertex3f(this.getVertecies().get(index.getVertexIndex()).getX(), this.getVertecies().get(index.getVertexIndex()).getY(), this.getVertecies().get(index.getVertexIndex()).getZ());
				if (this.hasNormals())
					glNormal3f(this.getNoramls().get(index.getNormalIndex()).getX(), this.getNoramls().get(index.getNormalIndex()).getY(), this.getNoramls().get(index.getNormalIndex()).getZ());
				if (this.hasTexureCoords())
					glTexCoord2f(this.getTextureCodrinates().get(index.getTextureIndex()).getX(), this.getTextureCodrinates().get(index.getTextureIndex()).getY());
			}
			glEnd();

			glBegin(GL_LINES);
			for (int fi = 0; fi < f.getFacesIndecies().size(); fi++) {
				index = f.getFacesIndecies().get(fi);
				glVertex3f(this.getVertecies().get(index.getVertexIndex()).getX(), this.getVertecies().get(index.getVertexIndex()).getY(), this.getVertecies().get(index.getVertexIndex()).getZ());
				if (this.hasNormals())
					glNormal3f(this.getNoramls().get(index.getNormalIndex()).getX(), this.getNoramls().get(index.getNormalIndex()).getY(), this.getNoramls().get(index.getNormalIndex()).getZ());
				if (this.hasTexureCoords())
					glTexCoord2f(this.getTextureCodrinates().get(index.getTextureIndex()).getX(), this.getTextureCodrinates().get(index.getTextureIndex()).getY());

				if (fi == (f.getFacesIndecies().size() - 1)) {
					index = f.getFacesIndecies().get(0);
				}
			}
			glEnd();

		}
		glEndList();
	}

	@Override
	public void render() {
		if (!isvbo) {
			if (this.getFaces().size() > 0) {
				Face f = null;
				FaceIndex index = null;
				for (int i = 0; i < this.getFaces().size(); i++) {
					f = this.getFaces().get(i);
					glColor3f(1, 1, 1);
					glBegin(GL_TRIANGLES);
					for (int fi = 0; fi < f.getFacesIndecies().size(); fi++) {
						index = f.getFacesIndecies().get(fi);
						glVertex3f(this.getVertecies().get(index.getVertexIndex()).getX(), this.getVertecies().get(index.getVertexIndex()).getY(), this.getVertecies().get(index.getVertexIndex()).getZ());
						if (this.hasNormals())
							glNormal3f(this.getNoramls().get(index.getNormalIndex()).getX(), this.getNoramls().get(index.getNormalIndex()).getY(), this.getNoramls().get(index.getNormalIndex()).getZ());
						if (this.hasTexureCoords())
							glTexCoord2f(this.getTextureCodrinates().get(index.getTextureIndex()).getX(), this.getTextureCodrinates().get(index.getTextureIndex()).getY());
					}
					glEnd();
					glColor3f(0, 0, 0);
					glBegin(GL_LINES);
					for (int fi = 0; fi < f.getFacesIndecies().size(); fi++) {
						index = f.getFacesIndecies().get(fi);
						glVertex3f(this.getVertecies().get(index.getVertexIndex()).getX(), this.getVertecies().get(index.getVertexIndex()).getY(), this.getVertecies().get(index.getVertexIndex()).getZ());
						if (this.hasNormals())
							glNormal3f(this.getNoramls().get(index.getNormalIndex()).getX(), this.getNoramls().get(index.getNormalIndex()).getY(), this.getNoramls().get(index.getNormalIndex()).getZ());
						if (this.hasTexureCoords())
							glTexCoord2f(this.getTextureCodrinates().get(index.getTextureIndex()).getX(), this.getTextureCodrinates().get(index.getTextureIndex()).getY());

						if (fi == (f.getFacesIndecies().size() - 1)) {
							index = f.getFacesIndecies().get(0);
						}
					}
					glEnd();
				}
			}
		} else {
			glCallList(vboHandle);
		}
		super.render();
	}

	@Override
	protected void finalize() throws Throwable {
		if (isvbo)
			glDeleteLists(vboHandle, 1);
	}

	@Override
	public void input() {
		super.input();
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public int hashCode() {
		int hc = 17;
		int hashMultiplier = 59;
		hc = hc * hashMultiplier + 2;
		hc = (int) (hc * hashMultiplier + this.getTransform().getPosition().getX() + this.getTransform().getPosition().getY() + this.getTransform().getPosition().getZ());
		hc = (int) (hc * hashMultiplier + this.getTransform().getRotation().getAngelX() + this.getTransform().getRotation().getAngelY() + this.getTransform().getRotation().getAngelZ());
		return hc;
	}

	@Override
	public String toString() {
		return "[[Vertecies|" + this.getVertecies().size() + "][Normals|" + this.getNoramls().size() + "][TexCoords|" + this.getTextureCodrinates().size() + "]]";
	}

}
