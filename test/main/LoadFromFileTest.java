package main;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.xpath.internal.operations.String;

public class LoadFromFileTest {

	LoadFromFile loadFromFile;
	FileOutputStream fileOutputStream;
	ObjectOutputStream objectOutputStream;
	LinkedList<Object> testList;

	@Before
	public void setUp() throws Exception {
		loadFromFile = new LoadFromFile();
		fileOutputStream = new FileOutputStream("testFile");
		objectOutputStream = new ObjectOutputStream(fileOutputStream);
		testList = new LinkedList<Object>();
		testList.add(new String());
		testList.add(new String());
		objectOutputStream.writeObject(testList);
		objectOutputStream.close();
		fileOutputStream.close();
	}

	@Test
	public void testLoad() {
		// fail("Not yet implemented");
		loadFromFile.setFileName("testFile");
		LinkedList<Object> list = loadFromFile.load();
		assertNotNull(list);

	}

	@Test(expected = NullPointerException.class)
	public void testLoadWithException() {
		LinkedList<Object> list = loadFromFile.load();

	}

}
