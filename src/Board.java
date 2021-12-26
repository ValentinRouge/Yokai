import java.util.ArrayList;
import java.util.Collections;

public class Board {
    Case[][] board = new Case[16][16];

    public Board(){
        for (int i = 0; i<16; i++){
            for (int y = 0; y<16; y++){
                board[i][y] = new Case();
            }
        }

        ArrayList<YokaiCard> yokaiCards = new ArrayList<>();
        for (int i=0; i<4; i++){
            for (int y =0; y<4; y++){
                yokaiCards.add(new YokaiCard(Families.valueOf(i)));
            }
        }

        //for (YokaiCard card : yokaiCards){
        //    System.out.println(card.getFamily());
        //}

        Collections.shuffle(yokaiCards);

        for (int i = 6; i<10; i++){
            for (int y = 6; y<10; y++){
                board[i][y].setYokaiCard(yokaiCards.get(0));
                //System.out.println(board[i][y].getYokaiCard().getFamily());
                yokaiCards.remove(0);
            }
        }
    }

    public void displayBoard(){ //TODO : IG
        for (int i = 0; i<16; i++){
            for (int y = 0; y<16; y++){
                if (board[i][y].getYokaiCard() != null){
                    System.out.print(" " + 1 + " ");
                } else {
                    System.out.print(" " + 0 + " ");
                }
            }
            System.out.println( );
        }
    } //public void displayBoard(){

    public boolean seeCard(String stringToSee){
        String[] stringToSeeSplited = stringToSee.split("&");
        int col1 = stringToSeeSplited[0].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
        int col2 = stringToSeeSplited[1].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
        int l1 = Integer.parseInt(stringToSeeSplited[0].substring(1))-1; //on récupère le nombre ensuite
        int l2 = Integer.parseInt(stringToSeeSplited[1].substring(1))-1;

        System.out.println("La première carte est : " + board[l1][col1].getYokaiCard().getFamily());
        System.out.println("La seconde carte est : " + board[l2][col2].getYokaiCard().getFamily());
        return true;
    } //public boolean seeCard(String stringToSee)
}
