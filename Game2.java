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
            int key = 6;
            switch(key){
                case 1: keys[i] = "left";
                    break;
                case 2: keys[i] = "right";
                    break;
                case 3: keys[i] = "up";
                    break;
                case 4: keys[i] = "down";
                    break;
                case 5: keys[i] = "b";
                    break;
                case 6: keys[i] = "v";
                    break;
                default: keys[i] = "n";
                    break;
            }
        }
        return keys;
    }
    
    //TESTS
    
    // Creates a random game state with a random score, life number, player,
    // coin count, bomb count, and mode
    public static Game2 randomGame() {
        int x = r.nextInt(500);
        Player randomP = new Player(new Posn(x, 590));
        int rScore = r.nextInt(101);
        int rLives = r.nextInt(4);
        int rBCount = r.nextInt(101);
        int rCCount = r.nextInt(101);
        String temp;
        Random r = new Random();
            int rand = r.nextInt();
            if (rand % 2 == 0) {
                temp = "dodge";
            } else {
                temp = "town";
            }
        return new Game2(randomP, bq, cq, mq, rScore, rLives, 500, 600, rBCount,
        rCCount, temp, "no one");
    }

    // Tests whether the game is over when lives equal 0
    public static Boolean endTest(Game2 g) {
        if (g.lives <= 0) {
            return (g.endHuh());
        } else {
            return true;
        }
    }
    
    // Tests whether the player location is ever out of the frame
    public static Boolean playerBoundsTest(Game2 g){
        return !((g.p.position.x<0) || (g.p.position.x>500) ||
                (g.p.position.y<0) || (g.p.position.y>600));
    }

    // Given that something has hit the player, tests whether the player has
    // lost a life
    public static Boolean collideTest(Game2 g){
        if((g.bq.anyHitPlayer()) || (g.mq.anyMinesHitPlayer())){
            if(g.lives == 3){
                return lives==2;
            } else if (g.lives==2){
                return lives==1;
            } else if(g.lives==1){
                return lives==0;
            } else return false;
        } else return true;
    }
    
    // Tests whether picking up a coin adds to wealth
    public static Boolean coinTest(Game2 g){
        if(cq.anyCoinsHitPlayer()){
            return g.coinCount>0;
        } else return true;
    }
    
    // Tests whether using a bomb clears the balloon queue
    public static Boolean bombTest(Game2 g){
        if ((g.mode == "dodge") && (g.bombCount > 0)){
            g.onKeyEvent("b");
            return (g.bq == mtbq);
        } else return true;
    }
    
    // Tests whether attempting to buy a life with insufficient coins
    // transitions to the broke talking state
    public static Boolean brokeTest(Game2 g) {
        if ((g.mode == "town") && (g.talking == "lives")) {
           Posn p = new Posn(200, 550);
            g.onMouseClicked(p);
            if (g.coinCount<10){
                return (g.talking == "broke");
            } else return true;
        } else return true;
    }
    
    // Tests whether hitting "v" changes the game mode appropriately
    public static Boolean modeTest(Game2 g){
        if (g.mode == "town") {
            return g.onKeyEvent("v").mode == "dodge";
        } else if (g.mode == "dodge"){
            return g.onKeyEvent("v").mode == "town";
        } else return true;
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
        
        // Creates a random game state by having the game play itself on every
        // other tick
            String[] keys = randomInput(800);
            for (int s = 0; s < keys.length * 2; s++) {
                if (s % 2 == 1) {
                    game = game.onKeyEvent(keys[s / 2]);
                } else {
                    game = game.onTick();
                }
            }

        //Runs endTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!endTest(rGame)) {
                System.out.println("endTest has failed");
            }
        }
        
        //Runs playerBoundsTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!playerBoundsTest(rGame)) {
                System.out.println("playerBoundsTest has failed");
            }
        }
        
        //Runs collideTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!collideTest(rGame)) {
                System.out.println("collideTest has failed");
            }
        }
        
        // Runs coinTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!coinTest(rGame)) {
                System.out.println("coinTest has failed");
            }
        }
        
        // Runs bombTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!bombTest(rGame)) {
                System.out.println("bombTest has failed");
            }
        }
        
        // Runs brokeTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!brokeTest(rGame)) {
                System.out.println("brokeTest has failed");
            }
        }
        
        // Runs modeTest on 100 different games
        for (int x = 0; x < 100; x++) {
            Game2 rGame = randomGame();
            if (!modeTest(rGame)) {
                System.out.println("modeTest has failed");
            }
        }

        game.bigBang(width, height, 0.3);

    }

}
