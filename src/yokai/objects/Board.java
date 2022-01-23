package yokai.objects;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    Case[][] board = new Case[16][16];
    ArrayList<Case> caseWithYokai = new ArrayList<>(); //est utile uniquement pour déterminer à la fin si la partie est gagnée

    public Board(){
        for (int i = 0; i<16; i++){
            for (int y = 0; y<16; y++){
                board[i][y] = new Case(i, y);
            }
        }

        ArrayList<YokaiCard> yokaiCards = new ArrayList<>();
        for (int i=0; i<4; i++){
            for (int y =0; y<4; y++){
                yokaiCards.add(new YokaiCard(Families.valueOf(i)));
            }
        }

        Collections.shuffle(yokaiCards);

        for (int i = 6; i<10; i++){
            for (int y = 6; y<10; y++){
                board[i][y].setYokaiCard(yokaiCards.get(0));
                yokaiCards.remove(0);
                caseWithYokai.add(board[i][y]);
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
        try {
            String[] stringToSeeSplited = stringToSee.split("&");
            int col1 = stringToSeeSplited[0].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
            int col2 = stringToSeeSplited[1].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
            int l1 = Integer.parseInt(stringToSeeSplited[0].substring(1))-1; //on récupère le nombre ensuite
            int l2 = Integer.parseInt(stringToSeeSplited[1].substring(1))-1;

            System.out.println("La première carte est : " + board[l1][col1].getYokaiCard().getFamily());
            System.out.println("La seconde carte est : " + board[l2][col2].getYokaiCard().getFamily());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    } //public boolean seeCard(String stringToSee)

    //fonction pour placer un indice
    public boolean placeHint(HintCard hintCard, String place){
        try {
            int col = place.charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
            int l = Integer.parseInt(place.substring(1))-1; //on récupère le nombre ensuite

            if (board[l][col].getYokaiCard()!=null && board[l][col].getHintCard()==null){ //on vérifie qu'il y ait une carte yokai et pas de cartes indices
                board[l][col].setHintCard(hintCard);
                return true;
            }else {
                System.out.println("Vous ne pouvez pas placer d'indice sur cette case");
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    } //public boolean placeHint(Objects.HintCard hintCard, String place){

    public boolean makeAMove(String move){
        try {
            String[] moveSplited = move.split("->");
            int col1 = moveSplited[0].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
            int col2 = moveSplited[1].charAt(0) - 'A'; //manière pour récupérer l'index de la 1ère lettre
            int l1 = Integer.parseInt(moveSplited[0].substring(1))-1; //on récupère le nombre ensuite
            int l2 = Integer.parseInt(moveSplited[1].substring(1))-1;

            if (board[l2][col2].getYokaiCard()!=null){ //on vérifie qu'il n'y ai pas déjà une carte à cet emplacement
                System.out.println("Il y a déjà une carte ici");
                return false;
            } else {
                if (board[l2+1][col2]==null && board[l2-1][col2]==null && board[l2][col2+1]==null && board[l2][col2-1]==null){ //on vérifie que la carte sois bien collée à une autre
                    System.out.println("La carte est isolée");
                    return false;
                } else {
                    board[l2][col2].setYokaiCard(board[l1][col1].getYokaiCard());
                    board[l1][col1].setYokaiCard(null);

                    caseWithYokai.remove(board[l1][col1]);// on maintient la liste à jour pour la vérification finale
                    caseWithYokai.add(board[l2][col2]);

                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    } //public boolean makeAMove(String move){

    public boolean checkIfWon(){
        ArrayList<Integer> FamilyList = new ArrayList<Integer>(); //On créer une liste avec une couleur dispo
        for (int i=0; i<4; i++) FamilyList.add(i);
        ArrayList<Integer> familiesGrupped = new ArrayList<>();
        ArrayList<Integer> familiesNotGrupped = new ArrayList<>();
        int[] PossiblesMoves = {0, 1, 0, -1, 1, 0, -1, 0};

        while (true){
            for (int i = 0; i<16; i++){
                Case thisCase = caseWithYokai.get(i);
                if (!familiesGrupped.contains(thisCase.getYokaiCard().getFamily().getValue()) && !familiesNotGrupped.contains(thisCase.getYokaiCard().getFamily().getValue())) {
                    for (int a = 0; a<8; a=a+2){
                        if (board[thisCase.getLine()+PossiblesMoves[a]][thisCase.getCol()+PossiblesMoves[a+1]].getYokaiCard().getFamily().getValue() == thisCase.getYokaiCard().getFamily().getValue()){

                        }
                        return true;
                    }

                }
            }
        }
    } //public boolean checkIfWon(){
}
