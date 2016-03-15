package br.ufal.ic.systemComponentes;

import java.util.ArrayList;

public class Community {
	protected String name;
	protected String description;
	protected ArrayList<Researcher> participants;
	
	public Community() {
		this.participants = new ArrayList<Researcher>();
	}
	
	public ArrayList<Researcher> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Researcher> participants) {
		this.participants = participants;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
