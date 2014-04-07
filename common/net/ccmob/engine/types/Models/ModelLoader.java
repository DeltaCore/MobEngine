package net.ccmob.engine.types.Models;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import net.ccmob.engine.types.Models.Materials.Material;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;


/**
 * 
 * @author Marcel Benning
 * 
 */

public class ModelLoader {

	public ModelLoader(Model model, String path) {
		long start = System.currentTimeMillis();
		System.out.println("Started at " + start);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/models/" + path)));
			MaterialLib lib = null;
			String line = "";
			String[] token = null;
			SubModel sModel = null;
			String name = "";
			boolean addet = false;
			while ((line = reader.readLine()) != null) {
				token = line.split(" ");
				if (token[0].equals("mtllib")) {
					lib = MaterialLib.loadFromFile(line.split(" ")[1]);
					model.setMaterials(lib);
				} else if (token[0].equals("usemtl")) {
					System.out.println("Using Material : " + token[1] + " for " + name);
					sModel.setMaterialName(token[1]);
					System.out.println("Material name : " + sModel.getMaterialName());
				} else if (token[0].equals("g") || token[0].equals("o")) {
					System.out.println("New OBJObject " + token[1]);
					name = token[1];
					if(sModel == null){
						sModel = new SubModel(model);
						sModel.setName(name);
					}else{
						addet = true;
						model.addSubModel(sModel.clone(), name);
						sModel = new SubModel(model);
						sModel.setName(name);
						addet = false;
					}
				} else if (token[0].equals("v")) {
					//System.out.println("New vector : " + line);
					float x = Float.valueOf(token[1]);
					float y = Float.valueOf(token[2]);
					float z = Float.valueOf(token[3]);
					model.getVertecies().add(new Vector3f(x, y, z));
				} else if (token[0].equals("f")) {
					//System.out.print("New face : " + line + " ");
					Face f = new Face();
					for(int i = 1;i<token.length;i++){
						f.addIndex(parseIndex(token[i]));
					}
					sModel.getFaces().add(f);
				} else if (token[0].equals("vt")) {
					//System.out.println("New texture : " + line);
					model.getTextureCoords().add(new Vector2f(Float.valueOf(token[1]), Float.valueOf(token[2])));
				} else if (token[0].equals("vn")) {
					model.getNormals().add(new Vector3f(Float.valueOf(token[1]), Float.valueOf(token[2]), Float.valueOf(token[3])));
				}
			}
			if(!model.contains(name)){
				model.addSubModel(sModel, name);
			}
			reader.close();
			for (Material m : lib.getMaterials()) {
				System.out.println("Material " + m.getName());
			}
			for(SubModel s : model.getSubModels()){
				System.out.println("SubModel " + s.getName() + " -> " + s.getMaterialName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long delta = System.currentTimeMillis() - start;
		System.out.println("It took " + delta / 1000 + "s to load " + model.getVertecies().size() + "vertecies, " + model.getNormals().size() + "normals and " + model.getTextureCoords().size() + "texcoords");
	}
	
	private static FaceIndex parseIndex(String line){
		FaceIndex index = new FaceIndex();
		String[] token = line.split("/");
		index.setVertexIndex(Integer.parseInt(token[0]) - 1);
		if(token.length > 1){
			index.setTextures(true);
			index.setTextureIndex(Integer.parseInt(token[1]) - 1);
			if(token.length > 2){
				index.setNormals(true);
				index.setNormalIndex(Integer.parseInt(token[2]) - 1);
			}
		}
		return index;
	}
	
}
