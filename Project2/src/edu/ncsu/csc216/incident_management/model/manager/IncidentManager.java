package edu.ncsu.csc216.incident_management.model.manager;

import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;


public class IncidentManager {
	
	private IncidentManager singleton;
	
	private ManagedIncidentList incidentList;
	
	private IncidentManager() {
		
	}
	
	public static IncidentManager getInstance() {
		return null;
	}
	
	public void saveManagedIncidentsToFile(String fileName) {
		
	}
	
	public void loadManagedIncidentsFromFile(String fileName) {
		
	}
	
	public void createNewManagedIncidentList() {
		
	}
	
	public String[][] getManagedIncidentsAsArray(){
		return null;
	}
	                
	public String[][] getManagedIncidentsAsArrayByCategory(Category c){
		return null;
	}
	
	public ManagedIncident getManagedIncidentById(int id) {
		return null;
	}
	public void executeCommand(int i, Command c) {
		
	}
	
	public void deleteManagedIncidentById(int id) {
		
	}
	
	public void addManagedIncidentToList(String s, Category c, Priority p, String a, String b) {
		
	}
}
