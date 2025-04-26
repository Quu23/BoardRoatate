package window;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JWindow;

import app.Application;

public class HistoryWindow extends JWindow{

    private JPanel drawPanel;
    /** i axis mini's num, j axis mini's num */
    private final int[] MINI_NUM;
    private final int CELL_SIZE;

    HistoryWindow(Frame owner){
        super(owner);
        this.setBounds(0,0,400,400);
        this.CELL_SIZE = 4;
        this.MINI_NUM = new int[2];
        this.MINI_NUM[0] = this.getHeight() / (4 * this.CELL_SIZE);
        this.MINI_NUM[1] = this.getWidth() / (4 * this.CELL_SIZE);
        
        drawPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int k = 0; k < Application.historyBoard.size(); k++) {

                    int shiftJ = k % MINI_NUM[1];
                    int shiftI = (k - shiftJ) / MINI_NUM[1];
                    int[][] history = Application.historyBoard.get(k);

                    for (int i = 0; i < history.length; i++) {
                        for (int j = 0; j < history[0].length; j++) {
                            g.setColor(Board.getCellColor(history[i][j]));
                            g.fillRect(CELL_SIZE * (j + 4 * shiftJ), CELL_SIZE * (i + 4 * shiftI), CELL_SIZE, CELL_SIZE);
                        }
                    }

                    g.setColor(Color.BLACK);
                    g.drawRect(CELL_SIZE*4*shiftJ, CELL_SIZE*4*shiftI, CELL_SIZE*4, CELL_SIZE*4); // 4ますだから掛けてる.
                }
            }
        };
        this.add(drawPanel);

        this.setVisible(true);
    }

    public void repaint(){
        this.drawPanel.repaint();
    }
}
