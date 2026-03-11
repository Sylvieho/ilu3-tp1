package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes;
	
	private int nbOperations = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide(){
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if(nbCartes < cartes.length) {
			cartes[nbCartes] = carte;
			nbCartes++;
			
			nbOperations++;
		} else throw new ArrayIndexOutOfBoundsException();
	}
	
	public Carte piocher() {
		Iterator<Carte> iterateur = iterator();
		
		Carte premiereCarte = iterateur.next();
		iterateur.remove();
		
		return premiereCarte;
	}
	
	private class Iterateur implements Iterator<Carte>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int indiceConcurrence = nbOperations;
	
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();
			if(hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur ++;
				nextEffectue = true;
				return carte;
			}else throw new NoSuchElementException();

		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if(nbCartes< 1 || !nextEffectue) throw new IllegalStateException();
			for (int i = indiceIterateur -1 ; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur --;
			nbCartes --;
			nbOperations ++;
			indiceConcurrence ++;
		}
		
		private void verificationConcurrence() {
			if(nbOperations != indiceConcurrence) throw new ConcurrentModificationException();
		}
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
}
