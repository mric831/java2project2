package edu.ncsu.csc216.incident_management.model.manager;

import java.util.List;

import edu.ncsu.csc216.incident.io.IncidentIOException;
import edu.ncsu.csc216.incident.io.IncidentReader;
import edu.ncsu.csc216.incident.io.IncidentWriter;
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
	private static IncidentManager singleton;
	/** The list of managed incidents that are being worked with */
	private ManagedIncidentList incidentList;
	/**
	 * Constructor of the incident manager
	 */
	private IncidentManager() {
		incidentList = new ManagedIncidentList();
	}
	/**
	 * Gets an instance of IncidentManager to work with
	 * @return an IncidentManager instance
	 */
	public static IncidentManager getInstance() {
		if(singleton == null) {
			singleton = new IncidentManager();
		}
		return singleton;
	}
	/**
	 * Saves all of the incidents on the list to an external file 
	 * @param fileName name of the file to save to
	 * @throws IllegalArgumentException for invalid file names
	 */
	public void saveManagedIncidentsToFile(String fileName)  {
		IncidentWriter writer = null;
		writer = new IncidentWriter(fileName);
		for(int i = 1; i < incidentList.getManagedIncidents().size(); i++) {
			ManagedIncident m = incidentList.getIncidentById(i);
			writer.addItem(m.getXMLIncident());
		}
		try {
			writer.marshal();
		} catch(IncidentIOException e) {
			throw new IllegalArgumentException();
		}

	}
	/**
	 * Loads in incidents from a file to the list
	 * @param fileName name of the file to load in from
	 * @throws IllegalArgumentException for invalid file names
	 */
	public void loadManagedIncidentsFromFile(String fileName) {
		try {
			IncidentReader reader = new IncidentReader(fileName);
			incidentList.addXMLIncidents(reader.getIncidents());
		} catch(IncidentIOException e) {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Creates an entirely new list of managed incidents
	 */
	public void createNewManagedIncidentList() {
		incidentList = new ManagedIncidentList();
	}
	/**
	 * Returns the list of managed incidents as an array
	 * @return array of managed incidents
	 */
	public String[][] getManagedIncidentsAsArray(){
		List<ManagedIncident> temp = incidentList.getManagedIncidents();
		String[][] s = new String[temp.size()][5];
		for(int i = 0; i < temp.size(); i++) {
			s[i][0] = "" + temp.get(i).getIncidentId();
			s[i][1] = temp.get(i).getCategoryString();
			s[i][2] = temp.get(i).getState().getStateName();
			s[i][3] = temp.get(i).getPriorityString();
			s[i][4] = temp.get(i).getName();
		}
		return s;
	}
	/**
	 * Returns a specific category of managed incidents as an array                
	 * @param c the category of incidents that are being searched for
	 * @return array of managed incidents of the specified category
	 * @throws IllegalArgumentException if given a null category
	 */
	public String[][] getManagedIncidentsAsArrayByCategory(Category c){
		if(c == null) {
			throw new IllegalArgumentException();
		}
		List<ManagedIncident> temp = incidentList.getIncidentsByCategory(c);
		String[][] s = new String[temp.size()][5];
		for(int i = 0; i < temp.size(); i++) {
			s[i][0] = "" + temp.get(i).getIncidentId();
			s[i][1] = temp.get(i).getCategoryString();
			s[i][2] = temp.get(i).getState().getStateName();
			s[i][3] = temp.get(i).getPriorityString();
			s[i][4] = temp.get(i).getName();
		}
		return s;
	}
	/**
	 * Gets a specified incident from the list
	 * @param id the id of the incident that is being searched for
	 * @return the specified incident
	 */
	public ManagedIncident getManagedIncidentById(int id) {
		return incidentList.getIncidentById(id);
	}
	/**
	 * Executes a command on a managed incident
	 * @param id the id of the incident to modify
	 * @param c the command to execute on the specified incident
	 */
	public void executeCommand(int id, Command c) {
		incidentList.executeCommand(id, c);
	}
	/**
	 * Deletes a specified incident from the list
	 * @param id the id of the incident to remove
	 */
	public void deleteManagedIncidentById(int id) {
		incidentList.deleteIncidentById(id);
	}
	/**
	 * Adds an incident with the specified parameters to the list
	 * @param caller the person calling in the incident
	 * @param c the category the incident falls under
	 * @param p the priority level of the incident
	 * @param name the name of the incident
	 * @param workNote the note describing the incident
	 */
	public void addManagedIncidentToList(String caller, Category c, Priority p, String name, String workNote) {
		incidentList.addIncident(caller, c, p, name, workNote);
	}
}
