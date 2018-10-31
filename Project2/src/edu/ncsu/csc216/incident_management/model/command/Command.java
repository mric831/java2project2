package edu.ncsu.csc216.incident_management.model.command;

public class Command {
	public static final String OH_CALLER = "Awaiting Caller";
	public static final String OH_CHANGE = "Awaiting Change";
	public static final String OH_VENDOR = "Awaiting Vendor";
	public static final String RC_PERMANENTLY_SOLVED = "Permanently Solved";
	public static final String RC_WORKAROUND = "Workaround";
	public static final String RC_NOT_SOLVED = "Not Solved";
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	public static final String CC_DUPLICATE = "Duplicate";
	public static final String CC_UNNECESSARY = "Unnecessary";
	public static final String CC_NOT_AN_INCIDENT = "Not an Incident";
	private String ownerId;
	private String note;
	private CommandValue c;
	private OnHoldReason onHoldReason;
	private ResolutionCode resolutionCode;
	private CancellationCode cancellationCode;
	
	public Command(CommandValue v, String id, OnHoldReason o, ResolutionCode r, CancellationCode c, String note) {
		
	}
	
	public CommandValue getCommand() {
		return null;
	}
	
	public String getOwnerId() {
		return null;
	}
	
	public ResolutionCode getResolutionCode() {
		return null;
	}
	
	public String getWorkNote() {
		return null;
	}
	
	public OnHoldReason getOnHoldReason() {
		return null;
	}
	
	public CancellationCode getCancellationCode() {
		return null;
	}
}
