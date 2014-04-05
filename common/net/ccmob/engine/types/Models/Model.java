package net.ccmob.engine.types.Models;

import net.ccmob.engine.types.GameObject;

public abstract class Model extends GameObject {

	private boolean	_normals	= false;
	private boolean	_texure	 = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.devgensoft.engine.types.GameObject#render()
	 */
	@Override
	public void render() {
		super.render();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.devgensoft.engine.types.GameObject#input()
	 */
	@Override
	public void input() {
		// TODO Auto-generated method stub
		super.input();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.devgensoft.engine.types.GameObject#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
	}

	/**
	 * @return true if model has normals
	 */
	public boolean hasNormals() {
		return _normals;
	}

	/**
	 * @param normals
	 *          the _normals to set
	 */
	public void setHasNormals(boolean hasNormals) {
		this._normals = hasNormals;
	}

	/**
	 * @return true if model has texture coords.
	 */
	public boolean hasTexureCoords() {
		return _texure;
	}

	/**
	 * @param hasTextureCoords
	 *          (boolean)
	 */
	public void setHasTextureCoords(boolean hasTextureCoords) {
		this._texure = hasTextureCoords;
	}

}
