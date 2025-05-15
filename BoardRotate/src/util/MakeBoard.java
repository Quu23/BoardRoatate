package util;

import java.util.ArrayList;
import java.util.Collections;

public class MakeBoard {
    public static void makeRandom(int[][] board){
        int colorNum = board.length * board.length / 2;

        ArrayList<Integer> tempList = new ArrayList<Integer>();

        for (int i=0;i<colorNum;i++){
            tempList.add(i);
            tempList.add(i);
        }

        Collections.shuffle(tempList);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tempList.get(i * board.length + j);
            }
        }
    }
}
