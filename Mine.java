package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.Random;
import javalib.colors.*;

public class Mine {

    static Random r = new Random();
    
    Posn position;
    
    Mine() {
        int x = r.nextInt(500);
        this.position = new Posn(x, 15);
    }
    
    Mine(Posn p) {
        this.position = p;
    }

    public Boolean hitGroundHuh() {
        return this.position.y-20 >= 600;
    }

    public Mine moveMineDown() {
        if ((!this.hitGroundHuh())) {
            Posn position2 = new Posn(this.position.x, this.position.y + 58);
            return new Mine(position2);
        }
        return this;
    }

    public WorldImage drawMine() {
        return new OvalImage(this.position,
                20, 20, new White());
    }
    
    
    public static Boolean mineHitPlayerHuh(Mine m){
        int mLeft = m.position.x-10;
        int mRight = m.position.x+10;
        int mBot = m.position.y-10;
        int pTop = Player.position.y-5;
        int pLeft = Player.position.x-10;
        int pRight = Player.position.x+10;
        
        return ((mRight>=pLeft) &&
               (mLeft<=pRight)) &&
               (mBot>= 585);
    }

}