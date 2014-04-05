package net.ccmob.engine.types.Models.Loader;

import java.util.ArrayList;

import net.ccmob.engine.types.Models.Model;

public abstract class ModelLoader {

	private static ArrayList<ModelLoader>	handlers	= new ArrayList<ModelLoader>();

	public ModelLoader(String fileEnd) {
		ModelLoader.handlers.add(this);
	}

	public abstract void loadModel(String file, Model m);

}
