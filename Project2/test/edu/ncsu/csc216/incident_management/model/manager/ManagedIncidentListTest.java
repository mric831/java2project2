package edu.ncsu.csc216.incident_management.model.manager;

import static org.junit.Assert.*;


import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.incident.xml.Incident;
import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.command.Command.CancellationCode;
import edu.ncsu.csc216.incident_management.model.command.Command.CommandValue;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;
/**
 * Tests the methods of the Managed Incident List
 * @author Max Richgruber
 *
 */
public class ManagedIncidentListTest {
	/**
	 * Tests Managed Incident List constructor
	 */
	@Test
	public void testManagedIncidentList() {
		ManagedIncidentList list = new ManagedIncidentList();
		assertNotNull(list.getManagedIncidents());
	}
	/**
	 * Tests method that adds incident to list
	 */
	@Test
	public void testAddIncident() {
		ManagedIncidentList list = new ManagedIncidentList();
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(list.addIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note"), 0);
		assertEquals(list.getManagedIncidents().get(0).getCaller(),  m.getCaller());
		
	}
	/**
	 * Tests method that adds xml incidents to list
	 */
	@Test
	public void testAddXMLIncidents() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Incident i = m.getXMLIncident();
		assertEquals(i.getCaller(), "caller");
		assertEquals(i.getCategory(), "Database");
		assertEquals(i.getPriority(), "High");
		assertEquals(i.getName(), "name");
		assertEquals(i.getWorkNotes().getNotes().get(0), "note");
		ManagedIncidentList list = new ManagedIncidentList();
		ArrayList<Incident> tempList = new ArrayList<Incident>();
		tempList.add(i);
		list.addXMLIncidents(tempList);
		assertEquals(list.getManagedIncidents().get(0).getCaller(), m.getCaller());
		
	}
	/**
	 * Tests method that gets the list of incidents
	 */
	@Test
	public void testGetManagedIncidents() {
		ManagedIncidentList list = new ManagedIncidentList();
		assertEquals(list.getManagedIncidents().size(), 0);
		
	}
	/**
	 * Tests method that gets the list of incidents by category
	 */
	@Test
	public void testGetIncidentsByCategory() {
		ManagedIncidentList list = new ManagedIncidentList();
		list.addIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		list.addIncident("caller2", Category.HARDWARE, Priority.HIGH, "name2", "note2");
		assertEquals(list.getIncidentsByCategory(Category.HARDWARE).get(0).getCaller(), "caller2");
		assertEquals(list.getIncidentsByCategory(Category.SOFTWARE).size(), 0);
	}
	/**
	 * Tests method that gets a specific incident from list
	 */
	@Test
	public void testGetIncidentById() {
		ManagedIncidentList list = new ManagedIncidentList();
		list.addIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		list.addIncident("caller2", Category.HARDWARE, Priority.HIGH, "name2", "note2");
		assertEquals(list.getIncidentById(1).getCaller(), "caller2");
	}
	/**
	 * Tests method that executes a command on the list
	 */
	@Test
	public void testExecuteCommand() {
		ManagedIncidentList list = new ManagedIncidentList();
		list.addIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c = new Command(CommandValue.CANCEL, "owner", null, null, CancellationCode.DUPLICATE, "note");
		list.executeCommand(0, c);
		assertEquals(list.getIncidentById(0).getCancellationCodeString(), "Duplicate");
	}
	/**
	 * Tests method that deletes a specific incident from list
	 */
	@Test
	public void testDeleteIncidentById() {
		ManagedIncidentList list = new ManagedIncidentList();
		list.addIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		list.deleteIncidentById(0);
		assertEquals(list.getManagedIncidents().size(), 0);
	}

}
