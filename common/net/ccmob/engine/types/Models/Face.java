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
    
}
