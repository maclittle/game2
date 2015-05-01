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
        return this.position.y >= 600;
    }

    public Coin moveCoinDown() {
        if ((!this.hitGroundHuh())) {
            Posn position2 = new Posn(this.position.x, this.position.y + 57);
            return new Coin(position2);
        }
        return this;
    }

    public WorldImage drawCoin() {
        return new OvalImage(this.position,
                13, 20, new Yellow());
    }
    
    
    public static Boolean coinHitPlayerHuh(Coin c){
        int cLeft = c.position.x-7;
        int cRight = c.position.x+7;
        int cBot = c.position.y-10;
        int pTop = Player.position.y-10;
        int pLeft = Player.position.x-10;
        int pRight = Player.position.x+10;
        
        return ((cRight>=pLeft) &&
               (cLeft<=pRight)) &&
               (cBot>= 585);
    }

}