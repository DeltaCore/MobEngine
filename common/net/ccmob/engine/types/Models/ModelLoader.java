package net.ccmob.engine.types.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.ccmob.engine.types.Models.Materials.Material;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * 
 * @author Marcel Benning
 * 
 */

public class ModelLoader {

	float	   x	           = 0, y = 0, z = 0;

	float	   x1	           = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0, z1 = 0, z2 = 0, z3 = 0, z4 = 0;

	float	   s	           = 0, t = 0;

	Vector3f	v;
	Face	   f;

	String	 vector_regex	 = "^v ([-0-1]{1,}) ([-0-1]{1,}) ([-0-1]{1,})$";
	String	 texture_regex	= "^vt ([-0-1]{1,}) ([-0-1]{1,}) ([-0-1]{1,})$";
	String	 face_regex	   = "^f ([0-9.]).([0-9.]) ([0-9.]).([0-9.]) ([0-9.]).([0-9.]) ([0-9.]).([0-9.])";

	String	face_quad_no_normals_1	   = "^f ([0-9.]{1,})[\\/][\\/]([0-9.]{1,})[\\s]([0-9.])[\\/][\\/]([0-9.]{1,})[\\s]([0-9.])[\\/][\\/]([0-9.]{1,})[\\s]([0-9.])[\\/][\\/]([0-9.])";
	String	face_quad_no_normals_2	   = "^f ([0-9.]{1,})[\\/]([0-9.]{1,})[\\s]([0-9.]{1,})[\\/]([0-9.]{1,})[\\s]([0-9.]{1,})[\\/]([0-9.]{1,})[\\s]([0-9.]{1,})[\\/]([0-9.]{1,})";

	String	face_quad	                 = "^f ([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})[\\s]([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})[\\s]([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})[\\s]([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})";

	String	face_triangle_no_normals_1	= "^f ([0-9.]{1,})[\\/][\\/]([0-9.]{1,})[\\s]([0-9.])[\\/][\\/]([0-9.]{1,})[\\s]([0-9.])[\\/][\\/]([0-9.]{1,})";
	String	face_triangle_no_normals_2	= "^f ([0-9.]{1,})[\\/]([0-9.]{1,})[\\s]([0-9.])[\\/]([0-9.]{1,})[\\s]([0-9.])[\\/]([0-9.]{1,})";

	String	face_triangle	             = "^f ([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})[\\s]([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})[\\s]([0-9]{1,})[\\/]([0-9]{1,})[\\/]([0-9]{1,})";

	String	texture3f	                 = "^vt ([0-9.]{1,})[\\s]([0-9.]{1,})[\\s]([0-9.]{1,})";
	String	texture2f	                 = "^vt ([0-9.]{1,})[\\s]([0-9.]{1,})";

