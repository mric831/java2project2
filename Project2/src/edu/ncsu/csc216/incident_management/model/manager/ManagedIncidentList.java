package edu.ncsu.csc216.incident_management.model.manager;


import java.util.List;

import edu.ncsu.csc216.incident.xml.Incident;
import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;
/**
 * Class that describes a list of managed incidents 
 * @author Max Richgruber
 *
 */
public class ManagedIncidentList {
	/** A list of incidents */
	private List<Incident> incidents;
	/**
	 * ManagedIncidentList constructor 
	 */
	public ManagedIncidentList() {
		
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
	public int addIncident(String caller, Category c, Priority p, String name, String workNote) {
		return 0;
	}
	/**
	 * Adds incidents to the list from an xml file
	 * @param list the collection of incidents stored in the xml file
	 */
	public void addXMLIncidents(List<Incident> list) {
		
	}
	/**
	 * Get the list of managed incidents
	 * @return
	 */
	public List<ManagedIncident> getManagedIncidents(){
		return null;
	}
	/**
	 * Gets a list of managed incidents of the specified category
	 * @param c the category that is being searched for
	 * @return a list of managed incidents that fall under the provided category
	 */
	public List<ManagedIncident> getIncidentsByCategory(Category c){
		return null;
	}
	/**
	 * Gets a specific incident from the list
	 * @param id the id of the incident that is being searched for
	 * @return the incident matching the provided id
	 */
	public ManagedIncident getIncidentById(int id) {
		return null;
	}
	/**
	 * Executes the specified command on the specified incident 
	 * @param id the id of the incident to modify
	 * @param c the command that is going to be enacted on the incident
	 */
	public void executeCommand(int id, Command c) {
		
	}
	/**
	 * Removes the specified incident from the list
	 * @param id the id of the incident to remove
	 */
	public void deleteIncidentById(int id) {
		
	}

	
}
