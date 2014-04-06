package net.ccmob.engine.types.Models;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;

import net.ccmob.engine.types.GameObject;
import net.ccmob.engine.types.Models.Face.FaceIndex;

public class SubModel extends GameObject implements Cloneable{

	private Model parent;
	private ArrayList<Face> faces = new ArrayList<Face>();
	
    public SubModel(Model m) {
    	this.parent = m;
    }
	
	@Override
	public void render() {
		for(Face f : faces){
			if(f.is4f()){
				glBegin(GL_QUADS);
			}else{
				glBegin(GL_TRIANGLES);
			}
			
			FaceIndex index = null;
			for(int i = 0;i<f.getIndecies().size();i++){
				index = f.getIndecies().get(i);
				glVertex3f(parent.getVertecies().get(index.getVertexIndex()).getX(), parent.getVertecies().get(index.getVertexIndex()).getY(), parent.getVertecies().get(index.getVertexIndex()).getZ());
				if(index.hasNormals())
					glNormal3f(parent.getNormals().get(index.getNormalIndex()).getX(),parent.getNormals().get(index.getNormalIndex()).getY(),parent.getNormals().get(index.getNormalIndex()).getZ());
				
				if(index.hasTexturCoords())
					glTexCoord2f(parent.getTextureCoords().get(index.getTextureIndex()).getX(), parent.getTextureCoords().get(index.getTextureIndex()).getY());
			}
			glEnd();
		}
		
	    super.render();
	}
	
	@Override
	protected SubModel clone() throws CloneNotSupportedException {
	    SubModel model = new SubModel(parent);
	    model.faces = new ArrayList<Face>();
	    for(Face f : this.faces){
	    	model.faces.add(f.clone());
	    }
	    return model;
	}
	
}
