import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameInterface extends JFrame{

    static JFrame fenetre;
    public static Scene scene;                                       //panneau nouvelle variable, classe Scene
    Game ga;

    public GameInterface (){
        fenetre = new JFrame();                         // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique

        fenetre.setTitle("Jeu du YOKAI");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setSize(1000,700);                     //dimensions de la fenetre 300 425
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        fenetre.setResizable(true);                                //interdir de redimessionner la fenetre
        fenetre.setAlwaysOnTop(false);//fenetrenetre toujours au dessus des autres

        JPanel panel = new JPanel();

        JLabel text = new JLabel();
        text.setText("coucou");

        fenetre.add(panel);

        panel.add(text);

        fenetre.setVisible(true);                                   //fenetre visible
    }

    public void createInterface(Game ga){

    }

    public void updateBoard(){

    }


}
