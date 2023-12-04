import java.io.*;

public class App {
    // MÉTHODE PRINCIPALE
    public static void main(String[] args) throws IOException {
        String pathFichier = "/Users/faye/Desktop/Ahuntsic/5-Programmation orientée objet/6-Labors/Lab1/GestionSpectacle/src/spectacles.txt";
        SpectacleController.setMonPath(pathFichier);

        FrameSpectacle maFrame = new FrameSpectacle();
        maFrame.setVisible(true);

        // SwingMenuDemo maFenetre = new SwingMenuDemo();
        // maFenetre.montrer();

        //SwingMenuDemo.montrer();
    }

//    static BufferedReader clavier;
//    static String nomFichier = "/Users/faye/Desktop/GestionSpectacle/spectacles.txt";
//    static BufferedReader entree;
//    static BufferedWriter sortie;
//    static int nbSpectacles;
//
//    public static int menu() throws IOException {
//        int choix;
//        boolean valide = false;
//        clavier = new BufferedReader(new InputStreamReader(System.in));
//
//        do {
//            System.out.println("\n****** MENU DE GESTION DE SPECTACLE");
//            System.out.println("\n\t1-Ajouter");
//            System.out.println("\t2-Lister");
//            System.out.println("\t3-Modifier");
//            System.out.println("\t4-Supprimer");
//            System.out.println("\t5-Quitter");
//            System.out.println("\n\tEntrez votre choix : ");
//            choix = Integer.parseInt(clavier.readLine());
//            valide = (choix == 1 || choix == 2 || choix == 3 || choix == 4|| choix == 5);
//        } while (!valide);
//        return choix;
//    }
//
//    public static int chargerSpectacles(Spectacle tabSpectacles[]) throws IOException {
//        entree = new BufferedReader(new FileReader(nomFichier));
//        nbSpectacles = 0;
//        //Lire les spectacles depuis le fichier
//        try {
//            String ligne; //ligne = 12345;Ice Follies;23.45";
//            while ((ligne = entree.readLine()) != null) {
//                //Séparer la ligne en trois parties : numéro de spectacle; titre; prix
//                String[] attributs = ligne.split(";"); //attributs[0]="12345", attributs[1]="Ice Follies", attributs[2]="23.45"
//                int num = Integer.parseInt(attributs[0]);
//                String titre = attributs[1];
//                double prix = Double.parseDouble(attributs[2]);
//                //Ajouter le spectacle à la liste
//                tabSpectacles[nbSpectacles] = new Spectacle(num, titre, prix);
//                nbSpectacles++;
//            }
//        } catch (IOException e) {
//            System.out.println("Une erreur s'est produite lors de la lecture des données.");
//        } catch (Exception e) {
//            System.out.println("Une erreur s'est produite mais je ne sais pas pourquoi.");
//        } finally {
//            entree.close();
//        }
//        return nbSpectacles;
//    }
//
//    public static void sauvegarderSpectacles(Spectacle tabSpectacles[]) {
//        try {
//            // Ouvrir le document pour la rédaction
//            sortie = new BufferedWriter(new FileWriter(nomFichier));
//            // Écrire chaque spectacle dans le fichier
//            for (int i = 0; i < nbSpectacles; i++) {
//                System.out.println(tabSpectacles[i].getTitre());
//                sortie.write(tabSpectacles[i].getNum()
//                        + ";" + tabSpectacles[i].getTitre()
//                        + ";" + tabSpectacles[i].getPrix() + "\n");
//            }
//
//            //Fermer le fichier
//            sortie.close();
//            System.out.println("Ces données sont enregistrées dans " + nomFichier);
//        } catch (IOException e) {
//            System.out.println("Une erreur s'est produite lors de l'enregistrement des données.");
//        }
//    }
//
//    public static void ajouter(Spectacle tabSpectacles[]) throws IOException {
//        int num;
//        String titre;
//        double prix;
//        boolean continuer = true;
//        clavier = new BufferedReader(new InputStreamReader(System.in));
//        while (continuer) {
//            System.out.println("\tEntrez le numéro du spectacle : ");
//            num = Integer.parseInt(clavier.readLine());
//            System.out.println("\tEntrez le titre du spectacle : ");
//            titre = clavier.readLine();
//            System.out.println("\tEntrez le prix du spectacle : ");
//            prix = Double.parseDouble(clavier.readLine());
//
//            tabSpectacles[nbSpectacles] = new Spectacle(num, titre, prix);
//            nbSpectacles++;
//            System.out.println("\n\tVoulez-vous ajouter un autre spectacle (O-Oui, autre-Non) ? ");
//            continuer = clavier.readLine().equalsIgnoreCase("O");
//        }
//    }
//
//    public static void lister(Spectacle tabSpectacles[]) {
//        System.out.println("\n\tNuméro de spectacle\t\t\tTitre\t\t\tPrix\n");
//        //Afficher chaque spectacle
//        for (int i = 0; i < nbSpectacles; i++) {
//            System.out.println("\t"+String.format("%-20s",tabSpectacles[i].getNum())+"\t"+ String.format("%-20s",
//                    tabSpectacles[i].getTitre()) + "\t"
//                    + String.format("%-20s",tabSpectacles[i].getPrix()));
//        }
//    }
//
//    public static int rechercher(Spectacle tabSpectacle[], String titre) {
//        boolean trouver = false;
//        int i = 0, pos = -1;
//        while(i < nbSpectacles && !trouver) {
//            if(tabSpectacle[i].getTitre().equalsIgnoreCase(titre)){
//                trouver = true;
//                pos = i;
//            } else {
//                ++i;
//            }
//        }
//        return pos;
//    }
//
//    public static void modifier(Spectacle tabSpectacle[]) throws IOException {
//        int num, pos;
//        String titre;
//        double prix;
//        boolean continuer = true;
//        boolean changer = false;
//
//        clavier = new BufferedReader(new InputStreamReader(System.in));
//
//        while (continuer) {
//            System.out.print("\tEntrez le titre du film : ");
//            titre = clavier.readLine();
//            pos = rechercher(tabSpectacle, titre);
//            if(pos != -1){
//                num = tabSpectacle[pos].getNum();
//                prix = tabSpectacle[pos].getPrix();
//
//                System.out.print("\n\tVoulez-vous changer le numéro du spectacle (O-Oui, autre-Non) ?  ");
//                changer = clavier.readLine().equalsIgnoreCase("O");
//                if(changer){
//                    System.out.print("\tEntrez le nouveau titre du spectacle : ");
//                    num = Integer.parseInt(clavier.readLine());
//                    tabSpectacle[pos].setNum(num);
//                }
//                System.out.print("\n\tVoulez-vous changer le titre du film (O-Oui, autre-Non) ?  ");
//                changer = clavier.readLine().equalsIgnoreCase("O");
//                if (changer) {
//                    System.out.print("\tEntrez le nouveau titre du film : ");
//                    titre = clavier.readLine();
//                    tabSpectacle[pos].setTitre(titre);
//                }
//                System.out.print("\n\tVoulez-vous changer le prix du film (O-Oui, autre-Non) ?  ");
//                changer = clavier.readLine().equalsIgnoreCase("O");
//                if (changer) {
//                    System.out.print("\tEntrez le prix du film : ");
//                    prix = Double.parseDouble(clavier.readLine());
//                    tabSpectacle[pos].setPrix(prix);
//                }
//            } else {
//                System.out.println("\n\tLe film de titre "+titre+ " est introuvable !");
//            }
//            System.out.print("\n\tVoulez-vous modifier un autre film (O-Oui, autre-Non) ?  ");
//            continuer = clavier.readLine().equalsIgnoreCase("O");
//        }
//    }
//
//    public static void supprimer(Spectacle tabSpectacles[]) throws IOException {
//        String titre;
//        int pos = 0;
//        boolean continuer = true;
//        final int NBSPECTACLES = nbSpectacles;
//
//        Spectacle tabSpectacleTemp[] = new Spectacle[NBSPECTACLES];
//        clavier = new BufferedReader(new InputStreamReader(System.in));
//        while (continuer) {
//            System.out.print("\tEntrez le titre du spectacle à supprimer : ");
//            titre = clavier.readLine();
//            for (int i=0; i < NBSPECTACLES; i++) {
//                if(!tabSpectacles[i].getTitre().equalsIgnoreCase(titre)){
//                    tabSpectacleTemp[pos] = tabSpectacles[i];
//                    ++pos;
//                }else {
//                    nbSpectacles--;
//                }
//            }
//            System.out.print("\n\tVoulez-vous supprimer un autre spectacle (O-Oui, autre-Non) ?  ");
//            continuer = clavier.readLine().equalsIgnoreCase("O");
//
//        } // while
//        // Transfert dans tabFilms
//        for (int i = 0; i < nbSpectacles; i++) {
//            tabSpectacles[i] = tabSpectacleTemp[i];
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        final int MAX_SPECTACLES = 200;
//        Spectacle tabSpectacles[] = new Spectacle[MAX_SPECTACLES];
//        int choix;
//        nbSpectacles = chargerSpectacles(tabSpectacles);
//        do {
//            choix = menu();
//            switch(choix){
//                case 1:
//                    ajouter(tabSpectacles);
//                    break;
//                case 2:
//                    lister(tabSpectacles);
//                    break;
//                case 3:
//                    modifier(tabSpectacles);
//                    break;
//                case 4:
//                    supprimer(tabSpectacles);
//                    break;
//                case 5:
//                    sauvegarderSpectacles(tabSpectacles);
//                    break;
//            }
//        }while(choix != 5);
//
//        System.out.println("\n**** Au revoir et bonne chance !!!");
//    }
}
