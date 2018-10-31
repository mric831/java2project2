package edu.ncsu.csc216.incident_management.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.incident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.Priority;

public class ManagedIncidentList {
	private ArrayList incidents;
	
	public ManagedIncidentList() {
		
	}
	
	public int addIncident(String s, Category c, Priority p, String a, String b) {
		return 0;
	}
	
	public void addXMLIncidents(ArrayList<Incident> list) {
		
	}
	
	public ArrayList<ManagedIncident> getManagedIncidents(){
		return null;
	}
	
	public ArrayList<ManagedIncident> getIncidentsByCategory(Category c){
		return null;
	}
	
	public ManagedIncident getIncidentById(int id) {
		return null;
	}
	
	public void executeCommand(int i, Command c) {
		
	}
	
	public void deleteIncidentById(int id) {
		
	}

	
}
