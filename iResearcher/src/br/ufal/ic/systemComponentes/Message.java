package br.ufal.ic.systemComponentes;

public class Message {
	protected Researcher Sender;
	protected String text;
	protected String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Researcher getSender() {
		return Sender;
	}
	public void setSender(Researcher sender) {
		Sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
