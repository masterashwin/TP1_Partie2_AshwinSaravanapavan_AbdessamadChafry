import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.*;
import java.util.Arrays;;

//Atelier 7 sorie 00 pour extraire les ligne ayant x nom abdesssamad.

public class Facture {

	static String FICHIER = "restaurant.txt";

	public static void main(String[] args) throws IOException {
		String[] nouvTab = lireFichier(FICHIER);
		nouvTab = remLastCase(nouvTab);
		for (int i = 0; i < nouvTab.length; i++) {
			System.out.println(nouvTab[i]);
			// hello.
			// if (hello[i].contains(":")) {
			// ArrayTools.
		}
		int[] index = indexSection(nouvTab);
		System.out.println(index[0] +"hello"+index[1]);
	}
	// }

	//DONNE INDEX DES SECTIONS
	public static int[] indexSection(String tmp[]) {
		String separateur = ":";
		int compteur=0;
		int[] index = new int[3];
		for (int i=0;i<tmp.length;i++) {
		    if (tmp[i].contains(separateur)) {
		        index[compteur] = i;
		        compteur++;
		    }
		}
		return index;
	}
	
	
	//ENLEVE DERNIER LIGNE
	public static String[] remLastCase(String tmp[]) {
		String[] out = new String[tmp.length - 1];
		for (int i = 0; i < out.length; i++) {
			out[i] = tmp[i];
		}
		return out;
	}

	//LIRE FICHIER
	public static String[] lireFichier(String cheminFichier) throws IOException {
		String[] tableau = new String[compteLine(FICHIER) + 1];
		int i = 0;
		FileReader fluxEntreeBrute = null;
		BufferedReader fluxEntreeBuffered = null;
		String ligneLue = null;

		try {
			fluxEntreeBrute = new FileReader(cheminFichier);// FileNotFoundException
			fluxEntreeBuffered = new BufferedReader(fluxEntreeBrute);// IOException
			while ((ligneLue = fluxEntreeBuffered.readLine()) != null) {// IOException
				tableau[i] = ligneLue;
				i++;
			}
		} catch (FileNotFoundException exc) {
			System.out.println("Le fichier " + cheminFichier + " est absent");
		} catch (IOException exc) {
			System.out.print("problème de lecture du fichier ");
		} finally {
			try {
				if (fluxEntreeBrute != null) {
					fluxEntreeBrute.close();
				}
				if (fluxEntreeBuffered != null) {
					fluxEntreeBuffered.close();
				}
			} catch (IOException exc) {
				System.out.println("problème de fermeture du fichier ");
			}
		}
		return tableau;

	}

	//COMPTE LIGNE DU FICHIER
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

}
