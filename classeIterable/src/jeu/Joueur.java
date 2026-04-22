package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private final MainJoueur main = new MainJoueur();
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}
	
	public String getNom() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Joueur joueur) {
			return nom.equals(joueur.getNom());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) return null;
		Carte premiereCarte = sabot.piocher();
		donner(premiereCarte);
		return premiereCarte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsValides = new HashSet<>();
		for(Joueur joueurCible : participants) {
			for(Iterator<Carte> iter = main.iterator(); iter.hasNext();) {
				Carte carte = iter.next();
				Coup nouveauCoup = new Coup(this, carte, joueurCible);
				if(nouveauCoup.estValide()) coupsValides.add(nouveauCoup);
			}
		}
		return coupsValides;
	}
	
	public Set<Coup> coupsDefausse(){
		HashSet<Coup> coups = new HashSet<>();
			for (Iterator<Carte> iter = main.iterator(); iter.hasNext();) {
				Carte carte = iter.next();
				coups.add(new Coup(this, carte, null));
			}
				
		return coups;
	}
}
