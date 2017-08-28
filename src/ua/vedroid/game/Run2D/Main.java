package ua.vedroid.game.Run2D;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame("Road Racing 2D");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1400, 800);
        f.setLocationRelativeTo(null);
        f.add(new Road());
        f.setVisible(true);
    }
}
