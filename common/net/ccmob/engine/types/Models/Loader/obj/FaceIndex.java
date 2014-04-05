package net.ccmob.engine.types.Models.Loader.obj;

public class FaceIndex {

	private int	vertexIndex	 = 0;
	private int	normalIndex	 = 0;
	private int	TextureIndex	= 0;

	/**
	 * @return the vertexIndex
	 */
	public int getVertexIndex() {
		return vertexIndex;
	}

	/**
	 * @param vertexIndex
	 *          the vertexIndex to set
	 */
	public void setVertexIndex(int vertexIndex) {
		this.vertexIndex = vertexIndex;
	}

	/**
	 * @return the normalIndex
	 */
	public int getNormalIndex() {
		return normalIndex;
	}

	/**
	 * @param normalIndex
	 *          the normalIndex to set
	 */
	public void setNormalIndex(int normalIndex) {
		this.normalIndex = normalIndex;
	}

	/**
	 * @return the textureIndex
	 */
	public int getTextureIndex() {
		return TextureIndex;
	}

	/**
	 * @param textureIndex
	 *          the textureIndex to set
	 */
	public void setTextureIndex(int textureIndex) {
		TextureIndex = textureIndex;
	}

	@Override
	public String toString() {
		return "[" + this.getVertexIndex() + "|" + this.getTextureIndex() + "|" + this.getNormalIndex() + "]";
	}
}
