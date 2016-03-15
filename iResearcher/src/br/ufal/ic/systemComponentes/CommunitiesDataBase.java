package br.ufal.ic.systemComponentes;

import java.util.ArrayList;

public class CommunitiesDataBase implements DataBase<Community>{

	protected ArrayList<Community> communities;
	
	public CommunitiesDataBase() {
		this.communities = new ArrayList<Community>();
	}
	
	@Override
	public void remove(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Community element) {
		this.communities.add(element);
		
	}

	@Override
	public ArrayList<Community> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Community search(String string) {
		for(int i = 0; i < this.communities.size(); i++){
			if(this.communities.get(i).getName().equals(string)){
				return this.communities.get(i);
			}
		}
		return null;
	}

}
