package edu.ncsu.csc216.incident_management.model.manager;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.command.Command.CancellationCode;
import edu.ncsu.csc216.incident_management.model.command.Command.CommandValue;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;
/**
 * Tests the methods of the Incident Manager class
 * @author Max Richgruber
 *
 */
public class IncidentManagerTest {
	/**
	 * Tests functionality of instance getter
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(IncidentManager.getInstance());
		
	}
	/**
	 * Tests functionality of method that saves incidents
	 */
	@Test
	public void testSaveManagedIncidentsToFile() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		i.addManagedIncidentToList("caller2", Category.HARDWARE, Priority.HIGH, "name2", "note2");
		try {
			i.saveManagedIncidentsToFile("invalid-file");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		i.saveManagedIncidentsToFile("test-files/test1.xml");
	}
	/**
	 * Tests functionality of method that loads incidents
	 */
	@Test
	public void testLoadManagedIncidentsFromFile() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		try {
			i.loadManagedIncidentsFromFile("NOT_A_FILE");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		i.loadManagedIncidentsFromFile("test-files/incident1.xml");
		assertEquals(i.getManagedIncidentById(1).getCaller(), "sesmith5");
	}
	/**
	 * Tests functionality of method that creates new list
	 */
	@Test
	public void testCreateNewManagedIncidentList() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		i.createNewManagedIncidentList();
		assertNull(i.getManagedIncidentById(0));
	}
	/**
	 * Tests functionality of managed incident array getter
	 */
	@Test
	public void testGetManagedIncidentsAsArray() {
		IncidentManager i = IncidentManager.getInstance();
		try {
			i.getManagedIncidentsAsArrayByCategory(null);
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		i.createNewManagedIncidentList();
		String[][] compare = new String[2][5];
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		i.addManagedIncidentToList("caller2", Category.HARDWARE, Priority.HIGH, "name2", "note2");
		compare [0][0] = "0";
		compare [0][1] = "Database";
		compare [0][2] = "New";
		compare [0][3] = "High";
		compare [0][4] = "name";
		compare [1][0] = "1";
		compare [1][1] = "Hardware";
		compare [1][2] = "New";
		compare [1][3] = "High";
		compare [1][4] = "name2";
		String[][] result = i.getManagedIncidentsAsArray();
		assertEquals(result[0][0], compare[0][0]);
		assertEquals(result[0][1], compare[0][1]);
		assertEquals(result[0][2], compare[0][2]);
		assertEquals(result[0][3], compare[0][3]);
		assertEquals(result[0][4], compare[0][4]);
		assertEquals(result[1][0], compare[1][0]);
		assertEquals(result[1][1], compare[1][1]);
		assertEquals(result[1][2], compare[1][2]);
		assertEquals(result[1][3], compare[1][3]);
		assertEquals(result[1][4], compare[1][4]);
		
	}
	/**
	 * Tests functionality of managed incident array by category getter
	 */
	@Test
	public void testGetManagedIncidentsAsArrayByCategory() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		String[][] compare = new String[1][5];
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		i.addManagedIncidentToList("caller2", Category.HARDWARE, Priority.HIGH, "name2", "note2");
		
		compare [0][0] = "1";
		compare [0][1] = "Hardware";
		compare [0][2] = "New";
		compare [0][3] = "High";
		compare [0][4] = "name2";
		String[][] result = i.getManagedIncidentsAsArrayByCategory(Category.HARDWARE);
		assertEquals(result[0][0], compare[0][0]);
		assertEquals(result[0][1], compare[0][1]);
		assertEquals(result[0][2], compare[0][2]);
		assertEquals(result[0][3], compare[0][3]);
		assertEquals(result[0][4], compare[0][4]);
	}
	/**
	 * Tests functionality of incident by id getter
	 */
	@Test
	public void testGetManagedIncidentById() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(i.getManagedIncidentById(0).getCaller(), "caller");
	}
	/**
	 * Tests functionality of method that executes commands
	 */
	@Test
	public void testExecuteCommand() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c = new Command(CommandValue.CANCEL, "owner", null, null, CancellationCode.DUPLICATE, "note");
		i.executeCommand(0, c);
		assertEquals(i.getManagedIncidentById(0).getCancellationCodeString(), "Duplicate");
	}
	/**
	 * Tests functionality of method that deletes incidents by id
	 */
	@Test
	public void testDeleteManagedIncidentById() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		i.addManagedIncidentToList("caller2", Category.DATABASE, Priority.HIGH, "name2", "note2");
		i.deleteManagedIncidentById(0);
		assertEquals(i.getManagedIncidentById(1).getCaller(), "caller2");
	}
	/**
	 * Tests functionality of method that adds incidents to the list
	 */
	@Test
	public void testAddManagedIncidentToList() {
		IncidentManager i = IncidentManager.getInstance();
		i.createNewManagedIncidentList();
		i.addManagedIncidentToList("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertNotNull(i);
		
	}

}
