package net.ccmob.engine.types.Models;



public class FaceIndex implements Cloneable{
	
	private int vertexIndex, normalIndex, textureIndex;
	private boolean normals = false;
	private boolean textures = false;
	
    public FaceIndex() {
        this.setNormalIndex(0);
        this.setTextureIndex(0);
        this.setVertexIndex(0);
    }

    /**
     * @return the vertexIndex
     */
    public int getVertexIndex() {
    	return vertexIndex;
    }


    /**
     * @return the normalIndex
     */
    public int getNormalIndex() {
    	return normalIndex;
    }

	
    /**
     * @return the textureIndex
     */
    public int getTextureIndex() {
    	return textureIndex;
    }

	
    /**
     * @param vertexIndex the vertexIndex to set
     */
    public void setVertexIndex(int vertexIndex) {
    	this.vertexIndex = vertexIndex;
    }


    /**
     * @param normalIndex the normalIndex to set
     */
    public void setNormalIndex(int normalIndex) {
    	this.normalIndex = normalIndex;
    	this.normals = true;
    }


    /**
     * @param textureIndex the textureIndex to set
     */
    public void setTextureIndex(int textureIndex) {
    	this.textureIndex = textureIndex;
    	this.textures = true;
    }

	
    /**
     * @return the normals
     */
    public boolean hasNormals() {
    	return normals;
    }

	
    /**
     * @return the textures
     */
    public boolean hasTexturCoords() {
    	return textures;
    }
	
    @Override
    protected FaceIndex clone() throws CloneNotSupportedException {
        FaceIndex index = new FaceIndex();
        index.setNormalIndex(getNormalIndex());
        index.setTextureIndex(getTextureIndex());
        index.setVertexIndex(getVertexIndex());
        index.normals = normals;
        index.textures = textures;
        return index;
    }

	
    /**
     * @return the normals
     */
    public boolean isNormals() {
    	return normals;
    }

	
    /**
     * @return the textures
     */
    public boolean isTextures() {
    	return textures;
    }

	
    /**
     * @param normals the normals to set
     */
    public void setNormals(boolean normals) {
    	this.normals = normals;
    }

	
    /**
     * @param textures the textures to set
     */
    public void setTextures(boolean textures) {
    	this.textures = textures;
    }
    
}
