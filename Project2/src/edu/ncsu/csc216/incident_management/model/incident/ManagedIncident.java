package edu.ncsu.csc216.incident_management.model.incident;

import java.util.ArrayList;

import edu.ncsu.csc216.incident.xml.Incident;

import edu.ncsu.csc216.incident_management.model.command.Command;

/**
 * Class that describes the instances and behaviors of Managed Incidents
 * @author Max Richgruber
 *
 */
public class ManagedIncident {
	/** Category of Inquiry incidents */
	public static final String C_INQUIRY = "Inquiry";
	/** Category of Software incidents */
	public static final String C_SOFTWARE = "Software";
	/** Category of Hardware incidents */
	public static final String C_HARDWARE = "Hardware";
	/** Category of Database incidents */
	public static final String C_DATABASE = "Database";
	/** Category of Network incidents */
	public static final String C_NETWORK = "Network";
	/** Urgent priority string */
	public static final String P_URGENT = "Urgent";
	/** High priority string */
	public static final String P_HIGH = "High";
	/** Medium priority string */
	public static final String P_MEDIUM = "Medium";
	/** Low priority string */
	public static final String P_LOW = "Low";
	/** The incident's id */
	private int incidentId;
	/** The caller of the incident */
	private String caller;
	/** The owner of the incident */
	private String owner;
	/** The name of the incident */
	private String name;
	/** The request to change the incident */
	private String changeRequest;
	/** The notes associated with the incident */
	private ArrayList<String> notes;
	/** New state string */
	public static final String NEW_NAME = "New";
	/** In progress state string */
	public static final String IN_PROGRESS_NAME = "In Progress";
	/** On hold state string */
	public static final String ON_HOLD_NAME = "On Hold";
	/** Resolved state string */
	public static final String RESOLVED_NAME = "Resolved";
	/** Closed state string */
	public static final String CLOSED_NAME = "Closed";
	/** Canceled state string */
	public static final String CANCELED_NAME = "Canceled";
	/** Counter to keep track of incidents */
	private static int counter = 0;
	/** All possible priorities */
	public enum Priority { URGENT, HIGH, MEDIUM, LOW}
	/** All possible cancellation codes */
	public enum CancellationCode { DUPLICATE, UNNECESSARY, NOT_AN_INCIDENT }
	/** All possible resolution codes */
	public enum ResolutionCode { PERMANENTLY_SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/** All possible on hold reasons */
	public enum OnHoldReason { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_VENDOR }
	/** All possible categories */
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }
	/** New state */
	private IncidentState newState;
	/** Current state */
	private IncidentState state;
	/** Resolved state */
	private IncidentState resolvedState;
	/** On hold state */
	private IncidentState onHoldState;
	/** Closed state */
	private IncidentState closedState;
	/** In progress state */
	private IncidentState inProgressState;
	/** Canceled state */
	private IncidentState canceledState;
	/** Priority of the incident */
	private Priority priority;
	/** Cancellation code of the incident */
	private CancellationCode cancellationCode;
	/** Resolution code of the incident */
	private ResolutionCode resolutionCode;
	/** On hold reason of the incident */
	private OnHoldReason onHoldReason;
	/** Category of the incident */
	private Category category;
	/**
	 * ManagedIncident constructor from parameters
	 * @param caller the person calling in the incident
	 * @param c the category the incident falls under
	 * @param p the priority level of the incident
	 * @param name the name of the incident
	 * @param workNote the note describing the incident
	 */
	public ManagedIncident(String caller, Category c, Priority p, String name, String workNote) {
		if(caller == null || caller.equals("")) {
			throw new IllegalArgumentException();
		} else if(c == null) {
			throw new IllegalArgumentException();
		} else if(p == null) {
			throw new IllegalArgumentException();
		} else if(name == null || name.equals("")) {
			throw new IllegalArgumentException();
		} else if(workNote == null || workNote.equals("")) {
			throw new IllegalArgumentException();
		}
		this.incidentId = counter;
		incrementCounter();
		this.caller = caller;
		this.category = c;
		this.priority = p;
		this.name = name;
		this.notes.add(workNote);
		this.cancellationCode = null;
		this.resolutionCode = null;
		this.onHoldReason = null;
		this.owner = null;
		this.changeRequest = null;
	}
	/**
	 * Constructor for Managed incidents when given an incident
	 * @param i the incident to create as a managed incident
	 */
	public ManagedIncident(Incident i) {
		/////////////
	}
	/**
	 * Increments the counter variable
	 */
	public static void incrementCounter() {
		counter++;
	}
	/**
	 * Gets the id of this instance of incident
	 * @return incident id
	 */
	public int getIncidentId() {
		return 0;
	}
	/**
	 * Get the change string that the incident is attempting
	 * @return the change that is being requested for the incident
	 */
	public String getChangeRequest() {
		return null;
	}
	/**
	 * Get the incident's category
	 * @return the incident's category
	 */
	public Category getCategory() {
		return null;
	}
	/**
	 * Gets the incident's category as a string
	 * @return the incident's category
	 */
	public String getCategoryString() {
		return null;
	}
	/**
	 * Sets the incident's category
	 * @param s the category to set 
	 */
	private void setCategory(String s) {
		
	}
	/**
	 * Gets the incident's priority as a string
	 * @return the incident's priority
	 */
	public String getPriorityString() {
		return null;
	}
	/**
	 * Sets the incident's priority
	 * @param s the priority to set to
	 */
	private void setPriority(String s) {
		
	}
	/**
	 * Gets the incident's on hold reason as a string
	 * @return the incident's on hold reason
	 */
	public String getOnHoldReasonString() {
		return null;
	}
	/**
	 * Sets the incident's on hold reason
	 * @param s the on hold reason to set
	 */
	private void setOnHoldReason(String s) {
		
	}
	/**
	 * Gets the incident's cancellation code as a string
	 * @return the incident's cancellation code
	 */
	public String getCancellationCodeString() {
		return null;
	}
	/**
	 * Sets the incident's cancellation code
	 * @param s the cancellation code to set
	 */
	private void setCancellationCode(String s) {
		
	}
	/**
	 * Gets the current state of the incident
	 * @return the incident's current state
	 */
	public IncidentState getState() {
		return null;
	}
	/**
	 * Sets the incident's state
	 * @param s the state to set to
	 */
	private void setState(String s) {
		
	}
	/**
	 * Gets the incident's resolution code
	 * @return the incident's resolution code
	 */
	public ResolutionCode getResolutionCode() {
		return null;
	}
	/**
	 * Gets the incident's resolution code as a string
	 * @return the incident's resolution code
	 */
	public  String getResolutionCodeString() {
		return null;
	}
	/**
	 * Sets the incident's resolution code
	 * @param s the resolution code to set
	 */
	private void setResolutionCode(String s) {
		
	}
	/**
	 * Gets the incident's owner
	 * @return the incident's owner
	 */
	public String getOwner() {
		return null;
	}
	/**
	 * Gets the incident's name
	 * @return the incident's name
	 */
	public String getName() {
		return null;
	}
	/**
	 * Gets the incident's caller
	 * @return the incident's caller
	 */
	public String getCaller() {
		return null;
	}
	/**
	 * Gets the notes associated with the incident
	 * @return the incident's notes
	 */
	public ArrayList<String> getNotes(){
		return null;
	}
	/**
	 * Gets the incident's notes as a string
	 * @return the incident's notes
	 */
	public String getNotesString() {
		return null;
	}
	/**
	 * Updates the incident with the given command
	 * @param c the command to execute
	 */
	public void update(Command c) {
		
	}
	/**
	 * Gets an incident from xml data
	 * @return an unmanaged incident
	 */
	public Incident getXMLIncident() {
		return null;
	}
	/**
	 * Sets the counter to the specified value
	 * @param i the value to set the counter to
	 */
	public static void setCounter(int i ) {
		
	}
	/**
	 * The state of an incident on hold
	 * @author Max Richgruber
	 *
	 */
	public class OnHoldState implements IncidentState {
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "OnHold";
		}

	}
	/**
	 * The state of an incident that has been resolved
	 * @author Max Richgruber
	 *
	 */
	public class ResolvedState implements IncidentState {
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "Resolved";
		}

	}
	/**
	 * The state of a brand new incident
	 * @author Max Richgruber
	 *
	 */
	public class NewState implements IncidentState {
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "New";
		}

	}
	/**
	 * The state of an incident in progress
	 * @author Max Richgruber
	 *
	 */
	public class InProgressState implements IncidentState{
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "In Progress";
		}

	}
	/**
	 * The state of an incident that has been closed
	 * @author Max Richgruber
	 *
	 */
	public class ClosedState implements IncidentState {
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return "Closed";
		}

	}
	/**
	 * The state of an incident that has been canceled
	 * @author Max Richgruber
	 *
	 */
	public class CanceledState implements IncidentState {
		/**
		 * Changes the current state based on the provided command
		 * @param command the command that's going to change the state
		 */
		@Override
		public void updateState(Command command) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return "Canceled";
		}

	}

}
