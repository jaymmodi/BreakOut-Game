package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

/**
 * Class LoadFromFile - this class is used to read serialized objects from file
 * 
 * load() - this function returns the list which it reads from the file.
 */
public class LoadFromFile {

	private LinkedList<Object> loadedList;
	private String fileName;

	/*
	 * @returns LinkedList - returns the list read from the file.
	 */
	public LinkedList<Object> getLoadedList() {
		return loadedList;
	}

	/*
	 * @param loadedList - sets the list which is read from the file as new
	 * list.
	 */
	public void setLoadedList(LinkedList<Object> loadedList) {
		this.loadedList = loadedList;
	}

	/*
	 * @returns fileName - returns the name of the file.
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * @param - sets the name of the file.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LoadFromFile() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Method - returns the list which it reads from the file.
	 */
	@SuppressWarnings("unchecked")
	public LinkedList<Object> load() {
		loadedList = new LinkedList<Object>();
		try {
			FileInputStream fileInputStream = new FileInputStream(getFileName());
			ObjectInputStream objectInputStream = new ObjectInputStream(
					fileInputStream);
			loadedList = (LinkedList<Object>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found Exception");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception");
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		return loadedList;
	}

}
