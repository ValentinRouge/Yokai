import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private ArrayList<HintCard> listOfHintCardHidden = new ArrayList<HintCard>();
    private ArrayList<HintCard> listOfHintCardAvailable = new ArrayList<HintCard>();
    private ArrayList<YokaiCard> listOfYokaiCard = new ArrayList<YokaiCard>();
    private ArrayList<Player> players = new ArrayList<Player>();
    private boolean gameInAction;
    private Board board;

    public void BeginGame(){
        CreatePlayers();
        CreateHints();
    }

    private void CreatePlayers(){
        for(int i=0;i<2;i++){ //on créer deux utilisateurs
            Scanner scanner = new Scanner(System.in); //UNIQUEMENT POUR LA VERSION NON GRAPHIQUE
            players.add(new Player(scanner.nextLine()));
        }
    }

    private void CreateHints(){
        Random random = new Random();
        ArrayList<Integer> HintNeeded = new ArrayList<Integer>();
        switch (players.size()){ //Le switch case ne sert à rien mais il sera utile si on décide d'ouvrir le jeu à plus que 2 joueurs
            case 2: {
                HintNeeded.add(2);
                HintNeeded.add(3);
                HintNeeded.add(2);
                break;
            }
        }
        //on tire les X carte d'une couleur
        ArrayList<Integer> FamilyList = new ArrayList<Integer>(); //On créer une liste avec une couleur dispo
        for (int i=0; i<4; i++) FamilyList.add(i);
        for (int i=0; i<HintNeeded.get(0); i++){
            int family = random.nextInt(FamilyList.size());
            listOfHintCardHidden.add(new HintCard(Families.valueOf(FamilyList.get(family))));
            FamilyList.remove(family);
        }

        for (HintCard card : listOfHintCardHidden){
            System.out.println(card.getFamily1());
        }


    }

}
