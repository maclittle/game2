package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;
import java.util.LinkedList;


public class EmptyMQueue implements mQueue {
    
    EmptyMQueue() {
    }

    public boolean isEmpty() {
        return true;
    }
    public mQueue add(Mine m, Double d) {
        return new MineQueue(m, this, 1);
    }

    public Mine front() throws EmptyException{
        throw new EmptyException("Nothing is here");
    }

    public mQueue back() {
        return this;
    }

    public mQueue remove() {
        return this;
    }

    public int queueSize() {
        return 0;
    }

    public mQueue moveMines() {
        return this;
    }

    public WorldImage drawMines() {
        return new CircleImage(new Posn(5,5), 5, new Black());
    }

    public Boolean anyMinesHitGround(){
        return false;
    }
    
    public Boolean anyMinesHitPlayer(){
        return false;
    }
            
}