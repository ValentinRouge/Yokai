package yokai.graphique;

import javax.swing.*;
import java.awt.*;

import yokai.global.Game;
import yokai.objects.Board;

public class GameInterface extends JFrame{

    static JFrame fenetre;
   // public static Interface.Scene scene;                                       //panneau nouvelle variable, classe Interface.Scene
    Game ga;
    JPanelWithBackground topPanel;
    JPanelWithBackground gridPanel;
    JPanelWithBackground borderPanel;
    int minHeightBoard;
    int maxHeightBoard;
    int minWidthBoard;
    int mawWidthBoard;


    // CA private ImageIcon icoDosIndice;//stock l'image du fond d'ecran, type imageIcon et le nom IcoB...
    //CA private Image imgDosIndice;

    public GameInterface (Game ga){
        super();
        this.ga = ga;
        fenetre = this;                      // on instencie la variable fenetre et on lui donne un nom, elle a pas encore d'existence physique

        fenetre.setTitle("Jeu du YOKAI");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //fermer le programme en meme temps que la fenetre
        fenetre.setSize(1000,700);                     //dimensions de la fenetre 300 425
        fenetre.setLocationRelativeTo(null);                        // on centralise la fenetre
        fenetre.setResizable(true);                                //interdir de redimessionner la fenetre
        fenetre.setAlwaysOnTop(false);//fenetrenetre toujours au dessus des autres

        try {
            borderPanel = new JPanelWithBackground("./src/Image/redBackGround.png");
            topPanel = new JPanelWithBackground("./src/Image/redBackGround.png");
            gridPanel = new JPanelWithBackground("./src/Image/redBackGround.png");
        } catch (Exception e) {
           e.printStackTrace();
        }

        borderPanel.setLayout(new BorderLayout()); // on créer un panel avec un layout border
        fenetre.add(borderPanel);

        borderPanel.add(gridPanel, BorderLayout.CENTER);
        borderPanel.add(topPanel, BorderLayout.NORTH);

        JLabel text = new JLabel();
        text.setText("coucou");
        topPanel.add(text);

        fenetre.setVisible(true);                                   //fenetre visible


        displayBoard();
    } //public GameInterface (Game ga){

    private void displayBoard(){
        determineBoardDimension();

        int height = maxHeightBoard-minHeightBoard+1;
        int width = mawWidthBoard-minWidthBoard+1;

        gridPanel.removeAll();
        GridBagLayout gridLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridPanel.setLayout(gridLayout);

        gridBagConstraints.weightx = width;
        gridBagConstraints.weighty = height;

        String[] Alphabet = {"A", "B", "C", "D", "E", "F", "G", "H","I","J","K","L","M","O","P","Q"};
        for (int i=1; i<width; i++){
            JLabel j1 = new JLabel();
            j1.setForeground(Color.white);
            gridBagConstraints.gridy=0;
            gridBagConstraints.gridx = i;
            gridPanel.add(j1, gridBagConstraints);
            j1.setText(Alphabet[minWidthBoard+i]);
            j1.setHorizontalAlignment(JLabel.CENTER);
            j1.setVerticalAlignment(JLabel.CENTER);
        }

        for (int l=1; l<height; l++){
            for (int i=0; i<width; i++){
                JLabel j1 = new JLabel();
                gridBagConstraints.gridy = l;
                gridBagConstraints.gridx = i;
                gridPanel.add(j1,gridBagConstraints);
                if (i%height==0){
                    j1.setText(String.valueOf(minHeightBoard+Math.round(i/width)+2));
                    j1.setForeground(Color.white);
                } else {
                    j1.setIcon(new ImageIcon("./src/Image/img_1.png"));
                }
                j1.setHorizontalAlignment(JLabel.CENTER);
                j1.setVerticalAlignment(JLabel.CENTER);
            }
        }

    }

    private void determineBoardDimension(){
        minHeightBoard=0;
        maxHeightBoard=0;
        minWidthBoard=0;
        mawWidthBoard=0;
        Board board = ga.getBoard();

        for (int i=0; i<16; i++){
            boolean hasCard = false;
            for (int l=0; l<16; l++){
                if (board.board[i][l].getYokaiCard()!= null) hasCard=true;
            }
            if (hasCard) {
                maxHeightBoard = i;
            } else if (minHeightBoard == i-1){
                minHeightBoard = i;
            }
        }

        for (int i=0; i<16; i++){
            boolean hasCard = false;
            for (int l=0; l<16; l++){
                if (board.board[l][i].getYokaiCard()!= null) hasCard=true;
            }
            if (hasCard) {
                mawWidthBoard = i;
            } else if (minWidthBoard == i-1){
                minWidthBoard = i;
            }
        }
    } //private void determineBoardDimension(){



};
