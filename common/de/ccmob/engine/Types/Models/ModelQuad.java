package de.ccmob.engine.Types.Models;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.ccmob.engine.CoreFunctions.Core;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class ModelQuad extends AbstractModel {

	List<Vector3f>	 vertecies;
	List<Vector3f>	 textureCoords;
	List<Face>	     faces;
	private Texture	 texture;

	private int	     vboVectorHandle	= 0;
	private int	     vboTextureHandle	= 0;
	private boolean	 renderFlag;
	private Vector3f	vec;

	public ModelQuad(String path) {
		super(path);
		new ModelLoader(this, path);
	}

	public ModelQuad(String path, String textPath) {
		super(path, textPath);
		new ModelLoader(this, path);
	}

	public void init() {
		try {
			texture = TextureLoader.getTexture("PNG", Core.class.getResourceAsStream(this.getTexturePath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getVboVectorHandle() {
		return vboVectorHandle;
	}

	public void setVboVectorHandle(int vboVectorHandle) {
		this.vboVectorHandle = vboVectorHandle;
	}

	public int getVboTextureHandle() {
		return vboTextureHandle;
	}

	public void setVboTextureHandle(int vboTextureHandle) {
		this.vboTextureHandle = vboTextureHandle;
	}

	@Override
	void bindTexture() {
		if (texture != null) {
			texture.bind();
		}
	}

	@Override
	void unbindTexture() {
		if (texture != null) {
			texture.release();
		}
	}

	@Override
	public void renderVBO() {
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		glBindBuffer(GL_ARRAY_BUFFER, this.getVboVectorHandle());
		glVertexPointer(3, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, this.getVboTextureHandle());
		glTexCoordPointer(3, GL_FLOAT, 0, 0L);

		glDrawArrays(GL_QUADS, 0, faces.size());

		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
	}

	@Override
	public void renderImmediateAt(int x, int y, int z) {
		glTranslatef((float) x, (float) y, (float) z);
		glBegin(GL_QUADS);
		{
			for (Face f : faces) {
				vec = vertecies.get((int) (f.getVertex4f().x - 1));
				glTexCoord2f(0, 0);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().y - 1));
				glTexCoord2f(0, 1);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().z - 1));
				glTexCoord2f(1, 1);
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().w - 1));
				glTexCoord2f(1, 0);
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		glEnd();
		glTranslatef(-(float) x, -(float) y, -(float) z);
	}

	@Override
	public void renderImmediateAtBlank(int x, int y, int z) {
		glTranslatef((float) x, (float) y, (float) z);
		{
			for (Face f : faces) {
				vec = vertecies.get((int) (f.getVertex4f().x - 1));
				glTexCoord2f(0, 0);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().y - 1));
				glTexCoord2f(0, 1);
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().z - 1));
				glTexCoord2f(1, 1);
				renderFlag = !renderFlag;
				glVertex3f(vec.x, vec.y, vec.z);
				vec = vertecies.get((int) (f.getVertex4f().w - 1));
				glTexCoord2f(1, 0);
				glVertex3f(vec.x, vec.y, vec.z);
			}
		}
		glTranslatef(-(float) x, -(float) y, -(float) z);
	}

	@Override
	public void renderImmediateBlank() {
	}

}
