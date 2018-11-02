package edu.ncsu.csc216.incident_management.model.manager;

import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;

/**
 * Class that manages all of the incidents and facilitates program execution
 * @author Max Richgruber
 *
 */
public class IncidentManager {
	/** Single instance of the incident manager */
	private IncidentManager singleton;
	/** The list of managed incidents that are being worked with */
	private ManagedIncidentList incidentList;
	/**
	 * Constructor of the incident manager
	 */
	private IncidentManager() {
		
	}
	/**
	 * Gets an instance of IncidentManager to work with
	 * @return an IncidentManager instance
	 */
	public static IncidentManager getInstance() {
		return null;
	}
	/**
	 * Saves all of the incidents on the list to an external file 
	 * @param fileName name of the file to save to
	 */
	public void saveManagedIncidentsToFile(String fileName) {
		
	}
	/**
	 * Loads in incidents from a file to the list
	 * @param fileName name of the file to load in from
	 */
	public void loadManagedIncidentsFromFile(String fileName) {
		
	}
	/**
	 * Creates an entirely new list of managed incidents
	 */
	public void createNewManagedIncidentList() {
		
	}
	/**
	 * Returns the list of managed incidents as an array
	 * @return array of managed incidents
	 */
	public String[][] getManagedIncidentsAsArray(){
		return null;
	}
	/**
	 * Returns a specific category of managed incidents as an array                
	 * @param c the category of incidents that are being searched for
	 * @return array of managed incidents of the specified category
	 */
	public String[][] getManagedIncidentsAsArrayByCategory(Category c){
		return null;
	}
	/**
	 * Gets a specified incident from the list
	 * @param id the id of the incident that is being searched for
	 * @return the specified incident
	 */
	public ManagedIncident getManagedIncidentById(int id) {
		return null;
	}
	/**
	 * Executes a command on a managed incident
	 * @param id the id of the incident to modify
	 * @param c the command to execute on the specified incident
	 */
	public void executeCommand(int id, Command c) {
		
	}
	/**
	 * Deletes a specified incident from the list
	 * @param id the ide of the incident to remove
	 */
	public void deleteManagedIncidentById(int id) {
		
	}
	/**
	 * Adds an incident with the specified parameters to the list
	 * @param caller the person calling in the incident
	 * @param c the category the incident falls under
	 * @param p the priority level of the incident
	 * @param name the name of the incident
	 * @param workNote the note describing the incident
	 * @return the index in the list the added incident was assigned
	 */
	public void addManagedIncidentToList(String caller, Category c, Priority p, String name, String workNote) {
		
	}
}
