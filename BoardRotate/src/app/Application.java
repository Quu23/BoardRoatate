package app;


import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import window.*;

public class Application {

    public static Point mousePoint = new Point(0,0);

    // 手数
    public static int count = 0;

    private static int radius = 2;

    public static int[][] board;

    /** startI, startJ, endI, end J */
    public static int[] rangeIndex = {0,0,2,2};

    /** [startI,startJ,Radius] */
    public static Stack<int[]> actions;

    public static void main(String[] args) {
        actions=new Stack<>();
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
                    rangeIndex[2] = nowI + radius - 1;
                    rangeIndex[3] = nowJ + radius - 1;
                }
            }
        });

        timer.start();
    }

    public static void action(){
        count++;
        {
            int[] tmp = {rangeIndex[0],rangeIndex[1],radius};
            actions.push(tmp);
        }
        rotateBoard();
    }

    /** 
     * 
     * @param leftOrRight 
     * 0 is left, the other is right 
     */
    private static void rotateBoard(int leftOrRight){
        ArrayList<ArrayList<Integer>> minimap = new ArrayList<>();
        for (int i = 0; i < radius; i++) {   
            ArrayList<Integer> mini = new ArrayList<>();
            for (int j = 0; j < radius; j++) {            
                mini.add(board[rangeIndex[0]+i][rangeIndex[1]+j]);
            }
            minimap.add(mini);
        }

        for (int i = 0; i < radius; i++) {
            for (int j = 0; j < radius; j++) {
                if(leftOrRight!=0){// 右回転
                    board[j + rangeIndex[0]][radius - i - 1 + rangeIndex[1]] = minimap.get(i).get(j);
                }else{//左回転
                    board[radius - j - 1 + rangeIndex[0]][i + rangeIndex[1]] = minimap.get(i).get(j);
                }
            }
        }
    }

    /** right rotation */
    private static void rotateBoard(){
        rotateBoard(1);
    }

    public static void Undo(){
        if(count==0)return;
        
        count--;
        int[] action = actions.pop();
        rangeIndex[0] = action[0];
        rangeIndex[1] = action[1];
        setRadius(action[2]);
        rotateBoard(0);
    }

    public static int getRadius(){
        return radius;
    }

    public static void setRadius(int setRadius){
        if(rangeIndex[0]+setRadius < 5 && rangeIndex[1]+setRadius < 5)radius=setRadius;
    }
}
