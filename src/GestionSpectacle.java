import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestionSpectacle extends JFrame {
    private List<Spectacle> listeSpectacles;
    private JTable tabSpectacles;
    private JTextField textFieldNum, textFieldTitre, textFieldPrix, textNbBillets;

    private JLabel labelNum, labelTitre, labelPrix, labelNbBillets;

    private JPanel mainPanel;

    private boolean enregQuitter = false;
    private Object contexte = this; // JFileChooser dans Spectaclestore

    public GestionSpectacle() throws IOException {
        super("Gestion de Spectacles");
        listeSpectacles = new ArrayList<>();
        initUI();
        this.listeSpectacles = chargerSpectacles();
        montrerlisteSpectacles();
    }

    public GestionSpectacle obtenirContexte() {
        return (GestionSpectacle) this.contexte;
    }

    private void initUI() {

        // Couleur de fond
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 475);
        setLocationRelativeTo(null);

        // Pour le panel du tableau
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBounds(50, 25, 700, 200);
        add(tablePanel);
        tablePanel.setLayout(new BorderLayout());

        // Créer un model pour la table Spectacle
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Numéro de spectacle");
        model.addColumn("Titre");
        model.addColumn("Prix");
        model.addColumn("Nombre de billets");

        for (Spectacle Spectacle : listeSpectacles) {
            model.addRow(new Object[] { Spectacle.getNum(), Spectacle.getTitre(),  Spectacle.getPrix() });
        }

        // Créer la table via le model (colonnes)
        tabSpectacles = new JTable(model);

        // Ajouter une barre de defilement
        JScrollPane scrollPane = new JScrollPane(tabSpectacles);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Ajouter le panel pour le formulaire
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBounds(50, 250, 700, 200);
        add(formPanel);

        // Ajouter les étiquettes et les champs de saisie pour formulaire
        labelNum = new JLabel("Numéro de Spectacle : ");
        labelNum.setFont(new Font("Courier", Font.PLAIN, 14));
        textFieldNum = new JTextField(4);
        labelTitre = new JLabel("Titre : ");
        labelTitre.setFont(new Font("Courier", Font.PLAIN, 14));
        textFieldTitre = new JTextField(20);
        labelPrix = new JLabel("Prix : ");
        labelPrix.setFont(new Font("Courier", Font.PLAIN, 14));
        textFieldPrix = new JTextField(20);
        labelNbBillets = new JLabel("Nombre de billets : ");
        labelNbBillets.setFont(new Font("Courier", Font.PLAIN, 14));
        textNbBillets = new JTextField(20);

        // Ajouter les boutons
        JButton buttonAjouter = new JButton("Ajouter");
        buttonAjouter.addActionListener(e -> ajouterSpectacle());
        JButton buttonModifier = new JButton("Modifier");
        buttonModifier.addActionListener(e -> modifierSpectacle());
        JButton buttonSupprimer = new JButton("Supprimer");
        buttonSupprimer.addActionListener(e -> supprimerSpectacle());
        JButton buttonQuitter = new JButton("Quitter");
        buttonQuitter.addActionListener(e -> quitter()); // ((JFrame) SwingUtilities.getWindowAncestor((Component)
        // e.getSource())).dispose();

        // Ajouter la mise en page d'un formulaire
        GroupLayout layoutForm = new GroupLayout(formPanel);
        formPanel.setLayout(layoutForm);
        layoutForm.setAutoCreateGaps(true);
        layoutForm.setAutoCreateContainerGaps(true);
        layoutForm.setHorizontalGroup(layoutForm.createSequentialGroup()
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelNum)
                        .addComponent(labelTitre)
                        .addComponent(labelPrix)
                        .addComponent(labelNbBillets))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldNum)
                        .addComponent(textFieldTitre)
                        .addComponent(textFieldPrix)
                        .addComponent(textNbBillets)
                        .addGroup(layoutForm.createSequentialGroup()
                                .addComponent(buttonAjouter)
                                .addComponent(buttonModifier)
                                .addComponent(buttonSupprimer)
                                .addComponent(buttonQuitter))));

        layoutForm.linkSize(SwingConstants.HORIZONTAL, buttonAjouter, buttonModifier, buttonSupprimer,
                buttonQuitter);

        layoutForm.setVerticalGroup(layoutForm.createSequentialGroup()
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNum)
                        .addComponent(textFieldNum))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitre)
                        .addComponent(textFieldTitre))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPrix)
                        .addComponent(textFieldPrix))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNbBillets)
                        .addComponent(textNbBillets))
                .addGroup(layoutForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAjouter)
                        .addComponent(buttonModifier)
                        .addComponent(buttonSupprimer)
                        .addComponent(buttonQuitter)));

        // Ajouter le panel principal (qui va contenir tous les autres panels)
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBounds(0, 80, 800, 600);
        add(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.add(tablePanel);
        mainPanel.add(formPanel);

        // Montrer la fenêtre setVisible(false); pour cacher la fenêtre
        setVisible(true);
    }

    private List<Spectacle> chargerSpectacles() throws IOException {
        return SpectacleController.obtenirSpectacleController().chargerSpectacles();
    }

    private void montrerlisteSpectacles() {
        DefaultTableModel model = (DefaultTableModel) tabSpectacles.getModel();
        for (Spectacle unSpectacle : listeSpectacles) {
            model.addRow(new Object[] { unSpectacle.getNum(), unSpectacle.getTitre(), unSpectacle.getPrix(),
                    unSpectacle.getNbBillets() });
        }
    }

    private void ajouterSpectacle() {
        // Récupérer les valeurs des champs de texte
        int num = Integer.parseInt(textFieldNum.getText());
        String titre = textFieldTitre.getText();
        int prix = Integer.parseInt(textFieldPrix.getText());
        int nbBillets = Integer.parseInt(textNbBillets.getText());

        // Ajouter un nouveau Spectacle à la liste
        listeSpectacles.add(new Spectacle(num, titre, prix, nbBillets));

        // Mettre à jour le modèle de tableau
        DefaultTableModel model = (DefaultTableModel) tabSpectacles.getModel();
        model.addRow(new Object[] { num, titre, prix, nbBillets });

        // Effacer les champs de texte
        textFieldNum.setText("");
        textFieldTitre.setText("");
        textFieldPrix.setText("");
        textNbBillets.setText("");
        if (!enregQuitter) {
            // Mise-à-Jour du fichier Spectacles.txt
            SpectacleController.obtenirSpectacleController().ajouterSpectacle(listeSpectacles);
        }
    }

    private void modifierSpectacle() {
        // Récupérer l'indice de la ligne sélectionnée dans la table
        int rowIndex = tabSpectacles.getSelectedRow();

        if (rowIndex >= 0) {
            // Récupérer le Spectacle correspondant dans la liste
            Spectacle Spectacle = listeSpectacles.get(rowIndex);

            // Mettre à jour les propriétés du Spectacle avec les nouvelles valeurs
            if (textFieldNum.getText().length() > 0) {
                Spectacle.setNum(Integer.parseInt(textFieldNum.getText()));
            }
            if (textFieldTitre.getText().length() > 0) {
                Spectacle.setTitre(textFieldTitre.getText());
            }
            if (textFieldPrix.getText().length() > 0) {
                Spectacle.setPrix(Integer.parseInt(textFieldPrix.getText()));
            }
            if (textNbBillets.getText().length() > 0) {
                Spectacle.setNbBillets(Integer.parseInt(textNbBillets.getText()));
            }
            listeSpectacles.set(rowIndex, Spectacle);
            // Mettre à jour le modèle de tableau avec les nouvelles valeurs
            DefaultTableModel model = (DefaultTableModel) tabSpectacles.getModel();
            model.setValueAt(Spectacle.getNum(), rowIndex, 0);
            model.setValueAt(Spectacle.getTitre(), rowIndex, 1);
            model.setValueAt(Spectacle.getPrix(), rowIndex, 2);
            model.setValueAt(Spectacle.getNbBillets(), rowIndex, 3);
            if (!enregQuitter) {
                // Mise-à-Jour du fichier Spectacles.txt
                SpectacleController.obtenirSpectacleController().modifierSpectacle(listeSpectacles);
            }
        }
    }

    private void supprimerSpectacle() {
        // Récupérer l'indice de la ligne sélectionnée dans la table
        int rowIndex = tabSpectacles.getSelectedRow();

        if (rowIndex >= 0) {
            // Supprimer le Spectacle correspondant de la liste
            listeSpectacles.remove(rowIndex);

            // Mettre à jour le modèle de tableau
            DefaultTableModel model = (DefaultTableModel) tabSpectacles.getModel();
            model.removeRow(rowIndex);
            if (!enregQuitter) {
                // Mise-à-Jour du fichier Spectacles.txt
                SpectacleController.obtenirSpectacleController().supprimerSpectacle(listeSpectacles);
            }
        }
    }

    // Cette méthode sera utilisée si vous décidez d'enregistrer votre liste de
    // Spectacles
    // une seule fois lors vous terminez votre application. Dans ce cas ils faudra
    // retirer
    // de toutes les méthodes précédentes les appels à
    // SpectacleController.obtenirSpectacleController().supprimerSpectacle(listeSpectacles);
    // Ceci est un exemple pour supprimerSpectacle mais aussi enlever pour
    // ajouterSpectacle et
    // modifierSpectacle.
    // Présentement on enregistre à chaque opération et donc si vous cliquez sur le
    // bouton
    // Quitter celui-ci enregistrer toute la liste de Spectacles chose qui a déjà
    // été faite dans les autres opérations. J'ai ajouté ce bouton pour que vous
    // sachiez comment enregistrer en une seule fois lorsqu'on quitte
    // l'application.
    private void quitter() {
        if (enregQuitter) {
            SpectacleController.obtenirSpectacleController().sauvegarderSpectacles(listeSpectacles);
        }
        dispose();// Fermer la fenêtre
    }
}
