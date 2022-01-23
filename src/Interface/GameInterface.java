package Interface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import global.Game;

public class GameInterface extends JFrame{

    static JFrame fenetre;
   // public static Interface.Scene scene;                                       //panneau nouvelle variable, classe Interface.Scene
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

        try {
            table.getModel().setValueAt(new ImageIcon("./src/Image/img.png"),3,3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel text = new JLabel();
        text.setText("coucou");

        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());

        fenetre.add(borderPanel);

        borderPanel.add(panel, BorderLayout.CENTER);
        borderPanel.add(text, BorderLayout.NORTH);

        //panel.add(text);

        GridLayout gridLayout = new GridLayout(3,3,1,1);
        panel.setLayout(gridLayout);

        for (int i=0; i<3*3; i++){
            JLabel j1 = new JLabel();
            panel.add(j1);
            System.out.println(j1.getWidth());
            j1.setIcon(new ImageIcon("./src/Image/img_1.png"));
            j1.setHorizontalAlignment(JLabel.CENTER);
            j1.setVerticalAlignment(JLabel.CENTER);
            System.out.println(j1.getWidth());

        }
        panel.setPreferredSize(new Dimension(100,100));

        //panel.add(table);

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

};
