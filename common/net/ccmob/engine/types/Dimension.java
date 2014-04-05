package net.ccmob.engine.types;

public class Dimension {

	private int	x	= 0, y = 0;

	public Dimension(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *          the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *          the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
