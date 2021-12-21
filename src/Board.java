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

        Collections.shuffle(yokaiCards);

        for (int i = 6; i<10; i++){
            for (int y = 6; y<10; y++){
                board[i][y].setYokaiCard(yokaiCards.get(0));
                yokaiCards.remove(0);
            }
        }
    }
}
