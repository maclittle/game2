package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;
import java.util.LinkedList;


public class EmptyQueue implements Queue {
    
    EmptyQueue() {
    }

    public boolean isEmpty() {
        return true;
    }
    public Queue add(Balloon b) {
        return new BalloonQueue(b, this, 1);
    }

    public Balloon front() throws EmptyException{
        throw new EmptyException("Nothing is here");
    }

    public Queue back() {
        return this;
    }

    public Queue remove() {
        return this;
    }

    public int queueSize() {
        return 0;
    }

    public Queue moveBalloons() {
        return this;
    }

    public WorldImage drawBalloons() {
        return new CircleImage(new Posn(5,5), 5, new Black());
    }

    public Boolean anyHitGround(){
        return false;
    }
    
    public Boolean anyHitPlayer(){
        return false;
    }
            
}