package yokai.global;

import yokai.graphique.GameInterface;
import yokai.graphique.PlayerInterface;
import yokai.objects.Board;
import yokai.objects.Families;
import yokai.objects.HintCard;
import yokai.objects.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private ArrayList<HintCard> listOfHintCardHidden = new ArrayList<>();
    private ArrayList<HintCard> listOfHintCardAvailable = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>(); //pour la récupération depuis l'interface
    private boolean gameInAction;
    private Board board;
    Scanner scanner = new Scanner(System.in);
    PlayerInterface playerInterface;
    GameInterface gameInterface;
    int playerNumber;

    public ArrayList<HintCard> getListOfHintCardAvailable() {
        return listOfHintCardAvailable;
    }

    public Board getBoard() {
        return board;
    }

    public void BeginGame() {
        playSound();
        gameInAction = true;
        playerInterface = new PlayerInterface(this); // on créer une interface d'entrée de joueeurs
    }

    public void continueGame(){
        CreateHints();
        board = new Board();
        gameInterface = new GameInterface(this);

        playerNumber = -1;
        beginATurn();
    } //public void BeginGame()


    // CREATION DE LA PIOCHE

    private void CreateHints(){ //fonction qui creer la pioche
        Random random = new Random();
        ArrayList<Integer> HintNeeded = new ArrayList<>();
        switch (players.size()) { //Le switch case ne sert à rien mais il sera utile si on décide d'ouvrir le jeu à plus que 2 joueurs
            case 2 -> {
                HintNeeded.add(2); //pour les 1 couleur
                HintNeeded.add(3);//pour les 2 couleur
                HintNeeded.add(2);//pour les 3 couleur
                break;
            }
        }
        //on tire les X carte d'une couleur
        ArrayList<Integer> FamilyList = new ArrayList<Integer>(); //On créer une liste avec une couleur dispo
        for (int i=0; i<4; i++) FamilyList.add(i);
        for (int i=0; i<HintNeeded.get(0); i++){
            int family = random.nextInt(FamilyList.size()); //a chaque fois on créé une carte avec un couleur et on enlève la famille des familles dispo
            listOfHintCardHidden.add(new HintCard(Families.valueOf(FamilyList.get(family))));
            FamilyList.remove(family);
        }

        // début des X cartes de 2 couleurs

        ArrayList<String> DoubleHintAvailable = new ArrayList<>();
        for (int i = 0; i<4; i++){ // génération des doublets possibles
            for (int y=i; y<4; y++){
                if (i != y) DoubleHintAvailable.add(i+"-"+y); //on ne met pas de doublons de couloeur et on les mets dans la liste des doublets
            }
        }
        for (int i=0; i<HintNeeded.get(1);i++){ //on en prend dans notre liste prégénérée et on les ajoutes à notre liste
            int index = random.nextInt(DoubleHintAvailable.size());
            String[] families = DoubleHintAvailable.get(index).split("-");
            listOfHintCardHidden.add(new HintCard(Families.valueOf(Integer.parseInt(families[0])), Families.valueOf(Integer.parseInt(families[1]))));
            DoubleHintAvailable.remove(index);
        }

        //début des X cartes de 3 couleurs

        FamilyList.clear(); //on recréer la liste avec toutes les familles
        for (int i=0; i<4; i++) FamilyList.add(i);
        for (int i=0; i<HintNeeded.get(2); i++){
            ArrayList<Integer> familiesToAdd = new ArrayList<Integer>(); //On créer une liste avec les familles
            for (int y=0; y<4; y++) familiesToAdd.add(y);

            int family = random.nextInt(FamilyList.size()); //a chaque fois on créé une carte avec 3 couleur en donnant tout sauf la couleur que l'on a tirer et on enlève la famille des familles dispo
            familiesToAdd.remove(FamilyList.get(family));

            listOfHintCardHidden.add(new HintCard(Families.valueOf(familiesToAdd.get(0)), Families.valueOf(familiesToAdd.get(1)), Families.valueOf(familiesToAdd.get(2))));
            FamilyList.remove(family);
        }

        Collections.shuffle(listOfHintCardHidden); //on mélange la pioche
    } //private void CreateHints(){


    // FONCTION QUI JOUE UN TOUR
    public void beginATurn(){
        playerNumber+=1;
        if (playerNumber>players.size()-1){
            playerNumber=0;
        }
        gameInterface.changePlayer(players.get(playerNumber).getName());
    }

    public void tryDisplayCard(String cardToDisplay){
        String[] result = board.seeCard(cardToDisplay).split("-");

        if (result[0].equals("OK")){
            gameInterface.displayCard(result);
        } else {
            gameInterface.askDisplayCard("Le format de carte demandé n'est pas correct :/");
        }
    }

    public void goToHints(){
        if (listOfHintCardAvailable.size()==0){
            pick();
            gameInterface.displayHints(listOfHintCardAvailable, "Il n'y a pas de carte déjà pioché, vous aller piocher automatiquement. Voilà les carte indices retournées : ", 1);
        } else {
            gameInterface.pickOrUse();
        }
    }


    public void pick(){
        listOfHintCardAvailable.add(listOfHintCardHidden.get(0)); //Piocher
        listOfHintCardHidden.remove(0); //supprimer
    }

    public void useAHint(String value){
        try {
            String[] splied = value.split("->");
            Integer hintCardNumber = Integer.parseInt(splied[1]);
            if (hintCardNumber>0 && hintCardNumber<= listOfHintCardAvailable.size()){
                board.placeHint(listOfHintCardAvailable.get(hintCardNumber - 1), splied[1]);
                listOfHintCardAvailable.remove(hintCardNumber-1);
                gameInterface.makeAMove(" ");
            } else {
                gameInterface.displayHints(listOfHintCardAvailable, "Quelque chose n'a pas marché, vérifiez le format de votre réponse. Quel indice voulez vous utiliser ? Où voulez vous le placer ? (Format : 1->G7) ", 2);
            }
        } catch (Exception e){
            gameInterface.displayHints(listOfHintCardAvailable, "Quelque chose n'a pas marché, vérifiez le format de votre réponse. Quel indice voulez vous utiliser ? Où voulez vous le placer ? (Format : 1->G7) ", 2);
        }
    }

    public void makeAMove(String valueToWatch){
        boolean done = false;
            try {
                done = board.makeAMove(valueToWatch);
                gameInterface.appeasedYokai();
            } catch (Exception e){
                System.out.println(e);
                gameInterface.makeAMove("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
            }
    } //private void makeAMove(){

    private void playSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./src/sound/music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
} //class yokai.global.Game