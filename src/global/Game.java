package global;

import Interface.GameInterface;
import Interface.PlayerInterface;
import Objects.Board;
import Objects.Families;
import Objects.HintCard;
import Objects.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

class Game {
    private ArrayList<HintCard> listOfHintCardHidden = new ArrayList<>();
    private ArrayList<HintCard> listOfHintCardAvailable = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    private boolean gameInAction;
    private Board board;
    Scanner scanner = new Scanner(System.in);
    PlayerInterface playerInterface;



    public void BeginGame() {
        gameInAction = true;
        playerInterface = new PlayerInterface(this); // on créer une interface d'entrée de joueeurs
    }

    public void continueGame(){
        System.out.println(players.get(0).getName());
        System.out.println(players.get(1).getName());
        CreateHints();
        board = new Board();
        GameInterface gameInterface = new GameInterface();

        int playerNumber = -1;
        gameInAction=false;
        while (gameInAction){
            playerNumber+=1;
            if (playerNumber>players.size()-1){
                playerNumber=0;
            }
            playATurn(players.get(playerNumber));
        }
    } //public void BeginGame()


    private void CreatePlayers(ArrayList<String> names){
        for(int i=0;i<2;i++){ //on créer deux utilisateurs
            players.add(new Player(names.get(i)));
        }
    } //private void CreatePlayers()



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


    private void playATurn(Player player){
        System.out.println("C'est au tour de " + player.getName()); //TODO : IG
        System.out.println("Soit sûr que personne ne te regarde jouer");//TODO : IG
        board.displayBoard(); //TODO : IG
        boolean done = false; // on créé une boucle sur le try catch pour le recommencer tant que l'on a pas réussit
        while (!done){
            try {
                System.out.println("Quelles cartes veux tu regarder ?");
                System.out.println("Format : A4&B5 : A la colonne, 4 la ligne");
                String valueToWatch = scanner.next();
                System.out.println(valueToWatch);
                done = board.seeCard(valueToWatch);
            } catch (Exception e){
                System.out.println("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
                System.out.println(e);
            }
        }
        if (listOfHintCardAvailable.size()==0){
            System.out.println("Il n'y a pas de carte déjà pioché, vous aller piocher automatiquement.");
            pick();
            displayHintsAvalailable();
        } else {
            pickOrUse();
        }
        makeAMove();
        board.displayBoard();
    } //private void playATurn(Objects.Player player);

    private void pick(){
        listOfHintCardAvailable.add(listOfHintCardHidden.get(0)); //Piocher
        listOfHintCardHidden.remove(0); //supprimer
    }

    private void displayHintsAvalailable(){
        int i = 0;
        for (HintCard card : listOfHintCardAvailable){
            i+=1;
            switch (card.getNumberOfFamilies().getValue()){
                case 1 -> {
                    System.out.println("("+i+") Une carte indice une famille : " + card.getFamily1());
                    break;
                }
                case 2 -> {
                    System.out.println("("+i+") Une carte indice deux famille : " + card.getFamily1() + " et " + card.getFamily2());
                    break;
                }
                case  3 -> {
                    System.out.println("("+i+") Une carte indice deux famille : " + card.getFamily1() + ", " + card.getFamily2()+" et "+card.getFamily3());
                    break;
                }
            }
        }
    }

    private void pickOrUse(){
        System.out.println("Voulez vous piocher (1) ou déposer un indice (2)");
        //on récupère la valeur 1 ou deux pour savoir ce que l'on fait, avec une boucle while pour être sur que l'on le fait
        boolean done = false;
        int choice=0; //on met le 0 juste pour l'initialisation
        while (!done){
            try {
                choice = scanner.nextInt();
                if (choice==1 || choice==2){
                    done = true;
                }
            } catch (Exception e){
                System.out.println("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
                System.out.println(e);
            }
        }
        if (choice == 1){
            pick();
            System.out.println("Vos nouvelles cartes disponibles : ");
            displayHintsAvalailable();
        } else {
            System.out.println("Vos cartes disponibles : ");
            displayHintsAvalailable();
            System.out.println("Laquelle voulez vous utiliser ?");
            done=false;
            int hintCardNumber = 0;//on initialise le numéro de la carte
            while (!done){
                System.out.println("Veuillez saisir le numéro de l'indice que vous voulez utiliser");
                hintCardNumber = scanner.nextInt();
                if (hintCardNumber>0 && hintCardNumber<=listOfHintCardAvailable.size()){
                    done=true;
                }
            }
            //placement de la carte indice
            done = false;
            while (!done) {
                try {
                    System.out.println("Ou voulez vous placer cet indice ?");
                    System.out.println("Format : A4&B5 : A la colonne, 4 la ligne");
                    String valueToWatch = scanner.next();
                    done = board.placeHint(listOfHintCardAvailable.get(hintCardNumber - 1), valueToWatch); //en fonction de si cela marche on assigne done à true ou false
                } catch (Exception e) {
                    System.out.println("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
                    System.out.println(e);
                }
            } //while (!done) {
            listOfHintCardAvailable.remove(hintCardNumber-1);
        } //if (choice==1) ici le else
    } //private void pickOrUse(){

    private void makeAMove(){
        boolean done = false;
        while (!done){
            try {
                System.out.println("Quel déplacement voulez vous réaliser ?");
                System.out.println("Format : A4->B5 : A la colonne, 4 la ligne");
                String valueToWatch = scanner.next();
                System.out.println(valueToWatch);
                done = board.makeAMove(valueToWatch);
            } catch (Exception e){
                System.out.println("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
                System.out.println(e);
            }
        }
    }
} //class global.Game