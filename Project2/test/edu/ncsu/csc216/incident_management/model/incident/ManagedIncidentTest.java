package edu.ncsu.csc216.incident_management.model.incident;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.incident.xml.Incident;
import edu.ncsu.csc216.incident.xml.WorkNotes;
import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.command.Command.CancellationCode;
import edu.ncsu.csc216.incident_management.model.command.Command.CommandValue;
import edu.ncsu.csc216.incident_management.model.command.Command.OnHoldReason;
import edu.ncsu.csc216.incident_management.model.command.Command.ResolutionCode;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;
/**
 * Tests the various methods of the Managed Incident class
 * @author Max Richgruber
 *
 */
public class ManagedIncidentTest {
	/**
	 * Tests functionality of the constructor with parameters
	 */
	@Test
	public void testManagedIncidentStringCategoryPriorityStringString() {
		try {
			ManagedIncident m = new ManagedIncident(null, Category.DATABASE, Priority.HIGH, "name", "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("", Category.DATABASE, Priority.HIGH, "name", "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", null, Priority.HIGH, "name", "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, null, "name", "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, null, "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "", "note");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", null);
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "");
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getIncidentId(), 0);
		ManagedIncident m2 = new ManagedIncident("caller2", Category.DATABASE, Priority.HIGH, "name2", "note2");
		assertEquals(m2.getIncidentId(), 1);
		ManagedIncident.setCounter(0);
		
	}
	/**
	 * Tests functionality of the constructor with a default incident
	 */
	@Test
	public void testManagedIncidentIncident() {
		Incident i = new Incident();
		i.setCaller("caller");
		i.setCategory("Database");
		i.setPriority("High");
		i.setOwner("name");
		i.setState("New");
		WorkNotes w = new WorkNotes();
		i.setWorkNotes(w);
		ManagedIncident m  = new ManagedIncident(i);
		assertNotNull(m);
		ManagedIncident.setCounter(0);
		
	}
	/**
	 * Tests functionality of the method that increments the counter
	 */
	@Test
	public void testIncrementCounter() {
		ManagedIncident.incrementCounter();
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getIncidentId(), 1);
		ManagedIncident.incrementCounter();
		ManagedIncident.incrementCounter();
		ManagedIncident.incrementCounter();
		ManagedIncident m2 = new ManagedIncident("caller2", Category.DATABASE, Priority.HIGH, "name2", "note2");
		assertEquals(m2.getIncidentId(), 5);
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of the incident id getter
	 */
	@Test
	public void testGetIncidentId() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getIncidentId(), 0);
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of the change request method
	 */
	@Test
	public void testGetChangeRequest() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertNull(m.getChangeRequest());
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of category getter
	 */
	@Test
	public void testGetCategory() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getCategory(), Category.DATABASE);
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of category string getter
	 */
	@Test
	public void testGetCategoryString() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getCategoryString(), "Database");
		ManagedIncident m2 = new ManagedIncident("caller", Category.HARDWARE, Priority.HIGH, "name", "note");
		assertEquals(m2.getCategoryString(), "Hardware");
		ManagedIncident m3 = new ManagedIncident("caller", Category.INQUIRY, Priority.HIGH, "name", "note");
		assertEquals(m3.getCategoryString(), "Inquiry");
		ManagedIncident m4 = new ManagedIncident("caller", Category.NETWORK, Priority.HIGH, "name", "note");
		assertEquals(m4.getCategoryString(), "Network");
		ManagedIncident m5 = new ManagedIncident("caller", Category.SOFTWARE, Priority.HIGH, "name", "note");
		assertEquals(m5.getCategoryString(), "Software");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of priority string getter
	 */
	@Test
	public void testGetPriorityString() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getPriorityString(), "High");
		ManagedIncident m2 = new ManagedIncident("caller", Category.DATABASE, Priority.URGENT, "name", "note");
		assertEquals(m2.getPriorityString(), "Urgent");
		ManagedIncident m3 = new ManagedIncident("caller", Category.DATABASE, Priority.LOW, "name", "note");
		assertEquals(m3.getPriorityString(), "Low");
		ManagedIncident m4 = new ManagedIncident("caller", Category.DATABASE, Priority.MEDIUM, "name", "note");
		assertEquals(m4.getPriorityString(), "Medium");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of on hold reason string getter
	 */
	@Test
	public void testGetOnHoldReasonString() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		Command c2 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		m.update(c);
		m.update(c2);
		assertEquals(m.getOnHoldReasonString(), "Awaiting Caller");
		c = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		m.update(c);
		c2 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CHANGE, null, null, "note");
		m.update(c2);
		assertEquals(m.getOnHoldReasonString(), "Awaiting Change");
		m.update(c);
		c2 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_VENDOR, null, null, "note");
		m.update(c2);
		assertEquals(m.getOnHoldReasonString(), "Awaiting Vendor");
	}
	/**
	 * Tests functionality of cancellation code string getter
	 */
	@Test
	public void testGetCancellationCodeString() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getCancellationCodeString(), null);
		Command c = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		m.update(c);
		assertEquals(m.getCancellationCodeString(), "Duplicate");
		m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		c = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.NOT_AN_INCIDENT, "note");
		m.update(c);
		assertEquals(m.getCancellationCodeString(), "Not an Incident");
		m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		c = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.UNNECESSARY, "note");
		m.update(c);
		assertEquals(m.getCancellationCodeString(), "Unnecessary");
	}
	
	/**
	 * Tests functionality of resolution code string getter
	 */
	@Test
	public void testGetResolutionCodeString() {
		ManagedIncident m1 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		ManagedIncident m2 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		ManagedIncident m3 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		ManagedIncident m4 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m1.getResolutionCodeString(), null);
		Command investigate = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		m1.update(investigate);
		m2.update(investigate);
		m3.update(investigate);
		m4.update(investigate);
		Command r1 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.CALLER_CLOSED, null, "note");
		Command r2 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.NOT_SOLVED, null, "note");
		Command r3 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		Command r4 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.WORKAROUND, null, "note");
		m1.update(r1);
		m2.update(r2);
		m3.update(r3);
		m4.update(r4);
		assertEquals(m1.getResolutionCodeString(), "Caller Closed");
		assertEquals(m2.getResolutionCodeString(), "Not Solved");
		assertEquals(m3.getResolutionCodeString(), "Permanently Solved");
		assertEquals(m4.getResolutionCodeString(), "Workaround");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of owner getter
	 */
	@Test
	public void testGetOwner() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		m.update(c);
		assertEquals(m.getOwner(), "test");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of name getter
	 */
	@Test
	public void testGetName() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getName(), "name");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of caller getter
	 */
	@Test
	public void testGetCaller() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(m.getCaller(), "caller");
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of notes getter
	 */
	@Test
	public void testGetNotes() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		ArrayList<String> match = new ArrayList<String>();
		match.add("note");
		assertEquals(m.getNotes(), match);
		Command c = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note2");
		match.add("note2");
		m.update(c);
		assertEquals(m.getNotes(), match);
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of notes string getter
	 */
	@Test
	public void testGetNotesString() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		String match = "[note]";
		assertEquals(m.getNotesString(), match);
		Command c = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note2");
		match = "[note, note2]";
		m.update(c);
		assertEquals(m.getNotesString(), match);
		ManagedIncident.setCounter(0);
	}
	/**
	 * Tests functionality of update method
	 */
	@Test
	public void testUpdate() {
		Command c = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		assertEquals(c.getCommand(), CommandValue.CANCEL);
		assertEquals(m.getState().getStateName(), "New");
		m.update(c);
		assertEquals(m.getCancellationCodeString(), "Duplicate");
		Command c2 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		ManagedIncident m2 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		try {
			m2.update(c2);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		Command c3 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.NOT_SOLVED, null, "note");
		ManagedIncident m3 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		try {
			m3.update(c3);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		Command c4 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		ManagedIncident m4 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		try {
			m4.update(c4);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		Command c5 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		ManagedIncident m5 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		try {
			m5.update(c5);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		Command c6 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		ManagedIncident m6 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		//Investigate
		m6.update(c6);
		assertEquals(m6.getName(), "name");
		assertEquals(m6.getState().getStateName(), "In Progress");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c6 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c6 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
			//Investigate to On Hold
		c6 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		m6.update(c6);
		assertEquals(m6.getOnHoldReasonString(), "Awaiting Caller");
		assertEquals(m6.getState().getStateName(), "On Hold");
			//Investigate to Resolved
		ManagedIncident m7 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c7 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		m7.update(c7);
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		m7.update(c7);
		assertEquals(m7.getResolutionCode(), ResolutionCode.PERMANENTLY_SOLVED);
		assertEquals(m7.getResolutionCodeString(), "Permanently Solved");
		assertEquals(m7.getState().getStateName(), "Resolved");
			//Investigate to Cancel
		ManagedIncident m8 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Command c8 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		m8.update(c8);
		c8 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		m8.update(c8);
		assertEquals(m8.getCancellationCodeString(), "Duplicate");
		assertEquals(m8.getState().getStateName(), "Canceled");
		//On Hold
		c6 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c6 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c6 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		try {
			m6.update(c6);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
			//On Hold to Resolved
		c6 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		m6.update(c6);
		assertEquals(m6.getResolutionCodeString(), "Permanently Solved");
		assertEquals(m6.getState().getStateName(), "Resolved");
			//On Hold to In Progress
		Command c9 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		ManagedIncident m9 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		m9.update(c9);
		c9 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		m9.update(c9);
		c9 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		m9.update(c9);
		assertEquals(m9.getState().getStateName(), "In Progress");
			//On Hold to Canceled
		Command c10 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		ManagedIncident m10 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		m10.update(c10);
		c10 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		m10.update(c10);
		c10 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		m10.update(c10);
		assertEquals(m10.getCancellationCodeString(), "Duplicate");
		assertEquals(m10.getState().getStateName(), "Canceled");
		//Resolved
		c7 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.CALLER_CLOSED, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
			//Resolved to On Hold
		c7 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		m7.update(c7);
		assertEquals(m7.getOnHoldReasonString(), "Awaiting Caller");
		assertEquals(m7.getResolutionCodeString(), null);
		assertEquals(m7.getState().getStateName(), "On Hold");
			//Resolved to Closed
		c7 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		m7.update(c7);
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		m7.update(c7);
		c7 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		m7.update(c7);
		assertEquals(m7.getState().getStateName(), "Closed");
			//Closed to In Progress 
		c7 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		m7.update(c7);
		assertEquals(m7.getState().getStateName(), "In Progress");
			//Resolved to In Progress
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		m7.update(c7);
		c7 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		m7.update(c7);
		assertEquals(m7.getResolutionCodeString(), null);
		assertEquals(m7.getState().getStateName(), "In Progress");
			//Resolved to Canceled
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.PERMANENTLY_SOLVED, null, "note");
		m7.update(c7);
		c7 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		m7.update(c7);
		assertEquals(m7.getCancellationCodeString(), "Duplicate");
		assertEquals(m7.getState().getStateName(), "Canceled");
		//Canceled
		c7 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.CALLER_CLOSED, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.REOPEN, "test", null, null, null, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c7 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		try {
			m7.update(c7);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		//Closed
		Command c11 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		ManagedIncident m11 = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		m11.update(c11);
		c11 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.CALLER_CLOSED, null, "note");
		m11.update(c11);
		c11 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		m11.update(c11);
		assertEquals(m11.getState().getStateName(), "Closed");
		c11 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note");
		try {
			m11.update(c11);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c11 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note");
		try {
			m11.update(c11);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c11 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.CALLER_CLOSED, null, "note");
		try {
			m11.update(c11);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c11 = new Command(CommandValue.CONFIRM, "test", null, null, null, "note");
		try {
			m11.update(c11);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		c11 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.DUPLICATE, "note");
		try {
			m11.update(c11);
		} catch(UnsupportedOperationException e) {
			assertNotNull(e);
		}
		ManagedIncident.setCounter(0);
		
	}
	/**
	 * Tests functionality of xml incident getter
	 */
	@Test
	public void testGetXMLIncident() {
		ManagedIncident m = new ManagedIncident("caller", Category.DATABASE, Priority.HIGH, "name", "note");
		Incident i = m.getXMLIncident();
		assertEquals(i.getCaller(), "caller");
		assertEquals(i.getCategory(), "Database");
		assertEquals(i.getPriority(), "High");
		assertEquals(i.getName(), "name");
		assertEquals(i.getWorkNotes().getNotes().get(0), "note");
	}
	/**
	 * Tests functionality of counter setter
	 */
	@Test
	public void testSetCounter() {
	
	}

}
