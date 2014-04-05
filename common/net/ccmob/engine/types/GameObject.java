package net.ccmob.engine.types;

import java.util.ArrayList;

public class GameObject {

	private Transform	               transform	 = new Transform();
	private ArrayList<GameObject>	   children	   = new ArrayList<GameObject>();
	private ArrayList<GameComponent>	components	= new ArrayList<GameComponent>();

	/**
	 * @return the childs
	 */
	public ArrayList<GameObject> getChildren() {
		return children;
	}

	/**
	 * @return the components
	 */
	public ArrayList<GameComponent> getComponents() {
		return components;
	}

	/**
	 * @param the
	 *          GameObject to add to the children
	 */
	public void addChild(GameObject obj) {
		this.getChildren().add(obj);
	}

	/**
	 * @param the
	 *          GameComponent to add to the components
	 */
	public void addComponent(GameComponent obj) {
		this.getComponents().add(obj);
	}

	/**
	 * Renders the children of this object
	 */
	public void render() {
		this.getTransform().translate();
		for (GameObject obj : this.getChildren())
			obj.render();
		for (GameComponent obj : this.getComponents())
			obj.render();
	}

	/**
	 * Updates the input from the children of this object
	 */
	public void input() {
		for (GameObject obj : this.getChildren())
			obj.input();
		for (GameComponent obj : this.getComponents())
			obj.input();
	}

	/**
	 * Updates the children of this object
	 */
	public void update() {
		for (GameObject obj : this.getChildren())
			obj.update();
		for (GameComponent obj : this.getComponents())
			obj.update();
	}

	/**
	 * @return the transform
	 */
	public Transform getTransform() {
		return transform;
	}

	/**
	 * @param transform
	 *          the transform to set
	 */
	public void setTransform(Transform transform) {
		this.transform = transform;
	}

}
