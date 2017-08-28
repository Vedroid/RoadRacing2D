package ua.vedroid.game.Run2D;

import javax.swing.*;
import java.awt.*;

class Enemy {

    int x;
    int y;
    private int v;
    Image img = new ImageIcon(getClass().getResource("/ua/vedroid/game/Resource/enemy.png")).getImage();
    private Road road;

    Enemy(int x, int y, int v, Road road) {
        this.x = x;
        this.v = v;
        this.road = road;

        if (y == 0) {
            this.y = 25;
            this.v = 0;
        }
        if (y == 1 || y == 6) {
            this.y = 120;
        }
        if (y == 2 || y == 7) {
            this.y = 270;
        }
        if (y == 3 || y == 8) {
            this.y = 435;
        }
        if (y == 4 || y == 9) {
            this.y = 590;
        }
        if (y == 5) {
            this.y = 680;
            this.v = 0;
        }
    }

    Rectangle getRect() {
        return new Rectangle(x, y+8, 190, 86);
    }

    void move() {
        x = x - road.p.v + v;
    }

}
