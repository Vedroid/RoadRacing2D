package ua.vedroid.game.Run2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ResDialog extends JDialog {

    private ScoreSaver sS;

    private JPanel contentPane;
    private JButton buttonRestart;
    private JButton buttonExit;
    private JLabel tRes;

    ResDialog(String res) {

        tRes.setText(res);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRestart);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        buttonRestart.addActionListener(e -> {
            sS = new ScoreSaver(res);
            sS.setVisible(true);
            try {
                Runtime.getRuntime().exec("java -jar F1.jar");
                System.exit(0);
            } catch (IOException ignored) {
            }
        });

        buttonExit.addActionListener(e -> {
            sS = new ScoreSaver(res);
            sS.setVisible(true);
            System.exit(0);
        });

    }
}
