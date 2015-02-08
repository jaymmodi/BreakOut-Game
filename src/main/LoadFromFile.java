package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import javax.swing.JFileChooser;

public class LoadFromFile {

	LinkedList<Object> loadedList;
	String fileName;
	
	public LinkedList<Object> getLoadedList() {
		return loadedList;
	}

	public void setLoadedList(LinkedList<Object> loadedList) {
		this.loadedList = loadedList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LoadFromFile() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public LinkedList<Object> load() {
		loadedList = new LinkedList<Object>();

		
		
		try {
			FileInputStream fileInputStream = new FileInputStream(
					getFileName());
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
