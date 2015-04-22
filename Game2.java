package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.Random;
import javalib.colors.*;

public class Game2 extends World {

    Player p;
    static Queue mt = new EmptyQueue();
    static Queue bq = new BalloonQueue(new Balloon(), mt, 1);
    static int score;
    static int lives;
    int worldWidth;
    int worldHeight;
    int bombCount;
    int coinCount;
    static Random r = new Random();

    
    Game2(Player player, Queue balloonQueue, int score,
            int lives, int worldWidth, int worldHeight,
            int bombCount, int coinCount) {
        this.p = player;
        this.bq = balloonQueue;
        this.score = score;
        this.lives = lives;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.bombCount = bombCount;
        this.coinCount = coinCount;
    }

    public Game2 onKeyEvent(String key) {
        if (key.equals("left")) {
            return new Game2(p.movePlayerLeft(),
                    bq, this.score, this.lives,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount);
        } else if (key.equals("right")) {
            return new Game2(p.movePlayerRight(),
                    bq, this.score, this.lives,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount);
        } else if (key.equals("space")) {
            if (this.bombCount > 0) {
            return new Game2(p.movePlayerRight(),
                    mt, this.score, this.lives,
                    this.worldWidth, this.worldHeight,
                    this.bombCount - 1, this.coinCount);
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public Game2 onMouseClicked(Posn p) {
        return this;
    }

    public Game2 onTick() {
        if (endHuh()) {
            return new Game2(p,
                    mt,
                    this.score, 0,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount);
        } else {
            if (bq.anyHitGround()) {
                return new Game2(p,
                        bq.moveBalloons().remove().add(new Balloon()),
                        score + 1, hitPlayer(),
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount);
            } else {
                return new Game2(p,
                        bq.moveBalloons().remove().add(new Balloon()),
                        score, hitPlayer(),
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount);
            }
        }
    }

    public int hitPlayer() {
        if (bq.anyHitPlayer()) {
            lives = lives - 1;
        }
        return lives;
    }

    public static int hitGround() {
        if (bq.anyHitGround()) {
            score = score + 1;
        }
        return score;
    }

    public static Boolean endHuh() {
        return (lives <= 0);
    }

    public WorldImage makeImage() {
        if (!endHuh()) {
            return new OverlayImages((new RectangleImage(new Posn(250, 400),
                    500, 800, new Black())),
                    new OverlayImages((bq.drawBalloons()),
                            (p.drawPlayer())));
        } else {
            return new OverlayImages((new RectangleImage(new Posn(250, 400),
                    500, 800, new Black())),
                    new TextImage(new Posn(250, 300),
                            "GAME OVER. YOUR SCORE WAS: " + score,
                            new White()));
        }
    }

    public static String[] randomInput(int size) {
        String[] keys = new String[size];
        for (int i = 0; i < size; i++) {
            Random r = new Random();
            int rand = r.nextInt();
            if (rand % 2 == 0) {
                keys[i] = "left";
            } else {
                keys[i] = "right";
            }
        }
        return keys;
    }

    public static void main(String[] args) {
        int width = 500;
        int height = 600;
        Posn pPosition = new Posn(250, height - 10);
        int lives = 3;
        int score = 0;
        Player p = new Player(pPosition);
        Queue mt = new EmptyQueue();
        Queue bq = new BalloonQueue(new Balloon(), mt, 1);
        int bombCount = 0;
        int coinCount = 0;

        Game2 game = new Game2(p, bq, score, lives, width, height,
                bombCount, coinCount);

        game.bigBang(width, height, 0.3);

    }

}
