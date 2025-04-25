package app;


import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import window.*;

public class Application {

    public static Point mousePoint = new Point(0,0);

    // 手数
    public static int count = 0;

    public static int radius = 2;

    public static int[][] board;

    /** startI, startJ, endI, end J */
    public static int[] rangeIndex = {0,0,2,2};

    public static void main(String[] args) {
        {
            int[][] tmp = {
                {6,3,1,0},
                {2,4,7,6},
                {7,1,3,0},
                {5,4,5,2},
            };

            board = tmp;
        }
        Window window = new Window();

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.board.repaint();
                if(Board.isMousePointerExited){
                    mousePoint = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mousePoint, window.board);
                }

                int nowJ = (int)(mousePoint.x / 100);
                int nowI = (int)(mousePoint.y / 100);

                if(nowJ+radius - 1 < 4 && nowI + radius - 1 < 4){
                    rangeIndex[0] = nowI;
                    rangeIndex[1] = nowJ;
                    rangeIndex[2] = nowI + radius - 1;
                    rangeIndex[3] = nowJ + radius - 1;
                }
            }
        });

        timer.start();
    }

    public static void action(){
        count++;
        rotateBoard();
    }

    private static void rotateBoard(){
        ArrayList<ArrayList<Integer>> minimap = new ArrayList<>();
        for (int i = 0; i < radius; i++) {   
            ArrayList<Integer> mini = new ArrayList<>();
            for (int j = 0; j < radius; j++) {            
                mini.add(board[rangeIndex[0]+i][rangeIndex[1]+j]);
                // System.out.print((rangeIndex[0]+i) + "/" + (rangeIndex[1]+j)+" ");
                // System.out.print(board[rangeIndex[0]+i][rangeIndex[1]+j]+" //");
            }
            // System.out.println();
            minimap.add(mini);
        }
        // System.out.println("  ");

        // System.out.println(minimap);

        for (int i = 0; i < radius; i++) {
            for (int j = 0; j < radius; j++) {
                // j' = r - i -1, i' = j
                board[j + rangeIndex[0]][radius - i - 1 + rangeIndex[1]] = minimap.get(i).get(j);
                // System.out.println(minimap);
            }
        }
    }
}
