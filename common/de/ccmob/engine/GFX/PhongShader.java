package de.ccmob.engine.GFX;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class PhongShader extends Shader {

	ArrayList<PointLight>	      pointLights	         = new ArrayList<PointLight>();
	boolean	                    hasPointLights	     = false;
	ArrayList<DirectionalLight>	directionalLights	   = new ArrayList<DirectionalLight>();
	boolean	                    hasDirectionalLights	= false;
	ArrayList<SpotLight>	      spotLights	         = new ArrayList<SpotLight>();
	boolean	                    hasSpotLights	       = false;

	public PhongShader(List<PointLight> pLights) {

		pointLights = (ArrayList<PointLight>) pLights;
		for (int i = 0; i < pointLights.size(); i++) {
			addUniform("plight[" + i + "].color");
			addUniform("plight[" + i + "].intensity");
			addUniform("plight[" + i + "].position");
			addUniform("plight[" + i + "].range");
		}
		this.hasPointLights = true;
	}

	public PhongShader(ArrayList<SpotLight> pLights) {

		spotLights = pLights;
		for (int i = 0; i < pointLights.size(); i++) {
			addUniform("spotlight[" + i + "].color");
			addUniform("spotlight[" + i + "].intensity");
			addUniform("spotlight[" + i + "].position");
			addUniform("spotlight[" + i + "].range");
		}
		if (pointLights.size() == 0) {
			addUniform("MAX_SPOT_LIGHTS");
		}
		this.hasSpotLights = true;
	}

	public void addPointLight(PointLight light) {
		pointLights.add(light);
	}

	public void addSpotLight(SpotLight light) {
		spotLights.add(light);
	}

	public void addDirectionalLight(DirectionalLight light) {
		directionalLights.add(light);
	}

	@Override
	public void updateUniforms() {

		setUniform("MAX_POINT_LIGHTS", pointLights.size());

		if (hasPointLights) {

			for (int i = 0; i < pointLights.size(); i++) {
				setUniform("plight[" + i + "].color", pointLights.get(i).getColor());
				setUniform("plight[" + i + "].intensity", pointLights.get(i).getIntensity());
				setUniform("plight[" + i + "].position", pointLights.get(i).getPos());
				setUniform("plight[" + i + "].range", pointLights.get(i).getRange());
			}
		}
	}

}
