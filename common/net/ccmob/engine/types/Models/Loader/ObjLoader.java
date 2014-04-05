package net.ccmob.engine.types.Models.Loader;

import java.util.ArrayList;

import net.ccmob.engine.resource.ResourceLoader;
import net.ccmob.engine.types.Models.Model;
import net.ccmob.engine.types.Models.ObjModel;
import net.ccmob.engine.types.Models.Loader.obj.Face;
import net.ccmob.engine.types.Models.Loader.obj.FaceIndex;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class ObjLoader extends ModelLoader {

	public ObjLoader() {
		super("[.][oO][bB][jJ]^");
	}

	@Override
	public void loadModel(String file, Model m) {
		ObjModel model = (ObjModel) m;
		ArrayList<String> fileContent = ResourceLoader.loadFileContentAsArrayList("models/" + file);
		String line = "";
		String[] token = {};
		for (int i = 0; i < fileContent.size(); i++) {
			line = fileContent.get(i);
			token = line.split(" ");
			if (token[0].equalsIgnoreCase("#")) {
				continue;
			} else if (token[0].equalsIgnoreCase("v")) {
				model.getVertecies().add(new Vector3f(Float.valueOf(token[1]), Float.valueOf(token[2]), Float.valueOf(token[3])));
			} else if (token[0].equalsIgnoreCase("vn")) {
				model.setHasNormals(true);
				model.getNoramls().add(new Vector3f(Float.valueOf(token[1]), Float.valueOf(token[2]), Float.valueOf(token[3])));
			} else if (token[0].equalsIgnoreCase("vt")) {
				model.setHasTextureCoords(true);
				model.getTextureCodrinates().add(new Vector2f(Float.valueOf(token[1]), Float.valueOf(token[2])));
			} else if (token[0].equalsIgnoreCase("f")) {
				if (token.length == 4) {
					Face f = new Face();
					f.getFacesIndecies().add(parseFaceIndex(token[1], model));
					f.getFacesIndecies().add(parseFaceIndex(token[2], model));
					f.getFacesIndecies().add(parseFaceIndex(token[3], model));
					model.getFaces().add(f);
				} else if (token.length == 5) {
					Face f = new Face();
					f.getFacesIndecies().add(parseFaceIndex(token[1], model));
					f.getFacesIndecies().add(parseFaceIndex(token[2], model));
					f.getFacesIndecies().add(parseFaceIndex(token[3], model));
					model.getFaces().add(f);
					Face t = new Face();
					t.getFacesIndecies().add(parseFaceIndex(token[1], model));
					t.getFacesIndecies().add(parseFaceIndex(token[3], model));
					t.getFacesIndecies().add(parseFaceIndex(token[4], model));
					model.getFaces().add(t);
				}
			}
		}
		System.out.println(model);
	}

	private static FaceIndex parseFaceIndex(String token, ObjModel model) {
		String[] values = token.split("/");
		FaceIndex result = new FaceIndex();
		result.setVertexIndex(Integer.valueOf(values[0]) - 1);
		if (values.length > 1) {
			model.setHasTextureCoords(true);
			result.setTextureIndex(Integer.valueOf(values[1]) - 1);
			if (values.length > 2) {
				model.setHasNormals(true);
				result.setNormalIndex(Integer.valueOf(values[2]) - 1);
			}
		}
		return result;
	}

}
