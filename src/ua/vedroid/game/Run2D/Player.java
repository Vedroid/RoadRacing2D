package ua.vedroid.game.Run2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

class Player {

    static final int MAX_V = 95;
    private static final int MAX_TOP = 50;
    private static final int MAX_BOTTOM = 650;

    private Image img_c = new ImageIcon(getClass().getResource("/ua/vedroid/game/Resource/player.png")).getImage();
    private Image img_l = new ImageIcon(getClass().getResource("/ua/vedroid/game/Resource/player_left.png")).getImage();
    private Image img_r = new ImageIcon(getClass().getResource("/ua/vedroid/game/Resource/player_right.png")).getImage();

    Image img = img_c;

    int v = 0;
    private int dv = 0;
    int s = 0;
    int x = 50;
    int y = 100;
    private int dy = 0;
    int layer1 = 0;
    int layer2 = 1600;

    Rectangle getRect() {
        return new Rectangle(x, y+10, 190, 71);
    }

    void move() {
        s += v / 10;
        v += dv;
        if (v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;
        y -= dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
        if (layer2 - v <= 0) {
            layer1 = 0;
            layer2 = 1600;
        } else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dv = 1;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dv = -2;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = 10;
            img = img_l;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = -10;
            img = img_r;
        }
    }

    void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dv = 0;
        }
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            dy = 0;
            img = img_c;
        }
        if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            dy = 0;
            img = img_c;
        }
    }
}
