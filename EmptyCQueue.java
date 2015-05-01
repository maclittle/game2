package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;
import java.util.LinkedList;


public class EmptyCQueue implements cQueue {
    
    EmptyCQueue() {
    }

    public boolean isEmpty() {
        return true;
    }
    public cQueue add(Coin c, Double d) {
        return new CoinQueue(c, this, 1);
    }

    public Coin front() throws EmptyException{
        throw new EmptyException("Nothing is here");
    }

    public cQueue back() {
        return this;
    }

    public cQueue remove() {
        return this;
    }

    public int queueSize() {
        return 0;
    }

    public cQueue moveCoins() {
        return this;
    }

    public WorldImage drawCoins() {
        return new CircleImage(new Posn(5,5), 5, new Black());
    }

    public Boolean anyCoinsHitGround(){
        return false;
    }
    
    public Boolean anyCoinsHitPlayer(){
        return false;
    }
            
}