package main;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class LoadFromFileTest {

	LoadFromFile loadFromFile;

	@Before
	public void setUp() throws Exception {
		loadFromFile = new LoadFromFile();
	}

	@Test
	public void testLoad() {
		// fail("Not yet implemented");
		loadFromFile.setFileName("C:\\Users\\jmmodi\\Documents\\abc");
		LinkedList<Object> list = loadFromFile.load();
		assertNotNull(list);

	}

	@Test(expected = NullPointerException.class)
	public void testLoadWithException() {
		LinkedList<Object> list = loadFromFile.load();

	}

}
