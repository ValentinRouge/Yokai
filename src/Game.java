import java.util.ArrayList;
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
    }
    private void CreatePlayers(){
        for(int i=0;i<2;i++){
            Scanner scanner = new Scanner(System.in);
            players.add(new Player(scanner.nextLine()));
        }
    }

}
