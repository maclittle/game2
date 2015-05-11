package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.Random;
import javalib.colors.*;

public class Coin {

    static Random r = new Random();
    
    Posn position;
    
    Coin() {
        int x = r.nextInt(500);
        this.position = new Posn(x, 15);
    }
    
    Coin(Posn p) {
        this.position = p;
    }

    public Boolean hitGroundHuh() {
        return this.position.y >= 580;
    }

    public Coin moveCoinDown() {
        if ((!this.hitGroundHuh())) {
            Posn position2 = new Posn(this.position.x, this.position.y + 57);
            return new Coin(position2);
        }
        else {
            Posn stay = new Posn(this.position.x, 590);
            return new Coin(stay);
                    }
    }

    public WorldImage drawCoin() {
        return new OvalImage(this.position,
                14, 20, new Yellow());
    }
    
    
    public static Boolean coinHitPlayerHuh(Coin c){
        int cLeft = c.position.x;
        int cRight = c.position.x;
        int cBot = c.position.y;
        int pTop = Player.position.y-10;
        int pLeft = Player.position.x-8;
        int pRight = Player.position.x+8;
        
        return ((cRight>=pLeft) &&
               (cLeft<=pRight)) &&
               (cBot>= 585);
    }

}