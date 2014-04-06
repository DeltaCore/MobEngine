package net.ccmob.engine.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.ccmob.engine.core.MobEngine;

public class ResourceLoader {

	/**
	 * 
	 * @param fileKey
	 *          -> starts in ./res/ + fileKey -> a valid file key : models/xyz.abc
	 * @return the content of the given file
	 * 
	 */
	public static String loadFileContentAsString(String fileKey) {
		StringBuilder content = new StringBuilder();
		try {
			Class<MobEngine> cls = MobEngine.class;
			ClassLoader cLoader = cls.getClassLoader();
			InputStream i = cLoader.getResourceAsStream(fileKey);
			BufferedReader r = new BufferedReader(new InputStreamReader(i));
			String l;
			while ((l = r.readLine()) != null) {
				content.append(l).append('\n');
			}
			i.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return content.toString();
	}

	public static ArrayList<String> loadFileContentAsArrayList(String fileKey) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class<MobEngine> cls = MobEngine.class;
			ClassLoader cLoader = cls.getClassLoader();
			InputStream i = cLoader.getResourceAsStream(fileKey);
			BufferedReader r = new BufferedReader(new InputStreamReader(i));
			String l;
			while ((l = r.readLine()) != null) {
				list.add(l);
			}
			i.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

}
