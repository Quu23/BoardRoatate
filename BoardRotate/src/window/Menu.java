package window;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Menu extends JPanel{

    /**
     * 0: BACK / 1: SAVE / 2: LOAD
     */
    private JButton[] buttons;
    private JSlider lineCounter;

    public Menu(){
        this.setLayout(new FlowLayout());

        {
            JButton[] tmpButtons = {
                new JButton("BACK"),
                new JButton("SAVE"),
                new JButton("LOAD"),
            };

            this.buttons=tmpButtons;
        }
        this.lineCounter = new JSlider(2, 4, 2);
        this.lineCounter.setLabelTable(lineCounter.createStandardLabels(1));
        this.lineCounter.setPaintLabels(true);

        for (JButton jButton : buttons) {
            jButton.setBackground(Color.WHITE);
            this.add(jButton);
        }
        this.add(lineCounter);

        //todo:以下でボタンのactionを設定する.
    }
}
