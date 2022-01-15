import javax.swing.*;

public class Main {

    public static JFrame fenetre;                                    // on declare la fenetre de jeu
    public static Scene scene;                                       //panneau nouvelle variable, classe Scene

    public static void main(String[] args) {


        //Creation de la fenetre
        //fenetre = new JFrame("YOKAI");                         // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique
        //scene = new Scene();                                        // on instencie la scene

        //JPanel panel = new JPanel();
        //JTextField nameField = new JTextField("...", 2);

        //fenetre.add(scene);
        //scene.add(nameField);
        //fenetre.setSize(600,400);



        // caracteristiques de la fenetre :
        //fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        //fenetre.setSize(1500, 900);                     //dimensions de la fenetre 300 425
        //fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        //fenetre.setResizable(false);                                //interdir de redimessionner la fenetre
        //fenetre.setAlwaysOnTop(true);                               //fenetre toujours au dessus des autres


        // Les images ne peuvent se mettre directement sur une fenetre il faut l'intermédiaire d'un panneau

        //fenetre.setContentPane(scene);                              // on associe la scene a la fenetre, setcon.. est une methode, la classe scene sert de panneau a la fenetre


        // On met cette instruction en dernier pour pas la perdre
        //fenetre.setVisible(true);                                   //fenetre visible


       Game game = new Game();
       game.BeginGame();

    }


}



