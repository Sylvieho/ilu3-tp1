package cartes;

public abstract class Probleme extends Carte {
	private Type type;

	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Probleme probleme) {
			return this.getType().equals(probleme.getType()) && this.getClass().equals(probleme.getClass());
		}
		return false;
	}

}
