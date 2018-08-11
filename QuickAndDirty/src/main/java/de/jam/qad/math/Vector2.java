package de.jam.qad.math;

/**
 * Vector2
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Vector2 {
    public final static Vector2 ONE = new Vector2(1F,1F);
    public final static Vector2 ZERO = new Vector2();

    public float x;
    public float y;


    public Vector2() {
        this(0F,0F);
    }

    public Vector2(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public void set(final Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public void add(final Vector2 v) {
        add(v.x,v.y);
    }

    public void add(final float x, final float y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(final float x, final float y) {
        add(-x,-y);
    }

    public void subtract(final Vector2 v) {
        add(-v.x,-v.y);
    }

    public void multiply(final float m) {
        x *= m;
        y *= m;
    }

    public void divide(final float d) {
        x /= d;
        y /= d;
    }

    public float length() {
        return (float) Math.sqrt(x*x + y*y);
    }

    public void normalize() {
        divide(length());
    }

    public int pX() {
        return Math.round(x);
    }

    public int pY() {
        return Math.round(y);
    }

}
