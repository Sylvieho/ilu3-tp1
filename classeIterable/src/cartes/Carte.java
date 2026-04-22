package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object objet){
		if(objet instanceof Carte carte) return toString().equals(carte.toString());
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}
}
