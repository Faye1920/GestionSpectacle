import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SpectacleStore {
    private String nomFichier;

    public SpectacleStore() {

    }

    public SpectacleStore(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public List<Spectacle> chargerSpectacles() throws IOException {
        List<Spectacle> listeSpectacles = new ArrayList<>();
        BufferedReader entree = new BufferedReader(new FileReader(nomFichier));
        // Lire les spectacles depuis le fichier
        try {
            String ligne;

            while ((ligne = entree.readLine()) != null) {
                // Séparer la ligne en trois parties : titre; année; réalisateur
                String[] attributs = ligne.split(";");
                int num = Integer.parseInt(attributs[0]);
                String titre = attributs[1];
                double prix = Double.parseDouble(attributs[2]);
                int nbBillets = Integer.parseInt(attributs[3]);

                // Ajouter le spectacle à la liste
                listeSpectacles.add(new Spectacle(num, titre, prix, nbBillets));
            }
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la lecture des données.");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite mais je ne sais pas pourquoi.");
        }
        finally{
            entree.close();
        }
        return listeSpectacles;
    }

    public void sauvegarderSpectacles(List<Spectacle> listeSpectacles) {
        try {
            // Ouvrir le fichier pour l'écriture
            BufferedWriter sortie = new BufferedWriter(new FileWriter(nomFichier));

            // Écrire chaque film dans le fichier
            for (Spectacle spectacle : listeSpectacles) {
                sortie.write(spectacle.getNum()
                        + ";" + spectacle.getTitre()
                        + ";" + spectacle.getPrix()
                        + ";" + spectacle.getNbBillets() + "\n");
            }

            // Fermer le fichier
            sortie.close();
            System.out.println("Les données ont été enregistrées dans le fichier " + nomFichier);
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de l'enregistrement des données.");
        }
    }

    public void sauvegarderSpectacleFichierChoisit(List<Spectacle> listeSpectacles) throws IOException {
        GestionSpectacle contexte = (GestionSpectacle) new GestionSpectacle().obtenirContexte();
        // Demander à l'utilisateur de choisir un fichier pour sauvegarder les données
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(contexte);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                // Ouvrir le fichier pour l'écriture
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

                // Écrire chaque spectacle dans le fichier
                for (Spectacle spectacle : listeSpectacles) {
                    writer.write(spectacle.getNum()
                            + ";" + spectacle.getTitre()
                            + ";" + spectacle.getPrix()
                            + ";" + spectacle.getNbBillets() + "\n");
                }
                // Fermer le fichier
                writer.close();
                JOptionPane.showMessageDialog(contexte, "Les données ont été enregistrées dans le fichier " + filename);

            } catch (IOException e) {
                JOptionPane.showMessageDialog(contexte, "Une erreur s'est produite lors de l'enregistrement des données.");
            }
        }
    }
}
