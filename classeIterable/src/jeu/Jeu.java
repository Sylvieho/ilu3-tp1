package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	private LinkedHashSet<Joueur> joueurs = new LinkedHashSet<>();
	private static final int NBCARTES = 6;
	
	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		
		GestionCartes gestion = new GestionCartes();
		GestionCartes.melanger(listeCartes);
		
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	public void inscrire(Set<Joueur> joueursInscrits) {
		for(Joueur joueur : joueursInscrits) {
			joueurs.addLast(joueur);
		}
	}
	
	public void distribuerCartes() {
		for(int i = 0; i < NBCARTES; i++) {
			for(Joueur joueur : joueurs) {
				joueur.donner(sabot.piocher());
			}
		}
	}
}
