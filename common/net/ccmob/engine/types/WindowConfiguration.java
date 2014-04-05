package net.ccmob.engine.types;

public class WindowConfiguration {

	private Dimension	size;
	private boolean	  vsync	        = false;
	private int	      fps	          = 60;
	private int	      multiSampling	= 0;
	private boolean	  fullscreeen	  = false;

	public WindowConfiguration(Dimension size, boolean vsync) {
		this.setSize(size);
		this.setVsync(vsync);
	}

	public WindowConfiguration(Dimension size, int fps) {
		this.setSize(size);
		this.setFps(fps);
	}

	/**
	 * @return the size
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * @param size
	 *          the size to set
	 */
	public void setSize(Dimension size) {
		this.size = size;
	}

	/**
	 * @return the vsync
	 */
	public boolean isVsync() {
		return vsync;
	}

	/**
	 * @param vsync
	 *          the vsync to set !Warning ! if vsync is true, then the fps can be
	 *          anything !
	 */
	public void setVsync(boolean vsync) {
		this.vsync = vsync;
	}

	/**
	 * @return the fps
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * @param fps
	 *          the fps to set
	 */
	public void setFps(int fps) {
		this.fps = fps;
	}

	/**
	 * @return the multiSampling
	 */
	public int getMultiSampling() {
		return multiSampling;
	}

	/**
	 * @param multiSampling
	 *          the multiSampling to set 0 = no multi sampling
	 */
	public void setMultiSampling(int multiSampling) {
		this.multiSampling = multiSampling;
	}

	/**
	 * Set's the multiSampling variable to 4.
	 */
	public void enableAntiAlising() {
		this.setMultiSampling(4);
	}

	/**
	 * @return the fullscreeen
	 */
	public boolean isFullscreeen() {
		return fullscreeen;
	}

	/**
	 * @param fullscreeen
	 *          the fullscreeen to set
	 */
	public void setFullscreeen(boolean fullscreeen) {
		this.fullscreeen = fullscreeen;
	}

	/**
	 * Set's the fullscreen variable to true.
	 */
	public void enableFullscreen() {
		this.setFullscreeen(true);
	}
}
