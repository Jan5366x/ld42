package de.jam.qad.math;

/**
 * Transform2D
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Transform2D {
    public Vector2 location = new Vector2();

    public Transform2D(){

    }

    public Transform2D(final float x, final float y) {
        location.set(x,y);
    }

    // No rotation yet :3
}
