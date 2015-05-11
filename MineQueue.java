package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.LinkedList;

public class MineQueue implements mQueue {

    Mine first;
    mQueue rest;

    int mineCount = 0;

    MineQueue(Mine first, mQueue rest, int mineCount) {
        this.first = first;
        this.rest = rest;
        this.mineCount = mineCount;
    }

    public boolean isEmpty() {
        return false;
    }

    public mQueue add(Mine m, Double md) {
        double r = Math.random();
        if ((r - md > .85)) {
                return new MineQueue(this.first, this.rest.add(m, md), mineCount + 1);
        } else {
            return this;
        }
    }

    public Mine front() {
        return this.first;
    }

    public mQueue back() {
        return this.rest;
    }

    public mQueue remove() {
        mQueue q = this;
        while (!q.isEmpty()) {
            if ((Mine.mineHitPlayerHuh(first)) || (first.hitGroundHuh())) {
                try {
                    return new MineQueue(rest.front(), rest.back(),
                            mineCount - 1);
                } catch (EmptyException e) {
                    return new EmptyMQueue();
                }
            } else {
                return q;
            }
        }
        return q;
    }

    public int queueSize() {
        return mineCount;
    }

    public Boolean anyMinesHitGround() {
        if (this.first.hitGroundHuh()) {
            return true;
        } else {
            return this.rest.anyMinesHitGround();
        }
    }

    public mQueue moveMines() {
        return new MineQueue(this.first.moveMineDown(),
                this.rest.moveMines(), mineCount);
    }

    public WorldImage drawMines() {
        return new OverlayImages(this.first.drawMine(),
                this.rest.drawMines());
    }

    public Boolean anyMinesHitPlayer() {
        mQueue q = this;
        if (Mine.mineHitPlayerHuh(first)) {
            return true;
        } else {
            return q.back().anyMinesHitPlayer();
        }
    }

}
