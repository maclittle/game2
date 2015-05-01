package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;

public class NPC {

    String name;
    Posn position;
    String dialog;
    Color color;

    NPC(String name, Posn position, String dialog, Color color) {
        this.name = name;
        this.position = position;
        this.dialog = dialog;
        this.color = color;
    }

    public WorldImage drawNPC() {
        return new RectangleImage((this.position), 15, 20, this.color);
    }

    static Posn mPosn = new Posn(100, 550);
    static String mDialog;
    static NPC mayor = new NPC("Mayor", mPosn, mDialog, Color.BLUE);
    
    static Posn lPosn = new Posn(175, 400);;
    static String lDialog = "I can sell you a life for 10 coins.";
    static NPC lifeMerchant = new NPC("Elise", lPosn, lDialog, Color.RED);
    
    static Posn bPosn = new Posn(250, 100);;
    static String bDialog = "You want a bomb? I can sell you one for 3 coins.";
    static NPC bombMerchant = new NPC("Kim", bPosn, bDialog, Color.CYAN);
    
    static Posn cPosn = new Posn(325, 200);;
    static String cDialog = "Money's hard to come by, aint it. But give me 20 coins"
            + " and I can lend you a hand.";
    static NPC coinMerchant = new NPC("Lee", cPosn, cDialog, Color.WHITE);
    
    static Posn mnPosn = new Posn(400, 200);;
    static String mnDialog = "Those mines are pretty dangerous. For 15 coins I can "
            + "reduce your chances of finding them.";
    static NPC mineMerchant = new NPC("Macks", mnPosn, mnDialog, Color.MAGENTA);
    
    static Posn dPosn = new Posn(475, 350);;
    static String dDialog = "woof woof bark";
    static NPC dog = new NPC("Jax", dPosn, dDialog, Color.ORANGE);
}
