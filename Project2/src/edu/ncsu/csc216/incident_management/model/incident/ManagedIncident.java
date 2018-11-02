package edu.ncsu.csc216.incident_management.model.incident;

import java.util.ArrayList;

import edu.ncsu.csc216.incident.xml.Incident;

import edu.ncsu.csc216.incident_management.model.command.Command;


public class ManagedIncident {
	public static final String C_INQUIRY = "Inquiry";
	public static final String C_SOFTWARE = "Software";
	public static final String C_HARDWARE = "Hardware";
	public static final String C_DATABASE = "Database";
	public static final String C_NETWORK = "Network";
	public static final String P_URGENT = "Urgent";
	public static final String P_HIGH = "High";
	public static final String P_MEDIUM = "Medium";
	public static final String P_LOW = "Low";
	private int incidentId;
	private String caller;
	private String owner;
	private String name;
	private String changeRequest;
	private ArrayList<String> notes;
	public static final String NEW_NAME = "New";
	public static final String IN_PROGRESS_NAME = "In Progress";
	public static final String ON_HOLD_NAME = "On Hold";
	public static final String RESOLVED_NAME = "Resolved";
	public static final String CLOSED_NAME = "Closed";
	public static final String CANCELED_NAME = "Canceled";
	private static int counter = 0;
	public enum Priority { URGENT, HIGH, MEDIUM, LOW}
	public enum CancellationCode { DUPLICATE, UNNECESSARY, NOT_AN_INCIDENT }
	public enum ResolutionCode { PERMANENTLY_SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	public enum OnHoldReason { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_VENDOR }
	private IncidentState newState;
	private IncidentState state;
	private IncidentState resolvedState;
	private IncidentState onHoldState;
	private IncidentState closedState;
	private IncidentState inProgressState;
	private IncidentState canceledState;
	public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }
	
	public ManagedIncident(String s, Category c, Priority p, String a, String b) {
		
	}
	
	public ManagedIncident(Incident i) {
		
	}
	
	public static void incrementCounter() {
		
	}
	
	public int getIncidentId() {
		return 0;
	}
	
	public String getChangeRequest() {
		return null;
	}
	
	public Category getCategory() {
		return null;
	}
	
	public String getCategoryString() {
		return null;
	}
	
	private void setCategory(String s) {
		
	}
	
	public String getPriorityString() {
		return null;
	}
	
	private void setPriority(String s) {
		
	}
	
	public String getOnHoldReasonString() {
		return null;
	}
	
	private void setOnHoldReason(String s) {
		
	}
	
	public String getCancellationCodeString() {
		return null;
	}
	
	private void setCancellationCode(String s) {
		
	}
	
	public IncidentState getState() {
		return null;
	}
	
	private void setState(String s) {
		
	}
	
	public ResolutionCode getResolutionCode() {
		return null;
	}
	
	public  String getResolutionCodeString() {
		return null;
	}
	
	private void setResolutionCode(String s) {
		
	}
	
	public String getOwner() {
		return null;
	}
	
	public String getName() {
		return null;
	}
	
	public String getCaller() {
		return null;
	}
	
	public ArrayList<String> getNotes(){
		return null;
	}
	
	public String getNotesString() {
		return null;
	}
	
	public void update(Command c) {
		
	}
	
	public Incident getXMLIncident() {
		return null;
	}
	
	public static void setCounter(int i ) {
		
	}

}
