package edu.ncsu.csc216.incident_management.model.incident;

import java.util.ArrayList;


import edu.ncsu.csc216.incident.xml.Incident;
import edu.ncsu.csc216.incident.xml.WorkNotes;
import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.command.Command.CancellationCode;
import edu.ncsu.csc216.incident_management.model.command.Command.OnHoldReason;
import edu.ncsu.csc216.incident_management.model.command.Command.ResolutionCode;

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
		notes = new ArrayList<String>();
		notes.add(workNote);
		cancellationCode = null;
		resolutionCode = null;
		onHoldReason = null;
		owner = null;
		changeRequest = null;
		newState = new NewState();
		resolvedState = new ResolvedState();
		onHoldState = new OnHoldState();
		closedState = new ClosedState();
		inProgressState = new InProgressState();
		canceledState = new CanceledState();
		state = newState;
	}
	/**
	 * Constructor for Managed incidents when given an incident
	 * @param i the incident to create as a managed incident
	 */
	public ManagedIncident(Incident i) {
		
		this.incidentId = counter;
		incrementCounter();
		this.caller = i.getCaller();
		this.owner = i.getOwner();
		this.name = i.getName();
		this.changeRequest = i.getChangeRequest();
		setCategory(i.getCategory());
		setPriority(i.getPriority());
		setOnHoldReason(i.getOnHoldReason());
		setResolutionCode(i.getResolutionCode());
		setCancellationCode(i.getCancellationCode());
		WorkNotes w = i.getWorkNotes();
		this.notes = (ArrayList<String>) w.getNotes();
		newState = new NewState();
		resolvedState = new ResolvedState();
		onHoldState = new OnHoldState();
		closedState = new ClosedState();
		inProgressState = new InProgressState();
		canceledState = new CanceledState();
		setState(i.getState());
		
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
		return incidentId;
	}
	/**
	 * Get the change string that the incident is attempting
	 * @return the change that is being requested for the incident
	 */
	public String getChangeRequest() {
		return changeRequest;
	}
	/**
	 * Get the incident's category
	 * @return the incident's category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * Gets the incident's category as a string
	 * @return the incident's category
	 */
	public String getCategoryString() {
		if(category.equals(Category.DATABASE)) {
			return C_DATABASE;
		} else if(category.equals(Category.HARDWARE)) {
			return C_HARDWARE;
		} else if(category.equals(Category.INQUIRY)) {
			return C_INQUIRY;
		} else if(category.equals(Category.NETWORK)) {
			return C_NETWORK;
		} else{
			return C_SOFTWARE;
		}
		
	}
	/**
	 * Sets the incident's category
	 * @param s the category to set 
	 */
	private void setCategory(String s) {
		this.category = convertCategory(s);
	}
	/**
	 * Gets the incident's priority as a string
	 * @return the incident's priority
	 */
	public String getPriorityString() {
		if(priority.equals(Priority.HIGH)) {
			return P_HIGH;
		} else if(priority.equals(Priority.MEDIUM)) {
			return P_MEDIUM;
		} else if(priority.equals(Priority.LOW)) {
			return P_LOW;
		} else {
			return P_URGENT;
		}
	}
	/**
	 * Sets the incident's priority
	 * @param s the priority to set to
	 */
	private void setPriority(String s) {
		this.priority = convertPriority(s);
	}
	/**
	 * Gets the incident's on hold reason as a string
	 * @return the incident's on hold reason
	 */
	public String getOnHoldReasonString() {
		if(onHoldReason.equals(OnHoldReason.AWAITING_CALLER)) {
			return Command.OH_CALLER;
		} else if(onHoldReason.equals(OnHoldReason.AWAITING_CHANGE)) {
			return Command.OH_CHANGE;
		} else {
			return Command.OH_VENDOR;
		}
		
	}
	/**
	 * Sets the incident's on hold reason
	 * @param s the on hold reason to set
	 */
	private void setOnHoldReason(String s) {
		this.onHoldReason = convertOnHoldReason(s);
	}
	/**
	 * Gets the incident's cancellation code as a string
	 * @return the incident's cancellation code
	 */
	public String getCancellationCodeString() {
		if(cancellationCode == null) {
			return null;
		} else if(cancellationCode.equals(CancellationCode.DUPLICATE)) {
			return Command.CC_DUPLICATE;
		} else if(cancellationCode.equals(CancellationCode.NOT_AN_INCIDENT)) {
			return Command.CC_NOT_AN_INCIDENT;
		} else {
			return Command.CC_UNNECESSARY;
		}
	}
	/**
	 * Sets the incident's cancellation code
	 * @param s the cancellation code to set
	 */
	private void setCancellationCode(String s) {
		this.cancellationCode = convertCancellationCode(s);
	}
	/**
	 * Gets the current state of the incident
	 * @return the incident's current state
	 */
	public IncidentState getState() {
		return state;
	}
	/**
	 * Sets the incident's state
	 * @param s the state to set to
	 */
	private void setState(String s) {
		this.state = convertState(s);
	}
	/**
	 * Gets the incident's resolution code
	 * @return the incident's resolution code
	 */
	public ResolutionCode getResolutionCode() {
		return resolutionCode;
	}
	/**
	 * Gets the incident's resolution code as a string
	 * @return the incident's resolution code
	 */
	public  String getResolutionCodeString() {
		if(resolutionCode == null) {
			return null;
		} else if(resolutionCode.equals(ResolutionCode.CALLER_CLOSED)) {
			return Command.RC_CALLER_CLOSED;
		} else if(resolutionCode.equals(ResolutionCode.NOT_SOLVED)) {
			return Command.RC_NOT_SOLVED;
		} else if(resolutionCode.equals(ResolutionCode.PERMANENTLY_SOLVED)) {
			return Command.RC_PERMANENTLY_SOLVED;
		} else {
			return Command.RC_WORKAROUND;
		}
	}
	/**
	 * Sets the incident's resolution code
	 * @param s the resolution code to set
	 */
	private void setResolutionCode(String s) {
		this.resolutionCode = convertResolutionCode(s);
	}
	/**
	 * Gets the incident's owner
	 * @return the incident's owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * Gets the incident's name
	 * @return the incident's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the incident's caller
	 * @return the incident's caller
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * Gets the notes associated with the incident
	 * @return the incident's notes
	 */
	public ArrayList<String> getNotes(){
		return notes;
	}
	/**
	 * Gets the incident's notes as a string
	 * @return the incident's notes
	 */
	public String getNotesString() {
		return notes.toString();
	}
	/**
	 * Updates the incident with the given command
	 * @param c the command to execute
	 */
	public void update(Command c) {
		switch(state.getStateName()) {
			case NEW_NAME:
				newState.updateState(c);
				break;
			case IN_PROGRESS_NAME:
				inProgressState.updateState(c);
				break;
			case ON_HOLD_NAME:
				onHoldState.updateState(c);
				break;
			case RESOLVED_NAME:
				resolvedState.updateState(c);
				break;
			case CLOSED_NAME:
				closedState.updateState(c);
				break;
			case CANCELED_NAME:
				canceledState.updateState(c);
				break;
				
		}
	}
	/**
	 * Gets an incident from xml data
	 * @return an unmanaged incident
	 */
	public Incident getXMLIncident() {
		Incident xml = new Incident();
		xml.setCaller(caller);
		if(cancellationCode != null) {
			xml.setCancellationCode(this.getCancellationCodeString());
		}
		xml.setCategory(this.getCategoryString());
		xml.setChangeRequest(changeRequest);
		xml.setId(incidentId);
		xml.setName(name);
		if(onHoldReason != null) {
			xml.setOnHoldReason(this.getOnHoldReasonString());
		}
		xml.setOwner(owner);
		xml.setPriority(this.getPriorityString());
		if(resolutionCode != null) {
			xml.setResolutionCode(this.getResolutionCodeString());
		}
		xml.setState(this.getState().getStateName());
		WorkNotes w = new WorkNotes();
		for(int i = 0; i < notes.size(); i++) {
			w.getNotes().add(notes.get(i));
		}
		xml.setWorkNotes(w);
		return xml;
	}
	/**
	 * Sets the counter to the specified value
	 * @param i the value to set the counter to
	 */
	public static void setCounter(int i ) {
		counter = i;
	}
	
	private Category convertCategory(String s) {
		if(s.equals(C_INQUIRY)) {
			return Category.INQUIRY;
		} else if(s.equals(C_SOFTWARE)) {
			return Category.SOFTWARE;
		} else if(s.equals(C_HARDWARE)) {
			return Category.HARDWARE;
		} else if(s.equals(C_NETWORK)) {
			return Category.NETWORK;
		} else if(s.equals(C_DATABASE)) {
			return Category.DATABASE;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private Priority convertPriority(String s) {
		if(s.equals(P_URGENT)) {
			return Priority.URGENT;
		} else if(s.equals(P_HIGH)) {
			return Priority.HIGH;
		} else if(s.equals(P_MEDIUM)) {
			return Priority.MEDIUM;
		} else if(s.equals(P_LOW)) {
			return Priority.LOW;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private OnHoldReason convertOnHoldReason(String s) {
		if(s == null) {
			return null;
		} else if(s.equals(Command.OH_CALLER)) {
			return OnHoldReason.AWAITING_CALLER;
		} else if(s.equals(Command.OH_CHANGE)) {
			return OnHoldReason.AWAITING_CHANGE;
		} else if(s.equals(Command.OH_VENDOR)) {
			return OnHoldReason.AWAITING_VENDOR;
		} else {
			return null;
		}
	}
	
	private ResolutionCode convertResolutionCode(String s) {
		if(s == null) {
			return null;
		} else if(s.equals(Command.RC_PERMANENTLY_SOLVED)) {
			return ResolutionCode.PERMANENTLY_SOLVED;
		} else if(s.equals(Command.RC_WORKAROUND)) {
			return ResolutionCode.WORKAROUND;
		} else if(s.equals(Command.RC_NOT_SOLVED)) {
			return ResolutionCode.NOT_SOLVED;
		} else if(s.equals(Command.RC_CALLER_CLOSED)) {
			return ResolutionCode.CALLER_CLOSED;
		} else {
			return null;
		}
	}
	
	private CancellationCode convertCancellationCode(String s) {
		if(s == null) {
			return null;
		} else if(s.equals(Command.CC_DUPLICATE)) {
			return CancellationCode.DUPLICATE;
		} else if(s.equals(Command.CC_UNNECESSARY)) {
			return CancellationCode.UNNECESSARY;
		} else if(s.equals(Command.CC_NOT_AN_INCIDENT)) {
			return CancellationCode.NOT_AN_INCIDENT;
		} else {
			return null;
		}
	}
	
	private IncidentState convertState(String s) {
		if(s == null) {
			throw new IllegalArgumentException();
		} else if(s.equals(NEW_NAME)) {
			return newState;
		} else if(s.equals(IN_PROGRESS_NAME)) {
			return inProgressState;
		} else if(s.equals(ON_HOLD_NAME)) {
			return onHoldState;
		} else if(s.equals(RESOLVED_NAME)) {
			return resolvedState;
		} else if(s.equals(CLOSED_NAME)) {
			return closedState;
		} else if(s.equals(CANCELED_NAME)) {
			return canceledState;
		} else {
			throw new IllegalArgumentException();
		}
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
			switch(command.getCommand()) {
				case INVESTIGATE:
					throw new UnsupportedOperationException();
				case HOLD:
					throw new UnsupportedOperationException();
				case RESOLVE:
					state = resolvedState;
					resolutionCode = command.getResolutionCode();
					break;
				case CONFIRM:
					throw new UnsupportedOperationException();
				case REOPEN:
					state = inProgressState;
					break;
				case CANCEL:
					state = canceledState;
					cancellationCode = command.getCancellationCode();
					break;
			
			}
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return ON_HOLD_NAME;
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
			switch(command.getCommand()) {
			case INVESTIGATE:
				throw new UnsupportedOperationException();
			case HOLD:
				state = onHoldState;
				onHoldReason = command.getOnHoldReason();
				resolutionCode = null;
				notes.add(command.getWorkNote());
				break;
			case RESOLVE:
				throw new UnsupportedOperationException();
			case CONFIRM:
				state = closedState;
				notes.add(command.getWorkNote());
				break;
			case REOPEN:
				state = inProgressState;
				resolutionCode = null;
				notes.add(command.getWorkNote());
				break;
			case CANCEL:
				state = canceledState;
				resolutionCode = null;
				cancellationCode = command.getCancellationCode();
				notes.add(command.getWorkNote());
				break;
			
			}
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return RESOLVED_NAME;
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
			switch(command.getCommand()) {
				case INVESTIGATE:
					state = inProgressState;
					owner = command.getOwnerId();
					notes.add(command.getWorkNote());
					break;
				case HOLD:
					throw new UnsupportedOperationException();
				case RESOLVE:
					throw new UnsupportedOperationException();
				case CONFIRM:
					throw new UnsupportedOperationException();
				case REOPEN:
					throw new UnsupportedOperationException();
				case CANCEL:
					state = canceledState;
					cancellationCode = command.getCancellationCode();
					notes.add(command.getWorkNote());
					break;
				
			}
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
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
			switch(command.getCommand()) {
			case INVESTIGATE:
				throw new UnsupportedOperationException();
			case HOLD:
				state = onHoldState;
				onHoldReason = command.getOnHoldReason();
				notes.add(command.getWorkNote());
				break;
			case RESOLVE:
				state = resolvedState;
				resolutionCode = command.getResolutionCode();
				notes.add(command.getWorkNote());
				break;
			case CONFIRM:
				throw new UnsupportedOperationException();
			case REOPEN:
				throw new UnsupportedOperationException();
			case CANCEL:
				state = canceledState;
				cancellationCode = command.getCancellationCode();
				notes.add(command.getWorkNote());
				break;
			
			}
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return IN_PROGRESS_NAME;
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
			switch(command.getCommand()) {
			case INVESTIGATE:
				notes.add(command.getWorkNote());
				throw new UnsupportedOperationException();
			case HOLD:
				notes.add(command.getWorkNote());
				throw new UnsupportedOperationException();
			case RESOLVE:
				notes.add(command.getWorkNote());
				throw new UnsupportedOperationException();
			case CONFIRM:
				notes.add(command.getWorkNote());
				throw new UnsupportedOperationException();
			case REOPEN:
				state = inProgressState;
				notes.add(command.getWorkNote());
				break;
			case CANCEL:
				notes.add(command.getWorkNote());
				throw new UnsupportedOperationException();
			
				
			}
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return CLOSED_NAME;
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
			switch(command.getCommand()) {
			case INVESTIGATE:
				throw new UnsupportedOperationException();
			case HOLD:
				throw new UnsupportedOperationException();
			case RESOLVE:
				throw new UnsupportedOperationException();
			case CONFIRM:
				throw new UnsupportedOperationException();
			case REOPEN:
				throw new UnsupportedOperationException();
			case CANCEL:
				throw new UnsupportedOperationException();
			
			}
			
		}
		/**
		 * Gets the name of the state
		 * @return the state name
		 */
		@Override
		public String getStateName() {
			return CANCELED_NAME;
		}

	}

}
