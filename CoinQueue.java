package game2;

import javalib.funworld.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;
import tester.*;
import java.util.LinkedList;

public class CoinQueue implements cQueue {

    Coin first;
    cQueue rest;

    int coinCount = 0;

    CoinQueue(Coin first, cQueue rest, int coinCount) {
        this.first = first;
        this.rest = rest;
        this.coinCount = coinCount;
    }

    public boolean isEmpty() {
        return false;
    }

    public cQueue add(Coin c, Double d) {
        double r = Math.random();
        if ((r + d > .85)) {
                return new CoinQueue(this.first, this.rest.add(c, d), coinCount + 1);
        } else {
            return this;
        }
    }

    public Coin front() {
        return this.first;
    }

    public cQueue back() {
        return this.rest;
    }

    public cQueue remove() {
        cQueue q = this;
        while (!q.isEmpty()) {
            if ((Coin.coinHitPlayerHuh(first)) || (first.hitGroundHuh())) {
                try {
                    return new CoinQueue(rest.front(), rest.back(),
                            coinCount - 1);
                } catch (EmptyException e) {
                    return new EmptyCQueue();
                }
            } else {
                return q;
            }
        }
        return q;
    }

    public int queueSize() {
        return coinCount;
    }

    public Boolean anyCoinsHitGround() {
        if (this.first.hitGroundHuh()) {
            return true;
        } else {
            return this.rest.anyCoinsHitGround();
        }
    }

    public cQueue moveCoins() {
        return new CoinQueue(this.first.moveCoinDown(),
                this.rest.moveCoins(), coinCount);
    }

    public WorldImage drawCoins() {
        return new OverlayImages(this.first.drawCoin(),
                this.rest.drawCoins());
    }

    public Boolean anyCoinsHitPlayer() {
        cQueue q = this;
        if (Coin.coinHitPlayerHuh(first)) {
            return true;
        } else {
            return q.back().anyCoinsHitPlayer();
        }
    }

}
