package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class LoadFromFile {

	LinkedList<Object> loadedList;

	public LoadFromFile() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public LinkedList<Object> load() {
		loadedList = new LinkedList<Object>();

		try {
			FileInputStream fileInputStream = new FileInputStream(
					"src\\save\\save.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(
					fileInputStream);
			loadedList = (LinkedList<Object>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			System.out.println(loadedList.size());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loadedList;
	}

}
