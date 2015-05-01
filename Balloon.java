package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.Random;
import javalib.colors.*;

public class Balloon {

    static Random r = new Random();
    
    Posn position;
    
    Balloon() {
        int x = r.nextInt(500);
        this.position = new Posn(x, 15);
    }
    
    Balloon(Posn p) {
        this.position = p;
    }

    public Boolean hitGroundHuh() {
        return this.position.y >= 590;
    }

    public Balloon moveBalloonDown() {
        if ((!this.hitGroundHuh())) {
            Posn position2 = new Posn(this.position.x, this.position.y + 30);
            return new Balloon(position2);
        }
        return this;
    }

    public WorldImage drawBalloon() {
        return new OvalImage(this.position,
                35, 40, new Red());
    }
    
    
    public static Boolean balloonHitPlayerHuh(Balloon b){
        int bLeft = b.position.x-20;
        int bRight = b.position.x+20;
        int bBot = b.position.y;
        int pTop = Player.position.y;
        int pLeft = Player.position.x-10;
        int pRight = Player.position.x+10;
        
        return ((bRight>=pLeft) &&
               (bLeft<=pRight)) &&
               (bBot>= 585);
    }

}