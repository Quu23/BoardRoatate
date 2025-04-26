package window;

import javax.swing.JFrame;

import app.Application;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Window extends JFrame{

    public Board board;
    private Menu menu;

    public Window(){
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                File old = new File("data/log.txt");
                old.delete();
                File newData = new File("data/log.txt");

                try{
                    newData.createNewFile();
                }catch(IOException exception){
                    exception.printStackTrace();
                }
                try (Writer saveWriter = new BufferedWriter(new FileWriter(newData));) {
                    //save処理
                    for (int i = 0; i < Application.actions.size(); i++) {
                        int[] action = Application.actions.pop();
                        saveWriter.write(action[0]+","+action[1]+","+action[2]+",");
                        saveWriter.write('\n');
                    }
                    saveWriter.flush();
                    System.exit(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        this.setLayout(new BorderLayout());

        board = new Board();
        menu = new Menu();

        this.add(board,BorderLayout.CENTER);
        this.add(menu,BorderLayout.SOUTH);

        this.setVisible(true);
    }
    
}
