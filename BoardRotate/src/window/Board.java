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

    public static boolean isMousePointerExited;
    private final BasicStroke THIN;
    private final BasicStroke THICK;

    public Board(){
        isMousePointerExited=false;
        THIN = new BasicStroke(1);
        THICK = new BasicStroke(5);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(e.getButton()){
                    //左クリック
                    case MouseEvent.BUTTON1:
                        
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
                isMousePointerExited = true;
            }
            @Override
            public void mouseExited(MouseEvent e) {
                isMousePointerExited = false;
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
        g.drawString("r:"+Application.radius, 440, 30);
        g.drawString(Application.rangeIndex[0]+"/"+Application.rangeIndex[1]+"//"+Application.rangeIndex[2]+"/"+Application.rangeIndex[3], 440,40);

        Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(THIN);
        for(int i=0; i < 5; i++){
            g.drawLine(0, 100 * i, 400, 100 * i);
            g.drawLine(100 * i, 0, 100 * i , 400);
        }

        g.setFont(new Font("メイリオ", Font.BOLD, 20));
        for (int i = 0; i < Application.board.length; i++) {
            for (int j = 0; j < Application.board[0].length; j++){
                g.setColor(getCellColor(Application.board[i][j]));
                g.drawString(""+Application.board[i][j], 50 + 100 * i,50 + 100 * j);
            }
        }

        g.setColor(Color.RED);
        g2 = (Graphics2D)g;
		g2.setStroke(THICK);
        int width  = Application.rangeIndex[3] - Application.rangeIndex[1] + 1;
        int height = Application.rangeIndex[2] - Application.rangeIndex[0] + 1;
        g.drawRect(Application.rangeIndex[1] * 100, Application.rangeIndex[0] * 100,width * 100, height * 100);

        g.setColor(Color.black);
        g.fillOval(Application.mousePoint.x-5, Application.mousePoint.y-5, 10, 10);
    }

    private Color getCellColor(int cell){
        switch (cell) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.PINK;
            case 7:
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }
}
