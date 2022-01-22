import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameInterface extends JFrame{

    static JFrame fenetre;
   // public static Scene scene;                                       //panneau nouvelle variable, classe Scene
    Game ga;
    JPanelWithBackground panel;
    JPanelWithBackground dosIndice;
    // CA private ImageIcon icoDosIndice;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    //CA private Image imgDosIndice;

    public GameInterface (){
        super();
        fenetre = this;                      // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique

        fenetre.setTitle("Jeu du YOKAI");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setSize(1000,700);                     //dimensions de la fenetre 300 425
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        fenetre.setResizable(true);                                //interdir de redimessionner la fenetre
        fenetre.setAlwaysOnTop(false);//fenetrenetre toujours au dessus des autres



        try {
            panel = new JPanelWithBackground("./src/Image/redBackGround.png");

        } catch (Exception e) {
           e.printStackTrace();
        }


        panel.setLayout(new GridBagLayout());

        JTable table = new JTable(17,17);
        table.setRowHeight(35);
        for(int i = 0; i<table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setMinWidth(35);
        }
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);

        String[] Alphabet = {"A", "B", "C", "D", "E", "F", "G", "H","I","J","K","L","M","O","P","Q"};
        for (int i=1; i<17; i++){
            table.getModel().setValueAt(Alphabet[i-1],0,i);
            table.getModel().setValueAt(i,i,0);
        }



        JLabel text = new JLabel();
        text.setText("coucou");

        fenetre.add(panel);

        panel.add(text);
        panel.add(table);

        fenetre.setVisible(true);                                   //fenetre visible


        //instancier ico et img

        // CA this.icoDosIndice = new ImageIcon(getClass().getResource("./Image/img_1.png"));// on instancie img et ico, on associe a la variable ico a l'image qui est stocke
        // CA this.imgDosIndice = this.icoDosIndice.getImage(); // on associe ico a imagebandefond

    }

    // CA public void paintComponent(Graphics g) {
    // CA    g.drawImage(this.imgDosIndice, 200, 400, null);
   // }
    public void createInterface(Game ga){

    }

    public void updateBoard(){

    }


}
