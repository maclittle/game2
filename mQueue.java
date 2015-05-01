package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.LinkedList;

public interface mQueue {

    public boolean isEmpty();
    public mQueue add(Mine m, Double d);
    public mQueue remove();
    public Mine front() throws EmptyException;
    public mQueue back();
    public int queueSize();
    public mQueue moveMines();
    public WorldImage drawMines();
    public Boolean anyMinesHitGround();
    public Boolean anyMinesHitPlayer();
}