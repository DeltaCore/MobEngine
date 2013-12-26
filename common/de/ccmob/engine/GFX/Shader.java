package de.ccmob.engine.GFX;

import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import de.ccmob.engine.CoreFunctions.Core;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class Shader {

	private int	                     shader	   = 0;
	private HashMap<String, Integer>	uniforms	= new HashMap<String, Integer>();

	public Shader() {
		this.setShader(glCreateProgram());
		if (this.getShader() == 0) {
			System.out.println("Shader Programm creation failed !");
			Display.destroy();
			System.exit(0);
		}
	}

	public void addVertexShader(String filename) {
		addShader(filename, GL_VERTEX_SHADER);
	}

	public void addGeometryShader(String filename) {
		addShader(filename, GL_GEOMETRY_SHADER);
	}

	public void addFragmentShader(String filename) {
		addShader(filename, GL_GEOMETRY_SHADER);
	}

	public void addUniform(String uniform) {
		int uniformLoc = glGetUniformLocation(this.getShader(), uniform);
		if (uniformLoc == 0xFFFFFFFF) {
			System.err.println("Failed to find uniform : " + uniform);
			new Exception().printStackTrace();
			System.exit(0);
		}
		this.getUniforms().put(uniform, uniformLoc);
	}

	private void addShader(String filename, int type) {
		int s = glCreateShader(type);
		glShaderSource(s, loadShader(filename));
		glCompileShader(s);
		glAttachShader(shader, s);
		glLinkProgram(shader);
		glValidateProgram(shader);
	}

	private int getShader() {
		return shader;
	}

	private void setShader(int shader) {
		this.shader = shader;
	}

	public HashMap<String, Integer> getUniforms() {
		return uniforms;
	}

	public void setUniforms(HashMap<String, Integer> uniforms) {
		this.uniforms = uniforms;
	}

	public void bind() {
		glUseProgram(this.getShader());
	}

	public void unbind() {
		glUseProgram(0);
	}

	public void setUniform(String uniform, int val) {
		glUniform1i(this.getUniforms().get(uniform), val);
	}

	public void setUniform(String uniform, float val) {
		glUniform1f(this.getUniforms().get(uniform), val);
	}

	public void setUniform(String uniform, Vector3f val) {
		glUniform3f(this.getUniforms().get(uniform), val.getX(), val.getY(), val.getZ());
	}

	public void updateUniforms() {

	}

	public static String loadShader(String shaderName) {
		StringBuilder source = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Core.class.getResourceAsStream("shader/" + shaderName)));
			String line = "";
			while ((line = reader.readLine()) != null) {
				source.append(line).append("\n");
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source.toString();
	}

}
