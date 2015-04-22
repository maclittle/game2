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

    public WorldImage drawPlayer() {
        return new RectangleImage((this.position), 15, 20, this.color);
    }

    Posn mPosn;
    String mDialog;
    NPC mayor = new NPC("Mayor", mPosn, mDialog, color.Blue);
    
    Posn lPosn;
    String lDialog = "I can sell you a life for 10 coins.";
    NPC lifeMerchant = new NPC("Elise", lPosn, lDialog, color.Red);
    
    Posn bPosn;
    String bDialog = "You want a bomb? I can sell you one for 3 coins.";
    NPC bombMerchant = new NPC("Kim", bPosn, bDialog, color.Yellow);
    
    Posn cPosn;
    String cDialog = "Money's hard to come by, aint it. But give me 20 coins"
            + " and I can lend you a hand.";
    NPC coinMerchant = new NPC("Lee", cPosn, cDialog, color.White);
    
    Posn mnPosn;
    String mnDialog = "Those mines are pretty dangerous. For 15 coins I can "
            + "reduce your chances of finding them.";
    NPC mineMerchant = new NPC("Macks", mnPosn, mnDialog, color.Purple);
    
    Posn dPosn;
    String dDialog = "woof woof bark";
    NPC dog = new NPC("Jax", dPosn, dDialog, color.Brown);
}
