import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrameSpectacle extends JFrame implements ActionListener {

    JButton ButtonAjouter, ButtonLister, ButtonFacturation, ButtonQuitter;
    JLabel jLabel;

    public FrameSpectacle() {
        super("Achat de billets de spectacle");
        setSize(400, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // créer menuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu fichierMenu = new JMenu("Fichier");
        JMenu editerMenu = new JMenu("Editer");
        JMenu aideMenu = new JMenu("Aide");

        JMenuItem sauvegarderItem = new JMenuItem("Sauvegarder");
        JMenuItem quitterItem = new JMenuItem("Quitter");

        sauvegarderItem.addActionListener(this);
        quitterItem.addActionListener(this);

        menuBar.add(fichierMenu);
        menuBar.add(editerMenu);
        menuBar.add(aideMenu);

        this.setJMenuBar(menuBar);

        // créer label
        jLabel = new JLabel();
        Font font = new Font("Courier", Font.PLAIN, 12);
        jLabel.setFont(font);
        jLabel.setForeground(Color.BLACK);

        fichierMenu.add(sauvegarderItem);
        fichierMenu.add(quitterItem);

        //Bouton
        ButtonAjouter = new JButton("ajouter");
        ButtonAjouter.addActionListener(this);
        ButtonLister = new JButton("lister");
        ButtonLister.addActionListener(this);
        ButtonFacturation = new JButton("facturation");
        ButtonFacturation.addActionListener(this);
        ButtonQuitter = new JButton("quitter");
        ButtonQuitter.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ButtonAjouter);
        buttonPanel.add(ButtonLister);
        buttonPanel.add(ButtonFacturation);
        buttonPanel.add(ButtonQuitter);

        Container contentPane = getContentPane();
        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    //Mise en œuvre de la méthode actionPerformed() de l'interface ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ButtonAjouter) {
            // TODO : ajouter une action pour le bouton ajouter
            try {
                GestionSpectacle gestionSpectacle = new GestionSpectacle();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == ButtonLister) {
            // TODO : lister une action pour le bouton lister
            try {
                ListerSpectacle listerSpectacle = new ListerSpectacle();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == ButtonFacturation) {
            // TODO : facturation une action pour le bouton facturation
            try {
                FenetreSpectacle fenetreSpectacle = new FenetreSpectacle();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == ButtonQuitter) {
            dispose();
        }
    }
}
