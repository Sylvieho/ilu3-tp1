package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee;
	private Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		super();
		this.joueurCourant = joueurCourant;
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if (!joueurCible.estDepotAutorise(carteJouee)) {
			return false;
		}
		
		if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
			return !joueurCourant.equals(joueurCible);
		}
		
		return joueurCourant.equals(joueurCible);
	}
	
	@Override
	public int hashCode() {
		return 31 * (joueurCourant.hashCode() + carteJouee.hashCode() + joueurCible.hashCode());
	}
	
	@Override
	public boolean equals(Object objet) {
		if (objet instanceof Coup coup) {
			return joueurCourant != null && joueurCourant.equals(coup.joueurCourant)
					&& carteJouee != null && carteJouee.equals(coup.carteJouee)
					&& joueurCible != null && joueurCible.equals(coup.joueurCible);
		}
		return false;
	}
	
	
}
