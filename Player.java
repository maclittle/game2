package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;

public class Player {

    static Posn position;

    Player(Posn p) {
        this.position = p;
    }

    public Player movePlayerLeft() {
        if(!this.atLeftHuh()){
        position = new Posn(position.x - 5, position.y);
        return new Player(position);
        } else return this;
    }

    public Player movePlayerRight() {
        if (!this.atRightHuh()){
        position = new Posn(position.x + 5, position.y);
        return new Player(position);
        } else return this;
    }
    
    public Player movePlayerUp() {
        if (!this.atTopHuh()) {
            position = new Posn(position.x, position.y - 15);
            return new Player(position);
        } else return this;
    }
    
    public Player movePlayerDown(){
        if (!this.atBottomHuh()) {
            position = new Posn(position.x, position.y + 15);
            return new Player(position);
        } else return this;
    }

    public Boolean atLeftHuh() {
        return this.position.x == 10;
    }

    public Boolean atRightHuh() {
        return this.position.x == 490;
    }
    
    public Boolean atTopHuh() {
        return this.position.y == 10;
    }
    
    public Boolean atBottomHuh() {
        return this.position.y == 590;
    }

    public  WorldImage drawPlayer() {
        return new RectangleImage((this.position), 15, 20, new Green());
    }
    
//    public Boolean talkHuh() {
//        int pTop = Player.position.y+8;
//        int pBottom = Player.position.y-8;
//        int pLeft = Player.position.x-10;
//        int pRight = Player.position.x+10;
//        int mTop = NPC.mayor.mPosn;
//        
//        return ((bRight>=pLeft) &&
//               (bLeft<=pRight)) &&
//               (bBot>= 585);
//    }
//    
}