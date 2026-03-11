package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes;
	
	public JeuDeCartes() {
		typesDeCartes = new Configuration[19];
		typesDeCartes[0] = new Configuration(new Borne(25), 10);
		typesDeCartes[1] = new Configuration(new Borne(50), 10);
		typesDeCartes[2] = new Configuration(new Borne(75), 10);
		typesDeCartes[3] = new Configuration(new Borne(100), 12);
		typesDeCartes[4] = new Configuration(new Borne(200), 4);
		typesDeCartes[5] = new Configuration(new Parade(Type.FEU), 14);
		typesDeCartes[6] = new Configuration(new FinLimite(), 6);
		typesDeCartes[7] = new Configuration(new Parade(Type.ESSENCE), 6);
		typesDeCartes[8] = new Configuration(new Parade(Type.CREVAISON), 6);
		typesDeCartes[9] = new Configuration(new Parade(Type.ACCIDENT), 6);
		typesDeCartes[10] = new Configuration(new Attaque(Type.FEU), 5);
		typesDeCartes[11] = new Configuration(new DebutLimite(), 4);
		typesDeCartes[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
		typesDeCartes[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
		typesDeCartes[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);
		typesDeCartes[15] = new Configuration(new Botte(Type.FEU), 1);
		typesDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1);
		typesDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1);
		typesDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1);
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder affichage = new StringBuilder("");
		for(Configuration carte : typesDeCartes) {
			affichage.append(carte.getNbExemplaires());
			affichage.append(" ");
			affichage.append(carte.getCarte());
			affichage.append("\n");
		}
		return affichage.toString();
	}
	
	private static class Configuration{
		private Carte carte;
		private int nbExemplaires;

		public Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
	}
	
	public Carte[] donnerCartes() {
		int nbCartes = 0;
		
		for(Configuration cartes : typesDeCartes) {
			nbCartes += cartes.getNbExemplaires();
		}
		
		Carte[] cartes = new Carte[nbCartes];
		
		for(int iType = 0, iCartes = 0; iType < typesDeCartes.length; iType++) {
			for(int nbExemplaire = 0; nbExemplaire < typesDeCartes[iType].getNbExemplaires(); nbExemplaire++, iCartes++) {
				cartes[iCartes] = typesDeCartes[iType].getCarte();
			}
		}
		
		return cartes;
	}

	public boolean checkCount() {
		Carte[] cartes = donnerCartes();
		int nbCartes = 0;
		for (int i = 0; i < typesDeCartes.length; i++) {
			for (int j = 0; j < typesDeCartes[i].getNbExemplaires(); j++) {
				if (!cartes[nbCartes].equals(typesDeCartes[i].getCarte()))
					return false;
				nbCartes++;
			}
		}
		return true;
	}
}
