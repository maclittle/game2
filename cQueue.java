package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.LinkedList;

public interface cQueue {

    public boolean isEmpty();
    public cQueue add(Coin c, Double d);
    public cQueue remove();
    public Coin front() throws EmptyException;
    public cQueue back();
    public int queueSize();
    public cQueue moveCoins();
    public WorldImage drawCoins();
    public Boolean anyCoinsHitGround();
    public Boolean anyCoinsHitPlayer();
}