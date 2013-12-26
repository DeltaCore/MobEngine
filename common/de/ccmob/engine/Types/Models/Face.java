package de.ccmob.engine.Types.Models;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Face {

	private Vector3f	normal, vertex, texture;
	private Vector4f	vertex4f, texture4f, normals4f;

	private boolean	 normals	= true;
	private boolean	 vector4f	= false;

	private boolean	 _4f	    = false;

	public Face(Vector3f vertex, Vector3f texture, Vector3f normal) {
		this.setVertex(vertex);
		this.setTexture(texture);
		this.setNormal(normal);
	}

	public Face(Vector3f vertex, Vector3f texture) {
		this.setVertex(vertex);
		this.setTexture(texture);
		normals = false;
	}

	public Face(Vector4f vertex, Vector4f textureCoord) {
		this.setTexture4f(textureCoord);
		this.setVertex4f(vertex);
		normals = false;
		vector4f = true;
		this.set_4f(true);
	}

	public Face(Vector4f vertex4f, Vector4f normals4f, Vector4f textureCoord4f) {
		this.setTexture4f(textureCoord4f);
		this.setVertex4f(vertex4f);
		this.setNormals4f(normals4f);
		normals = false;
		vector4f = true;
		this.set_4f(true);
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

	public Vector3f getVertex() {
		return vertex;
	}

	public void setVertex(Vector3f vertex) {
		this.vertex = vertex;
	}

	public Vector3f getTexture() {
		return texture;
	}

	public void setTexture(Vector3f texture) {
		this.texture = texture;
	}

	public Vector4f getVertex4f() {
		return vertex4f;
	}

	public void setVertex4f(Vector4f vertex4f) {
		this.vertex4f = vertex4f;
	}

	public Vector4f getTexture4f() {
		return texture4f;
	}

	public void setTexture4f(Vector4f texture4f) {
		this.texture4f = texture4f;
	}

	public boolean hasNormals() {
		return normals;
	}

	public boolean isVector4f() {
		return vector4f;
	}

	public Vector4f getNormals4f() {
		return normals4f;
	}

	public void setNormals4f(Vector4f normals4f) {
		this.normals4f = normals4f;
	}

	public boolean is4f() {
		return _4f;
	}

	private void set_4f(boolean _4f) {
		this._4f = _4f;
	}

	@Override
	public String toString() {
		String ret = "";
		if (is4f()) {
			ret += "Textures : ";
			ret += this.getTexture4f().toString() + "\n";
			if (this.normals) {
				ret += "Normals : ";
				ret += this.getNormals4f().toString() + "\n";
			}
			ret += "Vertecies : ";
			ret += this.getVertex4f().toString() + "\n";
		} else {
			ret += "Textures : ";
			ret += this.getTexture().toString() + "\n";
			if (this.normals) {
				ret += "Normals : ";
				ret += this.getNormal().toString() + "\n";
			}
			ret += "Vertecies : ";
			ret += this.getVertex().toString() + "\n";
		}
		return ret;
	}

}
