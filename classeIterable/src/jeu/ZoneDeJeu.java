package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;
 
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
}
