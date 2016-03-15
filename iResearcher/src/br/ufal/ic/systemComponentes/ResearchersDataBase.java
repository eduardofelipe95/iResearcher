package br.ufal.ic.systemComponentes;

import java.util.ArrayList;

public class ResearchersDataBase implements DataBase<Researcher>{

	protected ArrayList<Researcher> researchers;
	
	public ResearchersDataBase() {
		this.researchers = new ArrayList<Researcher>();
	}
	
	@Override
	public void remove(String login) {
		int j = 0;
		
		for(int i = 0; i < this.researchers.size(); i++){
			for(int k = 0; j < this.researchers.get(i).getMessages().size(); j++){
				if(this.researchers.get(i).getMessages().get(k).getSender().getLogin().equals(login)){
					this.researchers.get(i).getMessages().remove(k);
					k--;
				}
			}
		}
		
		for(int i = 0; i < this.researchers.size(); i++){
			if(this.researchers.get(i).getLogin().equals(login)){
				j = i;
			}else{
				for(int k = 0; k < this.researchers.get(i).getFriends().size(); k++){
					if(this.researchers.get(i).getFriends().get(k).getLogin().equals(login)){
						this.researchers.get(i).getFriends().remove(k);
						break;
					}
				}
			}
		}
		
		this.researchers.remove(j);
		
	}

	@Override
	public void add(Researcher researcher) {
		this.researchers.add(researcher);
		
	}

	
	public ArrayList<Researcher> findAll() {
		return this.researchers;
		
		
	}

	@Override
	public Researcher search(String login) {
		
		for(int i = 0; i < this.researchers.size(); i++){
			if(this.researchers.get(i).getLogin().equals(login)){
				return this.researchers.get(i);
			}
		}
		
		return null;
	}

}
