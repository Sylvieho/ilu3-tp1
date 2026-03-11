package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + "KM"; 
	}
	
	@Override 
	public boolean equals(Object objet) {
		if(objet instanceof Borne borne) return this.km == borne.km;
		return false;
	}
}