	public ModelLoader(ModelQuad model, String path) {
		try {
			File folder = new File("res/models/");
			for (File f_ : folder.listFiles()) {
				System.out.println(f_.getName());
			}
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/models/" + path)));
			MaterialLib lib = null;
			String line = "";
			String name = "";
			AbstractObject obj = null;
			int vectorCount = 0;
			int normalCount = 0;
			int textureCount = 0;
			int vCount = 0;
			int tCount = 0;
			int nCount = 0;
			Pattern pFace_quad_no_normals_1 = Pattern.compile(face_quad_no_normals_1);
			Pattern pFace_quad_no_normals_2 = Pattern.compile(face_quad_no_normals_2);
			Pattern pface_triangle = Pattern.compile(face_triangle);
			Pattern pface_quad = Pattern.compile(face_quad);
			Pattern pface_triangle_no_normals_1 = Pattern.compile(face_triangle_no_normals_1);
			Pattern pface_triangle_no_normals_2 = Pattern.compile(face_triangle_no_normals_2);

			Pattern ptexture2f = Pattern.compile(texture2f);
			Pattern ptexture3f = Pattern.compile(texture3f);

			Matcher mFace_quad_no_normals_1 = pFace_quad_no_normals_1.matcher("");
			Matcher mFace_quad_no_normals_2 = pFace_quad_no_normals_2.matcher("");
			Matcher mface_triangle = pface_triangle.matcher("");
			Matcher mface_quad = pface_quad.matcher("");
			Matcher mface_triangle_no_normals_1 = pface_triangle_no_normals_1.matcher("");
			Matcher mface_triangle_no_normals_2 = pface_triangle_no_normals_2.matcher("");

			Matcher mtexture2f = ptexture2f.matcher("");
			Matcher mtexture3f = ptexture3f.matcher("");

			while ((line = reader.readLine()) != null) {
				mFace_quad_no_normals_1 = pFace_quad_no_normals_1.matcher(line);
				mFace_quad_no_normals_2 = pFace_quad_no_normals_2.matcher(line);
				mface_triangle = pface_triangle.matcher(line);
				mface_quad = pface_quad.matcher(line);
				mface_triangle_no_normals_1 = pface_triangle_no_normals_1.matcher(line);
				mface_triangle_no_normals_2 = pface_triangle_no_normals_2.matcher(line);
				mtexture2f = ptexture2f.matcher(line);
				mtexture3f = ptexture3f.matcher(line);
				if (line.startsWith("mtllib ")) {
					lib = MaterialLib.loadFromFile(line.split(" ")[1]);
				}
				if (line.startsWith("usemtl ")) {
					System.out.println("New Material : " + line.split(" ")[1]);
					obj.setMaterialName(line.split(" ")[1]);
					obj.setMaterial(lib.getMaterial(obj.getMaterialName()));
				}
				if (line.startsWith("g ") || line.startsWith("o ")) {
					// System.out.println("New object : " + line);
					name = line.split(" ")[1];
					if (obj == null) {
						obj = new AbstractObject(name);
					} else {
						model.getObjects().add(obj);
						obj = new AbstractObject(name);
						vectorCount += vCount;
						textureCount += tCount;
						normalCount += nCount;
						vCount = 0;
						tCount = 0;
						nCount = 0;
					}
				} else if (line.startsWith("v ")) {
					// System.out.println("New vector : " + line);
					x = Float.valueOf(line.split(" ")[1]);
					y = Float.valueOf(line.split(" ")[2]);
					z = Float.valueOf(line.split(" ")[3]);
					// System.out.println("X:" + x + " Y:" + y + " Z:" + z);
					v = new Vector3f(x, y, z);
					obj.getVertecies().add(v);
					vCount++;
				} else if (line.startsWith("f ")) {
					System.out.print("New face : " + line + " ");
					if (mface_quad.find()) {
						System.out.println("QuadFace !");
						// System.out.println("Regex : " + face_quad);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
						y1 = Float.valueOf(line.split(" ")[1].split("/")[1]);
						z1 = Float.valueOf(line.split(" ")[1].split("/")[2]);
						x2 = Float.valueOf(line.split(" ")[2].split("/")[0]);
						y2 = Float.valueOf(line.split(" ")[2].split("/")[1]);
						z2 = Float.valueOf(line.split(" ")[2].split("/")[2]);
						x3 = Float.valueOf(line.split(" ")[3].split("/")[0]);
						y3 = Float.valueOf(line.split(" ")[3].split("/")[1]);
						z3 = Float.valueOf(line.split(" ")[3].split("/")[2]);
						x4 = Float.valueOf(line.split(" ")[4].split("/")[0]);
						y4 = Float.valueOf(line.split(" ")[4].split("/")[1]);
						z4 = Float.valueOf(line.split(" ")[4].split("/")[2]);
						obj.getFaces().add(new Face(new Vector4f(x1 - vectorCount, x2 - vectorCount, x3 - vectorCount, x4 - vectorCount), new Vector4f(z1 - normalCount, z2 - normalCount, z3 - normalCount, z4 - normalCount), new Vector4f(y1 - textureCount, y2 - textureCount, y3 - textureCount, y4 - textureCount)));
					} else if (mFace_quad_no_normals_1.find()) {
						// System.out.println("Regex : " + face_quad_no_normals_1);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
						y1 = Float.valueOf(line.split(" ")[1].split("/")[2]);
						x2 = Float.valueOf(line.split(" ")[2].split("/")[0]);
						y2 = Float.valueOf(line.split(" ")[2].split("/")[2]);
						x3 = Float.valueOf(line.split(" ")[3].split("/")[0]);
						y3 = Float.valueOf(line.split(" ")[3].split("/")[2]);
						x4 = Float.valueOf(line.split(" ")[4].split("/")[0]);
						y4 = Float.valueOf(line.split(" ")[4].split("/")[2]);
						obj.getFaces().add(new Face(new Vector4f(x1 - vectorCount, x2 - vectorCount, x3 - vectorCount, x4 - vectorCount), new Vector4f(y1 - textureCount, y2 - textureCount, y3 - textureCount, y4 - textureCount)));
					} else if (mFace_quad_no_normals_2.find()) {
						// System.out.println("Regex : " + face_quad_no_normals_2);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
						y1 = Float.valueOf(line.split(" ")[1].split("/")[1]);
						x2 = Float.valueOf(line.split(" ")[2].split("/")[0]);
						y2 = Float.valueOf(line.split(" ")[2].split("/")[1]);
						x3 = Float.valueOf(line.split(" ")[3].split("/")[0]);
						y3 = Float.valueOf(line.split(" ")[3].split("/")[1]);
						x4 = Float.valueOf(line.split(" ")[4].split("/")[0]);
						y4 = Float.valueOf(line.split(" ")[4].split("/")[1]);
						obj.getFaces().add(new Face(new Vector4f(x1 - vectorCount, x2 - vectorCount, x3 - vectorCount, x4 - vectorCount), new Vector4f(y1 - textureCount, y2 - textureCount, y3 - textureCount, y4 - textureCount)));
					} else if (mface_triangle.find()) {
						System.out.println("Triangle !");
						// System.out.println("Regex : " + face_triangle);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);// vector
						y1 = Float.valueOf(line.split(" ")[1].split("/")[1]);// texture
						z1 = Float.valueOf(line.split(" ")[1].split("/")[2]);// normal

						x2 = Float.valueOf(line.split(" ")[2].split("/")[0]);// vector
						y2 = Float.valueOf(line.split(" ")[2].split("/")[1]);// texture
						z2 = Float.valueOf(line.split(" ")[2].split("/")[2]);// normal

						x3 = Float.valueOf(line.split(" ")[3].split("/")[0]);// vector
						y3 = Float.valueOf(line.split(" ")[3].split("/")[1]);// texture
						z3 = Float.valueOf(line.split(" ")[3].split("/")[2]);// normal
						obj.getFaces().add(new Face(new Vector3f(x1, x2, x3), new Vector3f(y1, y2, y3), new Vector3f(z1, z2, z3)));
					} else if (mface_triangle_no_normals_1.find()) {
						// System.out.println("Regex : " + face_triangle_no_normals_1);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
						y1 = Float.valueOf(line.split(" ")[2].split("/")[0]);
						z1 = Float.valueOf(line.split(" ")[3].split("/")[0]);
						x2 = Float.valueOf(line.split(" ")[1].split("/")[2]);
						y2 = Float.valueOf(line.split(" ")[2].split("/")[2]);
						z2 = Float.valueOf(line.split(" ")[3].split("/")[2]);
						obj.getFaces().add(new Face(new Vector3f(x1, y1, z1), new Vector3f(x2, y2, z2)));
					} else if (mface_triangle_no_normals_2.find()) {
						// System.out.println("Regex : " + face_triangle_no_normals_2);
						x1 = Float.valueOf(line.split(" ")[1].split("/")[0]);
						y1 = Float.valueOf(line.split(" ")[2].split("/")[0]);
						z1 = Float.valueOf(line.split(" ")[3].split("/")[0]);
						x2 = Float.valueOf(line.split(" ")[1].split("/")[1]);
						y2 = Float.valueOf(line.split(" ")[2].split("/")[1]);
						z2 = Float.valueOf(line.split(" ")[3].split("/")[1]);
						obj.getFaces().add(new Face(new Vector3f(x1, y1, z1), new Vector3f(x2, y2, z2)));
					} else {
						System.out.println("no match ?");
					}
				} else if (line.startsWith("vt ")) {
					System.out.println("New texture : " + line);
					if (mtexture3f.find()) {
						// System.out.println("Texture 3f");
						x = Float.valueOf(line.split(" ")[1]);
						y = Float.valueOf(line.split(" ")[2]);
						z = Float.valueOf(line.split(" ")[3]);
						// System.out.println("X:" + x + " Y:" + y + " Z:" + z);
						v = new Vector3f(x, y, z);
						obj.getTextures().add(v);
						tCount++;
					} else if (mtexture2f.find()) {
						// System.out.println("Texture 2f");
						x = Float.valueOf(line.split(" ")[1]);
						y = Float.valueOf(line.split(" ")[2]);
						// System.out.println("X:" + x + " Y:" + y + " Z:" + 0);
						v = new Vector3f(x, y, 0);
						obj.getTextures().add(v);
						tCount++;
					}
				} else if (line.startsWith("vn ")) {
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
			if (obj != null) {
				model.getObjects().add(obj);
			}
			reader.close();
			for (Material m : lib.getMaterials()) {
				System.out.println(m.getName());
			}
			System.out.println("Total Vectors : " + vectorCount);
			System.out.println("Total Normals : " + normalCount);
			System.out.println("Total Texture : " + textureCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
