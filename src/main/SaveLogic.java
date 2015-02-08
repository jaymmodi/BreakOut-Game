package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class SaveLogic {

	/**
	 * 
	 */
	private FileOutputStream fileOutputStream;
	private ObjectOutputStream objectOutputStream;
	private LinkedList<Object> listToSave;

	public SaveLogic(LinkedList<Object> listToSave) {
		// TODO Auto-generated constructor stub
		this.listToSave = listToSave;
	}

	public void save() {
		try {
			fileOutputStream = new FileOutputStream("src\\save\\save.txt");
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			// listToSave = timerObservable.getReplayList();
			System.out.println(listToSave.size());
			objectOutputStream.writeObject(listToSave);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ObjectOutput Stream Exception");
		}

	}
	
	

}
