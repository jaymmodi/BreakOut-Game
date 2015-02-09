package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * SaveLogic Class This class saves the replay list as a object in a file
 * (Serialization)
 * 
 * save() - Creates a new fileOutputStream object and writes replay list to this
 * file.
 *
 */
public class SaveLogic {
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	private LinkedList<Object> listToSave;
	private String fileName;

	/*
	 * @returns String - returns name of the file.
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * @param fileName - this is used to set the name of the file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/*
	 * @param listToSave - This is the linkedList which contains all the
	 * coordinates of the ball, paddle and brick.
	 */
	public void setListToSave(LinkedList<Object> listToSave) {
		this.listToSave = listToSave;
	}

	/*
	 * @returns LinkedList<Object> - returns the linkedList which is being saved
	 * in the file.
	 */
	public LinkedList<Object> getListToSave() {
		return listToSave;
	}

	/*
	 * Method - this method it to used to serialize the replayList into file.
	 */
	public void save() {
		try {
			fileOutputStream = new FileOutputStream(getFileName());
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(listToSave);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("ObjectOutput Stream Exception");
		}

	}
}
