package window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;

import app.Application;
import util.MakeBoard;

public class Menu extends JPanel{

    /**
     * 0: BACK / 1: SAVE / 2: LOAD
     */
    private JButton[] buttons;

    private JSpinner boardSizeChanger; 

    public Menu(){
        this.setLayout(new FlowLayout());

        {
            JButton[] tmpButtons = {
                new JButton("BACK"),
                new JButton("SAVE"),
                new JButton("LOAD"),
                new JButton("RAND"),
            };

            this.buttons=tmpButtons;
        }

        this.boardSizeChanger =  new JSpinner(new SpinnerNumberModel(4, 2, 24, 2));

        for (JButton jButton : buttons) {
            jButton.setBackground(Color.WHITE);
            this.add(jButton);
        }

        this.add(boardSizeChanger);

        this.buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.Undo();
            }
        });

        this.buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File old = new File("data/board.txt");
                old.delete();
                File newData = new File("data/board.txt");

                try{
                    newData.createNewFile();
                }catch(IOException exception){
                    exception.printStackTrace();
                }
                try (Writer saveWriter = new BufferedWriter(new FileWriter(newData));) {
                    // save処理
                    // 必ずBoardサイズを先頭に書く.
                    saveWriter.write(Board.getBoardSize()+",");
                    saveWriter.write('\n');
                    for (int i = 0; i < Application.board.length; i++) {
                        for (int j = 0; j < Application.board[0].length; j++) {
                            saveWriter.write(Application.board[i][j]+",");
                        }
                        saveWriter.write('\n');
                    }
                    saveWriter.flush();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        this.buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.count=0;
                Application.historyBoard.clear();
                try (Scanner sc = new Scanner(new File("data/board.txt"))) {
                    //load処理
                    
                    sc.useDelimiter(",");

                    if(sc.hasNextLine()){
                        int boardSizeOfData = sc.nextInt();
                        if(boardSizeOfData != Board.getBoardSize()) return;
                        sc.nextLine();
                    }

                    int i=0;
                    while(sc.hasNextLine()){
                        //次の行を読み込み
                        for(int j=0;j<Board.getBoardSize();j++){
                            Application.board[i][j]=sc.nextInt();
                        }
                        i++;
                        sc.nextLine();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                Application.historyBoard.add(Application.copyArray(Application.board));
            }
        });

        this.buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeBoard.makeRandom(Application.board);
            }
        });

        JSpinner.NumberEditor editor = new NumberEditor(boardSizeChanger);
        this.boardSizeChanger.setEditor(editor);
        editor.getTextField().setEditable(false);
        this.add(boardSizeChanger);

        this.boardSizeChanger.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Board.setBoardSize((int)boardSizeChanger.getValue());

                Application.actions.clear();
                Application.count = 0;
                {
                    Application.board = new int[Board.getBoardSize()][Board.getBoardSize()];
                    MakeBoard.makeRandom(Application.board);
                }
                Application.historyBoard = new ArrayList<>();
                Application.historyBoard.add(Application.copyArray(Application.board));
            }
        });

    }
}
