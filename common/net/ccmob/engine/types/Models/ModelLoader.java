package net.ccmob.engine.types.Models;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.ccmob.engine.types.Models.Materials.Material;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;


/**
 * 
 * @author Marcel Benning
 * 
 */

public class ModelLoader {

	public ModelLoader(Model model, String path) {
		try {
			File folder = new File("res/models/");
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/models/" + path)));
			MaterialLib lib = null;
			String line = "";
			String name = "";
			String[] token = null;
			SubModel sModel = new SubModel(model);
			while ((line = reader.readLine()) != null) {
				token = line.split(" ");
				if (token[0].equals("mtllib")) {
					lib = MaterialLib.loadFromFile(line.split(" ")[1]);
				} else if (token[0].equals("usemtl")) {
					System.out.println("New Material : " + line.split(" ")[1]);
					// obj.setMaterialName(line.split(" ")[1]);
					// obj.setMaterial(lib.getMaterial(obj.getMaterialName()));
					// TODO use mtl
				} else if (token[0].equals("g") || token[0].equals("o")) {
					if(sModel == null){
						sModel = new SubModel(model);
					}
				} else if (token[0].equals("v")) {
					// System.out.println("New vector : " + line);
					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);
					// System.out.println("X:" + x + " Y:" + y + " Z:" + z);
					model.getVertecies().add(new Vector3f(x, y, z));
				} else if (token[0].equals("f")) {
					System.out.print("New face : " + line + " ");

				} else if (token[0].equals("vt")) {
					System.out.println("New texture : " + line);

				} else if (token[0].equals("vn")) {
					// System.out.println("New Normal");
					x = Float.valueOf(line.split(" ")[1]);
					y = Float.valueOf(line.split(" ")[2]);
					z = Float.valueOf(line.split(" ")[3]);
					// System.out.println("X:" + x + " Y:" + y + " Z:" + z);
					v = new Vector3f(x, y, z);
					obj.getNormals().add(v);
					nCount++;
				}
			}
			if (sModel != null) {
				model.getSubModels().add(sModel.clone());
				sModel = null;
			}
			reader.close();
			for (Material m : lib.getMaterials()) {
				System.out.println(m.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
