package net.ccmob.engine.types.Models;

import java.util.ArrayList;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Face implements Cloneable{

	private ArrayList<FaceIndex> indecies = new ArrayList<FaceIndex>();
		
	private boolean has4indecies = false;
	
    /**
     * @return the indecies
     */
    public ArrayList<FaceIndex> getIndecies() {
    	return indecies;
    }
    
    /**
     * @param indecies the indecies to set
     */
    public void setIndecies(ArrayList<FaceIndex> indecies) {
    	this.indecies = indecies;
    }

    public void addIndex(FaceIndex index){
    	this.getIndecies().add(index);
    	if(this.getIndecies().size() == 4){
    		this.has4indecies = true;
    	}
    }
    
    public boolean is4f(){
    	return has4indecies;
    }
    
    @Override
    protected Face clone() throws CloneNotSupportedException {
        Face f = new Face();
        f.has4indecies = has4indecies;
        f.indecies = new ArrayList<FaceIndex>();
        for(FaceIndex i : indecies){
        	f.indecies.add(i.clone());
        }
        return f;
    }
    
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
        
	}

}
