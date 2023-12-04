import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListerSpectacle extends JFrame {

    private List<Spectacle> listeSpectacles;
    private JTable tabSpectacles;

    public ListerSpectacle() throws IOException {
        super("Lister de Spectacles");
        listeSpectacles = new ArrayList<>();
        initUI();
        this.listeSpectacles = chargerSpectacles();
        montrerlisteSpectacles();
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

        // Créer un modèle de tableau par défaut
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Numero de spectacle");
        model.addColumn("Titre");
        model.addColumn("Prix");
        model.addColumn("Nombre de billets");

        // Créer la table via le model (colonnes)
        tabSpectacles = new JTable(model);

        // Ajouter une barre de defilement
        JScrollPane scrollPane = new JScrollPane(tabSpectacles);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Bouton d'ajout pour revenir au menu précédent
        JButton buttonRetour = new JButton("Retour au menu principal");

        buttonRetour.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource())).dispose();
            try {
                FrameSpectacle frameSpectacle = new FrameSpectacle();
            } catch (Exception ex) {
                // TODO automatically generated capture blocks
                ex.printStackTrace();
            }
        });

        // Créer un panneau pour le bouton de retour
        JPanel panelForm = new JPanel();
        GroupLayout layoutForm = new GroupLayout(panelForm);
        panelForm.setLayout(layoutForm);
        layoutForm.setAutoCreateGaps(true);
        layoutForm.setAutoCreateContainerGaps(true);
        layoutForm.setHorizontalGroup(layoutForm.createSequentialGroup()
                .addComponent(buttonRetour));

        layoutForm.setVerticalGroup(layoutForm.createSequentialGroup()
                .addComponent(buttonRetour));

        // Créer un panneau principal pour la table et le formulaire
        JPanel panelMain = new JPanel();
        GroupLayout layoutMain = new GroupLayout(panelMain);
        panelMain.setLayout(layoutMain);
        layoutMain.setAutoCreateGaps(true);
        layoutMain.setAutoCreateContainerGaps(true);
        layoutMain.setHorizontalGroup(layoutMain.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane)
                .addComponent(panelForm));
        layoutMain.setVerticalGroup(layoutMain.createSequentialGroup()
                .addComponent(scrollPane)
                .addComponent(panelForm));

        // Ajouter le panneau principal à la fenêtre
        add(panelMain);

        // Afficher la fenêtre
        setVisible(true);
    }

    private List<Spectacle> chargerSpectacles() throws IOException {
        return SpectacleController.obtenirSpectacleController().chargerSpectacles();
    }

    private void montrerlisteSpectacles() {
        DefaultTableModel model = (DefaultTableModel) tabSpectacles.getModel();
        for (Spectacle unSpectacle : listeSpectacles) {
            model.addRow(
                    new Object[] {
                            unSpectacle.getNum(), unSpectacle.getTitre(), unSpectacle.getPrix(), unSpectacle.getNbBillets()
                    });
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
}
