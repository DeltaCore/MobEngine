package de.ccmob.engine.Types.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import de.ccmob.engine.Types.Models.Materials.Material;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class MaterialLib {

	List<Material>	materials	= new ArrayList<Material>();

	public MaterialLib() {

	}

	public Material getMaterial(String name) {
		System.out.println("Searching for material with name " + name);
		for (int i = 0; i < materials.size(); i++) {
			if (materials.get(i).getName().equals(name)) {
				System.out.println("Yes ! - " + materials.get(i).getName());
				return materials.get(i);
			} else {
				System.out.println("Damn it ! - " + materials.get(i).getName());
			}
		}
		return null;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public static MaterialLib loadFromFile(String filename) {
		System.out.println("New Material lib : res/models/" + filename);
		MaterialLib lib = new MaterialLib();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/models/" + filename)));
			String line = "";
			String name = "";
			String temp = "";
			Material m = null;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("newmtl ")) {
					name = line.split(" ")[1];
					System.out.println("New LibMaterial : " + name);
					if (m == null) {
						m = new Material(name);
					} else {
						lib.getMaterials().add(m);
						m = new Material(name);
					}
				}
				if (line.startsWith("map_Kd ")) {
					temp = line.split(" ")[1];
					m.setTexture(temp);
				}
			}
			if (m != null) {
				lib.getMaterials().add(m);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lib;
	}

}
