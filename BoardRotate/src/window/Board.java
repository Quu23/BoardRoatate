package window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import app.Application;

public class Board extends JPanel{

    public static final int BOARD_SIZE =10;

    public static boolean isMousePointerExited;
    private final BasicStroke THIN;
    private final BasicStroke THICK;

    private final int CELL_SIZE;

    public Board(){
        isMousePointerExited=true;
        THIN = new BasicStroke(1);
        THICK = new BasicStroke(5);
        CELL_SIZE = 400 / BOARD_SIZE;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(e.getButton()){
                    //左クリック
                    case MouseEvent.BUTTON1:
                        Application.setNextRadius();
                        break;
                    case MouseEvent.BUTTON2:
                        
                        break;
                    //右クリック 
                    case MouseEvent.BUTTON3:
                        Application.action();
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isMousePointerExited = false;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isMousePointerExited = true;
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g){
        g.setFont(new Font("メイリオ", Font.PLAIN, 10));
        g.drawString("手数:"+Application.count,440,10);
        g.drawString(Application.mousePoint.x+":"+Application.mousePoint.y,440,20);
        g.drawString("r:"+Application.getRadius(), 440, 30);
        g.drawString(Application.rangeIndex[0]+"/"+Application.rangeIndex[1]+"//"+Application.rangeIndex[2]+"/"+Application.rangeIndex[3], 440,40);

        Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(THIN);
        for(int i=0; i < BOARD_SIZE+1; i++){
            g.drawLine(0, CELL_SIZE * i, 400, CELL_SIZE * i);
            g.drawLine(CELL_SIZE * i, 0, CELL_SIZE * i , 400);
        }

        g.setFont(new Font("メイリオ", Font.BOLD, 20));
        for (int i = 0; i < Application.board.length; i++) {
            for (int j = 0; j < Application.board[0].length; j++){
                g.setColor(getCellColor(Application.board[i][j]));
                g.drawString(""+Application.board[i][j], CELL_SIZE / 3 + CELL_SIZE * j, CELL_SIZE / 2 + CELL_SIZE * i);
            }
        }

        g.setColor(Color.RED);
        g2 = (Graphics2D)g;
		g2.setStroke(THICK);
        int width  = Application.rangeIndex[3] - Application.rangeIndex[1] + 1;
        int height = Application.rangeIndex[2] - Application.rangeIndex[0] + 1;
        g.drawRect(Application.rangeIndex[1] * CELL_SIZE, Application.rangeIndex[0] * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);

        g.setColor(Color.black);
        g.fillOval(Application.mousePoint.x-5, Application.mousePoint.y-5, 10, 10);
    }

    public static Color getCellColor(int cell){
        // 範囲の正規化
        float ratio = (float)(cell - 0) / (BOARD_SIZE * BOARD_SIZE / 2);
        ratio = Math.max(0, Math.min(1, ratio)); // 0〜1 に制限

        // ratio を 0〜1 の範囲で 3つのセグメントに分けて色を変える
        if (ratio < 0.33f) {
            // 青→緑
            float localRatio = ratio / 0.33f;
            return interpolateColor(Color.BLUE, Color.GREEN, localRatio);
        } else if (ratio < 0.66f) {
            // 緑→黄
            float localRatio = (ratio - 0.33f) / 0.33f;
            return interpolateColor(Color.GREEN, Color.YELLOW, localRatio);
        } else {
            // 黄→赤
            float localRatio = (ratio - 0.66f) / 0.34f;
            return interpolateColor(Color.YELLOW, Color.RED, localRatio);
        }
    }

        // 2色の間を補間
    private static Color interpolateColor(Color c1, Color c2, float ratio) {
        int red   = (int)(c1.getRed()   * (1 - ratio) + c2.getRed()   * ratio);
        int green = (int)(c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
        int blue  = (int)(c1.getBlue()  * (1 - ratio) + c2.getBlue()  * ratio);
        return new Color(red, green, blue);
    }
}
