package br.ufal.ic.systemComponentes;

import java.util.ArrayList;

public interface DataBase<E> {
	public void remove(String string);
	public void add(E element);
	public ArrayList<E> findAll();
	public E search(String string);

}
