/* 
 * Cette classe contient la m�thode qui permet d'effectuer une recherche
 * binaire dans un tableau d'objets ainsi que la m�thode qui permet de 
 * trier un tableau d'objets.
 * 
 */

public class Outil{
	
	//M�THODE QUI PERMET DE RECHERCHER UN NUM�RO DE SPECTACLE
	public static int rechercheBinaire(int no, Spectacle tableau[], int nbEl){
		int debut = 0;
		int fin = nbEl - 1;
		boolean trouve = false;
		int posi = -1;
		int milieu;
		
		while (debut <= fin && !trouve){
			milieu = (debut + fin) / 2;
			if (tableau[milieu].getNum() == no){
				posi = milieu;
				trouve = true;
			}
			else
				if (tableau[milieu].getNum() < no)
					debut = milieu + 1;
				else
					fin = milieu - 1;
		}
		return posi;
	}
	
	//M�THODE QUI PERMET DE TRIER LES SPECTACLES
	public static void triBulle(Spectacle tableau[], int attribut, int nbEl){
		Spectacle tempo;
		int limite = nbEl-1,
			posi=-1;
		boolean trier;
		
		do{
			trier = false;
			for(int i=0; i<limite; i++){
				if(tableau[i].compareTo(tableau[i+1],attribut)>0){
					tempo = tableau[i];
					tableau[i] = tableau[i+1];
					tableau[i+1] = tempo;
					trier = true;
					posi = i;
				}
			}
			limite = posi;
		}while(limite>0 && trier);
	}

	
}
