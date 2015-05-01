package game2;


import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import javalib.colors.*;
import java.util.LinkedList;

public class BalloonQueue implements Queue {

    Balloon first;
    Queue rest;

    int balloonCount = 0;

    BalloonQueue(Balloon first, Queue rest, int balloonCount) {
        this.first = first;
        this.rest = rest;
        this.balloonCount = balloonCount;
    }

    public boolean isEmpty() {
        return false;
    }

    public BalloonQueue add(Balloon b) {
        if (this.queueSize() <= 15) {
            return new BalloonQueue(this.first, this.rest.add(b), balloonCount + 1);
        } else {
            return this;
        }
    }

    public Balloon front() {
        return this.first;
    }

    public Queue back() {
        return this.rest;
    }

    public Queue remove() {
        Queue q = this;
        while (!q.isEmpty()) {
            if ((Balloon.balloonHitPlayerHuh(first)) || (first.hitGroundHuh())) {
                try {
                    return new BalloonQueue(rest.front(), rest.back(),
                            balloonCount - 1);
                } catch (EmptyException e) {
                    return new EmptyQueue();
                }
            } else {
                return q;
            }
        }
        return q;
    }

    public int queueSize() {
        return balloonCount;
    }

    public Boolean anyHitGround() {
        if (this.first.hitGroundHuh()) {
            return true;
        } else {
            return this.rest.anyHitGround();
        }
    }

    public BalloonQueue moveBalloons() {
        return new BalloonQueue(this.first.moveBalloonDown(),
                this.rest.moveBalloons(), balloonCount);
    }

    public WorldImage drawBalloons() {
        return new OverlayImages(this.first.drawBalloon(),
                this.rest.drawBalloons());
    }

    public Boolean anyHitPlayer() {
        Queue q = this;
        if (Balloon.balloonHitPlayerHuh(first)) {
            return true;
        } else {
            return q.back().anyHitPlayer();
        }
    }

}