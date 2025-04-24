package window;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g){
        
    }
}
