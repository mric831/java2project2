package edu.ncsu.csc216.incident_management.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.incident_management.model.command.Command.CancellationCode;
import edu.ncsu.csc216.incident_management.model.command.Command.CommandValue;
import edu.ncsu.csc216.incident_management.model.command.Command.OnHoldReason;
import edu.ncsu.csc216.incident_management.model.command.Command.ResolutionCode;
/**
 * Tests the various methods of the Command class
 * @author Max Richgruber
 *
 */
public class CommandTest {
	/**
	 * Tests the functionality of the command constructor
	 */
	@Test
	public void testCommand() {
		
		try {
			Command c1 = new Command(null, "test", null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, null );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.INVESTIGATE, null, null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.INVESTIGATE, "", null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.HOLD, "test", null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.RESOLVE, "test", null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		try {
			Command c1 = new Command(CommandValue.CANCEL, "test", null, null, null, "note" );
			c1.getCommand();
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
		
	}
	/**
	 * Tests the functionality of the command getter
	 */
	@Test
	public void testGetCommand() {
		Command c1 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note" );
		assertEquals(c1.getCommand(), CommandValue.INVESTIGATE);
	}
	/**
	 * Tests the functionality of the owner id getter
	 */
	@Test
	public void testGetOwnerId() {
		Command c1 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note" );
		assertEquals(c1.getOwnerId(), "test");
	}
	/**
	 * Tests the functionality of the resolution code getter
	 */
	@Test
	public void testGetResolutionCode() {
		Command c1 = new Command(CommandValue.RESOLVE, "test", null, ResolutionCode.NOT_SOLVED, null, "note" );
		assertEquals(c1.getResolutionCode(), ResolutionCode.NOT_SOLVED);
	}
	/**
	 * Tests the functionality of the work note getter
	 */
	@Test
	public void testGetWorkNote() {
		Command c1 = new Command(CommandValue.INVESTIGATE, "test", null, null, null, "note" );
		assertEquals(c1.getWorkNote(), "note");
	}
	/**
	 * Tests the functionality of the on hold reason getter
	 */
	@Test
	public void testGetOnHoldReason() {
		Command c1 = new Command(CommandValue.HOLD, "test", OnHoldReason.AWAITING_CALLER, null, null, "note" );
		assertEquals(c1.getOnHoldReason(), OnHoldReason.AWAITING_CALLER);
	}
	/**
	 * Tests the functionality of the cancellation code getter
	 */
	@Test
	public void testGetCancellationCode() {
		Command c1 = new Command(CommandValue.CANCEL, "test", null, null, CancellationCode.NOT_AN_INCIDENT, "note" );
		assertEquals(c1.getCancellationCode(), CancellationCode.NOT_AN_INCIDENT);
	}

}
