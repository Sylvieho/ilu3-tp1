package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;
 
public class ZoneDeJeu {
	private List<Limite> pileDeLimites = new ArrayList<>();
	private List<Bataille> pileDeBatailles = new ArrayList<>();
	private Collection<Borne> collectionDeBornes = new ArrayList<>();
	
	public int donnerLimitationVitesse() {
		if(!pileDeLimites.isEmpty()) {
			Limite pioche = pileDeLimites.remove(0);
			if(!(pioche instanceof FinLimite)){
				return 50;
			}
		}
		return 200;
	}
	
	public int donnerKmParcourus() {
		int kmParcourus = 0;
		
		for(Borne borne : collectionDeBornes) {
			kmParcourus += borne.getKm();
		}
		
		return kmParcourus;
	}
	
	public void deposer(Carte c) {
		if(c instanceof Borne borne) {
			collectionDeBornes.add(borne);
		} else if (c instanceof Limite limite) {
			pileDeLimites.add(limite);
		} else if (c instanceof Bataille bataille) {
			pileDeBatailles.add(bataille);
		}
	}
	
	public boolean peutAvancer() {
		return !pileDeBatailles.isEmpty() && pileDeBatailles.getFirst().equals(Cartes.FEU_VERT);
	}
	
	public boolean estDepotFeuVertAutorise() {
		if (pileDeBatailles.isEmpty()) return true;
		Bataille top = pileDeBatailles.getFirst();
		return top.equals(Cartes.FEU_ROUGE) || (top instanceof Parade && !top.equals(Cartes.FEU_VERT));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() && (donnerLimitationVitesse() >= borne.getKm() 
				|| donnerKmParcourus() + borne.getKm() <= 1000);
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileDeLimites.isEmpty() || pileDeLimites.getFirst() instanceof FinLimite;
		} else if (limite instanceof FinLimite) {
			return !pileDeLimites.isEmpty() && pileDeLimites.getFirst() instanceof DebutLimite;
		}
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		if(bataille instanceof Parade) {
			if(bataille.getType() == Type.FEU) {
				return estDepotFeuVertAutorise();
			} else if(!pileDeBatailles.isEmpty()){
				Bataille sommet = pileDeBatailles.getFirst();
				return sommet instanceof Attaque && sommet.getType() == bataille.getType();
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		switch(carte) {
			case Borne borne:
				return estDepotBorneAutorise(borne);
			case Limite limite :
				return estDepotLimiteAutorise(limite);
			case Bataille bataille : 
				return estDepotBatailleAutorise(bataille);
			default : 
				return true;
		}
	}
}
