package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.Random;
import javalib.colors.*;

public class Game2 extends World {

    Player p;
    static Queue mtbq = new EmptyQueue();
    static Queue bq = new BalloonQueue(new Balloon(), mtbq, 1);
    static cQueue mtcq = new EmptyCQueue();
    static cQueue cq = new CoinQueue(new Coin(), mtcq, 1);
    static mQueue mtmq = new EmptyMQueue();
    static mQueue mq = new MineQueue(new Mine(), mtmq, 1);
    static int score;
    static int lives;
    int worldWidth;
    int worldHeight;
    int bombCount;
    int coinCount;
    static Random r = new Random();
    String mode;
    double d;

    Game2(Player player, Queue balloonQueue, cQueue coinQueue, mQueue mineQueue,
            int score, int lives, int worldWidth, int worldHeight,
            int bombCount, int coinCount, String mode) {
        this.p = player;
        this.bq = balloonQueue;
        this.cq = coinQueue;
        this.mq = mineQueue;
        this.score = score;
        this.lives = lives;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.bombCount = bombCount;
        this.coinCount = coinCount;
        this.mode = mode;
    }

    public Game2 onKeyEvent(String key) {
        if (this.mode == "dodge") {
            if (key.equals("left")) {
                return new Game2(p.movePlayerLeft(),
                        bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, changeMode());
            } else if (key.equals("right")) {
                return new Game2(p.movePlayerRight(),
                        bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, changeMode());
            } else if (key.equals("space")) {
                if (this.bombCount > 0) {
                    return new Game2(p.movePlayerRight(),
                            mtbq, cq, mq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount - 1, this.coinCount, changeMode());
                } else {
                    return this;
                }
            } else if (key.equals("down")) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town");
            } else {
                return this;
            }
        } else if (this.mode == "town") {
            if (key.equals("left")) {
                return new Game2(p.movePlayerLeft(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town");
            } else if (key.equals("right")) {
                return new Game2(p.movePlayerRight(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town");
            } else if (key.equals("up")) {
                return new Game2(p.movePlayerUp(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town");
            } else if (key.equals("down")) {
                return new Game2(p.movePlayerDown(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town");
            } else if (key.equals("enter")) {
                return new Game2(p, bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "dodge");
            } else return this;
        } else return this;
    }

    public Game2 onMouseClicked(Posn p) {
        return this;
    }

    public Game2 onTick() {
        if (this.mode == "end") {
            return new Game2(p,
                    mtbq, mtcq, mtmq,
                    hitGround(), 0,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, getCoin(), this.mode);
        } else if (this.mode == "dodge") {
            return new Game2(p,
                    bq.moveBalloons().remove().add(new Balloon()),
                    cq.moveCoins().remove().add(new Coin(), d),
                    mq.moveMines().remove().add(new Mine(), d),
                    hitGround(), hitPlayer(),
                    this.worldWidth, this.worldHeight,
                    this.bombCount, getCoin(), changeMode());
        } else  if (this.mode == "town"){
            return new Game2(p,
                    mtbq, mtcq, mtmq,
                    this.score, this.lives,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount, this.mode);
        } else return this;
    }

    public int hitPlayer() {
        if ((bq.anyHitPlayer()) || (mq.anyMinesHitPlayer())) {
            lives = lives - 1;
        }
        return lives;
    }

    public int getCoin() {
        if (cq.anyCoinsHitPlayer()) {
            this.coinCount = this.coinCount + 1;
        }
        return this.coinCount;
    }

    public static int hitGround() {
        if (bq.anyHitGround()) {
            score = score + 1;
        } else if (mq.anyMinesHitGround()) {
            score = score + 2;
        }
        return score;
    }

    public static Boolean endHuh() {
        return (lives <= 0);
    }

    public String changeMode() {
        if (endHuh()) {
            return "end";
        } else {
            return "dodge";
        }
    }

    public WorldImage makeImage() {
        if (this.mode == "dodge") {
            return new OverlayImages((new RectangleImage(new Posn(250, 400),
                    500, 800, new Black())),
                    new OverlayImages((bq.drawBalloons()),
                            new OverlayImages((cq.drawCoins()),
                                    new OverlayImages((mq.drawMines()),
                                            (p.drawPlayer())))));
        } else if (this.mode == "town"){
            return new OverlayImages((new RectangleImage(new Posn(250, 400),
            500, 800, new Black())),
               new OverlayImages((p.drawPlayer()),
                       new OverlayImages((NPC.mayor.drawNPC()),
                       new OverlayImages((NPC.lifeMerchant.drawNPC()),
                       new OverlayImages((NPC.bombMerchant.drawNPC()),
                       new OverlayImages((NPC.coinMerchant.drawNPC()),
                       new OverlayImages((NPC.mineMerchant.drawNPC()),
                               (NPC.dog.drawNPC()))))))));
        } else {//if (this.mode == "end") {
            return new OverlayImages((new RectangleImage(new Posn(250, 400),
                    500, 800, new Black())),
                    new TextImage(new Posn(250, 300),
                            "GAME OVER. YOUR SCORE WAS: " + this.score
                            + " AND YOUR COIN COUNT WAS: " + this.coinCount,
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
        Queue mtbq = new EmptyQueue();
        Queue bq = new BalloonQueue(new Balloon(), mtbq, 1);
        cQueue mtcq = new EmptyCQueue();
        cQueue cq = new CoinQueue(new Coin(), mtcq, 1);
        mQueue mtmq = new EmptyMQueue();
        mQueue mq = new MineQueue(new Mine(), mtmq, 1);
        int bombCount = 0;
        int coinCount = 0;

        Game2 game = new Game2(p, bq, cq, mq, score, lives, width, height,
                bombCount, coinCount, "dodge");

        game.bigBang(width, height, 0.3);

    }

}
