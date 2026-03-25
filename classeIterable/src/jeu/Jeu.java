package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Jeu {
	private Sabot sabot;
	
	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		sabot = new Sabot((Carte[]) listeCartes.toArray());
	}
}
