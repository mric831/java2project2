package edu.ncsu.csc216.incident_management.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.incident.xml.Incident;
import edu.ncsu.csc216.incident_management.model.command.Command;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Category;
import edu.ncsu.csc216.incident_management.model.incident.ManagedIncident.Priority;

public class ManagedIncidentList {
	private List<Incident> incidents;
	
	public ManagedIncidentList() {
		
	}
	
	public int addIncident(String s, Category c, Priority p, String a, String b) {
		return 0;
	}
	
	public void addXMLIncidents(ArrayList<Incident> list) {
		
	}
	
	public List<ManagedIncident> getManagedIncidents(){
		return null;
	}
	
	public List<ManagedIncident> getIncidentsByCategory(Category c){
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
