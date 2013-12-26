package de.ccmob.engine.Types.Models;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_NORMAL_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glNormalPointer;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.ccmob.engine.CoreFunctions.Core;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Model {

	List<Vector3f>	  vectors	      = new ArrayList<Vector3f>();
	List<Vector3f>	  norms	        = new ArrayList<Vector3f>();
	List<Vector2f>	  texturePoints	= new ArrayList<Vector2f>();
	List<Face>	      faces	        = new ArrayList<Face>();

	Vector3f	        vec	          = new Vector3f();
	Texture	          texture	      = null;
	String	          texturePath	  = "";

	Random	          random	      = new Random(1);
	boolean	          renderFlag	  = false;
	private int	      vboVertexHandle;
	private int	      vboNormalHandle;
	private int	      vboTextureHandle;
	private final int	vertexSize	  = 3;
	private int	      textureSize	  = 2;

	public Model() {
	}

	public static Model getModel(String path) {
		return new Model(path);
	}

	public Model(String path) {
		new ModelLoader(this, path);
	}

	public Model(String path, String tPath) {
		new ModelLoader(this, path);
		this.texturePath = tPath;
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(this.vertexSize * faces.size() * 3);
		FloatBuffer normalData = BufferUtils.createFloatBuffer(this.vertexSize * faces.size() * 3);
		FloatBuffer textureData = BufferUtils.createFloatBuffer(this.textureSize * faces.size() * 3);
		int v = 0;
		int n = 0;
		int t = 0;
		for (Face f : faces) {
			v = (int) f.getVertex().x - 1;
			vertexData.put(vectors.get(v).x);
			vertexData.put(vectors.get(v).y);
			vertexData.put(vectors.get(v).z);
			v = (int) f.getVertex().y - 1;
			vertexData.put(vectors.get(v).x);
			vertexData.put(vectors.get(v).y);
			vertexData.put(vectors.get(v).z);
			v = (int) f.getVertex().z - 1;
			vertexData.put(vectors.get(v).x);
			vertexData.put(vectors.get(v).y);
			vertexData.put(vectors.get(v).z);

			n = (int) f.getNormal().x - 1;
			normalData.put(norms.get(n).x);
			normalData.put(norms.get(n).y);
			normalData.put(norms.get(n).z);
			n = (int) f.getNormal().y - 1;
			normalData.put(norms.get(n).x);
			normalData.put(norms.get(n).y);
			normalData.put(norms.get(n).z);
			n = (int) f.getNormal().z - 1;
			normalData.put(norms.get(n).x);
			normalData.put(norms.get(n).y);
			normalData.put(norms.get(n).z);

			t = (int) f.getTexture().x - 1;
			textureData.put(texturePoints.get(t).x);
			textureData.put(texturePoints.get(t).y);
			t = (int) f.getTexture().y - 1;
			textureData.put(texturePoints.get(t).x);
			textureData.put(texturePoints.get(t).y);
			t = (int) f.getTexture().z - 1;
			textureData.put(texturePoints.get(t).x);
			textureData.put(texturePoints.get(t).y);
		}
		normalData.flip();
		vertexData.flip();
		textureData.flip();

		this.setVboVertexHandle(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, this.getVboVertexHandle());
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		this.setVboNormalHandle(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, this.getVboNormalHandle());
		glBufferData(GL_ARRAY_BUFFER, normalData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		this.setVboTextureHandle(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, this.getVboTextureHandle());
		glBufferData(GL_ARRAY_BUFFER, textureData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public int getVboVertexHandle() {
		return vboVertexHandle;
	}

	public void setVboVertexHandle(int vboVertexHandle) {
		this.vboVertexHandle = vboVertexHandle;
	}

	public int getVboNormalHandle() {
		return vboNormalHandle;
	}

	public void setVboNormalHandle(int vboNormalHandle) {
		this.vboNormalHandle = vboNormalHandle;
	}

	public int getVboTextureHandle() {
		return vboTextureHandle;
	}

	public void setVboTextureHandle(int vboTextureHandle) {
		this.vboTextureHandle = vboTextureHandle;
	}

	public void init() {
		try {
			this.texture = TextureLoader.getTexture("PNG", Core.class.getResourceAsStream(texturePath));
			texture.bind();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void bindTexture() {
		if (texture != null) {
			texture.bind();
		}
	}

	protected void unbindTexture() {
		if (texture != null) {
			texture.release();
		}
	}

	public void renderVBO() {
		// bindTexture();
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_NORMAL_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, this.getVboVertexHandle());
		glVertexPointer(this.vertexSize, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, this.getVboNormalHandle());
		glNormalPointer(GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, this.getVboTextureHandle());
		glTexCoordPointer(this.textureSize, GL_FLOAT, 0, 0L);

		glDrawArrays(GL_TRIANGLES, 0, faces.size());

		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_NORMAL_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		// unbindTexture();
	}

	public void renderImmediate() {
		renderFlag = false;
		// glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glBegin(GL_TRIANGLES);
		// bindTexture();
		{
			for (Face f : faces) {
				vec = norms.get((int) (f.getNormal().x - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().x - 1));
				if (renderFlag) {
					glTexCoord2f(1, 1);
				} else {
					glTexCoord2f(0, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().y - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().y - 1));
				if (renderFlag) {
					glTexCoord2f(1, 0);
				} else {
					glTexCoord2f(1, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().z - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().z - 1));
				if (renderFlag) {
					glTexCoord2f(0, 0);
				} else {
					glTexCoord2f(0, 0);
				}
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		// unbindTexture();
		glEnd();
	}

	public void renderImmediatBlank() {
		// bindTexture();
		{
			for (Face f : faces) {
				vec = norms.get((int) (f.getNormal().x - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().x - 1));
				if (renderFlag) {
					glTexCoord2f(1, 1);
				} else {
					glTexCoord2f(0, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().y - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().y - 1));
				if (renderFlag) {
					glTexCoord2f(1, 0);
				} else {
					glTexCoord2f(1, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().z - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().z - 1));
				if (renderFlag) {
					glTexCoord2f(0, 0);
				} else {
					glTexCoord2f(0, 0);
				}
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		// unbindTexture();
	}

	public void renderImmediateAt(int x, int y, int z) {
		// glPolygonMode(GL_FRONT_AND_BACK , GL_LINE);
		glTranslatef((float) x, (float) y, (float) z);
		glBegin(GL_TRIANGLES);
		// bindTexture();
		{
			for (Face f : faces) {
				vec = norms.get((int) (f.getNormal().x - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().x - 1));
				if (renderFlag) {
					glTexCoord2f(1, 1);
				} else {
					glTexCoord2f(0, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().y - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().y - 1));
				if (renderFlag) {
					glTexCoord2f(1, 0);
				} else {
					glTexCoord2f(1, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().z - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().z - 1));
				if (renderFlag) {
					glTexCoord2f(0, 0);
				} else {
					glTexCoord2f(0, 0);
				}
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		// unbindTexture();
		glEnd();
		glTranslatef(-(float) x, -(float) y, -(float) z);
	}

	public void renderImmediatAtBlank(int x, int y, int z) {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glTranslatef((float) x, (float) y, (float) z);
		// bindTexture();
		{
			for (Face f : faces) {
				vec = norms.get((int) (f.getNormal().x - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().x - 1));
				if (renderFlag) {
					glTexCoord2f(1, 1);
				} else {
					glTexCoord2f(0, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().y - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().y - 1));
				if (renderFlag) {
					glTexCoord2f(1, 0);
				} else {
					glTexCoord2f(1, 1);
				}
				glVertex3f(vec.x, vec.y, vec.z);
				vec = norms.get((int) (f.getNormal().z - 1));
				glNormal3f(vec.x, vec.y, vec.z);
				vec = vectors.get((int) (f.getVertex().z - 1));
				if (renderFlag) {
					glTexCoord2f(0, 0);
				} else {
					glTexCoord2f(0, 0);
				}
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		// unbindTexture();
		glTranslatef(-(float) x, -(float) y, -(float) z);
	}

}
