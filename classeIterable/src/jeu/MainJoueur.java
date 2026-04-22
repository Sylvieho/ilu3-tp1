package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import cartes.Carte;
import jeu.Sabot.Iterateur;

public class MainJoueur implements Iterable<Carte>{
	private List<Carte> main;
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert(main.contains(carte));
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("Main : ");
		for(Carte carte : main) {
			string.append(carte.toString());
		}
		return string.toString();
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int ind = 0;
		
		public boolean hasNext() {
			return ind < main.size();
		}
		
		public Carte next() {
			if(hasNext()) {
				return main.get(ind);
			} else {
				throw new NoSuchElementException();
			}
		}
	}
	
}
