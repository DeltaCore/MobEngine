package net.ccmob.engine.types.Models.Loader.obj;

import java.util.ArrayList;

public class Face {

	private boolean	             is4f	          = false;	                    ;
	private ArrayList<FaceIndex>	facesIndecies	= new ArrayList<FaceIndex>();

	/**
	 * @return the facesIndecies
	 */
	public ArrayList<FaceIndex> getFacesIndecies() {
		return facesIndecies;
	}

	/**
	 * @param facesIndecies
	 *          the facesIndecies to set
	 */
	public void setFacesIndecies(ArrayList<FaceIndex> facesIndecies) {
		this.facesIndecies = facesIndecies;
	}

	/**
	 * @return the is4f
	 */
	public boolean is4f() {
		return is4f;
	}

	/**
	 * @param is4f
	 *          the is4f to set
	 */
	public void set4f(boolean is4f) {
		this.is4f = is4f;
	}

	@Override
	public String toString() {
		String s = "[";
		for (FaceIndex f : this.getFacesIndecies()) {
			s += f.toString();
		}
		s += "]";
		return s;
	}

}
