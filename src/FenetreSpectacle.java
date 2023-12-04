import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FenetreSpectacle extends JFrame implements ActionListener {
    private final int MAX_SPECTACLES = 15;

    private JLabel etiqNum, etiqTitre, etiqPrix, etiqNbBillets, etiqTotal;
    private JTextField champsNum, champsTitre, champsPrix, champsNbBillets, champsTotal;
    private JButton buttonTerminer, buttonInitialiser;
    //private JTextArea affichage;
    // Déclarer ton tableau
    // int tab[] = new int[15];
    Spectacle tabSpectacle[] = new Spectacle[MAX_SPECTACLES];

    //tabSpectacle[0] = new Spectacle(2222,"Celine Dion", 240);

	// CONSTRUCTEUR DE LA CLASSE
    public FenetreSpectacle() throws IOException{
		super("Facturation de spectacles");
		Container c = getContentPane();
		c.setLayout(new GridLayout(6,2,6,6));
		setSize(400,400);
		setLocation(400,400);
		this.setVisible(true);

		//premiere ligne de la fenetre
		etiqNum = new JLabel("Numéro de spectacle :");
		c.add(etiqNum);
		champsNum = new JTextField();
		champsNum.addActionListener(this);
		c.add(champsNum);

		//deuxieme ligne de la fenetre
		etiqTitre = new JLabel("Titre :");
		c.add(etiqTitre);
		champsTitre = new JTextField();
		champsTitre.setEditable(false);
		c.add(champsTitre);

		//troisieme ligne de la fenetre
		etiqPrix = new JLabel("Prix :");
		c.add(etiqPrix);
		champsPrix = new JTextField();
		champsPrix.setEditable(false);
		c.add(champsPrix);

		//quatrieme ligne de la fenetre
		etiqNbBillets = new JLabel("Nombre de billets :");
		c.add(etiqNbBillets);
		champsNbBillets = new JTextField();
		champsNbBillets.addActionListener(this);
		c.add(champsNbBillets);

		//cinquieme ligne de la fenetre
		etiqTotal = new JLabel("Total :");
		c.add(etiqTotal);
		champsTotal = new JTextField();
		champsTotal.setEditable(false);
		c.add(champsTotal);


        //Butons
        // sixieme ligne de la fenetre
        buttonTerminer = new JButton("Terminer");
        buttonTerminer.addActionListener(this);
		c.add(buttonTerminer);
        buttonInitialiser = new JButton("Initialiser");
        buttonInitialiser.addActionListener(this);
        c.add(buttonInitialiser);

		champsNbBillets.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateAll();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateAll();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateAll();
			}

			public void updateAll() {
				try {
					double prix = Double.parseDouble(champsPrix.getText());
					int nbBillets = Integer.parseInt(champsNbBillets.getText());
					double total = nbBillets * prix;
					champsTotal.setText(Double.toString(total));
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		//bouton
		buttonTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Retrieve the booked spectacle information from the file
				List<String> reserve = new ArrayList<>();
				try {
					SpectacleStore spectacleStore = new SpectacleStore(SpectacleController.getMonPath());
					List<Spectacle> listeSpectacle = spectacleStore.chargerSpectacles();
					List<String> reservation = new ArrayList<>();
					for (String res : reservation) {
						reserve.add(res);
					}
					for (Spectacle spectacle : listeSpectacle) {
						int nbBillets = spectacle.getNbBilletsInitial() - spectacle.getNbBillets();
						if (nbBillets > 0) {
							String message = spectacle.getTitre() + " : " + nbBillets + " billet(s)";
							reserve.add(message);
						}
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				// Afficher les informations sur les lunettes réservées dans une boîte de dialogue
				String message = "Résultat de la journée :\n";
				for (String r : reserve) {
					message += "- " + r + "\n";
				}
				JOptionPane.showMessageDialog(null, message, "Résultat de la journée", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		buttonInitialiser.addActionListener(new ActionListener() {
			//Ajouter une variable pour déterminer si le bouton "initialiser" a été cliqué pour la première fois
			boolean estPremierClique = true;

			public void actionPerformed(ActionEvent e) {
				if (estPremierClique) {
					// Afficher les informations relatives à l'affichage
					String nbSpectacle = champsNum.getText();
					boolean trouver = false;
					List<Spectacle> listeSpectacle = null;
					try {
						SpectacleStore spectacleStore = new SpectacleStore(SpectacleController.getMonPath());
						listeSpectacle = spectacleStore.chargerSpectacles();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					for (Spectacle spectacle : listeSpectacle) {
						if (Integer.toString(spectacle.getNum()).equals(nbSpectacle)) {
							champsTitre.setText((spectacle.getTitre()));
							champsPrix.setText(Double.toString(spectacle.getPrix()));
							int numBillets = Integer.parseInt(champsNbBillets.getText());
							champsTotal.setText(String.valueOf(spectacle.getPrix() * numBillets));
//							champsTotal.setText(String.valueOf(spectacle.getPrix() * spectacle.getNbBillets()));
							trouver = true;
							break;
						}
					}
					if (!trouver) {
						JOptionPane.showMessageDialog(null, "Numéro de spectacle incorrect ou inexistant");
					}
				} else {
					// Informations sur les réservations pour le spectacle
					String nbSpectacle = champsNum.getText();
					boolean trouver = false;
					List<Spectacle> listeSpectacle = null;
					try {
						SpectacleStore spectacleStore = new SpectacleStore(SpectacleController.getMonPath());
						listeSpectacle = spectacleStore.chargerSpectacles();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					for (Spectacle spectacle : listeSpectacle) {
						if (Integer.toString(spectacle.getNum()).equals(nbSpectacle)) {
							int numBillets = Integer.parseInt(champsNbBillets.getText());
							int numTickets = (spectacle.getNbBillets());
							if (numBillets > numTickets) {
								JOptionPane.showMessageDialog(null,
										"Nombre de billets dépasse nombre de billets en vente");
							} else {
								double total = spectacle.getPrix() * Integer.parseInt(champsNbBillets.getText());
								spectacle.setNbBillets(Integer.parseInt(String.valueOf(numTickets - numBillets)));
								SpectacleStore spectacleStore = new SpectacleStore(SpectacleController.getMonPath());
								spectacleStore.sauvegarderSpectacles(listeSpectacle);
								String messageCommande = "Vous avez réservé " + champsNbBillets.getText()
										+ " billet(s) pour le spectacle \"" + spectacle.getTitre()
										+ "\" votre total est de "
										+ total + "$.";
								JOptionPane.showMessageDialog(null, messageCommande);
								JOptionPane.showMessageDialog(null, "Réservation confirmée.");
								String messageConclusion = "Merci d'avoir réservé votre billet \""
										+ spectacle.getTitre() + "\". Bon spéctacle!";
								JOptionPane.showMessageDialog(null, messageConclusion);
							}
							trouver = true;
							break;
						}
					}
					if (!trouver) {
						JOptionPane.showMessageDialog(null, "Numéro de spectacle incorrect ou inexistant");
					}
				}
				// Basculer la valeur de la variable de manière à ce qu'un message différent s'affiche la prochaine fois que l'on clique dessus.
				estPremierClique = !estPremierClique;

				// Recherche d'informations sur les lunettes réservées
				JTable tableauPlaces = new JTable();
				List<String> reservation = new ArrayList<>();
				for (int i = 0; i < tableauPlaces.getRowCount(); i++) {
					String titre = (String) tableauPlaces.getValueAt(i, 0);
					int nbBillets = (int) tableauPlaces.getValueAt(i, 2);
					if (nbBillets > 0) {
						String message = titre + " : " + nbBillets + " billet(s)";
						System.out.println(champsTitre + " a " + champsNbBillets);
						reservation.add(message);
					}
				}
			}
		});
    }

	@Override
	public void actionPerformed(ActionEvent e) {}
}

