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
        return new RectangleImage((this.position), 16, 20, this.color);
    }

    static String yesDialog = "Thanks for your business!";
    static String noDialog = "Okay, see you later then.";
    static String brokeDialog = "You don't have enough coins for this!";
    
    static Posn mPosn = new Posn(100, 550);
    static String mDialog = "I am the mayor. Welcome to town!";
    static NPC mayor = new NPC("Mayor", mPosn, mDialog, Color.BLUE);
    
    static Posn lPosn = new Posn(175, 400);;
    static String lDialog = "I can sell you a life for 10 coins.";
    static NPC lifeMerchant = new NPC("Elise", lPosn, lDialog, Color.RED);
    
    static Posn bPosn = new Posn(250, 100);;
    static String bDialog = "You want a bomb? I can sell you one for 3 coins.";
    static NPC bombMerchant = new NPC("Kim", bPosn, bDialog, Color.CYAN);
    
    static Posn cPosn = new Posn(325, 200);;
    static String cDialog = "For 20 coins I can make money drop more often.";
    static NPC coinMerchant = new NPC("Lee", cPosn, cDialog, Color.WHITE);
    
    static Posn mnPosn = new Posn(440, 400);;
    static String mnDialog = "For 15 coins I can reduce the frequency of mines.";
    static NPC mineMerchant = new NPC("Macks", mnPosn, mnDialog, Color.MAGENTA);
    
    static Posn dPosn = new Posn(60, 80);;
    static String dDialog = "woof woof bark";
    static NPC dog = new NPC("Jax", dPosn, dDialog, Color.ORANGE);
}
