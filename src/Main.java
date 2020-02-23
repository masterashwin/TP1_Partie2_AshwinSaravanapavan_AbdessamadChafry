import java.io.IOException;

public class Main {

	static String FICHIER = "restaurant.txt";

	public static void main(String[] args) throws IOException {
		String[] tabSansFin = CalculeFacture.lireFichier(FICHIER);
		tabSansFin = CalculeFacture.remLastCase(tabSansFin);
		int[] tabIndex = CalculeFacture.indexSection(tabSansFin);
		String[][] tabPersonne = new String[tabIndex[1] - 1][tabIndex[1] - 1];
		String[][] tabPlat = new String[(tabIndex[2] - tabIndex[1])-1][(tabIndex[2] - tabIndex[1])-1];
		String[][] tabCommande = new String[tabSansFin.length - tabIndex[2]][tabSansFin.length - tabIndex[2]];
		tabPersonne = CalculeFacture.creerTabPersonne(tabIndex, tabSansFin);
		tabPlat = CalculeFacture.creerTabPlat(tabIndex, tabSansFin);
		tabCommande = CalculeFacture.creerTabCommande(tabIndex, tabSansFin);

		CalculeFacture.afficher(tabPlat, tabPersonne, tabCommande);
		
		
	}

}