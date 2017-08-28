package ua.vedroid.game.Run2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreSaver extends JDialog {
    private JPanel contentPane;
    private JButton buttonYes;
    private JButton buttonNo;

    ScoreSaver(String res) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonNo);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);


        buttonYes.addActionListener(e -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss");

            try (FileWriter writer = new FileWriter("ScoreF1.txt", true)) {
                writer.write(dateFormat.format(new Date()) + ":   " + res + "\r\n");
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            dispose();
        });

        buttonNo.addActionListener(e -> dispose());
    }
}
