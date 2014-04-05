package net.ccmob.engine.types;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import de.ccmob.game.TestGame;

public class Camera implements GameComponent {

	private Transform	transform	     = new Transform();
	private int	      mouseCreateCnt	= 0;
	private float	    mouse_scale	   = 0.2f;
	private float	    speed	         = 0.05f;

	@Override
	public void render() {

	}

	@Override
	public void input() {

		if (Mouse.isCreated()) {
			if (Mouse.isGrabbed()) {
				this.getTransform().getRotation().subAngelX(Mouse.getDY() * mouse_scale);
				this.getTransform().getRotation().addAngelY(Mouse.getDX() * mouse_scale);
			}
			if (!Mouse.isGrabbed() && Mouse.isButtonDown(0)) {
				Mouse.setGrabbed(true);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				Mouse.setGrabbed(false);
			}

			if (this.getTransform().getRotation().getAngelX() >= 90.0f) {
				this.getTransform().getRotation().setAngelX(90.0F);
			}
			if (this.getTransform().getRotation().getAngelX() <= -90.0f) {
				this.getTransform().getRotation().setAngelX(-90.0F);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					this.getTransform().getPosition().setY(this.getTransform().getPosition().getY() - (speed / 10));
				} else {
					this.getTransform().getPosition().setY(this.getTransform().getPosition().getY() - speed);
				}

			}

			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					this.getTransform().getPosition().setY(this.getTransform().getPosition().getY() + (speed / 10));
				} else {
					this.getTransform().getPosition().setY(this.getTransform().getPosition().getY() + speed);
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
					this.getTransform().getPosition().setX((float) (this.getTransform().getPosition().getX() - Math.sin(Math.toRadians(this.getTransform().getRotation().getAngelY())) * (speed / 10)));
					this.getTransform().getPosition().setZ((float) (this.getTransform().getPosition().getZ() + Math.cos(Math.toRadians(this.getTransform().getRotation().getAngelY())) * (speed / 10)));
				} else {
					this.getTransform().getPosition().setX((float) (this.getTransform().getPosition().getX() - Math.sin(Math.toRadians(this.getTransform().getRotation().getAngelY())) * speed));
					this.getTransform().getPosition().setZ((float) (this.getTransform().getPosition().getZ() + Math.cos(Math.toRadians(this.getTransform().getRotation().getAngelY())) * speed));
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				this.getTransform().getPosition().setX((float) (this.getTransform().getPosition().getX() - Math.sin(Math.toRadians(this.getTransform().getRotation().getAngelY() + 180f)) * speed));
				this.getTransform().getPosition().setZ((float) (this.getTransform().getPosition().getZ() + Math.cos(Math.toRadians(this.getTransform().getRotation().getAngelY() + 180f)) * speed));
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				this.getTransform().getPosition().setX((float) (this.getTransform().getPosition().getX() - Math.sin(Math.toRadians(this.getTransform().getRotation().getAngelY() + -90f)) * speed));
				this.getTransform().getPosition().setZ((float) (this.getTransform().getPosition().getZ() + Math.cos(Math.toRadians(this.getTransform().getRotation().getAngelY() + -90f)) * speed));
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				this.getTransform().getPosition().setX((float) (this.getTransform().getPosition().getX() - Math.sin(Math.toRadians(this.getTransform().getRotation().getAngelY() + 90f)) * speed));
				this.getTransform().getPosition().setZ((float) (this.getTransform().getPosition().getZ() + Math.cos(Math.toRadians(this.getTransform().getRotation().getAngelY() + 90f)) * speed));
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				TestGame.instance.getRootObject().getTransform().setPosition(new Vector3f(TestGame.instance.getRootObject().getTransform().getPosition().getX() - speed, TestGame.instance.getRootObject().getTransform().getPosition().getY(), TestGame.instance.getRootObject().getTransform().getPosition().getZ()));
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				TestGame.instance.getRootObject().getTransform().setPosition(new Vector3f(TestGame.instance.getRootObject().getTransform().getPosition().getX() + speed, TestGame.instance.getRootObject().getTransform().getPosition().getY(), TestGame.instance.getRootObject().getTransform().getPosition().getZ()));
			}

		} else {
			try {
				Mouse.create();
			} catch (LWJGLException e) {
				mouseCreateCnt++;
				if (mouseCreateCnt >= 1000) {
					e.printStackTrace();
					System.err.println("I tried now a thousand times to create the mouse ._. -> i can't help you anmore !");
					System.exit(1);
				}
			}
		}
	}

	@Override
	public void update() {
		this.getTransform().translate();
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
