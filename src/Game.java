import java.util.*;

public class Game {
    private ArrayList<HintCard> listOfHintCardHidden = new ArrayList<>();
    private ArrayList<HintCard> listOfHintCardAvailable = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private boolean gameInAction;
    private Board board;
    Scanner scanner = new Scanner(System.in);

    public void BeginGame(){
        gameInAction = true;
        CreatePlayers();
        CreateHints();
        board = new Board();
        int playerNumber = -1;
        while (gameInAction){
            playerNumber+=1;
            if (playerNumber>players.size()-1){
                playerNumber=0;
            }
            playATurn(players.get(playerNumber));
        }
    } //public void BeginGame()

    private void CreatePlayers(){
        for(int i=0;i<2;i++){ //on créer deux utilisateurs
            players.add(new Player(scanner.nextLine()));//TODO : IG
        }
    } //private void CreatePlayers()

    private void CreateHints(){
        Random random = new Random();
        ArrayList<Integer> HintNeeded = new ArrayList<>();
        switch (players.size()) { //Le switch case ne sert à rien mais il sera utile si on décide d'ouvrir le jeu à plus que 2 joueurs
            case 2 -> {
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
            int family = random.nextInt(FamilyList.size()); //a chaque fois on créé une carte avec un couleur et on enlève la famille des familles dispo
            listOfHintCardHidden.add(new HintCard(Families.valueOf(FamilyList.get(family))));
            FamilyList.remove(family);
        }

        ArrayList<String> DoubleHintAvailable = new ArrayList<>();
        for (int i = 0; i<4; i++){ // génération des doublets possibles
            for (int y=i; y<4; y++){
                if (i != y) DoubleHintAvailable.add(i+"-"+y);
            }
        }
        for (int i=0; i<HintNeeded.get(1);i++){ //on en prend dans notre liste prégénérée et on les ajoutes à notre liste
            int index = random.nextInt(DoubleHintAvailable.size());
            String[] families = DoubleHintAvailable.get(index).split("-");
            listOfHintCardHidden.add(new HintCard(Families.valueOf(Integer.parseInt(families[0])), Families.valueOf(Integer.parseInt(families[1]))));
            DoubleHintAvailable.remove(index);
        }

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

    private void playATurn(Player player){
        System.out.println("C'est au tour de " + player.getName()); //TODO : IG
        System.out.println("Soit sûr que personne ne te regarde jouer");//TODO : IG
        board.displayBoard(); //TODO : IG
        boolean done = false; // on créé une boucle sur le try catch pour le recommencer tant que l'on a pas réussit
        while (!done){
            try {
                System.out.println("Quelles cartes veut tu regarder ?");
                System.out.println("Format : A4&B5 : A la colonne, 4 la ligne");
                String valueToWatch = scanner.next();
                System.out.println(valueToWatch);
                done = board.seeCard(valueToWatch);
            } catch (Exception e){
                System.out.println("Oups, quelque chose n'a pas marché, ressaie d'entrer les valeurs");
                System.out.println(e);
            }
        }

    } //private void playATurn(Player player)


}
