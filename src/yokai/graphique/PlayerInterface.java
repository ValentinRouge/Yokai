package yokai.graphique;

import yokai.objects.Player;
import yokai.global.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerInterface extends JFrame{

    JFrame fenetre;
    JPanelWithBackground panel;// on declare la fenetre de jeu
    JPanel panel2;

    Game g;
    // CA private ImageIcon icoBandeFondAccueil; //stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    // CA private Image imgBandeFondAccueil;

    public PlayerInterface(Game g) {
        super();

        fenetre = this;                       // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique
        this.g = g;


        try {
            panel = new JPanelWithBackground("./src/Image/redBackground.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final JTextField name1Field = new JTextField("", 10);
        final JTextField name2Field = new JTextField("", 10);

        //on créer les éléments de la fenêtre
        final JLabel titleLabel = new JLabel("Bienvenue au jeu de Yokai");
        Font titleFont = titleLabel.getFont().deriveFont(Font.BOLD,20);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.white);

        final JLabel dscLabel = new JLabel("<html>Ce jeu vous est proposé par le groupe composé de : Chloé Pezzoni, Victor Genin et Valentin Rouge <br/><br/>Ce projet a été réalisé dans le cadre du cours de Java au sein de l'ISEP.<br/>Nous espérons que vous prendrez plaisir à jouer :).<br/></html>");
        dscLabel.setForeground(Color.white);

        final JLabel name1Label = new JLabel();
        final JLabel name2Label = new JLabel();
        name1Label.setText("Nom du joueur 1 : ");
        name2Label.setText("Nom du joueur 2 : ");
        name1Label.setForeground(Color.white);
        name2Label.setForeground(Color.white);

        JButton nameButton = new JButton();
        nameButton.setText("Créer les joueurs");

        //on ajoute le panel
        fenetre.add(panel);

        //on crée un GridBaglayout pour gérer la mise en page de la page
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        //on ajoute tous les éléments dans la panel
        panel.add(titleLabel, gbc);
        panel.add(dscLabel, gbc);
        panel.add(name1Label);
        panel.add(name1Field, gbc);
        panel.add(name2Label);
        panel.add(name2Field, gbc);
        panel.add(nameButton, gbc);

        // caracteristiques de la fenetre :
        fenetre.setSize(1000,500);
        fenetre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre

        fenetre.setVisible(true);
        nameButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                g.players.add(new Player(name1Field.getText()));
                g.players.add(new Player(name2Field.getText()));
                closeWindow();
                g.continueGame();
            }
        });
    }

    private void closeWindow(){
        this.setVisible(false);
    }
   // CA public void paintComponent(Graphics g) {
    // CA g.drawImage(this.imgBandeFondAccueil, 0, 0, null);
    //}
}
