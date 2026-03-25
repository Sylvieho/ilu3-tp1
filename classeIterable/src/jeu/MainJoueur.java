package jeu;

import java.util.List;

import cartes.Carte;

public class MainJoueur {
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
}
