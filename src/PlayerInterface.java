import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerInterface extends JFrame{
    static JFrame fenetre;                                    // on declare la fenetre de jeu
    static Scene scene;
    public ArrayList<String> names = new ArrayList<>(); //la ou l'on stocke les
    boolean ended = false;
    Game g;

    public PlayerInterface(Game g){
        fenetre = new JFrame("Choix des noms");                         // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique
        this.g = g;

        JPanel panel = new JPanel();
        final JTextField name1Field = new JTextField("", 10);
        final JTextField name2Field = new JTextField("", 10);

        //on créer les éléments de la fenêtre
        final JLabel name1Label = new JLabel();
        final JLabel name2Label = new JLabel();
        name1Label.setText("Nom du joueur 1 : ");
        name2Label.setText("Nom du joueur 2 : ");
        JButton nameButton = new JButton();
        nameButton.setText("Créer les joueurs");

        //on ajoute le panel
        fenetre.add(panel);

        //on ajoute tous les éléments dans la panel
        panel.add(name1Label);
        panel.add(name1Field);
        panel.add(name2Label);
        panel.add(name2Field);
        panel.add(nameButton);

        // caracteristiques de la fenetre :
        fenetre.setSize(300,150);
        fenetre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        fenetre.setVisible(true);                                   //fenetre visible

        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.players.add(new Player(name1Field.getText()));
                g.players.add(new Player(name2Field.getText()));
                closeWindow();
                g.continueGame();
            }
        });
    }

    private void closeWindow(){
        fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
    }
}
