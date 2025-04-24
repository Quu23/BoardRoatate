package window;

import javax.swing.JFrame;

import java.awt.BorderLayout;

public class Window extends JFrame{

    public Board board;
    private Menu menu;

    public Window(){
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        board = new Board();
        menu = new Menu();

        this.add(board,BorderLayout.CENTER);
        this.add(menu,BorderLayout.SOUTH);

        this.setVisible(true);
    }
    
}
