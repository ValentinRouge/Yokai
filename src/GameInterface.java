import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameInterface {

    public static JFrame fenetre;                                    // on declare la fenetre de jeu
    public static Scene scene;                                       //panneau nouvelle variable, classe Scene
    Game ga;

    public static void createInterface(Game ga){





        //Creation de la fenetre
        fenetre = new JFrame("YOKAI");                         // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique
        scene = new Scene();                                        // on instencie la scene

        fenetre.add(scene);


        //fenetre toujours au dessus des autres

        // caracteristiques de la fenetre :
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setSize(1000,700);                     //dimensions de la fenetre 300 425
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        fenetre.setResizable(false);                                //interdir de redimessionner la fenetre
        fenetre.setAlwaysOnTop(false);//fenetrenetre toujours au dessus des autres
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // (fenetre, 5000);



        // Les images ne peuvent se mettre directement sur une fenetre il faut l'intermédiaire d'un panneau


        fenetre.setContentPane(scene);                              // on associe la scene a la fenetre, setcon.. est une methode, la classe scene sert de panneau a la fenetre


        // On met cette instruction en dernier pour pas la perdre

        fenetre.setVisible(true);                                   //fenetre visible
    }

    public void updateBoard(){

    }


}
