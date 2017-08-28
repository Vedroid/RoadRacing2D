package ua.vedroid.game.Run2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    private Image img = new ImageIcon(getClass().getResource("/ua/vedroid/game/Resource/bg_road.jpg")).getImage();

    Player p = new Player();
    private ResDialog dialog;

    private ArrayList<Enemy> enemies = new ArrayList<>();

    private String res = "";

    Road() {
        Timer mainTimer = new Timer(20, this);
        mainTimer.start();
        Thread enemiesFactory = new Thread(this);
        enemiesFactory.start();
        Thread audioThread = new Thread(new AudioThread());
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img, p.layer2, 0, null);
        g.drawImage(p.img, p.x, p.y, null);     //Игрок

        double v = (200 / Player.MAX_V) * p.v;
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Speed: " + v + "км/ч.", 100, 30);
        g.drawString("Score: " + p.s + ".", 300, 30);

        try {
            Iterator<Enemy> i = enemies.iterator();     //Противники
            while (i.hasNext()) {
                Enemy e = i.next();
                if (e.x >= 3200 || e.x <= -1600) {
                    i.remove();
                } else {
                    e.move();
                    g.drawImage(e.img, e.x, e.y, null);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }

    private void testWin() {
        if (p.s % 100 == 0) System.out.println("Score: " + p.s);
        if (p.s > 20000) {
            res = ("You won!!!");
            dialog = new ResDialog(res);
            dialog.setVisible(true);

        }
    }

    private void testCollisionWithEnemies() {
        for (Enemy e : enemies) {
            if (p.getRect().intersects(e.getRect())) {
                res = ("You lose!!!\n Score: " + p.s + ".");
                dialog = new ResDialog(res);
                dialog.setVisible(true);

            }
        }
    }

    public void run() {

        while (true) {                              //Создание врагов
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1900,
                        rand.nextInt(10),
                        rand.nextInt(50),
                        this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

}
