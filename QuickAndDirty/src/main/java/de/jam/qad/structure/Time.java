package de.jam.qad.structure;

/**
 * Time
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Time {
    public static float delta = 1.0F;
    private static long lastFrameStamp = -1;


    public static void notifyFrame() {
        final var time = System.currentTimeMillis();
        if (lastFrameStamp == -1) {
            lastFrameStamp = time;
            return;
        }
        delta = (time - lastFrameStamp) / 1000F ;
        lastFrameStamp = time;
    }

}
