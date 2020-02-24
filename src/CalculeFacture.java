import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

//Atelier 7 sorie 00 pour extraire les ligne ayant x nom abdesssamadsalut.

public class CalculeFacture {

	static String FICHIER = "restaurant.txt";

	// }
	public static int[] tabIndex(int[] tab) {
		return tab;
	}

	public static String[][] creerTabPersonne(int[] tabIndex, String[] tabComplet) {
		String[][] tabPersonne = new String[tabIndex[1] - 1][tabIndex[1] - 1];

		for (int i = tabIndex[0]; i < tabIndex[1] - 1; i++) {
			tabPersonne[i][0] = tabComplet[i + 1];
			tabPersonne[i][1] = "0 ";

		}
		return tabPersonne;
	}

	public static String[][] creerTabPlat(int[] tabIndex, String[] tabComplet) {
		String[][] tabPlat = new String[tabIndex[2] - tabIndex[1]][tabIndex[2] - tabIndex[1]];

		int compteur = 0;
		try {
			for (int i = tabIndex[1] + 1; i < tabIndex[2]; i++) {

				String retour = "", retour1 = "", separateur = " ";

				String[] parti = tabComplet[i].split(separateur);
				retour = parti[0];
				retour1 = parti[1];

				tabPlat[compteur][0] = retour;
				tabPlat[compteur][1] = retour1;
				compteur++;

			}
		} catch (Exception e) {
			System.out.println("Le fichier ne respect pas le format demandé.");
		}

		return tabPlat;
	}

	public static String[][] creerTabCommande(int[] tabIndex, String[] tabComplet) {
		String[][] tabCommande = new String[tabComplet.length - tabIndex[2]][3];
		int compteur = 0;
		try {
			for (int i = tabIndex[2] + 1; i < tabComplet.length; i++) {

				String retour = "", retour1 = "", retour2 = "", separateur = " ";

				String[] parti = tabComplet[i].split(separateur);
				// System.out.println(tabComplet[i]);
				retour = parti[0];
				retour1 = parti[1];
				retour2 = parti[2];

				tabCommande[compteur][0] = retour;
				tabCommande[compteur][1] = retour1;
				tabCommande[compteur][2] = retour2;

				compteur++;

			}
		} catch (Exception e) {
			System.out.println("Le fichier ne respect pas le format demandé.");
		}

		// System.out.println("COUCOU" + tabCommande.length);
		return tabCommande;
	}

	// DONNE INDEX DES SECTIONS
	public static int[] indexSection(String tmp[]) {
		String separateur = ":";
		int compteur = 0;
		int[] index = new int[3];
		try {
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].contains(separateur)) {
					index[compteur] = i;
					compteur++;
				}
			}
		} catch (Exception e) {
			System.out.println("Le fichier ne respect pas le format demandé.");
		}
		
		return index;
	}

	// ENLEVE DERNIER LIGNE
	public static String[] remLastCase(String tmp[]) {
		String[] out = new String[tmp.length - 1];
		for (int i = 0; i < out.length; i++) {
			out[i] = tmp[i];
		}
		return out;
	}

	// LIRE FICHIER
	public static String[] lireFichier(String cheminFichier) throws IOException {
		String[] tableauOriginal = new String[compteLine(FICHIER) + 1];
		int i = 0;
		FileReader fluxEntreeBrute = null;
		BufferedReader fluxEntreeBuffered = null;
		String ligneLue = null;

		try {
			fluxEntreeBrute = new FileReader(cheminFichier);// FileNotFoundException
			fluxEntreeBuffered = new BufferedReader(fluxEntreeBrute);// IOException
			while ((ligneLue = fluxEntreeBuffered.readLine()) != null) {// IOException
				tableauOriginal[i] = ligneLue;
				i++;
			}
		} catch (FileNotFoundException exc) {
			System.out.println("Le fichier " + cheminFichier + " est absent");
		} catch (IOException exc) {
			System.out.print("problÃ¨me de lecture du fichier ");
		} finally {
			try {
				if (fluxEntreeBrute != null) {
					fluxEntreeBrute.close();
				}
				if (fluxEntreeBuffered != null) {
					fluxEntreeBuffered.close();
				}
			} catch (IOException exc) {
				System.out.println("problÃ¨me de fermeture du fichier ");
			}
		}
		return tableauOriginal;

	}

	// COMPTE LIGNE DU FICHIER
	public static int compteLine(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];

			int readChars = is.read(c);
			if (readChars == -1) {
				// bail out if nothing to read
				return 0;
			}

			// make it easy for the optimizer to tune this loop
			int count = 0;
			while (readChars == 1024) {
				for (int i = 0; i < 1024;) {
					if (c[i++] == '\n') {
						++count;
					}
				}
				readChars = is.read(c);
			}

			// count remaining characters
			while (readChars != -1) {
				// System.out.println(readChars);
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
				readChars = is.read(c);
			}

			return count == 0 ? 1 : count;
		} finally {
			is.close();
		}
	}

	public static void additionneString(String[][] tabPersn) {
		String separateur = " ";

		for (int i = 0; i < tabPersn.length; i++) {

		}

		for (int i = 0; i < tabPersn.length; i++) {
			String[] parti = tabPersn[i][1].split(separateur);
			double retour1 = 0, total = 0;

			for (int j = 0; j < parti.length; j++) {

				retour1 += Double.parseDouble(parti[j]);
			}

			total = retour1;

			tabPersn[i][1] = String.valueOf(total);

		}

		for (int i = 0; i < tabPersn[0].length; i++) {
			System.out.println(tabPersn[i][0] + " " + tabPersn[i][1] + "$");
		}

	}

	public static void afficher(String[][] tabPlat, String[][] tabPers, String[][] tabCom) {
		System.out.println("Bienvenue chez Abdessamad Chafry et Ashwin Saravanapavan!\n\nFacture:");
		double prix = 0;
		double quantite = 0;
		double total = 0;
		for (int i = 0; i < tabCom[0].length; i++) {

			for (int j = 0; j < tabPlat[0].length; j++) {

				if (tabCom[i][1].equals(tabPlat[j][0])) {

					prix = Double.parseDouble(tabPlat[j][1]);

					quantite = Double.parseDouble(tabCom[i][2]);

					total = prix * quantite;

					for (int k = 0; k < tabPers[0].length; k++) {
						if (tabPers[k][0].equals(tabCom[i][0])) {
							tabPers[k][1] += total + " ";
						}
					}

				}
			}

		}

		additionneString(tabPers);

	}

}
