package edu.ncsu.csc216.incident_management.model.command;
/**
 * Class that facilitates the processing of incidents
 * @author Max Richgruber
 *
 */
public class Command {
	/** Code when awaiting caller */
	public static final String OH_CALLER = "Awaiting Caller";
	/** Code when awaiting a change to be made */
	public static final String OH_CHANGE = "Awaiting Change";
	/** Code when awaiting vendor */
	public static final String OH_VENDOR = "Awaiting Vendor";
	/** Code when an incident has been permanently solved */
	public static final String RC_PERMANENTLY_SOLVED = "Permanently Solved";
	/** Code when an incident has been worked around */
	public static final String RC_WORKAROUND = "Workaround";
	/** Code when an incident has not been solved */
	public static final String RC_NOT_SOLVED = "Not Solved";
	/** Code when an incident has been closed by the caller */
	public static final String RC_CALLER_CLOSED = "Caller Closed";
	/** Code when an incident is a duplicate */
	public static final String CC_DUPLICATE = "Duplicate";
	/** Code when an incident is unnecessary */
	public static final String CC_UNNECESSARY = "Unnecessary";
	/** Code when an incident does not qualify as an incident */
	public static final String CC_NOT_AN_INCIDENT = "Not an Incident";
	/** The owner of the incident */
	private String ownerId;
	/** The note that describes the incident */
	private String note;
	/** Possible values of the command value */
	public enum CommandValue { INVESTIGATE, HOLD, RESOLVE, CONFIRM, REOPEN, CANCEL }
	/** Possible reasons for an incident to be on hold */
	public enum OnHoldReason { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_VENDOR }
	/** Possible codes for an incident to be resolved */
	public enum ResolutionCode { PERMANENTLY_SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }
	/** Possible codes for an incident to be cancelled */
	public enum CancellationCode { DUPLICATE, UNNECESSARY, NOT_AN_INCIDENT }
	/** The command value of the command */
	private CommandValue c;
	/** Hold reason of the command */
	private OnHoldReason onHoldReason;
	/** Resolution code of the command */
	private ResolutionCode resolutionCode;
	/** Cancellation code of the command */
	private CancellationCode cancellationCode;
	/**
	 * Constructor for command objects
	 * @param v CommandValue of the command object
	 * @param id The id of the incident owner
	 * @param o Reason for the incident to be put on hold
	 * @param r Code for the incident's resolution
	 * @param c Code for the incident's cancellation
	 * @param note Note describing the incident
	 */
	public Command(CommandValue v, String id, OnHoldReason o, ResolutionCode r, CancellationCode c, String note) {
		if(v == null) {
			throw new IllegalArgumentException();
		} else if(note == null || note.equals("")) {
			throw new IllegalArgumentException();
		} else if(v.equals(CommandValue.INVESTIGATE)) {
			if(id == null || id.equals("")) {
				throw new IllegalArgumentException();
			}
		} else if(v.equals(CommandValue.HOLD)) {
			if(o == null) {
				throw new IllegalArgumentException();
			}
		} else if(v.equals(CommandValue.RESOLVE)) {
			if(r == null) {
				throw new IllegalArgumentException();
			}
		} else if(v.equals(CommandValue.CANCEL)) {
			if(c == null) {
				throw new IllegalArgumentException();
			}
		}
		this.c = v;
		this.ownerId = id;
		this.onHoldReason = o;
		this.resolutionCode = r;
		this.cancellationCode = c;
		this.note = note;
	}
	/**
	 * Gets the command value of the Command object
	 * @return the action the command object is taking
	 */
	public CommandValue getCommand() {
		return c;
	}
	/**
	 * Gets the id of the command owner
	 * @return the owner's id
	 */
	public String getOwnerId() {
		return ownerId;
	}
	/**
	 * Gets the code to resolve the incident
	 * @return resolution code
	 */
	public ResolutionCode getResolutionCode() {
		return resolutionCode;
	}
	/**
	 * Gets the not associated with the incident
	 * @return work note
	 */
	public String getWorkNote() {
		return note;
	}
	/**
	 * Gets the reason the incident is on hold
	 * @return on hold reason
	 */
	public OnHoldReason getOnHoldReason() {
		return onHoldReason;
	}
	/**
	 * Gets the reason the incident is being cancelled
	 * @return cancellation code
	 */
	public CancellationCode getCancellationCode() {
		return cancellationCode;
	}
}
