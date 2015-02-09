package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ComputeCoordinatesTest {
	private ComputeCoordinates computeCoordinates;

	@Before
	public void setUp() throws Exception {
		computeCoordinates = new ComputeCoordinates(false);

	}

	@Test
	public void testGetUndoObjects() {

		ArrayList<Object> testList = computeCoordinates.getUndoObjects();
		assertNotNull(testList);

	}

	@Test
	public void testGetListShapeObjects() {
		// fail("Not yet implemented");
		ArrayList<Object> testlist = computeCoordinates.getListShapeObjects();
		//assertNotNull(testlist);
	}

	@Test
	public void testGetBrickFlags() {
		// fail("Not yet implemented");
		ArrayList<Boolean> booleanList = computeCoordinates.getBrickFlags();
		assertNotNull(booleanList);
	}

	@Test
	public void testUpdateDisplayClock() {
		// fail("Not yet implemented");

		String expected = "00:55";
		computeCoordinates.setTimerTracker(120);
		computeCoordinates.setCurrentMinute(0);
		computeCoordinates.setCurrentSecond(55);
		String actualString = computeCoordinates.updateDisplayClock();
		assertEquals(expected, actualString);

	}

	@Test
	public void testGameData() {
		// fail("Not yet implemented");
		StoreDimensions testDimensions = computeCoordinates.gameData();
		assertNotNull(testDimensions);

	}

}
