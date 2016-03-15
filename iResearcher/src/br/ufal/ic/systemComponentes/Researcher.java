package br.ufal.ic.systemComponentes;

import java.util.ArrayList;

public class Researcher {
	protected int id;
	protected String name;
	protected String login;
	protected String password;
	protected ArrayList<Researcher> friends;
	protected ArrayList<Community> communities;
	protected ArrayList<Message> messages;
	protected ArrayList<Message> requests;
	
	public Researcher() {
		this.friends = new ArrayList<Researcher>();
		this.communities = new ArrayList<Community>();
		this.messages = new ArrayList<Message>();
		this.requests = new ArrayList<Message>();
	}
	
	public ArrayList<Message> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Message> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		String string = this.name + " " + this.login;
		return string;
	}
	
	public String getPassword() {
		return password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Researcher> getFriends() {
		return friends;
	}
	public void setFriends(ArrayList<Researcher> friends) {
		this.friends = friends;
	}
	public ArrayList<Community> getCommunities() {
		return communities;
	}
	public void setCommunities(ArrayList<Community> communities) {
		this.communities = communities;
	}
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public void removeFriend(String login){
		
		for(int i = 0; i < this.friends.size(); i++){
		
			if(this.friends.get(i).getLogin().equals(login)){
				this.friends.remove(i);
				break;
			}
		}
	}
	public void removeCommunity(String name){
		for(int i = 0; i < this.communities.size(); i++){
			if(this.communities.get(i).getName().equals(name)){
				this.communities.remove(i);
				break;
			}
		}
	}
	public void printFriends(){
		for(int i = 0; i < this.friends.size(); i++){
			System.out.println(this.friends.get(i).toString());
		}
	}
	public void printRequests(){
		for(int i = 0; i < this.requests.size(); i++){
			System.out.println(this.requests.get(i).getSender() + " " + this.requests.get(i).getText()); 
		}
	}
}
