package yokai.graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import yokai.global.Game;
import yokai.objects.Board;
import yokai.objects.HintCard;

public class GameInterface extends JFrame{

    static JFrame fenetre;
   // public static Interface.Scene scene;                                       //panneau nouvelle variable, classe Interface.Scene
    Game ga;
    Board board;
    JPanelWithBackground topPanel;
    JPanelWithBackground gridPanel;
    JPanelWithBackground borderPanel;
    int minHeightBoard;
    int maxHeightBoard;
    int minWidthBoard;
    int mawWidthBoard;
    GridBagConstraints gridBagConstraints;
    GridBagLayout gridLayout;


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
        fenetre.repaint();


        displayBoard();
    } //public GameInterface (Game ga){

    private void displayBoard(){
        determineBoardDimension();

        int height = maxHeightBoard-minHeightBoard+1;
        int width = mawWidthBoard-minWidthBoard+1;

        gridPanel.removeAll();
        gridLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
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
                    j1.setText(String.valueOf(minHeightBoard+l+1));
                    j1.setForeground(Color.white);
                } else {
                    if (board.board[l+minHeightBoard][i+minWidthBoard].getYokaiCard()!= null){
                        j1.setIcon(new ImageIcon("./src/Image/dos_carte.jpg"));
                    } else {
                        j1.setText(" ");
                    }
                }
                j1.setHorizontalAlignment(JLabel.CENTER);
                j1.setVerticalAlignment(JLabel.CENTER);
            }
        }
        fenetre.setVisible(true);
        fenetre.repaint();

    }

    private void determineBoardDimension(){
        minHeightBoard=0;
        maxHeightBoard=0;
        minWidthBoard=0;
        mawWidthBoard=0;
        board = ga.getBoard();

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
        System.out.println(minHeightBoard);
        System.out.println(maxHeightBoard);
        System.out.println(minWidthBoard);
        System.out.println(mawWidthBoard);
    } //private void determineBoardDimension(){

    public void changePlayer(String playerName){
        displayBoard();
        topPanel.removeAll();

        JLabel explainLabel = new JLabel();
        explainLabel.setForeground(Color.white);
        explainLabel.setText("C'est au tour de " + playerName + ". Soit sûr que personne ne te regarde jouer");
        topPanel.add(explainLabel);

        JButton okButton = new JButton();
        okButton.setText("Valider");
        topPanel.add(okButton);

        fenetre.setVisible(true);
        fenetre.repaint();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askDisplayCard(" ");

            }
        });
    }

    public void askDisplayCard(String errorText){
        topPanel.removeAll();

        JLabel explainLabel = new JLabel();
        explainLabel.setForeground(Color.white);
        explainLabel.setText("Quelles cartes veux tu regarder ? (Format : G7&J8) ");
        topPanel.add(explainLabel);

        JTextField choiceTextField = new JTextField("",6);
        topPanel.add(choiceTextField);

        JButton okButton = new JButton();
        okButton.setText("Valider");
        topPanel.add(okButton);

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.white);
        errorLabel.setText(errorText);
        topPanel.add(errorLabel);

        fenetre.setVisible(true);
        fenetre.repaint();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.tryDisplayCard(choiceTextField.getText());
            }
        });
    } //public void askDisplayCard(String errorText){

    public void displayCard(String[] cardToDisplay) {
        determineBoardDimension();

        int height = maxHeightBoard - minHeightBoard + 1;
        int width = mawWidthBoard - minWidthBoard + 1;

        gridPanel.removeAll();
        gridLayout = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        gridPanel.setLayout(gridLayout);

        gridBagConstraints.weightx = width;
        gridBagConstraints.weighty = height;

        String[] Alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "O", "P", "Q"};
        for (int i = 1; i < width; i++) {
            JLabel j1 = new JLabel();
            j1.setForeground(Color.white);
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = i;
            gridPanel.add(j1, gridBagConstraints);
            j1.setText(Alphabet[minWidthBoard + i]);
            j1.setHorizontalAlignment(JLabel.CENTER);
            j1.setVerticalAlignment(JLabel.CENTER);
        }

        for (int l = 1; l < height; l++) {
            for (int i = 0; i < width; i++) {
                JLabel j1 = new JLabel();
                gridBagConstraints.gridy = l;
                gridBagConstraints.gridx = i;
                gridPanel.add(j1, gridBagConstraints);
                if (i % height == 0) {
                    j1.setText(String.valueOf(minHeightBoard + l + 1));
                    j1.setForeground(Color.white);
                } else {
                    if (board.board[l + minHeightBoard][i + minWidthBoard].getYokaiCard() != null) {
                        if (((l + minHeightBoard) == Integer.parseInt(cardToDisplay[1]) && (i + minWidthBoard) == Integer.parseInt(cardToDisplay[2]))) {
                            switch (Integer.parseInt(cardToDisplay[3])){
                                case 0:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_rouge.jpg"));
                                    break;

                                case 1:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_verte.jpg"));
                                    break;

                                case 2:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_violette.jpg"));
                                    break;

                                case 3:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_bleue.jpg"));
                                    break;
                            }
                        } else if (((l + minHeightBoard) == Integer.parseInt(cardToDisplay[4]) && (i + minWidthBoard) == Integer.parseInt(cardToDisplay[5]))){
                            switch (Integer.parseInt(cardToDisplay[6])){
                                case 0:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_rouge.jpg"));
                                    break;

                                case 1:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_verte.jpg"));
                                    break;

                                case 2:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_violette.jpg"));
                                    break;

                                case 3:
                                    j1.setIcon(new ImageIcon("./src/Image/carte_bleue.jpg"));
                                    break;
                            }
                        } else {
                            j1.setIcon(new ImageIcon("./src/Image/dos_carte.jpg"));

                        }
                    } else {
                        j1.setText(" ");
                    }
                }
                j1.setHorizontalAlignment(JLabel.CENTER);
                j1.setVerticalAlignment(JLabel.CENTER);
            }
        }
        fenetre.setVisible(true);
        fenetre.repaint();

        ga.goToHints();
    }

    public void displayHints(ArrayList<HintCard> listOfHints, String additionalText, Integer numberOfFunctionToExecute){
        topPanel.removeAll();

        String text = additionalText;

        int i = 0;
        for (HintCard card : listOfHints){
            i+=1;
            switch (card.getNumberOfFamilies().getValue()){
                case 1 -> {
                    text = text + "("+i+") Une carte indice une famille : " + card.getFamily1();
                    break;
                }
                case 2 -> {
                    text = text + "("+i+") Une carte indice deux familles : " + card.getFamily1() + " et " + card.getFamily2();
                    break;
                }
                case  3 -> {
                    text = text + "("+i+") Une carte indice trois familles : " + card.getFamily1() + ", " + card.getFamily2()+" et "+card.getFamily3();
                    break;
                }
            }
        }

        JLabel explainLabel = new JLabel();
        explainLabel.setForeground(Color.white);
        explainLabel.setText(text);
        topPanel.add(explainLabel);

        JTextField textField= new JTextField("",5);
        if (numberOfFunctionToExecute == 2){
            topPanel.add(textField);
        }

        JButton okButton = new JButton();
        okButton.setText("Valider");
        topPanel.add(okButton);

        fenetre.setVisible(true);
        fenetre.repaint();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (numberOfFunctionToExecute){
                    case 1:
                        makeAMove(" ");
                        break;
                    case 2:
                        ga.useAHint(textField.getText());
                }


            }
        });
    }

    public void pickOrUse(){
        displayBoard();
        topPanel.removeAll();

        JLabel explainLabel = new JLabel();
        explainLabel.setForeground(Color.white);
        explainLabel.setText("Voulez vous piocher (1) ou déposer un indice (2)");
        topPanel.add(explainLabel);

        JTextField textField = new JTextField("", 5);
        topPanel.add(textField);

        JButton okButton = new JButton();
        okButton.setText("Valider");
        topPanel.add(okButton);

        fenetre.setVisible(true);
        fenetre.repaint();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (1 == Integer.parseInt(textField.getText())){
                        ga.pick();
                        displayHints(ga.getListOfHintCardAvailable(), "Voilà les indices que vous avez retournés : ", 1);
                    } else if (2 == Integer.parseInt(textField.getText())){
                        displayHints(ga.getListOfHintCardAvailable(), "Quel indice voulez vous utiliser ? Où voulez vous le placer ? (Format : 1->G7) ", 2);
                    } else {
                        JLabel errorLabel = new JLabel();
                        errorLabel.setForeground(Color.white);
                        errorLabel.setText("Veuillez rentrer une valeur correcte (1 ou 2)");
                        topPanel.add(errorLabel);

                        fenetre.setVisible(true);
                        fenetre.repaint();

                    }

                } catch (Exception ez){
                    System.out.println(ez);
                    JLabel errorLabel = new JLabel();
                    errorLabel.setForeground(Color.white);
                    errorLabel.setText("Veuillez rentrer une valeur correcte (1 ou 2)");
                    topPanel.add(errorLabel);

                    fenetre.setVisible(true);
                    fenetre.repaint();

                }


            }
        });
    }

    public void makeAMove(String errorText){
        topPanel.removeAll();

        JLabel explainLabel = new JLabel();
        explainLabel.setForeground(Color.white);
        explainLabel.setText("Quel deplacement veut tu faire ? (Format : G7->G6) ");
        topPanel.add(explainLabel);

        JTextField choiceTextField = new JTextField("",6);
        topPanel.add(choiceTextField);

        JButton okButton = new JButton();
        okButton.setText("Valider");
        topPanel.add(okButton);

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.white);
        errorLabel.setText(errorText);
        topPanel.add(errorLabel);

        //fenetre.repaint();
        fenetre.setVisible(true);
        fenetre.repaint();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.makeAMove(choiceTextField.getText());
            }
        });
    }
};
