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
    int score;
    static int lives;
    int worldWidth;
    int worldHeight;
    int bombCount;
    int coinCount;
    static Random r = new Random();
    String mode;
    String talking;
    double cd;
    double md;

    Game2(Player player, Queue balloonQueue, cQueue coinQueue, mQueue mineQueue,
            int score, int lives, int worldWidth, int worldHeight,
            int bombCount, int coinCount, String mode, String talking) {
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
        this.talking = talking;
    }

    public Game2 onKeyEvent(String key) {
        Posn pPosition = new Posn(250, 590);
        if (this.mode == "dodge") {
            if (key.equals("left")) {
                return new Game2(p.movePlayerLeft(),
                        bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, changeMode(), this.talking);
            } else if (key.equals("right")) {
                return new Game2(p.movePlayerRight(),
                        bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, changeMode(), this.talking);
            } else if (key.equals("b")) {
                if (this.bombCount > 0) {
                    return new Game2(p, mtbq, cq, mq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount - 1, this.coinCount, changeMode(),
                            this.talking);
                } else {
                    return this;
                }
            } else if (key.equals("v")) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", this.talking);
            } else {
                return this;
            }
        } else if (this.mode == "town") {
            if (key.equals("left")) {
                return new Game2(p.movePlayerLeft(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", this.talking);
            } else if (key.equals("right")) {
                return new Game2(p.movePlayerRight(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", this.talking);
            } else if (key.equals("up")) {
                return new Game2(p.movePlayerUp(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", this.talking);
            } else if (key.equals("down")) {
                return new Game2(p.movePlayerDown(),
                        mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", this.talking);
            } else if (key.equals("v")) {
                return new Game2(new Player(pPosition), bq, cq, mq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "dodge", "no one");
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    public Game2 onMouseClicked(Posn ps) {
        if ((this.mode == "town") && (this.talking == "no one")) {
            if (((ps.x <= 108) && (ps.x >= 92)) && ((ps.y <= 560) && (ps.y >= 540))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "mayor");
            } else if (((ps.x <= 183) && (ps.x >= 167)) && ((ps.y <= 410) && (ps.y >= 390))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "lives");
            } else if (((ps.x <= 258) && (ps.x >= 242)) && ((ps.y <= 110) && (ps.y >= 90))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "bombs");
            } else if (((ps.x <= 333) && (ps.x >= 317)) && ((ps.y <= 210) && (ps.y >= 190))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "coins");
            } else if (((ps.x <= 448) && (ps.x >= 432)) && ((ps.y <= 410) && (ps.y >= 390))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "mines");
            } else if (((ps.x <= 68) && (ps.x >= 52)) && ((ps.y <= 90) && (ps.y >= 70))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "dog");
            } else {
                return this;
            }
        } else if ((this.talking == "dog") || (this.talking == "mayor")
                || (this.talking == "lno") || (this.talking == "bno")
                || (this.talking == "cno") || (this.talking == "mnno")
                || (this.talking == "broke")) {
            if (((ps.x <= 500) || (ps.x >= 0)) && ((ps.y <= 600) || (ps.y >= 0))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "no one");
            } else {
                return this;
            }
        } else if (this.talking == "lyes") {
            if (((ps.x <= 500) || (ps.x >= 0)) && ((ps.y <= 600) || (ps.y >= 0))) {
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "no one");
            }
        } else if (this.talking == "byes") {
            if (((ps.x <= 500) || (ps.x >= 0)) && ((ps.y <= 600) || (ps.y >= 0))) {
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "no one");
            }
        } else if (this.talking == "cyes") {
            if (((ps.x <= 500) || (ps.x >= 0)) && ((ps.y <= 600) || (ps.y >= 0))) {
                    cd = cd + 1;
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "no one");
                }
        } else if (this.talking == "mnyes") {
            if (((ps.x <= 500) || (ps.x >= 0)) && ((ps.y <= 600) || (ps.y >= 0))) {
                    md = md - 1;
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "no one");
                }
        } else if (this.talking == "lives") {
            if (((ps.x <= 220) && (ps.x >= 180)) && ((ps.y <= 570) && (ps.y >= 530))) {
                if (this.coinCount >= 10) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives+1,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "lyes");
                } else return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "broke");
            } else if (((ps.x <= 330) && (ps.x >= 270)) && ((ps.y <= 570) && (ps.y >= 530))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "lno");
            } else {
                return this;
            }
        } else if (this.talking == "bombs") {
            if (((ps.x <= 220) && (ps.x >= 180)) && ((ps.y <= 570) && (ps.y >= 530))) {
                if (this.coinCount >= 5) {   
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount+1, this.coinCount, "town", "byes");
                } else 
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "broke");
            } else if (((ps.x <= 330) && (ps.x >= 270)) && ((ps.y <= 570) && (ps.y >= 530))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "bno");
            } else {
                return this;
            }
        } else if (this.talking == "coins") {
            if (((ps.x <= 220) && (ps.x >= 180)) && ((ps.y <= 570) && (ps.y >= 530))) {
                if (this.coinCount >= 20) {
                    cd = cd + .15;
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "cyes");
                } else return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "broke");
            } else if (((ps.x <= 330) && (ps.x >= 270)) && ((ps.y <= 570) && (ps.y >= 530))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "cno");
            } else {
                return this;
            }
        } else if (this.talking == "mines") {
            if (((ps.x <= 220) && (ps.x >= 180)) && ((ps.y <= 570) && (ps.y >= 530))) {
                if (this.coinCount >= 15) {
                    md = md + .15;
                    return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "mnyes");
            } else return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                            this.worldWidth, this.worldHeight,
                            this.bombCount, this.coinCount, "town", "broke");
                    } else if (((ps.x <= 330) && (ps.x >= 270)) && ((ps.y <= 570) && (ps.y >= 530))) {
                return new Game2(p, mtbq, mtcq, mtmq, this.score, this.lives,
                        this.worldWidth, this.worldHeight,
                        this.bombCount, this.coinCount, "town", "mnno");
            } else {
                return this;
            }
        } return this;
    }

    public Game2 onTick() {
        if (this.mode == "end") {
            return new Game2(p,
                    mtbq, mtcq, mtmq,
                    this.score, 0,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount, this.mode, this.talking);
        } else if (this.mode == "dodge") {
            return new Game2(p,
                    bq.moveBalloons().remove().add(new Balloon()),
                    cq.moveCoins().remove().add(new Coin(), this.cd),
                    mq.moveMines().remove().add(new Mine(), this.md),
                    (this.score + 1), hitPlayer(),
                    this.worldWidth, this.worldHeight,
                    this.bombCount, getCoin(), changeMode(), this.talking);
        } else if (this.mode == "town") {
            return new Game2(p,
                    mtbq, mtcq, mtmq,
                    this.score, this.lives,
                    this.worldWidth, this.worldHeight,
                    this.bombCount, this.coinCount, this.mode, this.talking);
        } else {
            return this;
        }
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
        } else if (this.mode == "town") {
            if (this.talking == "mayor") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        (new TextImage(new Posn(250, 500),
                                                                                                NPC.mDialog, new Black())))))))))));
            } else if (this.talking == "lives") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        new OverlayImages((new TextImage(new Posn(250, 500),
                                                                                                        NPC.lDialog, new Black())),
                                                                                                new OverlayImages((new TextImage(new Posn(200, 550),
                                                                                                                "Yes.", new Red())),
                                                                                                        (new TextImage(new Posn(300, 550),
                                                                                                                "No thanks.", new Red())))))))))))));
            } else if (this.talking == "bombs") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        new OverlayImages((new TextImage(new Posn(250, 500),
                                                                                                        NPC.bDialog, new Black())),
                                                                                                new OverlayImages((new TextImage(new Posn(200, 550),
                                                                                                                "Yes.", new Red())),
                                                                                                        (new TextImage(new Posn(300, 550),
                                                                                                                "No thanks.", new Red())))))))))))));
            } else if (this.talking == "coins") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        new OverlayImages((new TextImage(new Posn(250, 500),
                                                                                                        NPC.cDialog, new Black())),
                                                                                                new OverlayImages((new TextImage(new Posn(200, 550),
                                                                                                                "Yes.", new Red())),
                                                                                                        (new TextImage(new Posn(300, 550),
                                                                                                                "No thanks.", new Red())))))))))))));
            } else if (this.talking == "mines") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        new OverlayImages((new TextImage(new Posn(250, 500),
                                                                                                        NPC.mnDialog, new Black())),
                                                                                                new OverlayImages((new TextImage(new Posn(200, 550),
                                                                                                                "Yes.", new Red())),
                                                                                                        (new TextImage(new Posn(300, 550),
                                                                                                                "No thanks.", new Red())))))))))))));
            } else if (this.talking == "dog") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        (new TextImage(new Posn(250, 500),
                                                                                                NPC.dDialog, new Black())))))))))));
            } else if (this.talking == "broke") {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        (new TextImage(new Posn(250, 500),
                                                                                                NPC.brokeDialog, new Black())))))))))));
            } else if ((this.talking == "lno") || (this.talking == "bno")
                    || (this.talking == "cno") || (this.talking == "mnno")) {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        (new TextImage(new Posn(250, 500),
                                                                                                NPC.noDialog, new Black())))))))))));
            } else if ((this.talking == "lyes") || (this.talking == "byes")
                    || (this.talking == "cyes") || (this.talking == "mnyes")) {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        new OverlayImages((NPC.dog.drawNPC()),
                                                                                new OverlayImages((new RectangleImage(new Posn(250, 500),
                                                                                                400, 200, new White())),
                                                                                        (new TextImage(new Posn(250, 500),
                                                                                                NPC.yesDialog, new Black())))))))))));
            } else {
                return new OverlayImages((new RectangleImage(new Posn(250, 400),
                        500, 800, new Black())),
                        new OverlayImages((p.drawPlayer()),
                                new OverlayImages((NPC.mayor.drawNPC()),
                                        new OverlayImages((NPC.lifeMerchant.drawNPC()),
                                                new OverlayImages((NPC.bombMerchant.drawNPC()),
                                                        new OverlayImages((NPC.coinMerchant.drawNPC()),
                                                                new OverlayImages((NPC.mineMerchant.drawNPC()),
                                                                        (NPC.dog.drawNPC()))))))));
            }

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
        Player p = new Player(pPosition);
        Queue mtbq = new EmptyQueue();
        Queue bq = new BalloonQueue(new Balloon(), mtbq, 1);
        cQueue mtcq = new EmptyCQueue();
        cQueue cq = new CoinQueue(new Coin(), mtcq, 1);
        mQueue mtmq = new EmptyMQueue();
        mQueue mq = new MineQueue(new Mine(), mtmq, 1);
        double cd = 0;
        double md = 0;

        Game2 game = new Game2(p, bq, cq, mq, 0, lives, width, height,
                1, 0, "dodge", "no one");

        game.bigBang(width, height, 0.3);

    }

}
