package app;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                {0,0,1,1},
                {2,2,3,3},
                {4,4,5,5},
                {6,6,7,7},
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
                    rangeIndex[2] = nowI + radius;
                    rangeIndex[3] = nowJ + radius;
                }
            }
        });

        timer.start();
    }

    public static void action(){
        count++;
    }

    private void rotateBoard(int[][] miniBoard){

    }
}
